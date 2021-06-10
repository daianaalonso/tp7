package a.modelo;

import java.util.Set;

public interface PersonaDao {

    Persona personaPorId(int id);

    Set<Telefono> obtenerTelefonosPorIdPersona(int idPersona);

}
