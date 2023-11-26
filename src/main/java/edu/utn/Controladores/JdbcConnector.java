package edu.utn.Controladores;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnector {
    public static Connection con = JdbcConnector.getConnection();
    private JdbcConnector() {}
    public static Connection getConnection() {
        if (con != null) { return con; }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname","root","password");
        } catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
}