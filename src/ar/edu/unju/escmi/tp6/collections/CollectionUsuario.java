package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Usuario;
import ar.edu.unju.escmi.tp6.exceptions.UsuarioNoRegistradoException;

public class CollectionUsuario {
    public static List<Usuario> usuarios = new ArrayList<>();

    public static void guardarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static Usuario buscarUsuario(int id) throws UsuarioNoRegistradoException {
        for (Usuario u : usuarios) {
            if (u.getId() == id)
                return u;
        }
        throw new UsuarioNoRegistradoException("Usuario con id '" + id + "' no registrado.");
    }

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

    public static boolean existeId(int id) {
        for (Usuario u : usuarios)
            if (u.getId() == id)
                return true;
        return false;
    }
}