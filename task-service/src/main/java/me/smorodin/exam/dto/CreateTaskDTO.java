package me.smorodin.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateTaskDTO {
    private String name;
    private String file; //todo change to filestream
    private BigDecimal points;
}
