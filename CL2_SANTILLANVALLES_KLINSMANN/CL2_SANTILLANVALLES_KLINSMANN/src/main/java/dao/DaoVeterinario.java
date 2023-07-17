package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import modelo.Veterinario;

public class DaoVeterinario implements CRUDsVeterinario{

	Connection conex;
	
	public DaoVeterinario() {
		this.conex = Conexion.getConnection();
	}
	@Override
	public int crearVeterinario(Veterinario veterinario) {
		
		int cambios = 0;
		try {
			String consulta = "INSERT INTO veterinario (nombre, apellido, fecha_nacimiento,sueldo,especialidad,id) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = conex.prepareStatement(consulta);
			statement.setString(1, veterinario.getNombre());
			statement.setString(2, veterinario.getApellido());
			statement.setDate(3, veterinario.getFecha_nacimiento());
			statement.setDouble(4, veterinario.getSueldo());
			statement.setString(5, veterinario.getEspecialidad());
			statement.setInt(6, veterinario.getId());
			cambios = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cambios;
	}

	@Override
	public Veterinario leerVeterinario(int id) {
		
		Veterinario veterinarioRespuesta = null;
		try {
			String consulta = "SELECT * FROM veterinario WHERE id = ?";
			PreparedStatement statement = conex.prepareStatement(consulta);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				Date fecha_nacimiento = resultSet.getDate("fecha_nacimiento");
				Double sueldo = resultSet.getDouble("sueldo");
				String especialidad = resultSet.getString("especialidad");
				veterinarioRespuesta = new Veterinario(id, nombre,apellido,fecha_nacimiento,sueldo,especialidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return veterinarioRespuesta;
	}

	@Override
	public int actualizarVeterinario(Veterinario veterinario) {
		int filasAlteras = 0;
		String consulta = "UPDATE veterinario SET  nombre = ?, apellido = ?, fecha_nacimiento = ?, sueldo = ?, especialidad = ? WHERE ID = ?";
		try {
			PreparedStatement statement = conex.prepareStatement(consulta);
			statement.setString(1, veterinario.getNombre());
			statement.setString(2, veterinario.getApellido());
			statement.setDate(3, veterinario.getFecha_nacimiento());
			statement.setDouble(4, veterinario.getSueldo());
			statement.setString(5, veterinario.getEspecialidad());
			statement.setInt(6, veterinario.getId());
			filasAlteras = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasAlteras;
	}

	@Override
	public int eliminarVeterinario(int id) {
		
		int filasAlteradas = 0;
		String consulta = "DELETE FROM veterinario WHERE id = ?";
		try {
			PreparedStatement statement = conex.prepareStatement(consulta);
			statement.setInt(1, id);
			filasAlteradas = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return filasAlteradas;
		
	}
	@Override
	public ArrayList<Veterinario> listarVeterinario() {
		 ArrayList<Veterinario> listaRespuesta = new ArrayList<>();
	        try {
	            String consulta = "SELECT * FROM veterinario";
	            PreparedStatement statement = conex.prepareStatement(consulta);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String nombre = resultSet.getString("nombre");
	                String apellido = resultSet.getString("apellido");
	                Date fecha_nacimiento = resultSet.getDate("fecha_nacimiento");
	                Double sueldo = resultSet.getDouble("sueldo");
	                String especialidad = resultSet.getString("especialidad");
	                Veterinario pezRespuesta = new Veterinario(id, nombre, apellido, fecha_nacimiento, sueldo, especialidad);
	                listaRespuesta.add(pezRespuesta);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return listaRespuesta;
	}

}
