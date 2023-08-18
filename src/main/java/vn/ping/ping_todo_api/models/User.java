package vn.ping.ping_todo_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="_user")
@Data
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(50)",nullable = false)
    private String name;
    @Column(columnDefinition = "nvarchar(255)",nullable = false)
    private String avatar;
    @Column(length = 11,nullable = false)
    private String phoneNumber;
    @Column(columnDefinition = "nvarchar(255)",nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
}
