package ar.edu.unju.escmi.tp6.dominio;

import java.time.LocalDate;

public class Prestamo {
    private int id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion; // null si no fue devuelto aún
    private Libro libro;
    private Usuario usuario;

    // Constructor
    public Prestamo(int id, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Libro libro, Usuario usuario) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libro = libro;
        this.usuario = usuario;
    }

    // Retorna el id del préstamo
    public int getId() {
        return id;
    }

    // Retorna la fecha de préstamo
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    // Retorna la fecha de devolución (null si aún no fue devuelto)
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    // Retorna el libro prestado
    public Libro getLibro() {
        return libro;
    }

    // Retorna el usuario que tomó el préstamo
    public Usuario getUsuario() {
        return usuario;
    }

    // Registra la devolución del libro
    public void registrarDevolucion(LocalDate fecha) {
        this.fechaDevolucion = fecha;
        if (this.libro != null) {
            this.libro.devolver();
        }
    }

    // Muestra los datos del préstamo por consola
    public void mostrarDatos() {
        System.out.println("---- Préstamo ----");
        System.out.println("ID Préstamo: " + id);
        System.out.println("Fecha Préstamo: " + fechaPrestamo);
        System.out.println("Fecha Devolución: " + (fechaDevolucion == null ? "Pendiente" : fechaDevolucion));
        if (usuario != null) {
            System.out.println("Usuario ID: " + usuario.getId());
        }
        if (libro != null) {
            System.out.println("Libro: " + libro.getTitulo() + " (ID: " + libro.getId() + ")");
        }
    }
}
