//package com.dss.rest_template_service.handler;
//
//import org.springframework.boot.CommandLineRunner;
//
////@Component
//public class ApiTriggerRunner implements CommandLineRunner {
//    @Override
//    public void run(String... args) throws Exception {
//
//
//
///*  MANUALLY USING THE ENTREATY:
//        Long id = 20L;
//            String url = "http://localhost:8080/getUser/"+ id;
//        Entreaty entreaty = new Entreaty(url);
//        Response<User> taskResponse = entreaty.get(User.class);
//        System.out.println(taskResponse);*/
//
///*
//        RestTemplate restTemplate = new RestTemplate();
//        //url , header -> request entity
//        ///reponse entity ->url,  reqyenitty,post,class
//
//        User user = new User();
//        user.setId(22L);
//
//        Task task = new Task();
//        task.setName("karo rest-template");
//        task.setStatus("Honewala hai");
//        task.setUser(user);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Task> reqEntity = new HttpEntity<>(task, headers);
//
//        //  RequestEntity<Task> reqEn = new RequestEntity<>(task, headers,HttpMethod.POST, URI.create(url));
////        ResponseEntity<Task> response = restTemplate.exchange(reqEn, Task.class);
//
//        ResponseEntity<Task> response = restTemplate.exchange(
//                url, HttpMethod.POST, reqEntity, Task.class);
//
//        System.out.println(response.getStatusCode() + " " + response.getBody());*/
//    }
//}
