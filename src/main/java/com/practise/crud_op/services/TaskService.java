package com.practise.crud_op.services;

import com.practise.crud_op.data_component.TaskComponent;
import com.practise.crud_op.repositries.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskComponent> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<TaskComponent> getSpecificTask(String id) {
        try {
            Integer taskID = Integer.parseInt(id);
            return taskRepository.findById(taskID);
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    public TaskComponent createTask(TaskComponent payload) {
        try {
            TaskComponent newTask = new TaskComponent();
            payload.setIsCompleted(false);
            newTask = taskRepository.save(payload);
            return newTask;
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    public Optional<TaskComponent> deleteTask(String id) {
        try {
            Integer taskId = Integer.parseInt(id);
            Optional<TaskComponent> specificTask = taskRepository.findById(taskId);
            taskRepository.deleteById(taskId);
            return specificTask;
        } catch (RuntimeException err) {
            throw new RuntimeException(err);
        }
    }

    public Boolean updateTask(String id, TaskComponent payload) {
        try {
            Integer taskId = Integer.parseInt(id);
            Optional<TaskComponent> isTaskExist = taskRepository.findById(taskId);
            if (isTaskExist.isPresent()) {
                TaskComponent updatedTask = isTaskExist.get();
                updatedTask.setTaskName(payload.getTaskName());
                updatedTask.setTaskDescription(payload.getTaskDescription());
                updatedTask.setIsCompleted(false);
                taskRepository.save(updatedTask);
                return true;
            } else {
                return false;
            }
        } catch (RuntimeException err) {
            throw new RuntimeException(err);
        }
    }

    public Page<TaskComponent> getTasksByPagination(Integer pgNo, Integer records, String sortProperty) {
        try {
            Pageable pageable = null;
            if (sortProperty != null && !sortProperty.isEmpty()) {
                pageable = PageRequest.of(pgNo, records, Sort.Direction.ASC, sortProperty);
            } else {
//                sortProperty="taskName";
                pageable = PageRequest.of(pgNo, records, Sort.Direction.ASC, "taskName");
            }
            return taskRepository.findAll(pageable);
        } catch (RuntimeException err) {
            throw new RuntimeException(err);
        }
    }
}
