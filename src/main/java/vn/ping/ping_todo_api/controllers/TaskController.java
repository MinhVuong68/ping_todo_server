package vn.ping.ping_todo_api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import vn.ping.ping_todo_api.models.request.TaskItem;
import vn.ping.ping_todo_api.services.ITaskService;
import java.time.DateTimeException;
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

    @GetMapping("")
    public ResponseEntity<?> readTaskByDate
            (@RequestParam("user_id") Long userId,
             @RequestParam("date") String date
            ) {
        try {
            return ResponseEntity.ok(taskService.readTaskByDate(userId,date));
        } catch (NullPointerException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{task_id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable("task_id") Long taskId) {
        try {
            return ResponseEntity.ok(taskService.updateStatus(taskId));
        } catch (NullPointerException | IllegalArgumentException | DateTimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{task_id}/name")
    public ResponseEntity<?> updateTaskName(
            @PathVariable("task_id") Long taskId,
            @RequestBody String taskName
            ) {
        try {
            return ResponseEntity.ok(taskService.updateTaskName(taskId,taskName));
        } catch (NullPointerException | IllegalArgumentException | DateTimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable("task_id") Long id){
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Delete task successfully");
        } catch (NullPointerException | DateTimeException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
