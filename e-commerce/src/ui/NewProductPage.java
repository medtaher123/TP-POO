package ui;

import Managers.InventoryManager;
import Managers.ProductsManager;
import authentication.AccessLevel;
import helpers.Action;
import helpers.ActionMenu;
import helpers.ConsoleHelper;
import models.Product;
import models.products.*;
import services.ProductsService;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class NewProductPage extends Page {
    @Override
    protected String getTitle() {
        return "New Product";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.ADMIN;
    }

    @Override
    protected void printContent() {
        String category = (String) new ActionMenu(mapCategoriesActions(), ActionMenu.PREV_PAGE_ACTION,"").execute().getSecond();
        Class<? extends Product> categoryClass = ProductsManager.getCategoriesClass(category);
        Product product;
        try {
            product = categoryClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        ConsoleHelper.printHint("If you do not want to define a field, enter \"null\". (entering \"null\" results in null value).");
        product.setTitle(ConsoleHelper.input("Title"));
        product.setBrand(ConsoleHelper.input("Brand"));
        product.setPrice(ConsoleHelper.readPositiveDouble("Price: "));
        product.setDescription(ConsoleHelper.input("Description"));


        if (product instanceof Laptop) {
            System.out.println("Product is a laptop.");
            ((Laptop) product).setProcessor(ConsoleHelper.input("Processor"));
            ((Laptop) product).setRam(ConsoleHelper.input("RAM"));
            ((Laptop) product).setStorage(ConsoleHelper.input("Storage"));
            ((Laptop) product).setOperatingSystem(ConsoleHelper.input("Operating System"));
            ((Laptop) product).setDisplaySize(ConsoleHelper.input("Dislpay Size"));

        } else if (product instanceof HomeDecoration) {
            System.out.println("Product is for home decoration.");
            ((HomeDecoration) product).setMaterial(ConsoleHelper.input("Material"));
            ((HomeDecoration) product).setColor(ConsoleHelper.input("Color"));
            ((HomeDecoration) product).setSize(ConsoleHelper.input("Size"));
            ((HomeDecoration) product).setRoom(ConsoleHelper.input("Room"));

        } else if (product instanceof Smartphone) {
            System.out.println("Product is a smartphone.");
            ((Smartphone) product).setOs(ConsoleHelper.input("Operating System"));
            ((Smartphone) product).setScreenSize(ConsoleHelper.input("Screen Size"));
            ((Smartphone) product).setCameraResolution(ConsoleHelper.input("Camera Resolution"));

        } else if (product instanceof Skincare) {
            System.out.println("Product is for skincare.");
            ((Skincare) product).setSkinType(ConsoleHelper.input("Skin Type"));
            ((Skincare) product).setUsage(ConsoleHelper.input("Usage"));
            ((Skincare) product).setOrganic(ConsoleHelper.readBoolean("Organic"));
            ((Skincare) product).setIngredients(ConsoleHelper.input("Ingredients"));


        } else if (product instanceof Fragrance) {
            System.out.println("Product is a fragrance.");
            ((Fragrance) product).setGender(ConsoleHelper.input("Gender"));

        } else if (product instanceof Groceries) {
            System.out.println("Product is groceries.");
            ((Groceries) product).setOrganic(ConsoleHelper.readBoolean("Organic"));
            ((Groceries) product).setQuantity(ConsoleHelper.input("Quantity"));
            ((Groceries) product).setNutritionFacts(ConsoleHelper.input("Nutrition Facts"));

        } else {
            System.out.println("Unknown product category.");
        }

        readOtherDetails(product);

        product = ProductsService.addProduct(product);
        ConsoleHelper.printSuccess("Product (id="+product.getId()+") created successfully");

        Product finalProduct = product;
        Action registerStockAction = new Action() {
                    @Override
                    public String getDescription() {
                        return "Register new stock";
                    }

                    @Override
                    public Object execute() {
                        InventoryManager.addStock(finalProduct,ConsoleHelper.readPositiveInt("Quantity: "));
                        new ActionMenu(null,ActionMenu.PREV_PAGE_ACTION,"").execute();
                        return null;
                    }
                };
        ConsoleHelper.printNewLines(2);
        new ActionMenu(new Action[]{registerStockAction},ActionMenu.PREV_PAGE_ACTION,"").execute();
    }

    private void readOtherDetails(Product product) {
        boolean readMore = ConsoleHelper.readBoolean("Do you want to add more details?");
        while (readMore){
            String key = ConsoleHelper.input("Key");
            String value = ConsoleHelper.input("Value");
            product.setOtherDetails(key,value);
            readMore = ConsoleHelper.readBoolean("Do you want to add more details?");
        }
    }

    private Action[] mapCategoriesActions() {
        Action[] actions = new Action[ProductsManager.categoriesClassesMap.size()];
        int i=0;
        for (Map.Entry<String, Class<? extends Product>> entry :  ProductsManager.categoriesClassesMap.entrySet()){
            actions[i] = new Action() {
                @Override
                public String getDescription() {
                    return entry.getKey();
                }

                @Override
                public String execute() {
                    return entry.getKey();
                }
            };
            i++;
        }
        return actions;
    }


}
