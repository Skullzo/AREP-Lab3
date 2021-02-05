package edu.escuelaing.arep.app;
import java.sql.*;
import java.util.ArrayList;
import java.sql.DriverManager;
/**
 * Clase encargada de llevar a cabo la conexion, implementacion, ejecucion y creacion de las bases de datos.
 * @author  Alejandro Toro Daza
 * @version 1.0.  (4 de Febrero del 2021) 
 */
public class DBConnection {
    private static String urlDB = "jdbc:postgresql://ec2-3-222-11-129.compute-1.amazonaws.com:5432/d6o6e4o75j6858?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private static String usuarioDB = "jzzckchskydmai";
    private static String passwordDB = "91cf496616f13a4a396ea1a9eb4cb4c0293c41c9f766de85a73d2bd730667209";
    private static Connection connection = null;
    /**
     * Metodo principal encargado de gestionar la URL de la base de datos, el ususario y la clave de la misma.
     */
    public DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(urlDB, usuarioDB, passwordDB);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo encargado de crear la tabla en la que se van a almacenar los datos de las personas en las bases de datos.
     */
    public void createTable(){
        String CREATE_TABLE="CREATE TABLE Information ("
                + "ID INT NOT NULL,"
                + "USERN VARCHAR(60) NOT NULL,"
                + "ADDRESS VARCHAR(30) NOT NULL,"
                + "PRIMARY KEY (ID))";

        try {
            Statement stmnt = connection.createStatement();
            stmnt.execute(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo encargado de agrupar todos los datos de los ciudadanos en un ArrayList de la base datos, para posteriormente desplegarlos en orden.
     * @return Retorna el ArrayList que contiene todos los datos de los ciudadanos.
     */
    public ArrayList<String[]> getInformation(){
        ArrayList<String[]> list = new ArrayList<>();
        String select = "SELECT * FROM Information;";
        try {

            Statement statement = connection.createStatement();
            ResultSet rs =statement.executeQuery(select);
            while(rs.next()){
                String usern = rs.getString("usern");
                String address = rs.getString("address");
                String[] tmp = {usern,address};
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}