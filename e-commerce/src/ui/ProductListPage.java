package ui;

import Managers.PageManager;
import Managers.ProductsManager;
import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import helpers.*;
import models.Product;
import models.User;
import services.ProductsService;

import java.util.Map;

public class ProductListPage extends Page {
    private ProductFilters.SearchFilter searchFilter = null;
    private ProductFilters.AttributeFilter categoryFilter = null;

    private int minPrice = -1;
    private int maxPrice = -1;

    public ProductListPage(String search) {
        super();
    }

    public ProductListPage() {

    }

    private ProductListPage(ProductFilters.SearchFilter searchFilter, ProductFilters.AttributeFilter categoryFilter, int minPrice, int maxPrice) {
        this.searchFilter = searchFilter;
        this.categoryFilter = categoryFilter;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    protected String getTitle() {
        return "Products";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.ALL_EXCEPT_GUEST;
    }

    @Override
    protected void printContent() {
        Product[] products;
        ProductFilters filters = new ProductFilters(searchFilter, categoryFilter);
        if (filtersAreActive()) {
            System.out.println("search results for:");
            if (!filters.getFilters().isEmpty()) System.out.println(" " + filters);
            if (minPrice > 0)
                System.out.println(" Min price: " + DataFormatter.formatPrice(minPrice));//TODO doc: (without using filters mainly because filters are applied directly to the http request, but the price range filter are applied locally since json-server doesn't support >(=) and <(=) operations )
            if (maxPrice > 0) System.out.println(" Max price: " + DataFormatter.formatPrice(maxPrice));
            ConsoleHelper.printNewLines(1);

            QueryParamsBuilder params = new QueryParamsBuilder();
            params.addQueryParam(filters);
            products = ProductsService.getAllProductsInPriceRange(minPrice, maxPrice, params.toString());
        } else {
            products = ProductsService.getAllProducts();
        }
        if (products.length == 0) {
            System.out.println("---no products---");
        }
        Action[] actions = mapActions(products);

        new ActionMenu(actions, goBack_editFilter_action, "choose a product!:").execute();
    }

    private Action[] mapActions(Product[] products) {
        Action[] actions = new Action[products.length];
        for (int i = 0; i < products.length; i++) {
            Product p = products[i];
            actions[i] = new Action() {
                @Override
                public String getDescription() {
                    return p.getShortDisplay() + " | "
                            + DataFormatter.formatPrice(p.getPrice())
                            + (AuthenticationSystem.getActiveUser().getType() == User.UserType.ADMIN ?
                            ConsoleColors.getColoredString(" (" + p.getStock() + " in stock)", ConsoleColors.DEFAULT_DIM) : "")
                            + (p.getStock() == 0 ? ConsoleColors.getColoredString(" (out of stock)", ConsoleColors.PURPLE) : "");
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new ProductDetailsPage(p));
                    return null;
                }
            };
        }
        return actions;
    }

    Action goBack_editFilter_action = new Action() {
        @Override
        public String getDescription() {
            return "Edit Filter / Go Back ";
        }

        @Override
        public Object execute() {
            editFilterMenu();
            return null;
        }
    };

    private void editFilterMenu() {
        Action[] actions = {
                new Action() {
                    @Override
                    public String getDescription() {
                        return "search";
                    }

                    @Override
                    public Object execute() {
                        readSearchFilter();
                        PageManager.redirect(new ProductListPage(searchFilter, categoryFilter, minPrice, maxPrice));
                        return null;
                    }
                },
                new Action() {
                    @Override
                    public String getDescription() {
                        return "Category";
                    }

                    @Override
                    public Object execute() {
                        readCategory();
                        PageManager.redirect(new ProductListPage(searchFilter, categoryFilter, minPrice, maxPrice));
                        return null;
                    }
                },
                new Action() {
                    @Override
                    public String getDescription() {
                        return "Min price";
                    }

                    @Override
                    public Object execute() {
                        PageManager.redirect(new ProductListPage(searchFilter, categoryFilter, ConsoleHelper.readInt("Min price: ", -1), maxPrice));
                        return null;
                    }
                },
                new Action() {
                    @Override
                    public String getDescription() {
                        return "Max price";
                    }

                    @Override
                    public Object execute() {
                        PageManager.redirect(new ProductListPage(searchFilter, categoryFilter, minPrice, ConsoleHelper.readInt("Max price: ", -1)));
                        return null;
                    }
                },
                new Action() {
                    @Override
                    public String getDescription() {
                        return "clear filters";
                    }

                    @Override
                    public Object execute() {
                        PageManager.redirect(new ProductListPage());
                        return null;
                    }
                }
        };
        ConsoleHelper.printNewLines(2);
        new ActionMenu(actions, ActionMenu.PREV_PAGE_ACTION).execute();
    }

    private void readCategory() {
        Action clearFilterAction = new Action() {
            @Override
            public String getDescription() {
                return "Clear category filter";
            }

            @Override
            public Object execute() {
                categoryFilter = null;
                return null;
            }
        };
        new ActionMenu(mapCategoriesActions(), clearFilterAction, "choose a category:").execute();
    }

    private Action[] mapCategoriesActions() {
        Action[] actions = new Action[ProductsManager.categoriesClassesMap.size()];
        int i = 0;
        for (Map.Entry<String, Class<? extends Product>> entry : ProductsManager.categoriesClassesMap.entrySet()) {
            actions[i] = new Action() {
                @Override
                public String getDescription() {
                    return entry.getKey();
                }

                @Override
                public Object execute() {
                    categoryFilter = new ProductFilters.AttributeFilter("category", entry.getKey());
                    return null;
                }
            };
            i++;
        }
        return actions;
    }

    private void readSearchFilter() {
        String input = ConsoleHelper.input("search filter (press enter to remove filter)");
        if (input.length() == 0) {
            searchFilter = null;
            return;
        }
        searchFilter = new ProductFilters.SearchFilter(input);

    }

    private boolean filtersAreActive() {
        return searchFilter != null || categoryFilter != null || minPrice > 0 || maxPrice > 0;
    }
}
