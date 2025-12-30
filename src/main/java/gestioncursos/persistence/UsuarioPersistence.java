package gestioncursos.persistence;

import gestioncursos.model.Usuario;

import java.util.List;

public interface UsuarioPersistence {
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(int usuarioId);
    Usuario findById(int usuarioId);
    List<Usuario> findAll();
}
