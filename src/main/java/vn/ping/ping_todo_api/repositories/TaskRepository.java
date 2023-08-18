package vn.ping.ping_todo_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ping.ping_todo_api.models.Task;

public interface TaskRepository extends JpaRepository <Task,Long> {
}
