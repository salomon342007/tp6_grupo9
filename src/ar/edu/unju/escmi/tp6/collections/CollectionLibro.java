package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Libro;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class CollectionLibro {
    // La lista se expresa mediante relación flecha en el diagrama, aquí es private
    private static List<Libro> libros = new ArrayList<>();

    // Getter para acceder a la lista (si es necesario)
    public static List<Libro> getLibros() {
        return libros;
    }

    // Agrega un libro a la colección
    public static void guardarLibro(Libro libro) {
        libros.add(libro);
    }

    // Busca un libro por id
    public static Libro buscarLibro(String id) throws LibroNoEncontradoException {
        for (Libro l : libros) {
            if (l.getId().equals(id))
                return l;
        }
        throw new LibroNoEncontradoException("Libro con id '" + id + "' no encontrado.");
    }

    // Muestra todos los libros
    public static void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros cargados.");
            return;
        }
        for (Libro l : libros) {
            l.mostrarDatos();
            System.out.println();
        }
    }

    // Verifica si existe un libro con el id indicado
    public static boolean existeId(String id) {
        for (Libro l : libros)
            if (l.getId().equals(id))
                return true;
        return false;
    }
}
