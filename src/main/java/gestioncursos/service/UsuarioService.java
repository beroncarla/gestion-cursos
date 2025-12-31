package gestioncursos.service;

import gestioncursos.model.Usuario;
import java.util.List;

public interface UsuarioService {

    void registrarUsuario(String nombre, String email, String password);

    Usuario obtenerPorId(int id);

    List<Usuario> listarTodos();

    void desactivarUsuario(int id);
}

