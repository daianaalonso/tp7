package a.persistencia;

import a.modelo.Persona;
import a.modelo.PersonaDao;
import a.modelo.SetProxy;
import a.modelo.Telefono;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonaDaoJDBC implements PersonaDao {

    private Connection obtenerConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bd_alonso?useSSL=false";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

    public Persona personaPorId(int idPersona) {
        String sql = "select nombre "
                + "from personas "
                + "where id = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idPersona);
            ResultSet result = statement.executeQuery();
            String nombrePersona = null;
            while (result.next()) {
                nombrePersona = result.getString("nombre");
            }
            return new Persona(idPersona, nombrePersona, new SetProxy(this, idPersona));
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener la persona.", e);
        }
    }

    public Set<Telefono> obtenerTelefonosPorIdPersona(int idPersona) {
        String sql = "select numero "
                + "from telefonos "
                + "where idPersona = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idPersona);
            ResultSet result = statement.executeQuery();
            Set<Telefono> telefonos = new HashSet<>();
            while (result.next()) {
                telefonos.add(new Telefono(result.getString("numero")));
            }
            return telefonos;
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener la lista de telefonos.", e);
        }
    }
}
