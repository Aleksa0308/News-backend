package com.example.news.repositories.kategorije;

import com.example.news.entities.Kategorija;
import com.example.news.entities.User;
import com.example.news.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlKategorijeRepository extends MySqlAbstractRepository implements KategorijeRepository {

    @Override
    public Kategorija findKategorija(Integer id) {
        Kategorija kategorija = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM kategorije WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer kategorijaId = resultSet.getInt("id");
                String tip = resultSet.getString("tip");
                String opis = resultSet.getString("opis");

                kategorija = new Kategorija(kategorijaId,tip,opis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kategorija;
    }

    @Override
    public List<Kategorija> allKategorije() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Kategorija> kategorije = new ArrayList<>();

        try {
            connection = newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM kategorije");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                kategorije.add(new Kategorija(resultSet.getInt("id"),resultSet.getString("tip"),
                        resultSet.getString("opis")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return kategorije;
    }

    @Override
    public void deleteKategoriju(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM kategorije WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }
    }

    @Override
    public Kategorija dodajKategoriju(Kategorija kategorija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO kategorije (tip, opis) VALUES(?, ?)",
                    generatedColumns);
            preparedStatement.setString(1,kategorija.getTip());
            preparedStatement.setString(2, kategorija.getOpis());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                kategorija.setId(resultSet.getInt(1));
                kategorija.setTip(resultSet.getString("tip"));
                kategorija.setOpis(resultSet.getString("opis"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return kategorija;
    }

    @Override
    public Kategorija editKategorija(Kategorija kategorija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE kategorije SET tip = ?, opis = ? WHERE id = ?");
            preparedStatement.setString(1, kategorija.getTip());
            preparedStatement.setString(2, kategorija.getOpis());
            preparedStatement.setInt(6, kategorija.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return kategorija;
    }

}
