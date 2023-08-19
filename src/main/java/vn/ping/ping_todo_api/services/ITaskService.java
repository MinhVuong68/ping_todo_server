package vn.ping.ping_todo_api.services;

import vn.ping.ping_todo_api.models.Task;
import vn.ping.ping_todo_api.models.request.TaskItem;

import java.util.List;

public interface ITaskService {
    Task createTask(TaskItem task);
    List<Task> readTaskByDate(Long userId,String date);
    Task updateStatus(Long taskId);
    Task updateTaskName(Long taskId, String taskName);
    void deleteTask(Long id);
}
