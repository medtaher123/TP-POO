package ui;

import Managers.PageManager;
import Managers.ProductsManager;
import authentication.AccessLevel;
import helpers.*;
import models.Product;
import services.ProductsService;


public class ManageProductsPage extends Page {

    @Override
    protected String getTitle() {
        return "Manage Products";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.ADMIN;
    }

    @Override
    protected void printContent() {
        Product[] products = ProductsService.getAllProducts();

        if (products.length == 0) {
            System.out.println("---no products---");
        }
        for (int i = 0; i < products.length; i++) {
            Product p = products[i];
            System.out.println((i + 1) + ". " +
                    p.getShortDisplay() + " | "
                    + DataFormatter.formatPrice(p.getPrice())
                    + ConsoleColors.getColoredString(" (" + p.getStock() + " in stock)", ConsoleColors.DEFAULT_DIM)
                    + (p.getStock() == 0 ? ConsoleColors.getColoredString(" (out of stock)", ConsoleHelper.WARNING_COLOR) : ""));
        }

        ConsoleHelper.printNewLines(2);
        new ActionMenu(actions, ActionMenu.PREV_PAGE_ACTION).execute();
    }

    private Action[] actions = {
            new Action() {
                @Override
                public String getDescription() {
                    return "Add Product";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new NewProductPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Product";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new EditProductPage(readProduct()));
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Delete Product";
                }

                @Override
                public Object execute() {
                    Product product = readProduct();
                    if(ConsoleHelper.readBoolean(ConsoleColors.getColoredString("Are you sure you want to delete " + product.getTitle() + "?",ConsoleHelper.WARNING_COLOR))){
                        ProductsService.DeleteProductById(product.getId());
                        ConsoleHelper.printSuccess("Product deleted successfully.");
                        if(product.getSupportsSerialNumbers() && ConsoleHelper.readBoolean("Do you want to delete all serial numbers?")){
                            ProductsManager.deleteAllSerialNumbers(product.getId());
                            ConsoleHelper.printSuccess("All serial numbers deleted successfully.");
                        }

                    }else {
                        ConsoleHelper.printWarning("Product not deleted.");
                    }
                    PageManager.redirect(new ManageProductsPage());
                    return null;
                }
            }
    };

    private Product readProduct() {
        while(true){
            String id = ConsoleHelper.input("Product id");
            Product product = ProductsService.getProductById(id);
            if(product != null){
                return product;
            }
            ConsoleHelper.printError("Product not found, try again.");
        }
    }
}
