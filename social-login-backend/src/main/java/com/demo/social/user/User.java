package com.demo.social.user;

import com.demo.social.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {


    @Id
    @GeneratedValue()
    private UUID id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "providerId", unique = true)
    private String providerId;

    private String provider;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    private String profilePictureUrl;

    @Column(name = "emailVerified", nullable = false)
    private boolean emailVerified;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "accountLocked", nullable = false)
    private boolean accountLocked;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Version
    private Integer version;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Represents the name of the user which is the id of the user.
     * This method is used in Spring Security for authentication purposes.
     * ----
     * In Spring Security, the `Principal` interface represents the currently authenticated user.
     * The `getName` method is used to retrieve the unique identifier of the user, which in this case is the user's id.
     * This id is inherited from the `BaseEntity` class and is used to uniquely identify the user in the system.
     * ----
     *
     * @return the id of the user as a String
     */
    @Override
    public String getName() {
        return id.toString();
    }


    /**
     * Represents the authorities of the user.
     * By default, the roles of the user are considered as authorities.
     * Authorities are the permissions or roles granted to the user.
     * This method is used in Spring Security for authorization purposes.
     * -------
     * In Spring Security, the `UserDetails` interface requires the implementation of the `getAuthorities` method.
     * This method returns a collection of `GrantedAuthority` objects, which represent the roles or permissions assigned to the user.
     * Here, we map each `RoleEntity` to a `SimpleGrantedAuthority` using the role's name.
     * This allows Spring Security to understand what roles or permissions the user has.
     *
     * @return a collection of authorities granted to the user
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username of the user, which is the user's email.
     * This method is used in Spring Security for authentication purposes.
     * ----
     * In Spring Security, the `UserDetails` interface requires the implementation of the `getUsername` method.
     * This method returns the username used for authentication, which in this case is the user's email.
     * The email is used as the unique identifier for the user during the login process.
     * ----
     *
     * @return the email of the user
     */
    @Override
    public String getUsername() {
        return getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
