package ui;

import Managers.CartManager;
import Managers.OrderManager;
import Managers.PageManager;
import Managers.PaymentProcessor;
import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import helpers.*;

public class PaymentPage extends Page {
    //TODO: add to doc: explain payment page
    //TODO doc: no real input validation here. This is just a dummy page to simulate a payment process.
    @Override
    protected String getTitle() {
        return "Payment";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.CUSTOMER;
    }

    @Override
    protected void printContent() {
        System.out.println("Please enter your payment details:");

        String creditCardNumber = ConsoleHelper.input("Credit card number");
        String expirationDate = ConsoleHelper.input("Expiration date (MM/YYYY)");
        String cvv = ConsoleHelper.input("CVV/CVC");
        String billingAddress = ConsoleHelper.input("Billing address");

        boolean captchaPassed = CaptchaTest.run();

        if (captchaPassed) {
            PaymentProcessor paymentProcessor = new PaymentProcessor(); //TODO: add purchase confirmation
            boolean purchaseConfirmed = confirmPurchase();
            if(purchaseConfirmed) {
                boolean paymentSuccess = paymentProcessor.processPayment(creditCardNumber, expirationDate, cvv, billingAddress);
                if (paymentSuccess) {
                    OrderManager.createOrder();
                    ConsoleHelper.printSuccess("Payment successful. Thank you for your purchase, " + AuthenticationSystem.getActiveUser().getFirstName() + "!");
                    ConsoleHelper.printNewLines(2);
                    new ActionMenu(successActions, ActionMenu.BACK_TO_HOMEPAGE_ACTION, "").execute();
                    return;
                } else {
                    ConsoleHelper.printInColor("Payment failed. Please check your payment information.\n",ConsoleColors.RED_BRIGHT); //not reachable code
                }
            }
            else {
                ConsoleHelper.printWarning("Payment cancelled.");
            }
        } else {
            System.out.println("Payment failed due to CAPTCHA verification failure.");
        }
        ConsoleHelper.printNewLines(2);
        new ActionMenu(failureActions, ActionMenu.BACK_TO_HOMEPAGE_ACTION, "").execute();
    }

    //this method redisplay the cart content and the total price and asks the user to confirm the purchase
    private boolean confirmPurchase(){
        /*if(CartManager.cartIsEmpty()){
            System.out.println("--- Cart is empty ---\n");
            return false;
        }
        ConsoleHelper.printNewLines(2);
        System.out.println("--- Cart content ---\n");
        int i=0;
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
        ConsoleHelper.printNewLines(2);*/
        CartManager.printCartTableLayout();
        ConsoleHelper.printNewLines(2);
        return ConsoleHelper.readBoolean("Confirm purchase?");
    }

    private final Action[] successActions = {
            new Action() {
                @Override
                public String getDescription() {
                    return "Back to cart";
                }

                @Override
                public Object execute() {
                    PageManager.prevPage();
                    return null;
                }

            }
    };
    private final Action[] purshaceCancelledAction = successActions; //same as success actions for now.
    private final Action[] failureActions = {
            new Action() {
                @Override
                public String getDescription() {
                    return "Try again";
                }

                @Override
                public Object execute() {
                    PageManager.redirect(new PaymentPage());
                    return null;
                }

            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Back to cart";
                }

                @Override
                public Object execute() {
                    PageManager.prevPage();
                    return null;
                }

            }
    };
}