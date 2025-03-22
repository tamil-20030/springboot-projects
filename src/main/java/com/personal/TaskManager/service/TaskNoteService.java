package com.personal.TaskManager.service;

import com.personal.TaskManager.dto.MessageDto;
import com.personal.TaskManager.dto.TaskWithNotesDto;
import com.personal.TaskManager.entity.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class TaskNoteService {

    private final TaskService taskService;
    private final NoteService noteService;

    public TaskNoteService(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService = noteService;
    }

    public TaskWithNotesDto getTaskById(int id)
    {
        var task=taskService.getTaskById(id);
        if(task==null)
            return new TaskWithNotesDto("Task does not exist");
        var notes=noteService.getNotesByTaskId(id);

        return new TaskWithNotesDto("fetch success",task,notes);
    }

    public boolean deleteTaskById(int id)
    {
        Task task=taskService.getTaskById(id);
        if(task==null)
            return false;

        ArrayList<Task> tasks=taskService.getTasks();
        Iterator<Task> iterator=tasks.iterator();
        boolean isDeleted=false;
        for(Iterator<Task> it =tasks.iterator(); iterator.hasNext();)
        {
            Task t =it.next();
            if(t.getId()==id)
            {
                isDeleted=true;
                if(noteService.map.containsKey(id))
                {
                    noteService.map.remove(id);
                }
                tasks.remove(t);
                break;
            }
        }

       return isDeleted;
    }

}
