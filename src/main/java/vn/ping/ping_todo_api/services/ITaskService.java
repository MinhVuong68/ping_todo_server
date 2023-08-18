package vn.ping.ping_todo_api.services;

import vn.ping.ping_todo_api.models.Task;
import vn.ping.ping_todo_api.models.request.TaskItem;

public interface ITaskService {
    Task createTask(TaskItem task);
}
