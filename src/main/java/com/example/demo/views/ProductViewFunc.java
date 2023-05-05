package com.example.demo.views;

import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProductViewFunc {
    public String[][] getListItems() throws IOException {
        String id = VaadinService.getCurrentRequest().getParameter("id");
        System.out.println("HERE ID: " + id);
        String inputString = Fetchfunc.generalFetch("/products", "id=" + id, "", "GET");
        return parse2DString(inputString);
    }




    public static String[][] parse2DString(String input) {
        // Split the input string into rows
        String[] rows = input.split("\n");

        // Determine the number of columns based on the first row
        int numColumns = rows[0].split(",").length;

        // Create the 2D array
        String[][] output = new String[rows.length][numColumns];

        // Populate the 2D array
        for (int i = 0; i < rows.length; i++) {
            String[] values = rows[i].split(",");
            for (int j = 0; j < numColumns; j++) {
                output[i][j] = values[j];
            }
        }

        return output;
    }

}
