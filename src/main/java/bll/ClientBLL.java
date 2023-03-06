package bll;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

/**
 * clasa aceasta rezolva toate operatiile din pachetul dao pentru client
 */

public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        //validators.add(new EmailValidator());
        //validators.add(new ClientAgeValidator());

        clientDAO = new ClientDAO();
    }

    public Client find(String id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    public Client insert(String id, String name, String address, String email, String age) {
        Client st = clientDAO.insert(id, name, address, email, age);
        return st;
    }

    public Client update(String id, String name, String address, String email, String age) {
        Client st = clientDAO.updateClient(id, name, address, email, age);
        return st;
    }

    public Client delete(String id) {
        Client st = clientDAO.delete(id);

        return st;
    }

}
