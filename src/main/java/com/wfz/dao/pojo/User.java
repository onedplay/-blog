package com.wfz.dao.pojo;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String userName;
    private String email;
    private String password;
    private String phoneNum;
    private List<Role> roleList;
}
