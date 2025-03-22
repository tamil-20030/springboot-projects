package com.personal.TaskManager.controller;

import com.personal.TaskManager.dto.*;
import com.personal.TaskManager.entity.Task;
import com.personal.TaskManager.service.TaskNoteService;
import com.personal.TaskManager.service.TaskService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskNoteService taskNoteService;

    public TaskController(TaskService taskService, TaskNoteService taskNoteService) {
        this.taskService = taskService;
        this.taskNoteService = taskNoteService;
    }


    @GetMapping("")

    public ResponseEntity<List<Task>> getTasks()

    {
        var tasks=taskService.getTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskWithNotesDto> getTaskById(@PathVariable int id)
    {
       TaskWithNotesDto result=taskNoteService.getTaskById(id);
       if(result.getTask()== null)
           return ResponseEntity.badRequest().body(new TaskWithNotesDto("Task does not exist"));
       return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<String> addTask(@RequestBody TaskDto body) throws Exception {
        taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
        var msg="Task created successfully";
        return ResponseEntity.ok(msg);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> updateTask(@PathVariable int id,@RequestBody UpdateDto body) throws ParseException {
        Task updatedTask= taskService.updateTask(id, body.getDescription(), body.getCompleted(),body.getDeadline());
        if(updatedTask==null)
            return ResponseEntity.badRequest().body(new ResponseDto("Invalid Request: Failed to update task"));
//            return new ResponseDto("Invalid Request: Failed to update task");
        return ResponseEntity.ok().body(new ResponseDto("Task updated Successfully.",updatedTask));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id)
    {
        boolean isTaskDeleted= taskNoteService.deleteTaskById(id);
        if(isTaskDeleted)
            return ResponseEntity.ok("Task Deleted");

        return ResponseEntity.badRequest().body("Invalid Request: Task does not exist");
    }

    @DeleteMapping("")
    public ResponseEntity<MessageDto> deleteAllTasks()
    {
        taskService.deleteALLTaskS();
        return ResponseEntity.ok().body(new MessageDto("All tasks deleted successfully"));
    }


    //exception handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDto> handleErrors(Exception e)
    {
        if(e instanceof ParseException)
            return ResponseEntity.badRequest().body(new MessageDto("Invalid date format"));

        return ResponseEntity.internalServerError().body(new MessageDto("Invalid Request"));

    }
}
