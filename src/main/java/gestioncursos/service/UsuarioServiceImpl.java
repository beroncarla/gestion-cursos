package gestioncursos.service;

import gestioncursos.model.Usuario;
import gestioncursos.persistence.UsuarioPersistence;

import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioPersistence usuarioPersistence;

    public UsuarioServiceImpl(UsuarioPersistence usuarioPersistence) {
        this.usuarioPersistence = usuarioPersistence;
    }
    @Override
    public void registrarUsuario(String nombre, String email, String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }
        Usuario usuario = new Usuario(nombre, email, password);
        usuarioPersistence.save(usuario);
    }

    @Override
    public Optional<Usuario> obtenerPorId(int id) {
        return usuarioPersistence.findById(id);
    }
    @Override
    public List<Usuario> listarTodos() {
        return usuarioPersistence.findAll();
    }
    @Override
    public void desactivarUsuario(int id) {
        Optional<Usuario> usuario = usuarioPersistence.findById(id);
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuario.get().desactivar();
        usuarioPersistence.update(usuario.orElse(null));
    }
}
