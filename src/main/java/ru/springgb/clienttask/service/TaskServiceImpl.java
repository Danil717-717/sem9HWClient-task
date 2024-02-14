package ru.springgb.clienttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.springgb.cliententity.model.Task;
import ru.springgb.clienttask.logAspect.TrackUserAction;
import ru.springgb.clienttask.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @TrackUserAction
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }


    @TrackUserAction
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task getTask(Long id) {
        return getAllTasks().stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }


    @TrackUserAction
    @Override
    public Task updateTask(Long id, Task task) {
        Task taskStaraya = getTask(id);
        if (taskStaraya != null) {
            taskStaraya.setDescription(task.getDescription());
            taskStaraya.setStatus(task.getStatus());
            taskStaraya.setCompletionTime(task.getCompletionTime());
            taskStaraya.setExecutors(task.getExecutors());
        }
        return taskStaraya;
    }


    @TrackUserAction
    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }


}
