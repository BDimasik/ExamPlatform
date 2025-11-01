package me.smorodin.exam.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DecideTaskDTO {
    @NotBlank
    private Long taskId;
    @NotBlank
    private Long deciderId;
}
