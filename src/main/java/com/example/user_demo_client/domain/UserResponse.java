package com.example.user_demo_client.domain;

import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    List<Link> links;
    List<User> content;
}
