package com.example.news.repositories.vesti;

import com.example.news.entities.Vest;
import com.example.news.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MySqlVestiRepository extends MySqlAbstractRepository implements VestiRepository {

    @Override
    public Vest addVest(Vest vest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO vesti (kategorijaId,naslov, tekst, vremeKreiranja,brPoseta, autor) VALUES(?, ?, ?, current_timestamp, 0, ?)",
                    generatedColumns);
            preparedStatement.setInt(1,vest.getKategorijaId());
            preparedStatement.setString(2, vest.getNaslov());
            preparedStatement.setString(3, vest.getTekst());
            preparedStatement.setString(4, vest.getAutor());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                vest.setId(resultSet.getInt(1));
                vest.setVremeKreiranja(resultSet.getDate("vremeKreiranja"));
                vest.setBrPoseta(resultSet.getInt("brPoseta"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vest;
    }

    @Override
    public List<Vest> allVesti() {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from vesti");
            while (resultSet.next()) {
                vesti.add(new Vest(resultSet.getInt("id"), resultSet.getInt("kategorijaId") ,resultSet.getString("naslov"), resultSet.getString("tekst"), resultSet.getString("autor"),
                        resultSet.getInt("brPoseta"), resultSet.getDate("vremeKreiranja")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }

    @Override
    public List<Vest> topVesti() {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from vesti order by vremeKreiranja desc limit 10");
            while (resultSet.next()) {
                vesti.add(new Vest(resultSet.getInt("id"), resultSet.getInt("kategorijaId") ,resultSet.getString("naslov"), resultSet.getString("tekst"), resultSet.getString("autor"),
                        resultSet.getInt("brPoseta"), resultSet.getDate("vremeKreiranja")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }

    @Override
    public List<Vest> popularVesti() {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from vesti order by brPoseta desc limit 10");
            while (resultSet.next()) {
                vesti.add(new Vest(resultSet.getInt("id"), resultSet.getInt("kategorijaId") ,resultSet.getString("naslov"), resultSet.getString("tekst"), resultSet.getString("autor"),
                        resultSet.getInt("brPoseta"), resultSet.getDate("vremeKreiranja")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }


    @Override
    public Vest findVest(Integer id) {
        Vest vest = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vesti where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int vestId = resultSet.getInt("id");
                int kategorijaId = resultSet.getInt("kategorijaId");
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                String autor = resultSet.getString("autor");
                Integer brPoseta = resultSet.getInt("brPoseta");
                Date vremeKreiranja = resultSet.getDate("vremeKreiranja");
                vest = new Vest(vestId,kategorijaId, naslov, tekst, autor, brPoseta, vremeKreiranja);
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

        return vest;
    }

    @Override
    public void obrisiVest(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM vesti WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM komentari WHERE vestId = ?");//todo ako nema komentara da li puca
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
    public Vest editVest(Vest vest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE vesti SET kategorijaId = ?, naslov = ?, tekst = ?, brPoseta = ? WHERE id = ?");
            preparedStatement.setInt(1, vest.getKategorijaId() );
            preparedStatement.setString(2, vest.getNaslov());
            preparedStatement.setString(3, vest.getTekst());
            preparedStatement.setInt(4, vest.getBrPoseta());
            preparedStatement.setInt(5, vest.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return vest;
    }

    @Override
    public List<Vest> searchVest(String search) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Vest> vesti = new ArrayList<>();

        String searchQuote = "%" + search + "%";


        try {
            connection = newConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM vesti WHERE naslov LIKE  ? OR tekst LIKE ? ");
            preparedStatement.setString(1, searchQuote);
            preparedStatement.setString(2, searchQuote);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vest toAdd = new Vest();
                toAdd.setId(resultSet.getInt("id"));
                toAdd.setKategorijaId(resultSet.getInt("kategorijaId"));
                toAdd.setAutor(resultSet.getString("autor"));
                toAdd.setBrPoseta(resultSet.getInt("brPoseta"));
                toAdd.setVremeKreiranja(resultSet.getDate("vremeKreiranja"));
                toAdd.setTekst(resultSet.getString("tekst"));
                toAdd.setNaslov(resultSet.getString("naslov"));

                vesti.add(toAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return vesti;
    }

    @Override
    public List<Vest> findVestKategorija(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Vest> vesti = new ArrayList<>();




        try {
            connection = newConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM vesti WHERE kategorijaId = ? ");
            preparedStatement.setInt(1, id);



            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vest toAdd = new Vest();
                toAdd.setId(resultSet.getInt("id"));
                toAdd.setKategorijaId(resultSet.getInt("kategorijaId"));
                toAdd.setAutor(resultSet.getString("autor"));
                toAdd.setBrPoseta(resultSet.getInt("brPoseta"));
                toAdd.setVremeKreiranja(resultSet.getDate("vremeKreiranja"));
                toAdd.setTekst(resultSet.getString("tekst"));
                toAdd.setNaslov(resultSet.getString("naslov"));

                vesti.add(toAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return vesti;
    }

    @Override
    public void povecajPosetu(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE vesti SET brPoseta = brPoseta + 1 WHERE id = ?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
