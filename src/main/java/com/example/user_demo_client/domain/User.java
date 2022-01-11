package com.example.user_demo_client.domain;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String name;
    private String joinDate;
    private List<Link> links;


    public User(int id, String name, String joinDate) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
    }


}
