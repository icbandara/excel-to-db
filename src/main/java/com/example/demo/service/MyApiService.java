package com.example.demo.service;

import com.example.demo.dto.ProjectDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Isuru Bandara <icbandara@controlunion.com>
 * @since : 01/11/2023
 **/


@Service
public class MyApiService {


    public Response sendPost(HashMap<Integer, ProjectDTO> map, String token) throws Exception {
        for (Map.Entry<Integer, ProjectDTO> ll : map.entrySet()) {
            System.out.println("data adding to proID : "+ll.getKey());
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, convertObjectToJson(ll.getValue()));
            Request request = new Request.Builder()
                    .url("https://farmer-back.azurewebsites.net/api/v1/project")
                    .method("PUT", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.code());
        }

        return null;
    }

    public static String convertObjectToJson(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
