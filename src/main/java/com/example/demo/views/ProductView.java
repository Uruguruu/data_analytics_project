package com.example.demo.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.router.Route;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.views.Fetchfunc.generalFetch;

@Route("list")
public class ProductView extends Div {

    public ProductView() {
        Grid<Item> grid = new Grid<>();
        grid.setAllRowsVisible(true);
        grid.setItems(getItems());

        grid.addColumn(Item::getName).setHeader("Name");
        grid.addColumn(Item::getQuantity).setHeader("Quantity");
        grid.addComponentColumn(item -> {
            MenuBar menuBar = new MenuBar();
            menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
            menuBar.addItem("Edit", event -> {});
            menuBar.addItem("Delete", event -> {});
            return menuBar;
        }).setWidth("70px").setFlexGrow(0);

        GridContextMenu<Item> menu = grid.addContextMenu();
        menu.addItem("Edit", event -> {
            System.out.println(event);

            try {
                String response = generalFetch("/product", "d", "gd", "PUT");
                // Verarbeite die Antwort
            } catch (IOException e) {
                // Behandle die IOException
            }
        });
        menu.addItem("Delete", event -> {
        });

        add(grid);
    }

    private List<Item> getItems() {
        ProductViewFunc productViewFunc = new ProductViewFunc();
        List<Item> items = new ArrayList<>();
        try {
            String[][] listItems = productViewFunc.getListItems();
            System.out.println("Number of list items: " + listItems.length);
            // Do something with the listItems array
            if (listItems == null) {
                // Handle the case where the query returns no results
                System.out.println("No items found");
                return items;
            }
            for (String[] listItem : listItems) {
                //String itemId = listItem[2]; // Get the ID value
                String itemName = listItem[0]; // Get the name value
                //String itemAmount = listItem[1]; // Get the Anzahl value
                //System.out.println(itemId);
                System.out.println(itemName);
                System.out.println("----------------");
                //System.out.println(itemAmount);
            }
        } catch (IOException e) {
            // Handle the exception
            System.err.println("An error occurred while getting the list items: " + e.getMessage());
            e.printStackTrace();
            // You may want to throw a custom exception or return a default value here
        }

        return items;
    }




    private static class Item {

        private final String name;
        private int quantity;

        Item(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
