package com.example.news.repositories.tag;

import com.example.news.entities.User;
import com.example.news.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagRepository {
    @Override
    public User findUser(String username) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM kategorije where username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String username2 = resultSet.getString("username");
                String role = resultSet.getString("role");
                String pass = resultSet.getString("hashedPassword");
                String mail = resultSet.getString("email");
                String stat = resultSet.getString("status");
                user = new User(id, username2, role, pass, mail,stat);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return user;
    }
}
