package com.practise.crud_op.data_component;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tasks")

public class TaskComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;
    private String taskName;
    private Boolean isCompleted;
    private String taskDescription;

}
