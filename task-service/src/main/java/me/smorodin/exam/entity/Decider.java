package me.smorodin.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deciders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Decider {
    @Id
    private Long id;
}
