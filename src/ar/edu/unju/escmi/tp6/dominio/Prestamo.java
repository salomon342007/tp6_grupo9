package ar.edu.unju.escmi.tp6.dominio;

import java.time.LocalDate;

import ar.edu.unju.escmi.tp6.collections.CollectionPrestamo;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoDisponibleException;

public class Prestamo {
    private int id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion; // null si no fue devuelto aún
    private Libro libro;
    private Usuario usuario;

    public Prestamo(int id, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Libro libro, Usuario usuario) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libro = libro;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    // Método estático para encapsular la creación de un préstamo con validación
    public static Prestamo crearPrestamo(int id, Libro libro, Usuario usuario, LocalDate fechaDevolucion)
            throws LibroNoDisponibleException {
        // delega a Libro la validación de disponibilidad
        libro.prestar();
        Prestamo p = new Prestamo(id, LocalDate.now(), fechaDevolucion, libro, usuario);
        CollectionPrestamo.guardarPrestamo(p);
        return p;
    }

    // Registrar la devolución: guarda la fecha y libera el libro
    public void registrarDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
        if (this.libro != null) {
            this.libro.devolver();
        }
    }

    public void mostrarDatos() {
        System.out.println("---- Préstamo ----");
        System.out.println("ID Préstamo: " + id);
        System.out.println("Fecha Préstamo: " + fechaPrestamo);
        System.out.println("Fecha Devolución: " + (fechaDevolucion == null ? "Pendiente" : fechaDevolucion));
        if (usuario != null) {
            System.out.println("Usuario ID: " + usuario.getId());
        }
        if (libro != null) {
            System.out.println("Libro ID: " + libro.getId() + " - " + libro.getTitulo());
        }
    }
}
