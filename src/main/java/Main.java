import a.modelo.Persona;
import a.modelo.PersonaDao;
import a.modelo.Telefono;
import a.persistencia.PersonaDaoJDBC;

public class Main {
    public static void main(String[] args) {
        PersonaDao dao = new PersonaDaoJDBC();
        Persona p = dao.personaPorId(1);
        System.out.println(p.nombre());
        for (Telefono telefono : p.telefonos()) {
            System.out.println(telefono);
        }
    }

}
