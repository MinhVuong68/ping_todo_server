package vn.ping.ping_todo_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ping.ping_todo_api.models.User;
import vn.ping.ping_todo_api.security.UserDetail;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    //User findByPhoneNumber(String phoneNumber);
}
