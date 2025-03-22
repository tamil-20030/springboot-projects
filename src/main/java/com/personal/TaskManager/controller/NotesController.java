package com.personal.TaskManager.controller;

import com.personal.TaskManager.dto.MessageDto;
import com.personal.TaskManager.dto.NotesDto;
import com.personal.TaskManager.dto.ResponseDto;
import com.personal.TaskManager.entity.Notes;
import com.personal.TaskManager.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    @Autowired
    private NoteService noteService;

    @RequestMapping("")
    ResponseEntity<ArrayList<Notes>> getNotesByTaskId(@PathVariable("taskId") int taskId)
    {
        return ResponseEntity.ok().body(noteService.getNotesByTaskId(taskId));
    }

    @PostMapping("")
    ResponseEntity<MessageDto> addNotesForTaskId(@PathVariable("taskId") int taskId, @RequestBody NotesDto body)
    {
        Boolean isTaskAdded=noteService.addNotesToTask(taskId, body.getTitle(), body.getBody());
        if(isTaskAdded)
            return  ResponseEntity.ok().body(new MessageDto("Note added to the task Successfully"));
        else
        return ResponseEntity.badRequest().body(new MessageDto("Note is not added"));
    }

}
