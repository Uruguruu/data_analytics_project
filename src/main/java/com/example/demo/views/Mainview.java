package com.example.demo.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField; // Fügen Sie diesen Import hinzu
import com.vaadin.flow.router.Route;
import elemental.json.Json;
import elemental.json.JsonObject;
import org.springframework.web.client.RestTemplate;

@Route("")
public class Mainview extends VerticalLayout {
    private TextField listNameField;
    private Button addListButton;
    private VerticalLayout listContainer;
    private int listCounter = 0;


    public Mainview() {
        listNameField = new TextField("List name");
        addListButton = new Button("Add list");
        addListButton.addClickListener(e -> addNewList());
        HorizontalLayout header = new HorizontalLayout(listNameField, addListButton);
        header.expand(listNameField);
        header.setWidth("100%");
        header.setJustifyContentMode(JustifyContentMode.END);

        listContainer = new VerticalLayout();
        add(header, listContainer);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
        setSpacing(false);
        setMargin(false);
        setSizeFull();

        addListButton.addClickListener(e -> addNewList(listNameField.getValue())); // Ändern Sie diesen Teil
    }

    private void loadLists() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "/products";
        JsonObject[] lists = restTemplate.getForObject(url, JsonObject[].class);

        if (lists != null) {
            for (JsonObject list : lists) {
                String listName = list.getString("name");
                addNewList(listName);
            }
        }
    }

    private void createList(String listName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "/products/?id={listID}";
        JsonObject newList = Json.createObject();
        newList.put("name", listName);
        restTemplate.postForLocation(url, newList);
    }

    private void deleteList(int listId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "/products?id={listID}";
        restTemplate.delete(url, listId);
    }

    private void addNewList(String listName) {
        if (listName.isBlank()) {
            listNameField.setErrorMessage("List name cannot be blank");
            return;
        }
        listNameField.setErrorMessage(null);

        HorizontalLayout listHeader = new HorizontalLayout(new Label(listName), new Button("Delete"));
        listHeader.setWidth("100%");
        listHeader.setJustifyContentMode(JustifyContentMode.END);
        listHeader.setAlignItems(Alignment.CENTER);
        listHeader.getStyle().set("background-color", "lightgray");

        VerticalLayout listContent = new VerticalLayout();
        listContent.setWidth("100%");
        listContent.getStyle().set("border", "1px solid lightgray");
        listContent.setPadding(false);
        listContent.setMargin(false);

        Button deleteButton = new Button("Delete");
        deleteButton.addClickListener(e -> {
            deleteList(listCounter);
            listContainer.remove(list);
        });

        listHeader.add(deleteButton);

        VerticalLayout list = new VerticalLayout(listHeader, listContent);
        list.setWidth("300px");
        list.getStyle().set("border", "1px solid gray");
        list.getStyle().set("border-radius", "5px");
        list.getStyle().set("overflow", "hidden");
        list.getStyle().set("box-shadow", "3px 3px 3px gray");
        listContainer.add(list);
        listCounter++;

        createList(listName);
    }


}