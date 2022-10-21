package com.farhan.bioskopapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Column(name = "full_name", length = 150, nullable = false)
    private String fullName;

    @NotBlank
    @Column(length = 300, nullable = false)
    private String address;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity(String username, String fullName, String address, String email, String password) {
        this.username = username;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    //    @Id
//    @NotEmpty(message = "username is required")
//    @Size(min = 8, message = "minimum lenght of the username is 8 letters")
//    @Column(length = 100)
//    private String username;
//
//    @NotEmpty(message = "full name is required")
//    @Column(name = "full_name", length = 150, nullable = false)
//    private String fullName;
//
//    @NotEmpty(message = "password is required")
//    @Size(min = 8, message = "minimum lenght of the password is 8 letters")
//    @Column(length = 100, nullable = false)
//    private String password;
//
//    @Email(message = "invalid email format")
//    @Column(length = 100, nullable = false, unique = true)
//    private String email;
//
//    @NotEmpty(message = "address is required")
//    @Column(length = 300, nullable = false)
//    private String address;
}
