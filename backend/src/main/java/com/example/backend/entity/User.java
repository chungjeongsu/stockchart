package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "user")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    private Long userCode;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_pw")
    private String userPw;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscribe_code")
    private Subscribe subscribe;
}
