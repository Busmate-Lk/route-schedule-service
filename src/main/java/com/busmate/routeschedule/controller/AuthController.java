//package com.busmate.routeschedule.controller;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    private String supabaseAuthUrl;
//
//
//    @CrossOrigin(origins = "*")
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        Map<String, String> body = new HashMap<>();
//        body.put("email", loginRequest.getEmail());
//        body.put("password", loginRequest.getPassword());
//
//        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
//
//        try {
//            HttpHeaders customHeaders = new HttpHeaders();
//            customHeaders.setContentType(MediaType.APPLICATION_JSON);
//            customHeaders.set("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imd2eGJ6Y3hqdWVnaHZydHNmZHhjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkyODAzMjksImV4cCI6MjA2NDg1NjMyOX0.b8JmC_Q-O6K5k_5gpIiQWIDKE5jqyQxb9FgrfJzngkg");
//            customHeaders.addAll(headers);
//
//            HttpEntity<Map<String, String>> customRequest = new HttpEntity<>(body, customHeaders);
//
//            ResponseEntity<String> response = restTemplate.postForEntity(
//                    supabaseAuthUrl + "/token?grant_type=password",
//                    customRequest,
//                    String.class
//            );
//
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode jsonNode = mapper.readTree(response.getBody());
//            String accessToken = jsonNode.get("access_token").asText();
//
//            Map<String, String> responseBody = new HashMap<>();
//            responseBody.put("access_token", accessToken);
//            return ResponseEntity.ok(responseBody);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
//        }
//    }
//
//    public static class LoginRequest {
//        private String email;
//        private String password;
//
//        // Getters and setters
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//    }
//}