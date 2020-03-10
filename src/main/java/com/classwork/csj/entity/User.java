package com.classwork.csj.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(min = 2)
    @ApiModelProperty(notes = "First name should be at least two characters.")
    private String firstName;

    @Size(min = 2)
    @ApiModelProperty(notes = "Last name should be at least two characters.")
    private String lastName;

    private Timestamp dateOfBirth;

    @NotEmpty(message = "Email address should not be empty")
    @ApiModelProperty(notes = "Email should be provided")
    @Email
    private String email;
    private String phoneNumber;
    private String address;
    private String gender;

    @OneToOne
    private userType userType;
}
