package com.example.demo.views;

import jakarta.servlet.http.HttpServletRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProductViewFetchFunc {
    private String[][] getListItems() {
        HttpServletRequest request = ...
        String id = request.getParameter("id");
        bigFetch("/products","id="+);
        return new String[][]{{"Volvo", "BMW", "Ford", "Mazda"}};
    }
    private String bigFetch(String endpoint, String params, String requestBody, String requestMethod) throws IOException {
        URL some_url = new URL("http://localhost:8080"+endpoint+"?"+params);
        HttpURLConnection con = (HttpURLConnection) some_url.openConnection();
        con.setRequestMethod(requestMethod);
        con.setDoOutput(true);

        // Write the request body to the output stream
        OutputStream os = con.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(requestBody);
        writer.flush();
        writer.close();

        // Read the response from the server
        InputStream is = con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        con.disconnect();

        return response.toString();
    }

}
