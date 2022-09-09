## Работа с базой данных SQLite в Java

1. Скачиваем драйвер SQLite для Java по [ссылке](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc) или берем из этого [репозитория](https://github.com/PavshinSV/SQLite_for_Java). 
2. Импортируем библиотеки `import java.sql.*;`   
3. Я здесь все буду писать одной простыней, но весь код разделю на блоки `try-catch`, 
которые вы потом легко разделите по классам для подключения к базе данных, 
созданию новой таблицы, добавления записей и формирования запросов. 

### Итак:
4. Для подключения к базе данных (или создания новой) необходимо:


    String url = "jdbc:sqlite:./src/main/java/org/example/test.db" //Путь к базе;  
    Connection conn = null; 
    try {  
        conn = DriverManager.getConnection(url);  
    } catch (SQLException e) {  
        throw new RuntimeException(e);  
    }  

5. Создаем новую таблицу:


    String command = "CREATE TABLE IF NOT EXISTS users (\n" +   
        "id INTEGER PRIMARY KEY,\n" +  
        "name TEXT NOT NULL,\n" +  
        "ip TEXT NOT NULL" +  
        ");";  
    try {  
        Statement stmt = conn.createStatement();  
        stmt.execute(command);  
    } catch (SQLException e) {  
        throw new RuntimeException(e);  
    }  


6. Добавляем запись:


    for (int i = 0; i < 10; i++) {
        command = "INSERT INTO users(name,ip) VALUES(?,?);";  
        String name = "user" + i;  
        String ip = "192.168.0." + i;  
        try (PreparedStatement pstmt = conn.prepareStatement(command);) {
            pstmt.setString(1, name);  
            pstmt.setString(2, ip);  
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println("error: " + e);  
        }  
    }

7. Выполняем запрос:


    command = "SELECT * FROM users;";

        try {
            PreparedStatement pstm = conn.prepareStatement(command);
            ResultSet res = pstm.executeQuery();
            while (res.next()){
                System.out.println(res.getInt("id")+"\t"+res.getString("name")+"\t"+res.getString("ip"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

В [репозитории](https://github.com/PavshinSV/SQLite_for_Java) рабочая демонстрация.

### Спасибо за внимание!! 

### Всем удачи!!