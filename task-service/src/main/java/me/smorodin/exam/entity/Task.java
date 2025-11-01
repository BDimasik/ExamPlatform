package me.smorodin.exam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(max = 64)
    @Column(unique = true)
    @NotBlank
    private String taskName;

    @Column(unique = true)
    private String s3FileId;

    @ManyToMany
    @JoinTable(
            name = "tasks_deciders",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "deciders_id")
    )
    private Set<Decider> deciders = new HashSet<>();

    @Column(name = "points", precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) DEFAULT 1.00")
    private BigDecimal points;
}
