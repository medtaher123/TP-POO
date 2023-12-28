package ui;

import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import helpers.*;
import models.Order;
import models.OrderLine;
import services.OrdersService;

import java.util.List;

public class PurchaseHistoryPage extends BackOnlyPage {

    @Override
    protected String getTitle() {
        return "Purchase History";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.CUSTOMER;
    }

    @Override
    protected void printContent() {
        Order[] orders = OrdersService.getOrdersByUserId(AuthenticationSystem.getActiveUser().getId());
        if (orders.length == 0) {
            ConsoleHelper.printWarning("You have no orders yet.");
            return;
        }
        TableLayout table = new TableLayout();
        table.setHeaders("Order ID", "Date", "Product","Unitary Price","Quantity","Total cost");
        table.setLeftPad(3,true);
        table.setLeftPad(4,true);
        table.setLeftPad(5,true);
        for (int i = 0; i < orders.length; i++) {
            //table.addRowSeparator();
            if(i>0) {
                //table.addRowSeparator();
                table.addRowSeparator();
            }

            Order order = orders[i];
            List<OrderLine> orderLines = order.getOrderLines();
            for (int j = 0; j < orderLines.size(); j++) {
                OrderLine orderLine = orderLines.get(j);
                table.addRow(
                        j==0?order.getId():"",
                        j==0?DateHelper.format(order.getDate()):"",
                        orderLine.getProduct().getShortDisplay(),
                        DataFormatter.formatPrice(orderLine.getProduct().getPrice(),false),
                        orderLine.getQuantity()+"",
                        DataFormatter.formatPrice(orderLine.getTotalPrice())
                );
            }
            table.addRow("","","","","", ConsoleColors.getColoredString("Total: ",ConsoleColors.DEFAULT_BOLD)+DataFormatter.formatPrice(order.getTotalCost()));
        }
        table.displayTable();
    }

}
