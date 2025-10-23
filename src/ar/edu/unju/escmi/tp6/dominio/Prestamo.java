package ar.edu.unju.escmi.tp6.dominio;

import java.time.LocalDate;

public class Prestamo {
    private int id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion; // puede ser null si no fue devuelto
    private Libro libro;
    private Usuario usuario;

    public Prestamo(int id, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Libro libro, Usuario usuario) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libro = libro;
        this.usuario = usuario;
    }

    public int getId() { return id; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public Libro getLibro() { return libro; }
    public Usuario getUsuario() { return usuario; }

    public void registrarDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
        if (libro != null) {
            libro.setEstado(true);
        }
    }

    public void mostrarDatos() {
        System.out.println("---- Préstamo ----");
        System.out.println("ID Préstamo: " + id);
        System.out.println("Fecha Préstamo: " + fechaPrestamo);
        System.out.println("Fecha Devolución: " + (fechaDevolucion == null ? "Pendiente" : fechaDevolucion));
        if (usuario != null) {
            System.out.println("Usuario (ID): " + usuario.getId());
        }
        if (libro != null) {
            System.out.println("Libro (ID): " + libro.getId() + " - " + libro.getTitulo());
        }
    }
}
