package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;

    public UserDTO(User user) {
        if (user != null) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.age = user.getAge();
        }
    }

    public User getUser() {
        User user = new User(name, email, age);
        user.setId(id);
        return user;
    }
}
