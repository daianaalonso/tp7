package b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBCFacade implements DBFacade {
    private Connection connection;

    @Override
    public void open() {
        try {
            String url = "jdbc:mysql://localhost:3306/bd_alonso?useSSL=false";
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

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener la lista.", e);
        }

        return null;
    }

    @Override
    public List<String[]> queryResultAsArray(String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener la lista.", e);
        }
        return null;
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
