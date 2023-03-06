package dao;
import model.Client;

/**
 * clasa aceasta face operatiile de find, delete, edit si insert pentru tabele
 */
public class ClientDAO extends AbstractDAO<Client> {

    // uses basic CRUD methods from superclass

    // TODO: create only client specific queries

    public Client findById(String id){
        return super.findById(id);
    }

    public Client insert(String field1, String field2, String field3, String field4, String field5){
        return super.insert(field1, field2, field3, field4, field5);
    }
    public Client delete(String id){
        return super.delete(id);
    }

    public Client updateClient(String field1, String field2, String field3, String field4, String field5){
        return super.updateClient(field1, field2, field3, field4, field5);
    }
}
