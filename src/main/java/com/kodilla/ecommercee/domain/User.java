package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "user_key")
    private Long id;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "status")
    private int status;

    @Column(name = "user_key")
    private int userKey;

    @Column(name = "key_expiration_time")
    private LocalDateTime keyExpirationTime;

    @OneToOne()
    private Cart cart;

    public void switchBlockade() {
        if(this.status == 0) {
            this.status = 1;
        } else {
            this.status = 0;
        }
    }

    public User(Long id, String username, int status, int userKey) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }

    public User(Long id, String username, int status, int userKey,  LocalDateTime keyExpirationTime) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.keyExpirationTime = keyExpirationTime;
    }
}