package com.example;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class TodoHandler implements RequestHandler<APIGatewayProxyRequestEvent, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        try {
            String body = input.getBody();  // Extract the JSON body
            TodoRequest todo = objectMapper.readValue(body, TodoRequest.class);
            System.out.println("Received request: " + body);
            return "Received: " + todo.getTitle();
        } catch (Exception e) {
            return "Error parsing request: " + e.getMessage();
        }
    }
}