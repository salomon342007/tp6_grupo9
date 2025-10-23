package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Prestamo;
import ar.edu.unju.escmi.tp6.exceptions.PrestamoNoEncontradoException;

public class CollectionPrestamo {
    public static List<Prestamo> prestamos = new ArrayList<>();

    public static void guardarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public static Prestamo buscarPrestamo(int id) throws PrestamoNoEncontradoException {
        for (Prestamo p : prestamos) {
            if (p.getId() == id)
                return p;
        }
        throw new PrestamoNoEncontradoException("Préstamo con id '" + id + "' no encontrado.");
    }

    public static void mostrarPrestamos() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }
        for (Prestamo p : prestamos) {
            p.mostrarDatos();
            System.out.println();
        }
    }
}
