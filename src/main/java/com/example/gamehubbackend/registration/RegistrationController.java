package com.example.gamehubbackend.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @Value("${frontend.url}")
    private String frontend_url;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path="confirm",produces = MediaType.TEXT_HTML_VALUE)
    public String confirm(@RequestParam("token") String token) {
        String result = registrationService.confirmToken(token);
        if (result=="confirmed"){
            return "<html>\n" + "<header><title>Game Hub Registration Confirm</title></header>\n" +
                    "<body>\n" +
                    "<h1>Congrats ! You have successfully confirm your accounct!</h1>\n"+
                    "<h2>Click The Following Button to return to GameHub</h2>\n"+
                    "<a href='" + frontend_url + "'>Click me</a>\n"+
                    "</body>\n" + "</html>";
        }
        return "<html>\n" + "<header><title>Game Hub Registration Confirm</title></header>\n" +
                "<body>\n" +
                "<h1>Confirmation Fail</h1>\n"+
                "<h2>Reason :</h2>\n"+
                "<p>"+result+"</p>\n"+
                "</body>\n" + "</html>";
    }
}
