package com.example.user_demo_client.domain;


import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Link {
    private String rel;
    private String href;

}
