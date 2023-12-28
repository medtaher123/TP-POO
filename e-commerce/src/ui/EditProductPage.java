package ui;

import Managers.PageManager;
import authentication.AccessLevel;
import helpers.Action;
import helpers.ActionMenu;
import helpers.ConsoleHelper;
import models.Product;
import services.ProductsService;

//TODO: not tested
public class EditProductPage extends Page {
    private Product product;

    public EditProductPage(Product product) {
        this.product = product;
    }

    @Override
    protected String getTitle() {
        return "Edit Product";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.ADMIN;
    }

    @Override
    protected void printContent() {
        System.out.print(product.getLongDisplay());
        printStockMessage();
        ConsoleHelper.printNewLines(2);
        new ActionMenu(actions, ActionMenu.PREV_PAGE_ACTION).execute();
    }

    private void printStockMessage() {
        if (product.getStock() <= 0) {
            ConsoleHelper.printError("This product is out of stock!");
        } else if (product.getStock() < 10) {
            ConsoleHelper.printWarning("Only " + product.getStock() + " left in stock!");
        } else{
            ConsoleHelper.printNewLines(1);
            ConsoleHelper.printHint(product.getStock() + " left in stock.");
        }
    }

    private Action[] actions = {
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Title";
                }

                @Override
                public Object execute() {
                    product.setTitle(ConsoleHelper.input("Title"));
                    product = ProductsService.updateProduct(product);
                    ConsoleHelper.printSuccess("Product updated successfully.");
                    PageManager.redirect(new EditProductPage(product));
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Brand";
                }

                @Override
                public Object execute() {
                    product.setBrand(ConsoleHelper.input("Brand"));
                    product = ProductsService.updateProduct(product);
                    ConsoleHelper.printSuccess("Product updated successfully.");
                    PageManager.redirect(new EditProductPage(product));
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Price";
                }

                @Override
                public Object execute() {
                    product.setPrice(ConsoleHelper.readPositiveDouble("Price: "));
                    product = ProductsService.updateProduct(product);
                    ConsoleHelper.printSuccess("Product updated successfully.");
                    PageManager.redirect(new EditProductPage(product));
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Description";
                }

                @Override
                public Object execute() {
                    product.setDescription(ConsoleHelper.input("Description"));
                    product = ProductsService.updateProduct(product);
                    ConsoleHelper.printSuccess("Product updated successfully.");
                    PageManager.redirect(new EditProductPage(product));
                    return null;
                }
            }
    };
}
