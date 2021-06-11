package b;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        DBFacade fachada = new JDBCFacade();
        fachada.open();
        List<Map<String, String>> listaMapa = fachada.queryResultAsAsociation("Select * from telefonos");
        List<String[]> listaArreglo = fachada.queryResultAsArray("Select * from personas");
        fachada.close();

        listaMapa.forEach(System.out::println);
    }

}
