package ar.edu.unju.escmi.tp6.dominio;

import ar.edu.unju.escmi.tp6.exceptions.LibroNoDisponibleException;

public class Libro {
    private String id;
    private String autor;
    private String titulo;
    private String isbn;
    private boolean estado; // true = disponible

    // Constructor
    public Libro(String id, String autor, String titulo, String isbn) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.isbn = isbn;
        this.estado = true; // disponible por defecto
    }

    // Getters
    public String getId() { return id; }
    public String getAutor() { return autor; }
    public String getTitulo() { return titulo; }
    public String getIsbn() { return isbn; }
    public boolean isDisponible() { return estado; }

    // Marca como prestado, lanza si no está disponible
    public void prestar() throws LibroNoDisponibleException {
        if (!estado) throw new LibroNoDisponibleException("El libro '" + titulo + "' no está disponible.");
        estado = false;
    }

    // Marca como devuelto (disponible)
    public void devolver() {
        estado = true;
    }

    // Muestra datos del libro
    public void mostrarDatos() {
        System.out.println("---- Libro ----");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Disponible: " + (estado ? "Sí" : "No"));
    }
}
