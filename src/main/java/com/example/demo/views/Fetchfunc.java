package com.example.demo.views;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class Fetchfunc {

    public static String generalFetch(String endpoint, String params, String requestBody, String requestMethod) throws IOException {
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
