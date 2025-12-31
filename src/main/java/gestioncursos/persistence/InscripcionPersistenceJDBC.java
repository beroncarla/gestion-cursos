package gestioncursos.persistence;
import gestioncursos.model.Inscripcion;
import gestioncursos.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionPersistenceJDBC implements InscripcionPersistence {

    @Override
    public void save(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion (usuario_id, curso_id, fecha_alta) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, inscripcion.getUsuarioId());
            stmt.setInt(2, inscripcion.getCursoId());
            stmt.setTimestamp(3, Timestamp.valueOf(inscripcion.getFechaAlta()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) inscripcion.setId(rs.getInt(1));
            }

            System.out.println("Inscripción guardada: usuario " + inscripcion.getUsuarioId() + " en curso " + inscripcion.getCursoId());

        } catch (SQLException e) {
            throw new PersistenceException("Error guardando inscripcion", e);
        }
    }

    @Override
    public Inscripcion findById(int id) {
        String sql = "SELECT * FROM inscripcion WHERE id_inscripcion=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Inscripcion i = new Inscripcion(
                            rs.getInt("id_inscripcion"),
                            rs.getInt("usuario_id"),
                            rs.getInt("curso_id"),
                            rs.getTimestamp("fecha_alta").toLocalDateTime()
                    );

                    return i;
                }
            }

        } catch (SQLException e) {
            throw new PersistenceException("Error buscando inscripcion por ID", e);
        }
        return null;
    }

    @Override
    public List<Inscripcion> findAll() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inscripcion i = new Inscripcion(
                        rs.getInt("id_inscripcion"),
                        rs.getInt("usuario_id"),
                        rs.getInt("curso_id"),
                        rs.getTimestamp("fecha_alta").toLocalDateTime()
                );

                inscripciones.add(i);
            }

        } catch (SQLException e) {
            throw new PersistenceException("Error obteniendo inscripciones", e);
        }

        return inscripciones;
    }

    @Override
    public void update(Inscripcion inscripcion) {
        String sql = "UPDATE inscripcion SET usuario_id=?, curso_id=?, fecha_alta=? WHERE id_inscripcion=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inscripcion.getUsuarioId());
            stmt.setInt(2, inscripcion.getCursoId());
            stmt.setTimestamp(3, Timestamp.valueOf(inscripcion.getFechaAlta()));
            stmt.setInt(4, inscripcion.getId());
            stmt.executeUpdate();

            System.out.println("Inscripcion actualizada: " + inscripcion.getId());

        } catch (SQLException e) {
            throw new PersistenceException("Error actualizando inscripcion", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM inscripcion WHERE id_inscripcion=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Inscripcion eliminada: " + id);

        } catch (SQLException e) {
            throw new PersistenceException("Error eliminando inscripcion", e);
        }
    }

    public List<Inscripcion> findByUsuarioId(int usuarioId) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion WHERE usuario_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inscripcion i = new Inscripcion(
                            rs.getInt("id_inscripcion"),
                            rs.getInt("usuario_id"),
                            rs.getInt("curso_id"),
                            rs.getTimestamp("fecha_alta").toLocalDateTime()
                    );

                    inscripciones.add(i);
                }
            }

        } catch (SQLException e) {
            throw new PersistenceException("Error obteniendo inscripciones por usuario", e);
        }
        return inscripciones;
    }
}
