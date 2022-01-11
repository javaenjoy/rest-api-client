package com.example.user_demo_client.service;


import com.example.user_demo_client.domain.Link;
import com.example.user_demo_client.domain.User;
import com.example.user_demo_client.domain.UserResponse;
import com.example.user_demo_client.exception.MyErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserApiService {

    private final String URI_USERS = "http://localhost:8080/api/users";
    private final String URI_USERS_ID = "http://localhost:8080/api/users/{id}";//

    private final RestTemplate restTemplate;

    /*******************************************************************
     * RestTemplate Error Handling
     ******************************************************************/
    public UserApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.restTemplate.setErrorHandler(new MyErrorHandler());
    }


    //사용자 목록 조회
    public UserResponse retrieveUserAll() throws Exception {
        ResponseEntity<UserResponse> responseEntity = restTemplate.getForEntity(URI_USERS, UserResponse.class);
        UserResponse userResponse = responseEntity.getBody();

        List<Link> links = userResponse.getLinks();
        for (Link link : links) {
            System.out.println("rel : " + link.getRel());
            System.out.println("href : " + link.getHref());
        }

        List<User> content = userResponse.getContent();
        for (User user : content) {
            System.out.println("id : " + user.getId());
            System.out.println("name : " + user.getName());
            System.out.println("joinDate : " + user.getId());

            List<Link> linkList = user.getLinks();
            for (Link link : linkList) {
                System.out.println("rel : " + link.getRel());
                System.out.println("href : " + link.getHref());
            }
        }
        return null;
    }


    //사옹자 상세 조회
    public User retrieveUserById(int id) throws Exception {

        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", id);
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(URI_USERS_ID, User.class, params);

        int statusCode = responseEntity.getStatusCodeValue();
        if (statusCode == 200) {
            User user = responseEntity.getBody();
            System.out.println("statusCode : " + responseEntity.getStatusCodeValue());
            System.out.println("id : " + user.getId());
            System.out.println("name : " + user.getName());
            System.out.println("joinDate : " + user.getJoinDate());
            List<Link> linkList = user.getLinks();
            if (linkList != null) {
                for (Link link : linkList) {
                    System.out.println("rel : " + link.getRel());
                    System.out.println("href : " + link.getHref());
                }
            }
            return user;
        }
        return null;

    }


    //사용자 생성
    public String registerUser(User user) {
        URI uri = restTemplate.postForLocation(URI_USERS, user);
        //중요
        System.out.println("URI : " + uri);
        return uri.toString();
    }


    //사용자 삭제
    public void removeUser(int id) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", id);
        restTemplate.delete(URI_USERS_ID, params);
    }

}
