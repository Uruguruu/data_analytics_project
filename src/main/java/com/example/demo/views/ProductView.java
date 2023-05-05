package com.example.demo.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.views.ProductViewFetchFunc;

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
        });
        menu.addItem("Delete", event -> {
        });

        add(grid);
    }

    private List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Milk", 2));
        items.add(new Item("Bread", 1));
        items.add(new Item("Eggs", 12));
        items.add(new Item("Apples", 5));
        items.add(new Item("Chicken breasts", 4));
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
