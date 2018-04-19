package com.clara.taskdb.controller;

import com.clara.taskdb.model.Task;
import com.clara.taskdb.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * Created by vq4988nx on 4/12/2018.
 */
@RestController
public class TaskAPIController {

    private final TaskRepository tasks;

    @Autowired
    public TaskAPIController(TaskRepository tasks) {
        this.tasks = tasks;

        // Example tasks. Remove for real application
//        tasks.save(new Task("task 1", true, false));
//        tasks.save(new Task("task 2", true, true));
//        tasks.save(new Task("task 3", false, false));
//        tasks.save(new Task("task 4", false, false));

    }


    @PostMapping(value="/add")
    public ResponseEntity addTask(@RequestBody Task task) {
        System.out.println("new task: " + task);
        try {
            tasks.save(task);
        } catch (Exception e) {
            return new ResponseEntity<>("Task object is invalid", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping(value="/completed")
    public ResponseEntity markTaskAsCompleted(@RequestBody Task task) {
        if (!tasks.findById(task.getId()).isPresent()) { return new ResponseEntity(HttpStatus.NOT_FOUND); }
        tasks.save(task);
        return new ResponseEntity(HttpStatus.NO_CONTENT);  // done task, nothing needed to say in return
    }




    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> queryTasks() {
        return new ResponseEntity<>(tasks.findAllByOrderByUrgentDesc(), HttpStatus.OK);
    }

    @DeleteMapping(value="/delete")
    public ResponseEntity deleteTask(@RequestBody Task task) {
        if (!tasks.findById(task.getId()).isPresent()) { return new ResponseEntity(HttpStatus.NOT_FOUND); }
        tasks.delete(task);
        return new ResponseEntity(HttpStatus.OK);
    }




}