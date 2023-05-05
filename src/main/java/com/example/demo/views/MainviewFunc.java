package com.example.demo.views;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainviewFunc {

    public String fetchProducts(String id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        if (id == null || id.isEmpty()) {
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/products"))
                    .GET()
                    .build();
        } else {
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/products?id=" + id))
                    .GET()
                    .build();
        }

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error fetching products: " + e.getMessage();
        }
    }

    public String createList(String name) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/products"))
                .POST(HttpRequest.BodyPublishers.ofString(name))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error creating list: " + e.getMessage();
        }
    }

    public String deleteList(String id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/proucts?id=" + id))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error deleting list: " + e.getMessage();
        }
    }
}
