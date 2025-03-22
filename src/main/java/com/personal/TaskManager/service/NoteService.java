package com.personal.TaskManager.service;

import com.personal.TaskManager.dto.ResponseDto;
import com.personal.TaskManager.entity.Notes;
import com.personal.TaskManager.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Service
public class NoteService {

    @Autowired
    private TaskService taskService;
    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }
    protected HashMap<Integer,TaskNotesHolder> map=new HashMap<>();
    class TaskNotesHolder
    {
        protected Integer noteId=1;
        protected ArrayList<Notes> notesList=new ArrayList<>();
    }

    public ArrayList<Notes> getNotesByTaskId(int id)
    {
        Task task=taskService.getTaskById(id);
        if(task==null)
            return null;

        map.computeIfAbsent(id,k->new TaskNotesHolder());

        return map.get(id).notesList;
    }

    public Boolean addNotesToTask(int taskId,String title,String body)
    {
       Task task=taskService.getTaskById(taskId);
        if(task==null)
            return false;

       map.computeIfAbsent(taskId,k->new TaskNotesHolder());
        TaskNotesHolder tasknotes=map.get(taskId);
        Notes newNote=new Notes();
        newNote.setId(tasknotes.noteId);
        newNote.setTitle(title);
        newNote.setBody(body);
        tasknotes.notesList.add(newNote); //adding to list
        tasknotes.noteId++;
        return true;
    }

    public Boolean deleteAllNotesWithTaskId(int taskId,int noteId)
    {
        Task task=taskService.getTaskById(taskId);
        if(task==null)
            return false;
        if(map.containsKey(taskId))
             map.computeIfPresent(taskId, (k,v)->null);
        return true;
    }
    public Boolean deleteNotesOfATask(int taskId,int noteId)
    {
        Task task=taskService.getTaskById(taskId);
        if(task==null)
            return false;
        if(!map.containsKey(taskId) || map.get(taskId)==null)
            return false;

        Boolean isDeleted=false;
        ArrayList<Notes> notes=map.get(taskId).notesList;
        Iterator<Notes> iterator=notes.iterator();
        for(Iterator<Notes> it=notes.iterator(); it.hasNext();)
        {
            Notes note=it.next();
            if(note.getId()==noteId)
            {
                isDeleted=true;
                notes.remove(note);
            }
        }

        return isDeleted;
    }

}
