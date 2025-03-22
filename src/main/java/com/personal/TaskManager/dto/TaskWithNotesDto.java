package com.personal.TaskManager.dto;

import com.personal.TaskManager.entity.Notes;
import com.personal.TaskManager.entity.Task;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskWithNotesDto {

    String message;
    Task task;
    ArrayList<Notes> notes;
    public TaskWithNotesDto(String message)
    {
        this.message=message;
        this.task=null;
        this.notes=null;
    }
}

