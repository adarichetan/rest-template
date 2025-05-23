package com.dss.rest_template_service.controller;


import com.dss.rest_template_service.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class OrderController {
        @GetMapping("/get-from-taskApp/{id}")
        public ResponseEntity<String> getTestFromTaskApp(@PathVariable Integer id) {

            HttpURLConnection httpURLConnection = null;
            try {
                String url = "http://localhost:8080/test/" + id;

                URL obj = new URL(url);

                httpURLConnection = (HttpURLConnection) obj.openConnection();

                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Accept", "application/json");

                httpURLConnection.setConnectTimeout(200);

                httpURLConnection.setReadTimeout(500);

                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = in.readLine()) != null) {
                    response.append(responseLine);
                }
                in.close();

                System.out.println("Response: " + response.toString());

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            return ResponseEntity.ok("Response call success!");
        }

    //FOR OBJECT::
    @GetMapping("/get-from-taskApp-user/{id}")
    public ResponseEntity<User> getUserFromTaskApp(@PathVariable Integer id) {
        HttpURLConnection httpURLConnection = null;
        User user = null;

        try {

            String url = "http://localhost:8080/getUser/" + id;
            URL obj = new URL(url);
            httpURLConnection = (HttpURLConnection) obj.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.setReadTimeout(3000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String responseLine;

            while ((responseLine = in.readLine()) != null) {
                response.append(responseLine);
            }
            in.close();

            // Convert JSON response to User object
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(response.toString(), User.class);

        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed URL", e);
        } catch (IOException e) {
            throw new RuntimeException("IO Error", e);
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return ResponseEntity.ok(user);
    }
}

