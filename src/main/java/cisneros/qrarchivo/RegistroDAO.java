package cisneros.qrarchivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistroDAO {
    public void insertarRegistro(Registro registro) throws SQLException {
        String sql = "INSERT INTO registro (antecedente, strTribunal, numExpediente, year, nombreUsuaria, estatusM, fechaPrestamo, fechaDevolucion, hora) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, registro.getAntecedente());
            pstmt.setString(2, registro.getStrTribunal());
            pstmt.setInt(3, registro.getNumExpediente());
            pstmt.setInt(4, registro.getYear());
            pstmt.setString(5, registro.getNombreUsuaria());
            pstmt.setBoolean(6, registro.isEstatusM());
            pstmt.setDate(7, registro.getFechaPrestamo());
            pstmt.setDate(8, registro.getFechaDevolucion());
            pstmt.setTime(9, registro.getHora());
            pstmt.executeUpdate();
        }
    }

    public Registro obtenerRegistroPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM registro WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Registro registro = new Registro();
                registro.setId(rs.getLong("id"));
                registro.setAntecedente(rs.getInt("antecedente"));
                registro.setStrTribunal(rs.getString("strTribunal"));
                registro.setNumExpediente(rs.getInt("numExpediente"));
                registro.setYear(rs.getInt("year"));
                registro.setNombreUsuaria(rs.getString("nombreUsuaria"));
                registro.setEstatusM(rs.getBoolean("estatusM"));
                registro.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                registro.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                registro.setHora(rs.getTime("hora"));
                return registro;
            }
        }
        return null;
    }

    public void actualizarRegistro(Registro registro) throws SQLException {
        String sql = "UPDATE registro SET antecedente = ?, strTribunal = ?, numExpediente = ?, year = ?, nombreUsuaria = ?, estatusM = ?, fechaPrestamo = ?, fechaDevolucion = ?, hora = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, registro.getAntecedente());
            pstmt.setString(2, registro.getStrTribunal());
            pstmt.setInt(3, registro.getNumExpediente());
            pstmt.setInt(4, registro.getYear());
            pstmt.setString(5, registro.getNombreUsuaria());
            pstmt.setBoolean(6, registro.isEstatusM());
            pstmt.setDate(7, registro.getFechaPrestamo());
            pstmt.setDate(8, registro.getFechaDevolucion());
            pstmt.setTime(9, registro.getHora());
            pstmt.setLong(10, registro.getId());
            pstmt.executeUpdate();
        }
    }

    public void eliminarRegistro(Long id) throws SQLException {
        String sql = "DELETE FROM registro WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
}