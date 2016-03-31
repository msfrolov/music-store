package com.epam.msfrolov.musicstore.db;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.epam.msfrolov.musicstore.util.FileHandler.getProperties;
import static com.epam.msfrolov.musicstore.util.FileHandler.readFileToList;
import static junit.framework.Assert.assertTrue;

public class TestDB {


    @Test
    public void fillStyle() throws SQLException {
        Properties pr = getProperties("properties/connection.properties");
        final String URL = pr.getProperty("url");
        final String USER = pr.getProperty("user");
        final String PASSWORD = pr.getProperty("password");

        Connection connection = null;
        PreparedStatement statement;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statementTest = connection.createStatement();
            statementTest.execute("SELECT * FROM STYLE");
            ResultSet resultSet = statementTest.getResultSet();
            List<String> strings = new ArrayList<>();
            while (resultSet.next()) {
                strings.add(resultSet.getString("name"));
            }
            statement = connection.prepareStatement("INSERT INTO STYLE(NAME) VALUES(?)");
            List<String> fileToList = readFileToList("music_style");
            for (String s : fileToList) {
                if (strings.contains(s))
                    continue;
                System.out.println(s);
                statement.setString(1, s);
                statement.execute();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.close();
        }
    }

    @Test
    public void testFetchStyle() {
        int fetchSize = 0;
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/h2", "user", "user");
            Statement statement = con.createStatement();
            statement.execute("SELECT * FROM STYLE");
            ResultSet resultSet = statement.getResultSet();
            fetchSize = resultSet.getFetchSize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue(fetchSize > 10);
    }

}
