package com.farhan.bioskopapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity implements Serializable {

    @Id
    @NotEmpty(message = "username is required")
    @Size(min = 8, message = "minimum lenght of the username is 8 letters")
    @Column(length = 100)
    private String username;

    @NotEmpty(message = "full name is required")
    @Column(name = "full_name", length = 150, nullable = false)
    private String fullName;

    @NotEmpty(message = "password is required")
    @Size(min = 8, message = "minimum lenght of the password is 8 letters")
    @Column(length = 100, nullable = false)
    private String password;

    @Email(message = "invalid email format")
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "address is required")
    @Column(length = 300, nullable = false)
    private String address;
}
