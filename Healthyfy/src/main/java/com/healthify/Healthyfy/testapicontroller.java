package com.healthify.Healthyfy;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class testapicontroller {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public testapicontroller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper(); // Initialize ObjectMapper for JSON conversion
    }

    @PostMapping("/generatetestContent")
    public ResponseEntity<?> generateContent(@RequestBody String requestBody) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyABKg6bFvs8LVxCt5eX9qmNUs2LNAPPFcw";
        
        try {
            // Convert the incoming JSON string to an Answer object
            Answer request = objectMapper.readValue(requestBody, Answer.class);

            // Create HTTP headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create the request entity
            HttpEntity<Answer> entity = new HttpEntity<>(request, headers);

            // Make the API call
            ResponseEntity<ContentResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, ContentResponse.class);

            // Return the full response object in JSON format
            if (response.getBody() != null) {
                return ResponseEntity.ok(response.getBody());  // Return the full ContentResponse object
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No content available.");
            }
        } catch (JsonProcessingException e) {
            // Handle JSON parsing error
            return ResponseEntity.badRequest().body("Invalid JSON format.");
        } catch (Exception e) {
            // Log the error (consider using a logging framework)
            System.err.println("Error during API call: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while generating content.");
        }
    }
}
