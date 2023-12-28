package ui;

import Managers.InventoryManager;
import Managers.PageManager;
import authentication.AccessLevel;
import helpers.Action;
import helpers.ActionMenu;
import helpers.ConsoleHelper;
import models.Product;
import services.ProductsService;

public class RegisterStockArrivalPage extends Page {
    @Override
    protected String getTitle() {
        return "Register Stock Arrival";
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
        Action[] actions = mapActions(products);

        Product product = (Product) new ActionMenu(actions, ActionMenu.PREV_PAGE_ACTION , "choose a product!:").execute().getSecond();
        if(product==null)return;
        ConsoleHelper.printNewLines(2);
        System.out.println("product: "+product.getShortDisplay());
        int quantity = ConsoleHelper.readPositiveInt("Quantity: ");
        if(quantity>0){
            InventoryManager.addStock(product,quantity);
        }
        ConsoleHelper.printNewLines(2);
        new ActionMenu(registerAgainAction,ActionMenu.PREV_PAGE_ACTION,"").execute();
    }

    private Action[] mapActions(Product[] products) {
        Action[] actions = new Action[products.length];
        for (int i = 0; i < products.length; i++) {
            Product p = products[i];
            actions[i] = new Action() {
                @Override
                public String getDescription() {
                    return "(id = "+ConsoleHelper.getPaddedString(p.getId(),3) + ") | " + p.getShortDisplay();// + " | " + DataFormatter.formatPrice(p.getPrice());
                }

                @Override
                public Object execute() {
                    return p;
                }
            };
        }
        return actions;
    }

    Action[] registerAgainAction = {new Action() {
        @Override
        public String getDescription() {
            return "Register another stock arrival";
        }

        @Override
        public Object execute() {
            PageManager.redirect(new RegisterStockArrivalPage());
            return null;
        }
    }};
}
