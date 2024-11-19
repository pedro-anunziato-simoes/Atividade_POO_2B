package com.example.kanban.service;

import com.example.kanban.model.Enum.Status;
import com.example.kanban.model.Task;
import com.example.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setStatus(Status.A_FAZER);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setPriority(taskDetails.getPriority());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    
    public Task moveTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        switch (task.getStatus()) {
            case A_FAZER:
                task.setStatus(Status.EMPROGRESSO);
                break;
            case EMPROGRESSO:
                task.setStatus(Status.CONCLUIDO);
                break;
        }
        return taskRepository.save(task);
    }
}
