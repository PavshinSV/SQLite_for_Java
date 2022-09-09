package org.example;

import java.sql.*;


public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:./src/main/java/org/example/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    }
}
