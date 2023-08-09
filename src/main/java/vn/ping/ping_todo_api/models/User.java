package vn.ping.ping_todo_api.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="_user")
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(50)")
    private String fullName;
    @Column(columnDefinition = "nvarchar(255)")
    private String avatar;
    @Column(length = 11)
    private String phoneNumber;
    @Column(columnDefinition = "nvarchar(255)")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
