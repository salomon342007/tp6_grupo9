package ar.edu.unju.escmi.tp6.principal;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import ar.edu.unju.escmi.tp6.collections.CollectionLibro;
import ar.edu.unju.escmi.tp6.collections.CollectionPrestamo;
import ar.edu.unju.escmi.tp6.collections.CollectionUsuario;
import ar.edu.unju.escmi.tp6.dominio.Alumno;
import ar.edu.unju.escmi.tp6.dominio.Bibliotecario;
import ar.edu.unju.escmi.tp6.dominio.Libro;
import ar.edu.unju.escmi.tp6.dominio.Prestamo;
import ar.edu.unju.escmi.tp6.dominio.Usuario;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoDisponibleException;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;
import ar.edu.unju.escmi.tp6.exceptions.PrestamoNoEncontradoException;
import ar.edu.unju.escmi.tp6.exceptions.UsuarioNoRegistradoException;
import ar.edu.unju.escmi.tp6.utils.FechaUtil;

public class Principal {
    // Scanner del diagrama
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        seedExampleData();
        int opcion = -1;
        while (opcion != 6) {
            mostrarMenu();
            try {
                System.out.print("Ingrese opción: ");
                opcion = Integer.parseInt(sc.nextLine().trim());
                switch (opcion) {
                    case 1 -> registrarLibro();
                    case 2 -> registrarUsuario();
                    case 3 -> prestarLibro();
                    case 4 -> devolverLibro();
                    case 5 -> CollectionLibro.mostrarLibros();
                    case 6 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("La opción debe ser un número.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== Biblioteca - Menú =====");
        System.out.println("1. Registrar libro");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Préstamo");
        System.out.println("4. Devolución");
        System.out.println("5. Listar libros");
        System.out.println("6. Salir");
    }

    private static void registrarLibro() {
        try {
            System.out.print("ID (string): ");
            String id = sc.nextLine().trim();
            if (CollectionLibro.existeId(id)) {
                System.out.println("ID de libro ya existe.");
                return;
            }
            System.out.print("Autor: ");
            String autor = sc.nextLine().trim();
            System.out.print("Título: ");
            String titulo = sc.nextLine().trim();
            System.out.print("ISBN: ");
            String isbn = sc.nextLine().trim();
            Libro l = new Libro(id, autor, titulo, isbn);
            CollectionLibro.guardarLibro(l);
            System.out.println("Libro registrado.");
        } catch (Exception e) {
            System.out.println("Error al registrar libro: " + e.getMessage());
        }
    }

    private static void registrarUsuario() {
        try {
            System.out.print("Tipo (1=Alumno,2=Bibliotecario): ");
            int tipo = Integer.parseInt(sc.nextLine().trim());
            System.out.print("ID (int): ");
            int id = Integer.parseInt(sc.nextLine().trim());
            if (CollectionUsuario.existeId(id)) {
                System.out.println("ID usuario ya existe.");
                return;
            }
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine().trim();
            System.out.print("Email: ");
            String email = sc.nextLine().trim();

            Usuario u;
            if (tipo == 1) {
                System.out.print("Nro libreta: ");
                String nro = sc.nextLine().trim();
                System.out.print("Curso: ");
                String curso = sc.nextLine().trim();
                u = new Alumno(id, nombre, apellido, email, nro, curso);
            } else if (tipo == 2) {
                System.out.print("Legajo (int): ");
                int legajo = Integer.parseInt(sc.nextLine().trim());
                u = new Bibliotecario(id, nombre, apellido, email, legajo);
            } else {
                System.out.println("Tipo inválido.");
                return;
            }
            CollectionUsuario.guardarUsuario(u);
            System.out.println("Usuario registrado.");
        } catch (NumberFormatException e) {
            System.out.println("Campos numéricos inválidos.");
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    // Crear préstamo: lógica en Principal según diagrama
    private static void prestarLibro() {
        try {
            System.out.print("ID usuario (int): ");
            int idUsuario = Integer.parseInt(sc.nextLine().trim());
            Usuario usuario = CollectionUsuario.buscarUsuario(idUsuario);

            System.out.print("ID libro (string): ");
            String idLibro = sc.nextLine().trim();
            Libro libro = CollectionLibro.buscarLibro(idLibro);

            System.out.print("Fecha devolución (dd/MM/yyyy): ");
            String fechaDevStr = sc.nextLine().trim();
            LocalDate fechaDevolucion = FechaUtil.convertirStringLocalDate(fechaDevStr);

            // marcar libro como prestado (lanza si no disponible)
            libro.prestar();

            int nuevoIdPrestamo = CollectionPrestamo.obtenerProximoId();
            Prestamo p = new Prestamo(nuevoIdPrestamo, LocalDate.now(), fechaDevolucion, libro, usuario);
            CollectionPrestamo.guardarPrestamo(p);

            System.out.println("Préstamo registrado con ID: " + p.getId());
        } catch (UsuarioNoRegistradoException | LibroNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (LibroNoDisponibleException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use dd/MM/yyyy.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    // Registrar devolución: usa CollectionPrestamo.buscarPrestamo
    private static void devolverLibro() {
        try {
            System.out.print("ID préstamo (int): ");
            int idPrestamo = Integer.parseInt(sc.nextLine().trim());
            Prestamo p = CollectionPrestamo.buscarPrestamo(idPrestamo);
            if (p.getFechaDevolucion() != null) {
                System.out.println("Préstamo ya devuelto.");
                return;
            }
            System.out.print("Fecha devolución (dd/MM/yyyy): ");
            String fechaStr = sc.nextLine().trim();
            LocalDate fecha = FechaUtil.convertirStringLocalDate(fechaStr);
            p.registrarDevolucion(fecha);
            System.out.println("Devolución registrada.");
        } catch (PrestamoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use dd/MM/yyyy.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        } catch (Exception e) {
            System.out.println("Error en devolución: " + e.getMessage());
        }
    }

    // Datos de ejemplo mínimos
    private static void seedExampleData() {
        CollectionUsuario.guardarUsuario(new Bibliotecario(1, "Ana", "Perez", "ana@example.com", 123));
        CollectionUsuario.guardarUsuario(new Alumno(2, "Juan", "Gomez", "juan@example.com", "LN123", "1A"));
        CollectionLibro.guardarLibro(new Libro("L1", "Autor A", "Título A", "ISBN-A"));
        CollectionLibro.guardarLibro(new Libro("L2", "Autor B", "Título B", "ISBN-B"));
    }
}
