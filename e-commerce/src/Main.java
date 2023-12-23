import Managers.PageManager;
import services.ServerTestService;

public class Main {
    //TODO: add to doc: I used this template for TODOs (TODO: add to doc:) whenever I have a note to add to the final documentation.
    // But some of these notes will not make it into the documentation since they are not important. (like this one)
    // So I chose to let them in my code instead of deleting them.
    // So keep an eye on green comments (or whatever color your editor uses)
    public static void main(String[] args) {
        ServerTestService.checkForServer();
        PageManager.restartApp();
    }
    public static void main1(String[] args) {
        /*Laptop l = new Laptop();
        l.setRam(12);
        l.setDisplaySize("dissplay");
        l.setBrand("hiro");
        l.setPrice(15.4);
        String r = DatabaseService.gson.toJson(new Product[]{l});
        System.out.println(r);*/
        //Product[] l = DatabaseService.gson.fromJson("[{\"category\":\"l\",\"ram\":12,\"storage\":0,\"displaySize\":\"dissplay\",\"price\":15.4,\"discountPercentage\":0.0,\"brand\":\"hiro\"}]", Product[].class);
        //System.out.println(l);
    }
}

