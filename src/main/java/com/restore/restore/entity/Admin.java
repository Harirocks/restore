package com.restore.restore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
@Table(name ="admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    @Column(nullable = false)
    @NotBlank(message = "enter your name")
    private String adminName;
    @Column(nullable = false)
    @NotBlank(message = "enter your gender")
    private String gender;
    @Column(nullable = false)
    @NotNull(message = "enter your present age")
    private int age;
    @Column(nullable = false)
    @NotBlank(message = "enter your valid email")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "enter your password")
    private String password;
    @Column(nullable = false)
    @NotNull(message = "enter your valid phone number")
    private long phoneNo;
    @Column(nullable = false)
    @NotBlank(message = "enter your role")
    private String role;
    @OneToMany
    private List<Doctor> doctorList;
}