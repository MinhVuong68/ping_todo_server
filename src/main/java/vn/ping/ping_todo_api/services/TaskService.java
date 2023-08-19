package vn.ping.ping_todo_api.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.ping.ping_todo_api.models.Task;
import vn.ping.ping_todo_api.models.User;
import vn.ping.ping_todo_api.models.request.TaskItem;
import vn.ping.ping_todo_api.repositories.TaskRepository;
import vn.ping.ping_todo_api.repositories.UserRepository;
import vn.ping.ping_todo_api.utils.Date;
import vn.ping.ping_todo_api.utils.Security;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Security security;
    @Override
    public Task createTask(TaskItem taskItem) {
        User userFound = userRepository.findById(
                taskItem.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(taskItem.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Task name can't not empty!");
        }
        security.officialCheck(taskItem.getUserId());
        var task = Task.builder()
                .name(taskItem.getName())
                .dateCreate(LocalDate.now())
                .timeCreate(LocalTime.now())
                .isCompleted(false)
                .user(userFound)
                .build();
        return taskRepository.save(task);
    }

    @Override
    public List<Task> readTaskByDate(Long userId,String date) {
        security.officialCheck(userId);
        LocalDate dateFormat = Date.formatFromDateStringtoLocalDate(date);
        return taskRepository.findTasksByUser_IdAndDateCreate(userId,dateFormat);
    }

    @Override
    public Task updateStatus(Long taskId) {
        Task taskFound = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new NullPointerException("Task not found"));
        security.officialCheck(taskFound.getUser().getId());
        if
        (taskFound.getDateCreate().isBefore(LocalDate.now()) ||
                taskFound.getDateCreate().isAfter(LocalDate.now())
        ){
            throw new DateTimeException("Could not update task of the days before and today after");
        }
        taskFound.setCompleted(!taskFound.isCompleted());
        return taskRepository.save(taskFound);
    }

    @Override
    public Task updateTaskName(Long taskId, String taskName) {
        Task taskFound = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new NullPointerException("Task not found"));
        security.officialCheck(taskFound.getUser().getId());
        if
        (taskFound.getDateCreate().isBefore(LocalDate.now()) ||
                taskFound.getDateCreate().isAfter(LocalDate.now())
        ){
            throw new DateTimeException("Could not update task of the days before and today after");
        }
        if(taskName.isEmpty()) {
            throw new IllegalArgumentException("Task name can't not empty!");
        }
        taskFound.setName(taskName);
        return taskRepository.save(taskFound);
    }

    @Override
    public void deleteTask(Long id) {
        Task taskFound = taskRepository
                .findById(id)
                .orElseThrow(() -> new NullPointerException("Task not found"));
        security.officialCheck(taskFound.getUser().getId());
        if
        (taskFound.getDateCreate().isBefore(LocalDate.now()) ||
        taskFound.getDateCreate().isAfter(LocalDate.now())
        ){
            throw new DateTimeException("Could not delete task of the days before and today after");
        }
        taskRepository.delete(taskFound);
    }
}


