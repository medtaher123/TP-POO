package ui;

import Managers.PageManager;
import adapters.Proxy;
import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import helpers.Action;
import helpers.ActionMenu;
import helpers.ConsoleHelper;
import models.Product;
import services.ProductsService;

import java.util.List;

public class WishlistPage extends Page {
//TODO doc: wishtlist is a list of products that the user wants to buy in the future

    @Override
        protected String getTitle() {
            return "Wishlist";
        }

        @Override
        public int getAccessLevel() {
            return AccessLevel.CUSTOMER;
        }

        @Override
        protected void printContent() {
            List<Proxy<Product>> wishlist = AuthenticationSystem.getActiveUser().getWishList();
            if (wishlist.isEmpty()) {
                ConsoleHelper.printWarning("--- Wishlist is empty ---");
                ConsoleHelper.printNewLines(2);
                new ActionMenu(null, ActionMenu.PREV_PAGE_ACTION, "").execute();
            }
            for (int i = 0; i < wishlist.size(); i++) {
                Product p = wishlist.get(i).getObject(ProductsService::getProductById);
                System.out.println((i + 1) + ". " +
                        p.getShortDisplay() + " | "
                        + p.getPrice());
            }
            ConsoleHelper.printNewLines(2);
            new ActionMenu(actions, ActionMenu.PREV_PAGE_ACTION).execute();


        }

    private Action[] actions = {
            new Action() {
                @Override
                public String getDescription() {
                    return "Remove Product";
                }

                @Override
                public Object execute() {
                    int index = ConsoleHelper.readIntInRange("Enter product number to remove: ", 1, AuthenticationSystem.getActiveUser().getWishList().size());
                    AuthenticationSystem.getActiveUser().removeFromWishlist(index-1);
                    AuthenticationSystem.updateActiveUser();
                    ConsoleHelper.printSuccess("Product removed successfully.");
                    PageManager.redirect(new WishlistPage());
                    return null;
                }
            }
    };
}
