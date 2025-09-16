package com.restore.restore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
@Table(name ="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;
    @Column(nullable = false)
    @NotBlank(message = "enter your valid name")
    private String doctorName;
    @Column(nullable = false)
    @NotBlank(message = "enter your speciality")
    private String doctorSpeciality;
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
    @OneToOne
    private DoctorAddress address;


}