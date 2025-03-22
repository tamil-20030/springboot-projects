package com.personal.TaskManager.service;


import com.personal.TaskManager.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

@Service
public class TaskService {

    ArrayList<Task> tasks = new ArrayList<>();


    private int taskId = 1;
    private SimpleDateFormat deadlineFromatter=new SimpleDateFormat("dd-MM-yyyy");



    public void addTask(String title, String description, String deadLine) throws Exception{
       Task task=new Task();
       task.setId(taskId);
       task.setTitle(title);
       task.setDescription(description);
       task.setCompleted(false);
       task.setDeadLine( deadlineFromatter.parse(deadLine));
       tasks.add(task);
       taskId++;
    }

    public ArrayList<Task> getTasks()
    {
        return tasks;
    }

    public Task getTaskById(int id)
    {
        for(Task t:tasks)
        {
            if(id==t.getId())
            {
                return t;
            }
        }
        return null;
    }


    public Task updateTask(int id, String description, Boolean completed, String date) throws ParseException {
        Task currentTask=getTaskById(id);
        if(currentTask==null)
            return null;
        if(description!=null)
            currentTask.setDescription(description);
        if(completed!=null)
            currentTask.setCompleted(completed);
        if(date!=null)
            currentTask.setDeadLine(deadlineFromatter.parse(date));
        return currentTask;

    }
    public Boolean deleteALLTaskS()
    {
        tasks.clear();
       return true;
    }


}
