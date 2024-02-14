package ru.springgb.clienttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
//import ru.springgb.clientexecutor.model.Executor;
import ru.springgb.cliententity.model.Task;
//import ru.springgb.clientexecutor.logAspect.TrackUserAction;
//import ru.springgb.clientexecutor.repository.ExecutorRepository;
import ru.springgb.clienttask.repository.TaskRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    //private final ExecutorRepository repository;
    //private final ExecutorService executorService;



   // @TrackUserAction
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

//    @TrackUserAction
//    @Override
//    public Task createExecutorForTask(Long id, Executor executor) {
//        save(executor);
//        Task task = getTaskById(id);
//        task.addExecutor(executor);
//        updateTask(task.getId(),task);
//        taskRepository.save(task);
//        return task;
//    }

//    @TrackUserAction
//    @Override
//    public void createTaskForExecutor(Long id, Task task) {
//        Executor executor = findByIdExecutor(id);
//        executor.addTask(task);
//        save(task);
//        //taskNew.setId(task.getId());
//        //save(task);
////        Executor executor = findByIdExecutor(id);
////        executor.addTask(task);
////        executorService.updateExecutor(executor.getId(),executor);
////        executorService.save(executor);
//
//    }

    //@TrackUserAction
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

   // @TrackUserAction
    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task getTask(Long id) {
        return getAllTasks().stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    //&&&&&&&&
    @Override
    public List<Task> getTaskStatus(String status) {
        List<Task> tasks =   taskRepository.findAll().stream().filter(task -> task.getStatus().equals(status)).toList();
        return tasks;
    }

    //@TrackUserAction
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


//    @TrackUserAction
//    public Task apdateTask(Task task){
//        return taskRepository.save(task);
//    }
//
//
//
//    @TrackUserAction
    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }



//    @Override
//    public Task assignExecutor(Long id, Long executorId) {
//        Task task = getTask(id);
//        Executor executor = getExecutor(executorId);
//        task.addExecutor(executor);
//        return taskRepository.save(task);
//    }



//    @Override
//    public Executor assignTask(Long id, Long taskId) {
//        Executor executor = findByIdExecutor(id);
//        Task task = getTaskById(taskId);
//        executor.addTask(task);
//        return repository.save(executor);
//    }


//    public List<Executor> findAllExecutor() {
//        return repository.findAll();
//    }


//    public Executor getExecutor(Long id) {
//        return findAllExecutor().stream().filter(executor  -> executor.getId().equals(id)).findFirst().orElse(null);
//    }
//
//    public Executor findByIdExecutor(Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Reader not found"));
//    }

//    @Override
//    public List<Task> getTasksExecutor(Long id){
//        Executor executor = findByIdExecutor(id);
//        return executor.getTasks();
//        //return findByIdExecutor(id).getTasks();
//    }

//    @Override
//    public List<Executor> getExecutorsTask(Long id){
//        return getTaskById(id).getExecutors();
//    }
//

//    @TrackUserAction
//    @Override
//    public Executor save(Executor executor){
//        return repository.save(executor);
//    }
//
//
//
//    @Override
//    public void removingTaskFromExecutor(Long executorId, Long taskId) {
//        Executor executor = findByIdExecutor(executorId);
//        executor.removeTask(taskId);
//        save(executor);
//
//    }

//    @Override
//    public void removingExecutorFromTask(Long taskId, Long executorId) {
//        Task task = getTaskById(taskId);
//        task.removeExecutor(executorId);
//        save(task);
//    }

    @Override
    public Page<Task> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return taskRepository.findAll(pageable);
    }

    @Override
    public Page<Task> findPaginated(Pageable pageable) {
            int pageSize = pageable.getPageSize();
            int currentPage = pageable.getPageNumber();
            int startItem = currentPage * pageSize;
            List<Task> list;

            if (taskRepository.findAll().size() < startItem) {
                list = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, taskRepository.findAll().size());
                list = taskRepository.findAll().subList(startItem, toIndex);
            }

            Page<Task> bookPage
                    = new PageImpl<Task>(list, PageRequest.of(currentPage, pageSize), taskRepository.findAll().size());

            return bookPage;
    }


}
