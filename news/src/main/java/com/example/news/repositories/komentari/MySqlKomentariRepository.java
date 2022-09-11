package com.example.news.repositories.komentari;

import com.example.news.entities.Komentar;
import com.example.news.entities.User;
import com.example.news.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlKomentariRepository extends MySqlAbstractRepository implements KomentariRepository {
    @Override
    public Komentar addComment(Komentar komentar) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO komentari (vestId, ime, tekst, datumKreiranja) VALUES(?, ?, ?, current_timestamp)",
                    generatedColumns);
            preparedStatement.setInt(1, komentar.getVestId());
            preparedStatement.setString(2, komentar.getIme());
            preparedStatement.setString(3, komentar.getTekst());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                komentar.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentar;
    }

    @Override
    public List<Komentar> findKomentarZaVest(Integer id) {
        List<Komentar> komentari = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM komentari WHERE vestId = ? ORDER BY datumKreiranja DESC");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                komentari.add(new Komentar(
                        resultSet.getInt("id"),
                        resultSet.getInt("vestId"),
                        resultSet.getString("ime"),
                        resultSet.getString("tekst"),
                        resultSet.getDate("datumKreiranja")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return komentari;
    }
}
