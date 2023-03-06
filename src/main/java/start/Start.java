package start;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import bll.ClientBLL;
import model.Client;
import presentation.Controller;


public class Start {


	public static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException{


		ClientBLL clientBLL = new ClientBLL();
		Client client = new Client();
		/*try {
			clientBLL.insert("2", "David", "reger", "rkgjb", "16");
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}


		System.out.println("client:\n");
		ReflectionExample.retrieveProperties(client);

		try {
			clientBLL.update("2", "Ana", "reger", "rkgjb", "25");
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
*/
		new Controller();

	}
}




