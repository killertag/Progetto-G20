package server.services.persistence;

import java.sql.SQLException;

import server.exception.ObjectNotFoundException;
import server.exception.SearchException;

/**
 * Interface for Mapper Class
 */
public interface IMapper {
	String DB_URL = "jdbc:mysql://185.205.41.113:3306/progG20?serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
	String USER = "cinemaG20";
	String PASSWORD = "progettoG20";

	/**
	 * Method which returns an Object for the table belonging to the Mapper
	 * @param OID is the code of the object
	 * @return the object requested
	 * @throws SQLException
	 * @throws ObjectNotFoundException
	 */
	Object  get(String OID) throws SQLException, ObjectNotFoundException;

	/**
	 * Method which insert a new Object in the table belonging to he Mapper
	 * @param OID is the code of the Object
	 * @param obj is the new Object which has to be inserted
	 * @throws SQLException
	 */
	void put(String OID, Object obj) throws SQLException;

	/**
	 * Method which delete the object linked to the mapper, represented by its key, from the table
	 * @param OID is the key of the object
	 * @return the object requested
	 * @throws SQLException
	 * @throws SearchException
	 */
	void delete(String OID) throws SQLException, SearchException;

	/**
	 * Method which modifies the information of the object in the table belonging to the Mapper
	 * @param OID is the code of the Object
	 * @param obj is the Object whose information has to be modified
	 * @throws SQLException
	 */
	void updateTable(String OID,Object obj) throws SQLException;
}