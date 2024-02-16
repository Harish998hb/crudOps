package com.practise.crud_op.controllers;

import com.practise.crud_op.data_component.TaskComponent;
import com.practise.crud_op.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public List<TaskComponent> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TaskComponent>> getSpecificTask(@PathVariable String id) {
        try {
            Optional<TaskComponent> specificTask = taskService.getSpecificTask(id);
            if (!(specificTask.isEmpty())) {
                return new ResponseEntity(specificTask, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskComponent> updateTask(@PathVariable String id, @RequestBody TaskComponent payload) {
        try {
            Boolean isUpdated = taskService.updateTask(id, payload);
            if (isUpdated) return new ResponseEntity("Task Updated Successfully", HttpStatus.OK);
            else return new ResponseEntity("Problem in updating", HttpStatus.NOT_FOUND);
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<TaskComponent> createTask(@RequestBody TaskComponent payload) {
        try {
            TaskComponent newTask = new TaskComponent();
            newTask = taskService.createTask(payload);
            if (newTask.getTaskId() != null) {
                return new ResponseEntity<>(newTask, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id) {
        try {
            Optional<TaskComponent> isTaskExists = taskService.deleteTask(id);
            if (isTaskExists.isPresent())
                return new ResponseEntity<>("Successfully removed the record", HttpStatus.OK);
            else return new ResponseEntity("No such record found", HttpStatus.NOT_FOUND);
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
