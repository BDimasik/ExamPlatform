package me.smorodin.exam.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.smorodin.exam.entity.Decider;
import me.smorodin.exam.entity.Task;
import me.smorodin.exam.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TaskService {
    TaskRepository taskRepository;
    DeciderService deciderService;

    public Iterable<Task> allTasks() {
        return taskRepository.findAll();
    }

    public Iterable<Task> allTasksWithUsers() {
        return taskRepository.findAllWithUsers();
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public void removeTask(Task task) {
        taskRepository.delete(task);
    }

    @Transactional
    public Task addDecider(Long task, Long decider) {
        Task entity = taskRepository.findById(task).orElseThrow(() -> new RuntimeException("No task find with id " + task));
        Decider entityDecider = deciderService.findOrCreateDecider(decider);
        entity.getDeciders().add(entityDecider);
        return entity;
    }
}
