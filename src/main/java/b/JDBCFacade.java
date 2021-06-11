package b;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCFacade implements DBFacade {
    private Connection connection;

    @Override
    public void open() {
        try {
            String url = "jdbc:mysql://localhost:3306/alonso_bd?useSSL=false";
            String user = "root";
            String password = "";
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo conectar con la base de datos.", e);
        }
    }

    @Override
    public List<Map<String, String>> queryResultAsAsociation(String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Map<String, String>> lista = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, String> filas = new HashMap<>();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int cantidadColumnas = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= cantidadColumnas; i++) {
                    filas.put(resultSetMetaData.getColumnName(i), resultSet.getString(i));
                }
                lista.add(filas);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener la lista.", e);
        }
    }

    @Override
    public List<String[]> queryResultAsArray(String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<String[]> lista = new ArrayList<>();
            while (resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int cantidadColumnas = resultSetMetaData.getColumnCount();
                String[] filas = new String[cantidadColumnas];
                for (int i = 1; i <= cantidadColumnas; i++) {
                    filas[i - 1] = resultSet.getString(i);
                }
                lista.add(filas);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener la lista.", e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("No pudo cerrarse la conexión con la base de datos.", e);
        }
    }
}
