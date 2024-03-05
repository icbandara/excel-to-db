package com.example.demo.controller;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.service.ExcelService;
import com.example.demo.service.MyApiService;
import okhttp3.*;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : Isuru Bandara <icbandara@controlunion.com>
 * @since : 01/11/2023
 **/

@RestController
@RequestMapping("/api/v1/createProcUnit")
public class ApiController {

    @Autowired
    private ExcelService apiService;

    @Autowired
    private MyApiService myApiService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody MultipartFile file, @RequestParam String token) {
        apiService.convert(file, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseBody test(@RequestParam String token, @RequestParam String user) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        okhttp3.RequestBody body = okhttp3.RequestBody.create("", mediaType);
        Request request = new Request.Builder()
                .url("https://farmer-back.azurewebsites.net/api/v1/project/allTable?page=0&size=250")
                .get()
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6ImtXYmthYTZxczh3c1RuQndpaU5ZT2hIYm5BdyIsImtpZCI6ImtXYmthYTZxczh3c1RuQndpaU5ZT2hIYm5BdyJ9.eyJhdWQiOiJhcGk6Ly9hZjBhNjk5NS0wMDM4LTQ0NjUtOTk3YS04ZmQ5ODhkNmVlNDMiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC80ZmMyZjNhYS0zMWM0LTRkY2ItYjcxOS1jNmMxNjM5M2U5ZDMvIiwiaWF0IjoxNzA4NTkzMDM1LCJuYmYiOjE3MDg1OTMwMzUsImV4cCI6MTcwODU5ODQ2MSwiYWNyIjoiMSIsImFpbyI6IkFiUUFTLzhXQUFBQXJTQmlKcU1vQUVIVm1SZ1ZMclp5MDljUkVzdWZhOSs2OEFhR2xRV280aDRsN1p6TlVHK0ppd3YzN2NiUHJkaGdhcmhjMHhqMjF2NmlVZEQ2R3E2Mk8ySzh5bk51RWFBVlBscXM2dnM4ZmdhZFEvb1dUVkpaR2haeExzUHQvdnZ2eGsxSSs3ZVU2d2JiQlNsWkFTbHNpbW4ySkUxR1o2V2NjMUpmOGt3NWR3QTA0WW5JRmRMZXZDOEpRK21ya0JlQkFHeHRoVExQenhnRTVRZFVOSGZrdWhmSitGaC84WHJoV0FTbUl4QUdKSmM9IiwiYW1yIjpbInJzYSIsIm1mYSJdLCJhcHBpZCI6ImFmMGE2OTk1LTAwMzgtNDQ2NS05OTdhLThmZDk4OGQ2ZWU0MyIsImFwcGlkYWNyIjoiMCIsImRldmljZWlkIjoiZDNjMmE1YWYtYjQyYS00NzVhLTk0ZDctY2RmZWY3YWI4OTdiIiwiZmFtaWx5X25hbWUiOiJCYW5kYXJhIiwiZ2l2ZW5fbmFtZSI6IklzdXJ1IENoYXRodXJhbmdhIiwiaXBhZGRyIjoiMjQwMjpkMDAwOjgxMTA6MTUyZDo5NDY4OjY0MDc6MzhmMTo4MGNhIiwibmFtZSI6IklzdXJ1IENoYXRodXJhbmdhIEJhbmRhcmEiLCJvaWQiOiIzMzIyZTk1Mi05ZWJjLTQyNjQtYWEzZC1mMDAzYTJiODRlYTIiLCJvbnByZW1fc2lkIjoiUy0xLTUtMjEtMjc0Njc4NzYwOC0yODY2MjUyMDk1LTIzMzQ3ODAyNTctOTU4NTciLCJyaCI6IjAuQVhRQXF2UENUOFF4eTAyM0djYkJZNVBwMDVWcENxODRBR1ZFbVhxUDJZalc3a1BpQUlnLiIsInNjcCI6ImFwcCIsInN1YiI6Ik9OdFh4V0c0WE96dEhjWkN0NHdQZWJjQ0J6T0tXYU1rbXZjMURDOVZLVUUiLCJ0aWQiOiI0ZmMyZjNhYS0zMWM0LTRkY2ItYjcxOS1jNmMxNjM5M2U5ZDMiLCJ1bmlxdWVfbmFtZSI6ImljYmFuZGFyYUBwY3Vncm91cC5jb20iLCJ1cG4iOiJpY2JhbmRhcmFAcGN1Z3JvdXAuY29tIiwidXRpIjoiR0l1cXluS3d6VW1IUGRCMVlseDlBQSIsInZlciI6IjEuMCJ9.M2iIUhIS1xM0DTlaDe4CfaI-ApyIupMvamb2XDPB6RP0Ex34sgBrK-5MVZ9b19tJfmtjnN_ZSw9nWywBGjGBPnk4UtaekrKZb0kC9y0vSFXivPNRClZ2aOx3td9sjMy4yiyZPiaZYWd3wpGHNXIdfaHtaodgiT9AXWBysG4TW07aGCsOk7M5dFkUFbUQNyfhK84ljxGlWneDsncXSDNH4FakVBu4cdut8hqUcX4uxDu9eMrNbQLbv5ukvifN4hoThQP5k4id_jNIquS47zVmeNkoZ6rfh-kQt81ggLLQndD97bXdAkwqAHjBUB0mSxqOCG2CFvX28jWwVokMBl3GGA")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response);
        return response.body();
    }
}
