package ar.edu.unju.escmi.tp6.dominio;

public class Bibliotecario extends Usuario {
    private int legajo;

    public Bibliotecario(int id, String nombre, String apellido, String email, int legajo) {
        super(id, nombre, apellido, email);
        this.legajo = legajo;
    }

    public int getLegajo() { return legajo; }

    @Override
    public void mostrarDatos() {
        System.out.println("---- Bibliotecario ----");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Legajo: " + legajo);
    }
}
