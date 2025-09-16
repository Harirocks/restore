package com.restore.restore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
@Table(name ="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;
    @Column(nullable = false)
    @NotNull(message = "enter date of your appointment")
    private LocalDate appointmentDate;
    @Column(nullable = false)
    @NotNull(message = "enter time of your appointment")
    private LocalTime appointmentTime;
    @Column(nullable = false)
    @NotBlank(message = "enter your patient name")
    private String patientName;
    @Column(nullable = false)
    @NotBlank(message = "enter your gender")
    private String gender;
    @Column(nullable = false)
    @NotNull(message = "enter your present age")
    private int age;
    @ManyToOne
    private User user;
    @ManyToOne
    private Doctor doctor;



}