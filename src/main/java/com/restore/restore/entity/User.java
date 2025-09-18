package com.restore.restore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restore.restore.domain.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Enter your valid username")
    private String userName;

    @Column(nullable = false)
    @NotBlank(message = "Enter your gender")
    private String gender;

    @Column(nullable = false)
    @NotNull(message = "Enter your present age")
    private Integer age;

    @Column(nullable = false, unique = true)
    @Email(message = "Enter a valid email")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Enter your password")
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Enter your valid phone number")
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Appointment> appointmentList = new ArrayList<>();
}
