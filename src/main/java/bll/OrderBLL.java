package bll;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.Validator;
import dao.OrderDAO;
import model.Order;

/**
 * clasa aceasta rezolva toate operatiile din pachetul dao pentru order1
 */
public class OrderBLL {

    private List<Validator<Order>> validators;
    private OrderDAO orderDAO;

    public OrderBLL() {
        validators = new ArrayList<>();
        orderDAO = new OrderDAO();
    }

    public Order find(String id) {
        Order st = orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }

    public Order insert(String id, String clientId, String productId, String productCount, String productPrice) {
        Order st = orderDAO.insertOrder(id, clientId, productId, productCount, productPrice);
        return st;
    }

    public Order deleteOrder(String id) {
        Order st = orderDAO.deleteOrder(id);
        return st;
    }

    public Order updateOrder(String id, String name, String count, String price, String quality) {
        Order st = orderDAO.updateOrder(id, name, count, price, quality);
        return st;
    }

    public void createBill(String i, String message){
        FileWriter myWriter;
        try {
            myWriter = new FileWriter("filename" + i + "txt");
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();}
    }
}

