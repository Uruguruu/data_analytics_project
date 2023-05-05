package com.example.demo.views;

import com.example.demo.views.Fetchfunc;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import elemental.json.JsonObject;
import java.util.List;


@Route("")
public class Mainview extends VerticalLayout {
    private TextField listNameField;
    private Button addListButton;
    private VerticalLayout listContainer;
    private int listCounter = 0;

    public Mainview() {
        listNameField = new TextField("List name");
        addListButton = new Button("Add list");
        addListButton.addClickListener(e -> {
            int listID = Fetchfunc.addList(listNameField.getValue());
            displayList(listID);
        });
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

        loadLists();
    }

    private void loadLists() {
        List<Integer> listIds = Fetchfunc.getListIds();
        for (Integer listID : listIds) {
            displayList(listID);
        }
    }

    private void displayList(int listID) {
        List<JsonObject> products = Fetchfunc.viewList(listID);
        VerticalLayout listLayout = new VerticalLayout();
        Label listNameLabel = new Label(products.get(0).getString("listname"));
        listLayout.add(listNameLabel);

        for (JsonObject product : products) {
            Label productLabel = new Label(product.getString("Name") + " - ID: " + product.getNumber("id") + " - Amount: " + product.getNumber("amount"));
            listLayout.add(productLabel);
        }

        Button deleteButton = new Button("Delete List");
        deleteButton.addClickListener(e -> {
            Fetchfunc.deleteList(listID);
            listContainer.remove(listLayout);
        });

        listLayout.add(deleteButton);
        listContainer.add(listLayout);
    }

}