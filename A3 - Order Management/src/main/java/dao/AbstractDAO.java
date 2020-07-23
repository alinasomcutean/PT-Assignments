package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * This class is a class that contains all the methods for creating the SQL syntax for queries and methods for generating the result of the queries.
 * @author Alina Somcutrsn
 *
 * @param <T> a generic class
 */
public abstract class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	private final Class<T> type;
	
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * Method for creating the select SQL syntax after a specific field
	 * @param field field after which the selection is made
	 * @return SQL syntax as a String
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM `");
		sb.append(type.getSimpleName());
		sb.append("` WHERE " + field + " = ?");
		return sb.toString();
	}
	
	/**
	 * Method for creating the select all SQL syntax
	 * @return SQL syntax as a String
	 */
	private String createSelectAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM `");
		sb.append(type.getSimpleName() + "`");
		return sb.toString();
	}
	
	/**
	 * Method for creating the update SQL syntax with a condition
	 * @param t The object that has to be updated
	 * @param condition condition after which the update is made
	 * @return SQL syntax as a String
	 */
	private String createUpdateQuery(T t, String condition) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE `");
		sb.append(type.getSimpleName());
		sb.append("` SET ");
		try {
			boolean first = false;
			for(Field field : type.getDeclaredFields()) {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
				Method method = propertyDescriptor.getReadMethod();
				Object o = method.invoke(t);
				if(field.getName().compareTo("id") == 0) {
					continue;
				}
				if(first == false) {
					sb.append(field.getName() + "=" + "'" + o + "'");
					first = true;
				}
				else {
					sb.append(", " + field.getName() + "=" + "'" + o + "'");
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		sb.append(" WHERE " + condition + " = ?");
		return sb.toString();
	}

	/**
	 * Method for creating the insert SQL syntax
	 * @param t the object that has to be inserted
	 * @return SQL syntax as a String
	 */
	private String createInsertQuery(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO `");
		sb.append(type.getSimpleName() + "` (");
		boolean first = false;
		for(Field field : type.getDeclaredFields()) {
			if(field.getName().compareTo("id") == 0) {
				continue;
			}
			if(first == false) {
				sb.append(field.getName());
				first = true;
			}
			else {
				sb.append(", " + field.getName());
			}
		}
		sb.append(") VALUES (");
		try {
			boolean firstInsert = false;
			for(Field field : type.getDeclaredFields()) {
				if(field.getName().compareTo("id") != 0) {
					if(firstInsert == false) {
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
						Method method = propertyDescriptor.getReadMethod();
						Object o = method.invoke(t);
						sb.append("'" + o + "'");
						firstInsert = true;
					}
					else {
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
						Method method = propertyDescriptor.getReadMethod();
						Object o = method.invoke(t);
						sb.append(", '" + o + "' ");
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * Method for creating the delete SQL syntax after a field
	 * @param field field after which the deletion is made
	 * @return SQL syntax as a String
	 */
	private String createDeleteQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM `");
		sb.append(type.getSimpleName());
		sb.append("` WHERE " + field + " = ?");
		return sb.toString();
	}
	
	/**
	 * Method which opens a connection to the database, execute the select after the id field query and close the connection
	 * @param id id of the object
	 * @return the objects found or a message in case that it doesn't exist
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			List<T> result = createObjects(resultSet);
			if(result != null) {
				return result.get(0);
			}
		} catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	/**
	 * Method which opens a connection to the database, execute the select all query and close the connection
	 * @return a list with all the records from the database
	 */
	public List<T> findALL(){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectAllQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	/**
	 * Method which opens a connection to the database, execute the update after the id query and close the connection
	 * @param t the object that has to be updated
	 * @param id id of the object
	 * @return the object updated
	 */
	public T update(T t, int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createUpdateQuery(t, "id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + " DAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}
	
	/**
	 * Method which opens a connection to the database, execute the insert query and close the connection
	 * @param t the object that has to be insert
	 * @return inserted object
	 */
	public T insert(T t) {
		Connection connection = null;
		Statement statement = null;
		String query = createInsertQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		}finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}
	
	/**
	 * Method which opens a connection to the database, execute the delete by an id query and close the connection
	 * @param id id of the record to be deleted
	 */
	public void deleteById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createDeleteQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById" + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
	
	private List<T> createObjects(ResultSet resultSet){
		List<T> list = new ArrayList<T>();
		try {
			while(resultSet.next()) {
				T instance = type.newInstance();
				for(Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch(InstantiationException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		if(list.size() > 0) {
			return list;
		}
		else {
			return null;
		}
	}
}
