package vn.ping.ping_todo_api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ping.ping_todo_api.error.UserAlreadyExistException;
import vn.ping.ping_todo_api.models.Task;
import vn.ping.ping_todo_api.models.request.TaskItem;
import vn.ping.ping_todo_api.services.ITaskService;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final ITaskService taskService;
    @PostMapping("")
    public ResponseEntity<?> createTask(@RequestBody TaskItem task){
        try {
            return ResponseEntity.ok(taskService.createTask(task));
        } catch (UsernameNotFoundException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
