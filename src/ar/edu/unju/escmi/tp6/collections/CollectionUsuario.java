package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Usuario;
import ar.edu.unju.escmi.tp6.exceptions.UsuarioNoRegistradoException;

public class CollectionUsuario {
    // lista encapsulada de usuarios
    private static List<Usuario> usuarios = new ArrayList<>();

    // Guarda un usuario en la colección
    public static void guardarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Busca un usuario por id o lanza excepción si no existe
    public static Usuario buscarUsuario(int id) throws UsuarioNoRegistradoException {
        for (Usuario u : usuarios) {
            if (u.getId() == id) return u;
        }
        throw new UsuarioNoRegistradoException("Usuario con id '" + id + "' no registrado.");
    }

    // Muestra todos los usuarios
    public static void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario u : usuarios) {
            u.mostrarDatos();
            System.out.println();
        }
    }

    // Indica si existe un usuario con el id dado
    public static boolean existeId(int id) {
        for (Usuario u : usuarios)
            if (u.getId() == id) return true;
        return false;
    }
}
