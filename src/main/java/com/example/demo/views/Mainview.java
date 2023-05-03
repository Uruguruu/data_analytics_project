package com.example.demo.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


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
    }

    private void addNewList() {
        String listName = listNameField.getValue();
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

        VerticalLayout list = new VerticalLayout(listHeader, listContent);
        list.setWidth("300px");
        list.getStyle().set("border", "1px solid gray");
        list.getStyle().set("border-radius", "5px");
        list.getStyle().set("overflow", "hidden");
        list.getStyle().set("box-shadow", "3px 3px 3px gray");
        listContainer.add(list);
        listCounter++;
    }
}

