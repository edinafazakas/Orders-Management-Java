package dao;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * clasa aceasta face operatiile de find, delete, edit si insert pentru tabele
 * @param <T>
 */

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	private final Class<T> type;
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE id = " + field + ";");
		return sb.toString();
	}
	private String createSelectQueryOrder(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(" order1 ");
		sb.append(" WHERE id = '" + field + "';");
		return sb.toString();
	}

	private String createInsertQuery(String field1, String field2, String field3, String field4, String field5) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ");
		sb.append(" into ");
		sb.append(type.getSimpleName());
		sb.append(" VALUES ('" + field1 + "',' " + field2 + "',' " + field3 + "',' " + field4 + "', '" + field5 + "');");
		return sb.toString();
	}

	private String createInsertQueryOrder(String field1, String field2, String field3, String field4, String field5) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ");
		sb.append(" into ");
		sb.append(" order1 ");
		sb.append("VALUES ('" + field1 + "', '" + field2 + "',' " + field3 + "', '" + field4 + "', '" + field5 + "');");
		return sb.toString();
	}
	private String createUpdateQueryClient(String field1, String field2, String field3, String field4, String field5) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(" client ");
		sb.append("set name = '" + field2 + "', address= '" + field3 + "', email= '" + field4 + "', age= " + field5 + " where id = " + field1 + ";");
		return sb.toString();
	}
	private String createUpdateQueryProduct(String field1, String field2, String field3, String field4, String field5) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(" product ");
		sb.append("set name = '" + field2 + "', count= " + field3 + ", price= " + field4 + ", quality= '" + field5 + "'  where id = " + field1 +  ";");
		return sb.toString();
	}
	private String createUpdateQueryOrder(String field1, String field2, String field3, String field4, String field5) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(" order1 ");
		sb.append("set clientId = " + field2 + ", productId= " + field3 + ", productCount= " + field4 + ", productPrice= " + field5 + "  where id = " + field1 +  ";");
		return sb.toString();
	}
	private String createDeleteQuery(String field1) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" where id= " + field1 + ";");
		return sb.toString();
	}
	private String createDeleteQueryOrder(String field1) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(" order1 ");
		sb.append(" where id= " + field1 + ";");
		return sb.toString();
	}
	public List<T> findAll() {
		return null;
	}
	public T findById(String id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(id);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			//statement.setInt(1, id);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}return null;}
	public T findByIdOrder(String id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQueryOrder(id);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}return null;}
	public List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T)ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}return list;}
	public T insert(String field1, String field2, String field3, String field4, String field5) {
		// TODO:
		Connection connection = null;
		PreparedStatement statement = null;
		//ResultSet resultSet = null;
		String query = createInsertQuery(field1, field2, field3, field4, field5);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute(query);
			//return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: insert " + e.getMessage());
		} finally {
			//ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}return null;}
	public T insertOrder(String field1, String field2, String field3, String field4, String field5) {
		// TODO:
		Connection connection = null;
		PreparedStatement statement = null;
		//ResultSet resultSet = null;
		String query = createInsertQueryOrder(field1, field2, field3, field4, field5);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute(query);
			//return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: insert " + e.getMessage());
		} finally {
			//ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}return null;}
	public T updateClient(String id, String name, String address, String email, String age) {
		Connection connection = null;
		PreparedStatement statement = null;
		//ResultSet resultSet = null;
		String query = createUpdateQueryClient(id, name, address, email, age);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute(query);
			//return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: update client " + e.getMessage());
		} finally {
			//ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}return null;}
	public T updateProduct(String id, String name, String count, String price, String quality) {
		Connection connection = null;
		PreparedStatement statement = null;
		//ResultSet resultSet = null;
		String query = createUpdateQueryProduct(id, name, count, price, quality);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute(query);
			//return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: update client " + e.getMessage());
		} finally {
			//ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}return null;}
	public T delete(String id) {
		Connection connection = null;
		PreparedStatement statement = null;
		//ResultSet resultSet = null;
		String query = createDeleteQuery(id);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute(query);
			//return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: delete " + e.getMessage());
		} finally {
			//ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}return null;}
	public T deleteOrder(String id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createDeleteQueryOrder(id);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute(query);
			//return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: delete " + e.getMessage());
		} finally {
			//ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}return null;}
	public T updateOrder(String id, String clientId, String productId, String productCount, String productPrice) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createUpdateQueryOrder(id, clientId, productId, productCount, productPrice);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute(query);
			//return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: update order " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}return null;}
}
