package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Libro;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class CollectionLibro {
    private static List<Libro> libros = new ArrayList<>();

    // Guarda un libro
    public static void guardarLibro(Libro libro) {
        libros.add(libro);
    }

    // Busca un libro por id (String) o lanza excepción
    public static Libro buscarLibro(String id) throws LibroNoEncontradoException {
        for (Libro l : libros) {
            if (l.getId().equals(id)) return l;
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

    // Indica si existe un libro con el id indicado
    public static boolean existeId(String id) {
        for (Libro l : libros)
            if (l.getId().equals(id)) return true;
        return false;
    }
}
