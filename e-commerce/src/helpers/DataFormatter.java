package helpers;

public class DataFormatter {


    private static final String PRICE_COLOR = ConsoleColors.DEFAULT_BOLD;

    public static String formatPrice(double x){
        return formatPrice(x,true);
    }
    public static String formatPrice(double x,boolean bold){
        return ConsoleColors.getColoredString(x + "dt",bold?PRICE_COLOR:"");
    }
}
