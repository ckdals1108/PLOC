package com.example.ploc.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public class BaseLogin {
    String loginId;
    String password;
}
