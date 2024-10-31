/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.multitask;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadosDAO {

 // Método para agregar un empleado
public boolean agregarEmpleado(String nombre, String cargo, double salario) {
    // Verificamos si el empleado ya existe
    if (existeEmpleado(nombre)) {
        System.err.println("El empleado " + nombre + " ya existe en la base de datos.");
        return false; // Retorna false si el empleado ya existe
    }

    String sql = "INSERT INTO empleados (nombre, cargo, salario) VALUES (?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nombre);
        stmt.setString(2, cargo);
        stmt.setDouble(3, salario);
        stmt.executeUpdate();
        return true; // Retorna true si se agregó con éxito
    } catch (SQLException e) {
        // Captura el error de SQL y proporciona un mensaje más claro
        System.err.println("Error al agregar empleado: " + e.getMessage());
        return false;
    }
}
    // Método para verificar si un empleado existe por nombre
    public boolean existeEmpleado(String nombre) {
        String sql = "SELECT COUNT(*) FROM empleados WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Retorna true si el conteo es mayor a 0
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de empleado: " + e.getMessage());
        }
        return false;
    }
    

    // Método para obtener todos los empleados
    public List<String[]> obtenerEmpleados() {
        List<String[]> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                empleados.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    rs.getString("cargo"),
                    String.valueOf(rs.getDouble("salario"))
                });
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener empleados: " + e.getMessage());
        }
        return empleados;
    }

    // Método para eliminar un empleado por ID
    public boolean eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleados WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }
    // Método para obtener empleados por nombre
public List<String[]> obtenerEmpleadosPorNombre(String nombreBuscado) {
    List<String[]> empleadosFiltrados = new ArrayList<>();
    String sql = "SELECT * FROM empleados WHERE nombre LIKE ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Usamos LIKE para buscar nombres que contengan el texto buscado
        stmt.setString(1, "%" + nombreBuscado + "%");
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                empleadosFiltrados.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    rs.getString("cargo"),
                    String.valueOf(rs.getDouble("salario"))
                });
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener empleados por nombre: " + e.getMessage());
    }
    return empleadosFiltrados;
}



}
    

