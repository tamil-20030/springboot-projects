package com.personal.TaskManager.dto;

import com.personal.TaskManager.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

   public String message;
    public Task t;

    public ResponseDto(String message)
    {
        this.message=message;
        this.t=null;
    }
}
