package ExcellReader;

import java.sql.*;

public class DBConnection {
  /*  String localhost = "localhost";
    String port = "3306";
    String dbName = "qadbt";
    String URL = "jdbc:mysql://" + localhost + ":" + port + "/"+dbName;
    String user = "root";
    String password = "admin123*";

    public Connection connectToDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }*/

    public void executeQuery(Connection connection, String query) {

    }


    public static void main(String[] args) throws SQLException {
        String localhost = "localhost";
        String port = "3306";
        String dbName = "qadbt";
        String URL = "jdbc:mysql://" + localhost + ":" + port + "/"+dbName;
        String user = "root";
        String password = "admin123*";
        Connection connection = null;
        String query = "select * from qadbt.employeeinfo where age=21;";

       /* try {
             connection = DriverManager.getConnection(URL,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        connection = DriverManager.getConnection(URL, user, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("location"));
            System.out.println(resultSet.getInt("age"));
        }

    }

}
