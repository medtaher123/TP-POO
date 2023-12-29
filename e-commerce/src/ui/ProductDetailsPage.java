package ui;

import Managers.CartManager;
import Managers.PageManager;
import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import exceptions.ProductOutOfStockException;
import helpers.Action;
import helpers.ActionMenu;
import helpers.ConsoleColors;
import helpers.ConsoleHelper;
import models.Product;
import services.ProductsService;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsPage extends Page {
    Product product;

    public ProductDetailsPage(Product product) {
        this.product = product;
    }

    @Override
    protected String getTitle() {
        return product.getShortDisplay();
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.ALL_EXCEPT_GUEST;
    }

    @Override
    protected void printContent() {
        System.out.print(product.getLongDisplay());
        printStockMessage();
        renderMenu();
    }

    //this method writes a message to the user to tell him that the product is out of stock, or to tell him how many are left if <10
    private void printStockMessage() {
        if (product.getStock() <= 0) {
            ConsoleHelper.printNewLines(1);
            ConsoleHelper.printError("This product is out of stock!");
        } else if (product.getStock() < 10) {
            ConsoleHelper.printNewLines(1);
            ConsoleHelper.printWarning("Only " + product.getStock() + " left in stock!\n");
        }
    }

    private void renderMenu() {
        ConsoleHelper.printNewLines(2);
        new ActionMenu(buildActions(), ActionMenu.PREV_PAGE_ACTION).execute();
    }

    private Action[] buildActions() {
        List<Action> actions = new ArrayList<>();

        if (product.getStock() > 0)
            actions.add(addToCartAction);

        actions.add(goToCartAction);
        actions.add(reviewAction);
        actions.add(addToWishlistAction);

        return actions.toArray(new Action[0]);
    }

    private final Action addToCartAction = new Action() {
        @Override
        public String getDescription() {
            return "Add to cart";
        }

        @Override
        public Object execute() {
            try {
                CartManager.addProduct(product, ConsoleHelper.readPositiveInt("Quantity: "));
            } catch (ProductOutOfStockException e) {
                e.printToUser();
            }
            renderMenu();
            return null;
        }
    };
    private final Action goToCartAction = new Action() {
        @Override
        public String getDescription() {
            return "Go to cart" + ConsoleColors.getColoredString(CartManager.containsProduct(product) ? " (x" + CartManager.getQuantity(product) + ")" : "", NUMBER_TAG_COLOR);
        }

        @Override
        public Object execute() {
            PageManager.callPage(new CartPage());
            return null;
        }
    };
    private final Action reviewAction = new Action() {
        @Override
        public String getDescription() {
            return "Add review";
        }

        @Override
        public Object execute() {
            PageManager.callPage(new ProductReviewPage(product));
            return null;
        }
    };
    Action addToWishlistAction = new Action() {
        @Override
        public String getDescription() {
            return  AuthenticationSystem.getActiveUser().isInWishlist(product)? "Remove from wishlist" : "Add to wishlist";
        }

        @Override
        public Object execute() {
            if (AuthenticationSystem.getActiveUser().isInWishlist(product)){
                AuthenticationSystem.getActiveUser().removeFromWishlist(product);
                ConsoleHelper.printSuccess("Product removed from wishlist.");
            } else {
                AuthenticationSystem.getActiveUser().addToWishlist(product);
                ConsoleHelper.printSuccess("Product added to wishlist.");
            }
            AuthenticationSystem.updateActiveUser();
            PageManager.redirect(new ProductDetailsPage(product));
            return null;
        }
    };

    @Override
    public void reset() {
        super.reset();
        product = ProductsService.getProductById(product.getId());
    }
}
