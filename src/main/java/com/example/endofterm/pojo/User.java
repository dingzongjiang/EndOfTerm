package com.example.endofterm.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer sum;
    private Integer identity;
    private Integer noBack;
    private Role role;

    public User() {
    }


    public User(Integer id, String username, String password, Integer sum, Integer identity, Integer noBack, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sum = sum;
        this.identity = identity;
        this.noBack = noBack;
        this.role = role;
    }
}
