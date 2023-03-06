package dao;
import model.Product;

/**
 *clasa aceasta face operatiile de find, delete, edit si insert pentru tabelul product
 */
public class ProductDAO extends AbstractDAO<Product> {
    // uses basic CRUD methods from superclass
    // TODO: create only product specific queries
    public Product findById(String id){
        return super.findById(id);
    }

    public Product insert(String field1, String field2, String field3, String field4, String field5){
        return super.insert(field1, field2, field3, field4, field5);
    }

    public Product delete(String id){
        return super.delete(id);
    }

    public Product updateProduct(String field1, String field2, String field3, String field4, String field5){
        return super.updateProduct(field1, field2, field3, field4, field5);
    }
}

