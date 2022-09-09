# Работа с базой данных SQLite в Java

1. Скачиваем драйвер SQLite для Java по ссылке https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc 
2. Импортируем библиотеки
   
`import java.sql.Connection;`   
`import java.sql.DriverManager;`  
`import java.sql.SQLException;`


3. Создаем класс для устанавления связи с базой данных

// @author sqlitetutorial.net
  */
  public class Connect {
  /**
    * Connect to a sample database
      */
      public static void connect() {
      Connection conn = null;
      try {
      // db parameters
      String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";
      // create a connection to the database
      conn = DriverManager.getConnection(url);

           System.out.println("Connection to SQLite has been established.");

      } catch (SQLException e) {
      System.out.println(e.getMessage());
      } finally {
      try {
      if (conn != null) {
      conn.close();
      }
      } catch (SQLException ex) {
      System.out.println(ex.getMessage());
      }
      }
      }
      /**
    * @param args the command line arguments
      */
      public static void main(String[] args) {
      connect();
      }
      }`