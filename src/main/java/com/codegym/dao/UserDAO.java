package com.codegym.dao;

import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{

    public static final String URLJDBC = "jdbc:mysql://localhost:3306/democ1221h1";

    //lay ket noi voi csdl
    protected Connection getConnection(){
        Connection connection = null;

        try {
            //Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Connection
            connection = DriverManager.getConnection(
                    URLJDBC,
                    "root",
                    "123456"
            );
            System.out.println("ket noi thanh cong");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ket noi chua thanh cong");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    @Override
    public void insertUser(User user) throws SQLException {

    }

    @Override
    public User selectUser(int id) {
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try(
                //lay ket noi den csdl
                Connection connection= getConnection();
                //chuan bi caau lenh truy van
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT  * FROM users"
                );

                ) {
            //luu du lieu thuc thi cau lenh vao trong resultSet
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                User user = new User(id, name, email, country);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return false;
    }
}
