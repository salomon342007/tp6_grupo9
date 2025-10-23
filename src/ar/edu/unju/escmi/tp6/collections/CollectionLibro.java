package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Libro;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class CollectionLibro {
    public static List<Libro> libros = new ArrayList<>();

    public static void guardarLibro(Libro libro) {
        libros.add(libro);
    }

    public static Libro buscarLibro(String id) throws LibroNoEncontradoException {
        for (Libro l : libros) {
            if (l.getId().equals(id))
                return l;
        }
        throw new LibroNoEncontradoException("Libro con id '" + id + "' no encontrado.");
    }

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

    public static boolean existeId(String id) {
        for (Libro l : libros)
            if (l.getId().equals(id))
                return true;
        return false;
    }
}
