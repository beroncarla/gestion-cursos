package gestioncursos.service;

import gestioncursos.model.Usuario;
import gestioncursos.persistence.UsuarioPersistence;

import java.util.*;

public class FakeUsuarioPersistence implements UsuarioPersistence {

    List<Usuario> usuariosList = new ArrayList<>();

    @Override
    public void save(Usuario usuario) {
        usuariosList.add(usuario);
    }

    @Override
    public void update(Usuario usuario) {

    }

    @Override
    public void delete(int usuarioId) {

    }

    @Override
    public Optional<Usuario> findById(int usuarioId) {
        return Optional.ofNullable(usuariosList.get(usuarioId));
    }



    @Override
    public List<Usuario> findAll() {
        return usuariosList;
    }
}
