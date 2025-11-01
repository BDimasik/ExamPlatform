package me.smorodin.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.smorodin.exam.entity.Task;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String name;
    private String s3id;
    private BigDecimal points;

    public TaskDTO(Task task) {
        this(task.getId(), task.getTaskName(), task.getS3FileId(), task.getPoints());
    }
}
