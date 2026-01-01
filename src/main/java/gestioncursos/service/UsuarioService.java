package gestioncursos.service;

import gestioncursos.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    void registrarUsuario(String nombre, String email, String password);

    Optional<Usuario> obtenerPorId(int id);

    List<Usuario> listarTodos();

    void desactivarUsuario(int id);
}

