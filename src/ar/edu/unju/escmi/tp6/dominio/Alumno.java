package ar.edu.unju.escmi.tp6.dominio;

public class Alumno extends Usuario {
    // Nº de libreta del alumno
    private String nroLibreta;
    // Curso o división del alumno
    private String curso;

    // Constructor: inicializa usuario y campos específicos de Alumno
    public Alumno(int id, String nombre, String apellido, String email, String nroLibreta, String curso) {
        super(id, nombre, apellido, email);
        this.nroLibreta = nroLibreta;
        this.curso = curso;
    }

    // Devuelve el número de libreta
    public String getNroLibreta() {
        return nroLibreta;
    }

    // Devuelve el curso
    public String getCurso() {
        return curso;
    }

    @Override
    public void mostrarDatos() {
        // Imprime por consola los datos del alumno
        System.out.println("---- Alumno ----");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Nro Libreta: " + nroLibreta);
        System.out.println("Curso: " + curso);
    }
}
