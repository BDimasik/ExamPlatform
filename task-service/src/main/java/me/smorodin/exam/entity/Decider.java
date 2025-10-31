package me.smorodin.exam.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deciders")
@Data
@NoArgsConstructor
public class Decider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
