package bll;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

/**
 *  clasa aceasta rezolva toate operatiile din pachetul dao pentru product
 */

public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductBLL() {
        validators = new ArrayList<>();
        productDAO = new ProductDAO();
    }


    public Product find(String id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    public Product insert(String id, String name, String count, String price, String quality) {
        Product st = productDAO.insert(id, name, count, price, quality);
        return st;
    }

    public Product update(String id, String name, String count, String price, String quality) {
        Product st = productDAO.updateProduct(id, name, count, price, quality);
        return st;
    }

    public Product delete(String id) {
        Product st = productDAO.delete(id);
        return st;
    }

}
