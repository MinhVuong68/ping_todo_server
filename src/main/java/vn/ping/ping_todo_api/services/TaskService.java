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
import vn.ping.ping_todo_api.utils.Security;

import java.time.LocalDate;
import java.time.LocalTime;

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
}


