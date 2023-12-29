package ui;

import Managers.CartManager;
import Managers.PageManager;
import Managers.SettingsManager;
import authentication.AccessLevel;
import exceptions.ProductOutOfStockException;
import helpers.Action;
import helpers.ActionMenu;
import helpers.ConsoleHelper;
import helpers.DataFormatter;
import models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends Page {
//TODO doc: shipping methods (normal delivery, free delivery for purchases over a fixed amount and extra fees for express delivery)

    @Override
    protected String getTitle() {
        return "Cart";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.CUSTOMER;
    }

    @Override
    protected void printContent() {
        int i = 0;
        if (CartManager.cartIsEmpty()) {
            ConsoleHelper.printWarning("--- Cart is empty ---\n\n");
            new ActionMenu(null, ActionMenu.PREV_PAGE_ACTION, "").execute();
            return;
        }

        /*
        for (Map.Entry<Product, Integer> entry : CartManager.getCart().entrySet()) {
            i++;
            System.out.println(i+": "+entry.getKey().getShortDisplay() + " | " + DataFormatter.formatPrice(entry.getKey().getPrice()) + " x" + entry.getValue() + " = " + DataFormatter.formatPrice(entry.getKey().getPrice() * entry.getValue()));
        }
        double cartCost = CartManager.getTotalCost();
        double shippingCost = cartCost> SettingsManager.getSettings().getStartingCostForFreeDelivery() ?0:SettingsManager.getSettings().getDeliveryFees();
        double expressDeliveryFees = CartManager.isExpressDelivery()?SettingsManager.getSettings().getExpressDeliveryFees():0;
        System.out.println("Shipping cost: " + DataFormatter.formatPrice(shippingCost) + " (free for purchases over " + DataFormatter.formatPrice(SettingsManager.getSettings().getStartingCostForFreeDelivery()) + ")");
        if(CartManager.isExpressDelivery())
            System.out.println("Express delivery: " + DataFormatter.formatPrice(expressDeliveryFees));
        System.out.println("Total: "+ DataFormatter.formatPrice(cartCost+shippingCost + expressDeliveryFees));
        */
        CartManager.printCartTableLayout();
        ConsoleHelper.printNewLines(2);
        new ActionMenu(buildActions(), ActionMenu.PREV_PAGE_ACTION).execute();

    }

    private Action[] buildActions() {
        List<Action> actions = new ArrayList<>();

        if (!CartManager.cartIsEmpty()) {
            actions.add(addQuantityToProductAction);
            actions.add(removeQuantityFromProductAction);
            actions.add(removeProductAction);
        }
        actions.add(toggleExpressDeliveryAction);
        if (!CartManager.cartIsEmpty())
            actions.add(placeOrderAction);
        return actions.toArray(new Action[0]);
    }

    Action addQuantityToProductAction = new Action() {
        @Override
        public String getDescription() {
            return "Add quantity to product";
        }

        @Override
        public Object execute() {
            Product p = readProduct();
            int q = ConsoleHelper.readPositiveInt("Quantity: ");
            try {
                CartManager.addProduct(p, q);
            } catch (ProductOutOfStockException e) {
                e.printToUser();
            }
            PageManager.redirect(new CartPage());
            return null;
        }
    };
    Action removeQuantityFromProductAction = new Action() {
        @Override
        public String getDescription() {
            return "Remove quantity from product";
        }

        @Override
        public Object execute() {
            Product p = readProduct();
            int q = ConsoleHelper.readPositiveInt("Quantity: ");
            CartManager.removeQuantity(p, q);
            PageManager.redirect(new CartPage());
            return null;
        }
    };
    Action removeProductAction = new Action() {
        @Override
        public String getDescription() {
            return "Remove product";
        }

        @Override
        public Object execute() {
            Product p = readProduct();
            CartManager.removeProduct(p);
            PageManager.redirect(new CartPage());
            return null;
        }
    };
    Action toggleExpressDeliveryAction = new Action() {
        @Override
        public String getDescription() {
            return (CartManager.isExpressDelivery() ? "Remove express delivery" : "Express delivery") + "(additional fees: " + DataFormatter.formatPrice(SettingsManager.getSettings().getExpressDeliveryFees()) + ")";
        }

        @Override
        public Object execute() {
            CartManager.setExpressDelivery(!CartManager.isExpressDelivery());
            PageManager.redirect(new CartPage());
            return null;
        }
    };

    Action placeOrderAction = new Action() {
        @Override
        public String getDescription() {
            return "Place order";
        }

        @Override
        public Object execute() {
            PageManager.callPage(new PaymentPage());
            return null;
        }
    };

    private Product readProduct() {
        int i;
        int maxI = CartManager.getCart().size();
        while (true) {
            i = ConsoleHelper.readPositiveInt("Product Index: ");
            if (i > 0 && i <= maxI) {
                break;
            }
            System.out.println("Invalid index");
        }
        return (Product) CartManager.getCart().keySet().toArray()[i - 1];
    }


}
