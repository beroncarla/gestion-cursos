package gestioncursos.persistence;

import gestioncursos.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioPersistence {
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(int usuarioId);
    Optional<Usuario> findById(int usuarioId);
    List<Usuario> findAll();
}
