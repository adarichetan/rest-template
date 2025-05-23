package com.dss.rest_template_service.manual;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Entreaty {

    private String url;
    private URL urlObject;
    private HttpURLConnection connection;

    public Entreaty(String url) {
        this.url = url;
        postInit();
    }

    public void postInit() {
        try {
            urlObject = new URL(url);
            connection = (HttpURLConnection) urlObject.openConnection();
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public <T> Response<T> get(Class<T> clazz) throws IOException {
        connection.setRequestMethod("GET");
        String result = result(200);
        T object = transform(clazz, result);
        return Response.<T>builder().
                message(object).statusCode(connection.getResponseCode()).build();
    }

    private String result(int statusCode) throws IOException {
        int responseCode = connection.getResponseCode();
        if (responseCode == statusCode) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        }
        return null;
    }

    private <T> T transform(Class<T> clazz, String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T object = mapper.readValue(jsonString, clazz);
        return object;
    }

}
