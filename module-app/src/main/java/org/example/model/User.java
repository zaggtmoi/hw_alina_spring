package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "\"users\"")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "email", length = 254)
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "created_at")
    private Instant createdAt;

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(id == null ? "no id" : id.toString());
        builder.append(": ").append(name);
        if (age != null) {
            builder.append(" (").append(age).append(") ");
        }
        if (email != null) {
            builder.append(" - ").append(email);
        }
        return builder.toString();
    }
}