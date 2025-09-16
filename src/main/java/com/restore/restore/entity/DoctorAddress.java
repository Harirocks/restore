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
@Table(name ="doctors_address")
public class DoctorAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    @Column(nullable = false)
    @NotBlank(message = "enter your clinic name")
    private String clinicName;
    @Column(nullable = false)
    @NotBlank(message = "enter your address")
    private String doctorAddress;
    @Column(nullable = false)
    @NotBlank(message = "enter your city ")
    private String city;
    @Column(nullable = false)
    @NotNull(message = "enter your pin code")
    private long pinCode;
}
