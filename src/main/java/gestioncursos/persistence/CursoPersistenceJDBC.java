package gestioncursos.persistence;

import gestioncursos.model.Curso;
import gestioncursos.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoPersistenceJDBC implements CursoPersistence {

    @Override
    public void save(Curso curso) {
        String sql = "INSERT INTO curso (nombre, descripcion, activo) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setBoolean(3, curso.isActivo());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) curso.setId(rs.getInt(1));
            }

            System.out.println("Curso guardado: " + curso.getNombre());

        } catch (SQLException e) {
            throw new PersistenceException("Error guardando curso", e);
        }
    }

    @Override
    public Curso findById(int id) {
        String sql = "SELECT * FROM curso WHERE id_curso=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso c = new Curso(rs.getString("nombre"), rs.getString("descripcion"));
                    c.setId(rs.getInt("id_curso"));
                    if (!rs.getBoolean("activo")) c.desactivar();
                    return c;
                }
            }

        } catch (SQLException e) {
            throw new PersistenceException("Error buscando curso por ID", e);
        }
        return null;
    }

    @Override
    public List<Curso> findAll() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso(rs.getString("nombre"), rs.getString("descripcion"));
                c.setId(rs.getInt("id_curso"));
                if (!rs.getBoolean("activo")) c.desactivar();
                cursos.add(c);
            }

        } catch (SQLException e) {
            throw new PersistenceException("Error obteniendo cursos", e);
        }

        return cursos;
    }

    @Override
    public void update(Curso curso) {
        String sql = "UPDATE curso SET nombre=?, descripcion=?, activo=? WHERE id_curso=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setBoolean(3, curso.isActivo());
            stmt.setInt(4, curso.getId());
            stmt.executeUpdate();

            System.out.println("Curso actualizado: " + curso.getNombre());

        } catch (SQLException e) {
            throw new PersistenceException("Error actualizando curso", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM curso WHERE id_curso=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Curso eliminado: " + id);

        } catch (SQLException e) {
            throw new PersistenceException("Error eliminando curso", e);
        }
    }
}
