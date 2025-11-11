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
        this.estado = true; // Inicia disponible
    }

    // Retorna el id del libro
    public String getId() {
        return id;
    }

    // Retorna el autor del libro
    public String getAutor() {
        return autor;
    }

    // Retorna el título del libro
    public String getTitulo() {
        return titulo;
    }

    // Retorna el isbn del libro
    public String getIsbn() {
        return isbn;
    }

    // Retorna true si está disponible, false si está prestado
    public boolean isDisponible() {
        return estado;
    }

    // Marca el libro como prestado
    public void prestar() throws LibroNoDisponibleException {
        if (!estado) {
            throw new LibroNoDisponibleException("El libro '" + titulo + "' no está disponible.");
        }
        estado = false;
    }

    // Marca el libro como devuelto (disponible)
    public void devolver() {
        estado = true;
    }

    // Muestra los datos del libro por consola
    public void mostrarDatos() {
        System.out.println("---- Libro ----");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Disponible: " + (estado ? "Sí" : "No"));
    }
}
