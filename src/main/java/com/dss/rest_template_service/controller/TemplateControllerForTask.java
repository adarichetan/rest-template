package com.dss.rest_template_service.controller;


import com.dss.rest_template_service.model.Task;
import com.dss.rest_template_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class TemplateControllerForTask {

    @Autowired
    RestTemplate restTemplate;

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
        //  ResponseEntity<Task> response = restTemplate.exchange(reqEn, Task.class);

        ResponseEntity<Task> response = restTemplate.exchange(
                url, HttpMethod.POST, reqEntity, Task.class);

        return response;
    }

    @GetMapping("/get-tasks")
    public ResponseEntity<String> getTask() {
        String url = "http://localhost:8080/allTasks";

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println(forEntity.getBody());

        return ResponseEntity.ok(forEntity.getBody());
    }

    @PutMapping("/udpate/{id}")
    public void getIndividualTask(@PathVariable Long id) {
        String url = "http://localhost:8080/udpate-task/" + id;

        User user = new User();
        user.setId(25L);
        user.setName("sf");

        Task task = new Task(id, "asffasfas", "out", user);
        restTemplate.put(url, task);
    }

//    @DeleteMapping("/delete/{id}")
//    public void deleteById(@PathVariable Long id) {
//        String url = "http://localhost:8080/delete-task/" + id;
//        restTemplate.delete(url);
//    }
}
