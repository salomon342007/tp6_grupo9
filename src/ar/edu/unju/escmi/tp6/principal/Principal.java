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
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            seedExampleData();
            runMenu();
        } catch (Exception e) {
            System.out.println("Error crítico: " + e.getMessage());
        } finally {
            // finally garantizado para liberar recurso Scanner
            if (scanner != null) {
                scanner.close();
            }
            System.out.println("Programa finalizado.");
        }
    }

    private static void runMenu() {
        int opcion = -1;
        while (opcion != 6) {
            mostrarMenu();
            try {
                System.out.print("Ingrese opción: ");
                opcion = Integer.parseInt(scanner.nextLine().trim());
                switch (opcion) {
                    case 1 -> registrarLibro();
                    case 2 -> registrarUsuario();
                    case 3 -> prestarLibro();
                    case 4 -> devolverLibro();
                    case 5 -> listarLibros();
                    case 6 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("La opción debe ser un número.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            } finally {
                // Mensaje de separación entre iteraciones
                System.out.println();
            }
        }
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
            System.out.print("ID libro (string): ");
            String id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("ID no puede estar vacío.");
                return;
            }
            if (CollectionLibro.existeId(id)) {
                System.out.println("ID ya existe.");
                return;
            }

            System.out.print("Título: ");
            String titulo = scanner.nextLine().trim();
            System.out.print("Autor: ");
            String autor = scanner.nextLine().trim();
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine().trim();

            Libro l = new Libro(id, autor, titulo, isbn);
            CollectionLibro.guardarLibro(l);
            System.out.println("Libro registrado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al registrar libro: " + e.getMessage());
        } finally {
            System.out.println("Operación registrar libro finalizada.\n");
        }
    }

    private static void registrarUsuario() {
        try {
            System.out.print("Tipo (1=Alumno, 2=Bibliotecario): ");
            int tipo = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("ID (int): ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            if (CollectionUsuario.existeId(id)) {
                System.out.println("ID de usuario ya existe.");
                return;
            }

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine().trim();
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();

            Usuario u;
            if (tipo == 1) {
                System.out.print("Nro Libreta: ");
                String nro = scanner.nextLine().trim();
                System.out.print("Curso: ");
                String curso = scanner.nextLine().trim();
                u = new Alumno(id, nombre, apellido, email, nro, curso);
            } else if (tipo == 2) {
                System.out.print("Legajo (int): ");
                int legajo = Integer.parseInt(scanner.nextLine().trim());
                u = new Bibliotecario(id, nombre, apellido, email, legajo);
            } else {
                System.out.println("Tipo inválido.");
                return;
            }
            CollectionUsuario.guardarUsuario(u);
            System.out.println("Usuario registrado con éxito.");
        } catch (NumberFormatException e) {
            System.out.println("Los campos numéricos deben ser números.");
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        } finally {
            System.out.println("Operación registrar usuario finalizada.\n");
        }
    }

    private static void prestarLibro() {
        try {
            System.out.print("ID usuario (int): ");
            int idUsuario = Integer.parseInt(scanner.nextLine().trim());
            Usuario usuario = CollectionUsuario.buscarUsuario(idUsuario);

            System.out.print("ID libro (string): ");
            String idLibro = scanner.nextLine().trim();
            Libro libro = CollectionLibro.buscarLibro(idLibro);

            System.out.print("Fecha devolución (dd/MM/yyyy): ");
            String fechaDevStr = scanner.nextLine().trim();
            LocalDate fechaDevolucion;
            try {
                fechaDevolucion = FechaUtil.convertirStringLocalDate(fechaDevStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use dd/MM/yyyy.");
                return;
            }

            // Validar y marcar libro como prestado antes de crear el objeto Prestamo
            try {
                libro.prestar(); // lanzará LibroNoDisponibleException si ya está prestado
            } catch (LibroNoDisponibleException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }

            int nuevoIdPrestamo = CollectionPrestamo.obtenerProximoId();
            Prestamo p = new Prestamo(nuevoIdPrestamo, LocalDate.now(), fechaDevolucion, libro, usuario);
            CollectionPrestamo.guardarPrestamo(p);

            System.out.println("Préstamo registrado con ID: " + p.getId());
        } catch (UsuarioNoRegistradoException | LibroNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ID de usuario inválido (debe ser número).");
        } catch (Exception e) {
            System.out.println("Error inesperado en préstamo: " + e.getMessage());
        } finally {
            System.out.println("Fin del proceso de préstamo.\n");
        }
    }

    private static void devolverLibro() {
        try {
            System.out.print("ID préstamo (int): ");
            int idPrestamo = Integer.parseInt(scanner.nextLine().trim());
            Prestamo p = CollectionPrestamo.buscarPrestamo(idPrestamo);

            System.out.print("Fecha devolución real (dd/MM/yyyy): ");
            String fechaStr = scanner.nextLine().trim();
            LocalDate fecha;
            try {
                fecha = FechaUtil.convertirStringLocalDate(fechaStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use dd/MM/yyyy.");
                return;
            }

            p.registrarDevolucion(fecha);
            System.out.println("Devolución registrada. Libro marcado como disponible.");
        } catch (PrestamoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido (debe ser número).");
        } catch (Exception e) {
            System.out.println("Error en devolución: " + e.getMessage());
        } finally {
            System.out.println("Fin del proceso de devolución.\n");
        }
    }

    private static void listarLibros() {
        try {
            CollectionLibro.mostrarLibros();
        } catch (Exception e) {
            System.out.println("Error al listar libros: " + e.getMessage());
        } finally {
            System.out.println("Operación listar libros finalizada.\n");
        }
    }

    private static void seedExampleData() {
        // Datos iniciales para probar
        Libro l1 = new Libro("L1", "J. K. Autor", "Programación Java", "ISBN-001");
        Libro l2 = new Libro("L2", "A. Escritor", "Estructuras de Datos", "ISBN-002");
        CollectionLibro.guardarLibro(l1);
        CollectionLibro.guardarLibro(l2);

        Alumno a = new Alumno(1, "Juan", "Perez", "juan@example.com", "LB123", "3A");
        Bibliotecario b = new Bibliotecario(2, "Ana", "Gomez", "ana@example.com", 1001);
        CollectionUsuario.guardarUsuario(a);
        CollectionUsuario.guardarUsuario(b);
    }
}
