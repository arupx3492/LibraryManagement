package com.example.Library.management.entity;

import com.example.Library.management.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;



    private final Boolean isActive=true;

    @OneToMany(mappedBy = "user")
    private List<BorrowingRecord> borrowingRecords;
}
