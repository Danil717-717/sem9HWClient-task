package ru.springgb.clienttask.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.springgb.cliententity.model.Task;

import java.util.List;

@Service
@ComponentScan
public interface TaskService {

    Task createTask(Task task);

    Task save(Task task);

    List<Task> getAllTasks();

    Task getTaskById(Long id);


    Task updateTask(Long id, Task task);

    void deleteById(Long id);

}
