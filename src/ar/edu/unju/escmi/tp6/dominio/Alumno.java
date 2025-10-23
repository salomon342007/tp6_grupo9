package ar.edu.unju.escmi.tp6.dominio;

public class Alumno extends Usuario {
    private String nroLibreta;
    private String curso;

    public Alumno(int id, String nombre, String apellido, String email, String nroLibreta, String curso) {
        super(id, nombre, apellido, email);
        this.nroLibreta = nroLibreta;
        this.curso = curso;
    }

    public String getNroLibreta() { return nroLibreta; }
    public String getCurso() { return curso; }

    @Override
    public void mostrarDatos() {
        System.out.println("---- Alumno ----");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Nro Libreta: " + nroLibreta);
        System.out.println("Curso: " + curso);
    }
}
