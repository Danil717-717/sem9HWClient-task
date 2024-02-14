package ru.springgb.clienttask.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
//import ru.springgb.clientexecutor.model.Executor;
import ru.springgb.cliententity.model.Task;

import java.util.List;

@Service
@ComponentScan
public interface TaskService {

    Task createTask(Task task);

    Task save(Task task);

    List<Task> getAllTasks();

    Task getTaskById(Long id);
    List<Task> getTaskStatus(String status);

    Task updateTask(Long id, Task task);

    //Task apdateTask(Task task);
    void deleteById(Long id);

    //Task createExecutorForTask(Long id, Executor executor);

    //void createTaskForExecutor(Long id, Task task);

    //Task assignExecutor(Long id, Long executorId);
    //Executor assignTask(Long id, Long taskId);
    //List<Task> getTasksExecutor(Long id);

    //List<Executor> getExecutorsTask(Long id);

    //Executor save(Executor executor);

    //void removingTaskFromExecutor(Long executorId, Long taskId);
    //void removingExecutorFromTask(Long taskId,Long executorId);


    Page<Task> findPaginated(int pageNo, int pageSize);


    Page<Task> findPaginated(Pageable pageable);
}
