package me.smorodin.exam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(max = 64)
    @Column(unique = true)
    private String taskName;

    @Column(unique = true)
    private String s3FileId;

    @ManyToMany
    private List<Long> decided = new ArrayList<>();

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 1.00")
    private BigDecimal points;
}
