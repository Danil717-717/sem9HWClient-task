package ru.springgb.clienttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.springgb.cliententity.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

}
