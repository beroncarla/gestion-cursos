package gestioncursos.service;

import gestioncursos.model.Usuario;
import gestioncursos.persistence.UsuarioPersistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeUsuarioPersistence implements UsuarioPersistence {

    private final Map<Integer, Usuario> usuarios = new HashMap<>();
    @Override
    public void save(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    @Override
    public void update(Usuario usuario) {

    }

    @Override
    public void delete(int usuarioId) {

    }

    @Override
    public Optional<Usuario> findById(int usuarioId) {
        return Optional.ofNullable(usuarios.get(usuarioId));
    }



    @Override
    public List<Usuario> findAll() {
        return List.of();
    }
}
