package com.mycompany.multitask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja las operaciones de base de datos relacionadas con los usuarios.
 */
public class UsuariosDAO {

    // Método para registrar un nuevo usuario
    public boolean registrarUsuario(String nombre, String contraseña, String tipoUsuario) {
        String sql = "INSERT INTO usuarios (nombre, contraseña, tipo_usuario) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, contraseña);
            stmt.setString(3, tipoUsuario);
            stmt.executeUpdate();
            System.out.println("Usuario registrado exitosamente.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar el usuario: " + e.getMessage());
            return false;
        }
    }
 // Método para verificar si el usuario ya existe en la base de datos
    public boolean existeUsuario(String nombre) {
        String sql = "SELECT 1 FROM usuarios WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true si existe el usuario
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia del usuario: " + e.getMessage());
            return false;
        }
    }
    // Método para verificar si el usuario existe
    public boolean verificarUsuario(String nombre, String contraseña, String tipoUsuario) {
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ? AND tipo_usuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, contraseña);
            stmt.setString(3, tipoUsuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true si existe el usuario
        } catch (SQLException e) {
            System.err.println("Error al verificar el usuario: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener el tipo de usuario
    public String obtenerTipoUsuario(String nombre, String contraseña) {
        String sql = "SELECT tipo_usuario FROM usuarios WHERE nombre = ? AND contraseña = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("tipo_usuario");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el tipo de usuario: " + e.getMessage());
        }
        return null;
    }

    // Método para obtener todos los usuarios
    public List<String[]> obtenerUsuarios() {
        String sql = "SELECT nombre, contraseña, tipo_usuario FROM usuarios";
        List<String[]> usuarios = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new String[]{
                    rs.getString("nombre"),
                    rs.getString("contraseña"),
                    rs.getString("tipo_usuario")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los usuarios: " + e.getMessage());
        }
        return usuarios;
    }
}
