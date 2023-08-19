package vn.ping.ping_todo_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ping.ping_todo_api.models.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository <Task,Long> {
    List<Task> findTasksByUser_IdAndDateCreate(Long userId,LocalDate date);
}
