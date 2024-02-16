package com.practise.crud_op.repositries;

import com.practise.crud_op.data_component.TaskComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskComponent,Integer> {

}
