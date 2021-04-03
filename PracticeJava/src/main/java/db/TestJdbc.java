package db;

import java.sql.*;

public class TestJdbc {
  public static void main(String[] args) {
    //
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con =
          DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/testDb?useSSL=false", "root", "root");
      PreparedStatement statement = con.prepareStatement("select * from persons");
      //      PreparedStatement statementInsert =
      //          con.prepareStatement("insert into persons values(?,?,?,?)");
      //      statementInsert.setInt(1, 2);
      //      statementInsert.setString(2, "Thomas Uduyo");
      //      statementInsert.setInt(3, 98);
      //      statementInsert.setString(4, "Uganda");
      //      long x = statementInsert.executeUpdate();
      //      System.out.println(x);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        System.out.println(
            rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3) + " " + rs.getString(4));
      }

      rs.close();
      statement.close();
      con.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
