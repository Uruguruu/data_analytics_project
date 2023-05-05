package com.example.demo.views;

import jakarta.servlet.http.HttpServletRequest;
import com.example.demo.views.Fetchfunc;
public class ProductViewFunc {
    private String[][] getListItems() {
        HttpServletRequest request = ...
        String id = request.getParameter("id");
        Fetchfunc.generalFetch("/products","id="+);
        return new String[][]{{"Volvo", "BMW", "Ford", "Mazda"}};
    }
}
