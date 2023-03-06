package dao;
import model.Order;
/**
 * clasa aceasta face operatiile de find, delete, edit si insert pentru tabelul order
 */
public class OrderDAO extends AbstractDAO<Order> {


        public Order findById(String id){
                return super.findByIdOrder(id);
        }

        public Order insert(String field1, String field2, String field3, String field4, String field5){
                return super.insert(field1, field2, field3, field4, field5);
        }
        public Order deleteOrder(String id){
                return super.deleteOrder(id);
        }

        public Order updateOrder(String field1, String field2, String field3, String field4, String field5){
                return super.updateOrder(field1, field2, field3, field4, field5);
        }


}

