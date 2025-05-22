package com.dss.rest_template_service.controller;

import com.dss.rest_template_service.handler.MyErrorHandler;
import com.dss.rest_template_service.model.Task;
import com.dss.rest_template_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class TemplateController {
    @Autowired
    RestTemplate restTemplate;

    //with the help of getFromTaskApp wit
    @GetMapping("/get-from-taskApp/{id}")
    public ResponseEntity<String> getTestFromTaskApp(@PathVariable Integer id) {

        String url = "http://localhost:8080/test/" + id;
        return restTemplate.getForEntity(url, String.class);
    }

    @GetMapping("/get-from-taskApp-user/{id}")
    public User getUserFromTaskApp(@PathVariable Integer id) {
        System.out.println("Invoked success..");
        String url = "http://localhost:8080/getUser/" + id;

        User forObject = restTemplate.getForObject(url, User.class);
        return forObject;
    }

    // ERROR HANDLER
    @GetMapping("/get-from-taskApp-user-error/{id}")
    public ResponseEntity<?> getUserFromTaskApp2(@PathVariable Integer id) {
        System.out.println("Invoked success..");

        String url = "http://localhost:8080/getUser/" + id;
        restTemplate.setErrorHandler(new MyErrorHandler());

        try {
            User user = restTemplate.getForObject(url, User.class);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            System.out.println("Exception caught in controller: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Error occurred while calling external API.");
        }
    }

    @PostMapping("/post-user-to-external")
    public ResponseEntity<User> postUserToExternal() {
        System.out.println("method invoked");

        String url = "http://localhost:8080//saveUser";
        User userToSend = new User();
        userToSend.setName("champu");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requstEntity = new HttpEntity<>(userToSend, headers);

        ResponseEntity<User> response = restTemplate.exchange(
                url, HttpMethod.POST, requstEntity, User.class);

        return response;
    }


//saving new Task


    @PostMapping("/save-task")
    public ResponseEntity<?> saveTask() {
        //url , header -> request entity
        ///reponse entity ->url,  reqyenitty,post,class
        String url = "http://localhost:8080/save";

        User user = new User();
        user.setId(22L);

        Task task = new Task();
        task.setName("karo rest-template");
        task.setStatus("Honewala hai");
        task.setUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Task> reqEntity = new HttpEntity<>(task, headers);

      //  RequestEntity<Task> reqEn = new RequestEntity<>(task, headers,HttpMethod.POST, URI.create(url));
//        ResponseEntity<Task> response = restTemplate.exchange(reqEn, Task.class);

        ResponseEntity<Task> response = restTemplate.exchange(
                url, HttpMethod.POST, reqEntity, Task.class);

        return response;
    }

}