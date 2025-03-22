package com.personal.TaskManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateDto {
    int id;
    String description;

    Boolean completed;
    String deadline;


}
