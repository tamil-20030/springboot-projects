package com.personal.TaskManager.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
public class Task {

    private int id;
    private String title;
    private String description;
    private Date deadLine;
    private boolean isCompleted;

//    private ArrayList<Notes> notes;

}
