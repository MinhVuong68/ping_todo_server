package vn.ping.ping_todo_api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dateCreate;
    @JsonFormat(pattern = "hh:mm:ss")
    @Column(nullable = false)
    private LocalTime timeCreate;
    @Column(name = "completed")
    private boolean isCompleted;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
