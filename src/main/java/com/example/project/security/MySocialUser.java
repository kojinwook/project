package com.example.project.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MySocialUser {
    private String sub;
    private String pass;
    private String name;
    private String email;

}
