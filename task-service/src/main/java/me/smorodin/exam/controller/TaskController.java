package me.smorodin.exam.controller;

import lombok.RequiredArgsConstructor;
import me.smorodin.exam.dto.CreateTaskDTO;
import me.smorodin.exam.dto.DecideTaskDTO;
import me.smorodin.exam.dto.TaskDTO;
import me.smorodin.exam.entity.Task;
import me.smorodin.exam.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<?> allTasks(@RequestParam(value = "show_deciders", defaultValue = "false") Boolean showDeciders) {
        if (!showDeciders)
            return ResponseEntity.ok(
                    StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                            taskService.allTasks().iterator(),
                            Spliterator.ORDERED
                    ), false).map(TaskDTO::new)
            );
        else return ResponseEntity.ok(taskService.allTasksWithUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody CreateTaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskName(taskDTO.getName());
        task.setPoints(taskDTO.getPoints());
        task.setS3FileId(taskDTO.getFile());
        return ResponseEntity.ok(taskService.addTask(task));
    }

    @PostMapping("/decide")
    @Transactional
    public ResponseEntity<?> decideTask(@RequestBody DecideTaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.addDecider(taskDTO.getTaskId(), taskDTO.getDeciderId()));
    }
}
