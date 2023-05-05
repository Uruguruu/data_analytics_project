package com.example.demo.views;

import com.example.demo.views.MainviewFunc;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private TextField idField;
    private Button fetchButton;
    private Button createButton;
    private Button deleteButton;
    private Grid<String> grid;
    private MainviewFunc mainviewFunc;

    public MainView() {
        mainviewFunc = new MainviewFunc();
        initComponents();
        configureLayout();
        attachListeners();
    }

    private void initComponents() {
        idField = new TextField("List ID");
        fetchButton = new Button("Fetch Products");
        createButton = new Button("Create List");
        deleteButton = new Button("Delete List");
        grid = new Grid<>(String.class);
    }

    private void configureLayout() {
        grid.setColumns("productName", "amount");
        add(idField, fetchButton, createButton, deleteButton, grid);
    }

    private void attachListeners() {
        fetchButton.addClickListener(event -> fetchProducts());
        createButton.addClickListener(event -> createList());
        deleteButton.addClickListener(event -> deleteList());
    }

    private void fetchProducts() {
        String id = idField.getValue();
        String response = mainviewFunc.fetchProducts(id);
        // TODO: Parse response and update grid with fetched data
    }

    private void createList() {
        String name = "New List"; // Replace this with the actual name for the new list
        String response = mainviewFunc.createList(name);
        // TODO: Update the UI based on the response
    }

    private void deleteList() {
        String id = idField.getValue();
        String response = mainviewFunc.deleteList(id);
        // TODO: Update the UI based on the response
    }
}
