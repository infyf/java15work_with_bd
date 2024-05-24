package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {

        String url = jdbcmysqllocalhost3306test1;
        String username = root;
        String password = qazwsxedc123;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            Statement statement = connection.createStatement();


            String createTableQuery = CREATE TABLE IF NOT EXISTS food_delivery (
                    + id INT AUTO_INCREMENT PRIMARY KEY,
                    + customer_name VARCHAR(255),
                    + order_details TEXT,
                    + today_data BLOB
                    + );

             Виконання SQL-запиту
            statement.executeUpdate(createTableQuery);

            System.out.println(Таблиця food_delivery створена успішно!);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
Лістинг коду 2 
package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        String url = jdbcmysqllocalhost3306test1;
        String username = root;
        String password = qazwsxedc123;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
             Встановлюємо автоматичний фіксатор транзакцій на ручний режим
            connection.setAutoCommit(false);


            PreparedStatement preparedStatement = connection.prepareStatement(
                    INSERT INTO food_delivery (customer_name, order_details, today_data) VALUES (, , ));


            addRecord(preparedStatement, Максим, Pizza, new byte[]{1, 2, 3});
            addRecord(preparedStatement, Олег, Burger, new byte[]{4, 5, 6});

           
            preparedStatement.executeBatch();


            connection.commit();

            System.out.println(Група записів додана успішно!);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     Метод для додавання одного запису до пакету запитів
    private static void addRecord(PreparedStatement preparedStatement, String customerName, String orderDetails, byte[] todayData) throws SQLException {
        preparedStatement.setString(1, customerName);
        preparedStatement.setString(2, orderDetails);
        preparedStatement.setBytes(3, todayData);
        preparedStatement.addBatch();  Додаємо запис до пакету запитів
    }
}


Лістинг коду 3
package org.example;

import java.sql.;
import java.util.Scanner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class App {
    private static final String URL = jdbcmysqllocalhost3306test1;
    private static final String USERNAME = root;
    private static final String PASSWORD = qazwsxedc123;

    private Connection connection;

    public App() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void insertRecordToDatabase(String customerName, String orderDetails, byte[] todayData) throws SQLException {
        String sql = INSERT INTO food_delivery (customer_name, order_details, today_data) VALUES (, , );
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, orderDetails);
            preparedStatement.setBytes(3, todayData);
            preparedStatement.executeUpdate();
        }
    }

    public void readRecordsFromDatabase() throws SQLException {
        String sql = SELECT  FROM food_delivery;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt(id);
                String customerName = resultSet.getString(customer_name);
                String orderDetails = resultSet.getString(order_details);
                byte[] todayData = resultSet.getBytes(today_data);
                System.out.println(ID  + id + , Customer  + customerName + , Order  + orderDetails + , Data  + new String(todayData));
            }
        }
    }

    public void updateRecordInDatabase(int id, String newOrderDetails) throws SQLException {
        String sql = UPDATE food_delivery SET order_details =  WHERE id = ;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newOrderDetails);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteRecordFromDatabase(int id) throws SQLException {
        String sql = DELETE FROM food_delivery WHERE id = ;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        try {
            App app = new App();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println(nMenu);
                System.out.println(1. Insert record);
                System.out.println(2. Read records);
                System.out.println(3. Update record);
                System.out.println(4. Delete record);
                System.out.println(5. Exit);
                System.out.print(Enter your choice );
                int choice = scanner.nextInt();
                scanner.nextLine();  

                switch (choice) {
                    case 1
                        System.out.println(nEnter customer name);
                        String customerName = scanner.nextLine();
                        System.out.println(Enter order details);
                        String orderDetails = scanner.nextLine();
                        System.out.println(Enter today data);
                        String todayDataString = scanner.nextLine();
                        byte[] todayData = todayDataString.getBytes();
                        app.insertRecordToDatabase(customerName, orderDetails, todayData);
                        System.out.println(Record inserted successfully!);
                        break;
                    case 2
                        app.readRecordsFromDatabase();
                        break;
                    case 3
                        System.out.println(nEnter ID of the record to update);
                        int updateId = scanner.nextInt();
                        scanner.nextLine();   Consume newline
                        System.out.println(Enter new order details);
                        String newOrderDetails = scanner.nextLine();
                        app.updateRecordInDatabase(updateId, newOrderDetails);
                        System.out.println(Record updated successfully!);
                        break;
                    case 4
                        System.out.println(nEnter ID of the record to delete);
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();   Consume newline
                        app.deleteRecordFromDatabase(deleteId);
                        System.out.println(Record deleted successfully!);
                        break;
                    case 5
                        app.closeConnection();
                        System.out.println(Exiting program...);
                        System.exit(0);
                    default
                        System.out.println(Invalid choice! Please enter a valid option.);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
