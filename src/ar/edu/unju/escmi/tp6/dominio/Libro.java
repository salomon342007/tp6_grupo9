package ar.edu.unju.escmi.tp6.dominio;

import ar.edu.unju.escmi.tp6.exceptions.LibroNoDisponibleException;

public class Libro {
    private String id;
    private String autor;
    private String titulo;
    private String isbn;
    private boolean estado; // true = disponible

    public Libro(String id, String autor, String titulo, String isbn) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.isbn = isbn;
        this.estado = true;
    }

    public String getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isEstado() {
        return estado;
    }

    // Se usa internamente y por pruebas/servicios
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Comportamiento: prestar
    public void prestar() throws LibroNoDisponibleException {
        if (!this.estado) {
            throw new LibroNoDisponibleException("El libro '" + titulo + "' ya está prestado.");
        }
        this.estado = false;
    }

    // Comportamiento: devolver
    public void devolver() {
        this.estado = true;
    }

    public void mostrarDatos() {
        System.out.println("---- Libro ----");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Estado: " + (estado ? "Disponible" : "No disponible"));
    }
}
