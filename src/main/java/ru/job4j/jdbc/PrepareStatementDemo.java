package ru.job4j.jdbc;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;

public class PrepareStatementDemo {
    private Connection connection;

    public PrepareStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/example";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public void insert(City city) {
        try (PreparedStatement statement =
                connection.prepareStatement("insert into cities(name, population) values(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.name);
            statement.setInt(2, city.population);
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.id = generatedKeys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.name);
            statement.setInt(2, city.population);
            statement.setInt(3, city.id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public class City {
        int id;
        String name;
        int population;

        public City(int id, String name, int population) {
            this.id = id;
            this.name = name;
            this.population = population;
        }
    }
}
