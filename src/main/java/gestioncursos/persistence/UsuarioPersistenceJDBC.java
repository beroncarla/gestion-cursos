package gestioncursos.persistence;

import gestioncursos.model.Usuario;
import gestioncursos.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioPersistenceJDBC implements UsuarioPersistence {
    public final Connection conn;
    public UsuarioPersistenceJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void save(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, email, password_hash, rol, activo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getPasswordHash());
            stmt.setString(4, usuario.getRol());
            stmt.setBoolean(5, usuario.isActivo());

            int affected = stmt.executeUpdate();
            if (affected == 0) throw new PersistenceException("No se pudo insertar el usuario", null);

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) usuario.setId(rs.getInt(1));
            }

            System.out.println("Usuario guardado: " + usuario.getEmail());

        } catch (SQLException e) {
            throw new PersistenceException("Error guardando usuario", e);
        }
    }

    @Override
    public Optional<Usuario> findById(int id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario(
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("password_hash")
                    );
                    u.setId(rs.getInt("id_usuario"));
                    u.cambiarRol(rs.getString("rol"));
                    if (!rs.getBoolean("activo")) u.desactivar();
                    return Optional.of(u);
                }
            }

        } catch (SQLException e) {
            throw new PersistenceException("Error buscando usuario por ID", e);
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                );
                u.setId(rs.getInt("id_usuario"));
                u.cambiarRol(rs.getString("rol"));
                if (!rs.getBoolean("activo")) u.desactivar();
                usuarios.add(u);
            }

        } catch (SQLException e) {
            throw new PersistenceException("Error obteniendo todos los usuarios", e);
        }

        return usuarios;
    }

    @Override
    public void update(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre=?, email=?, password_hash=?, rol=?, activo=? WHERE id_usuario=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getPasswordHash());
            stmt.setString(4, usuario.getRol());
            stmt.setBoolean(5, usuario.isActivo());
            stmt.setInt(6, usuario.getId());

            stmt.executeUpdate();
            System.out.println("Usuario actualizado: " + usuario.getEmail());

        } catch (SQLException e) {
            throw new PersistenceException("Error actualizando usuario", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM usuario WHERE id_usuario=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Usuario eliminado: " + id);

        } catch (SQLException e) {
            throw new PersistenceException("Error eliminando usuario", e);
        }
    }
}

