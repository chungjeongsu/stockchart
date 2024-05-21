package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "subscribe")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Subscribe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscribe_code")
    private Long subscribeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_code")
    private Stock stock;
}
