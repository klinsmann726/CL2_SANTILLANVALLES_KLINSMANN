package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexiones.Conexion;
import modelo.Mascota;
import modelo.Veterinario;

public class DaoMascota implements CRUDsMascota{
	
	Connection conex;
	
	public DaoMascota() {
		this.conex = Conexion.getConnection();
	}
	@Override
	public int crearMascota(Mascota mascota) {
		int cambios = 0;
		try {
			String consulta = "INSERT INTO veterinario (nombre, especie, fecha_nacimiento,dolencia,id) VALUES (?,?,?,?,?)";
			PreparedStatement statement = conex.prepareStatement(consulta);
			statement.setString(1, mascota.getNombre());
			statement.setString(2, mascota.getEspecie());
			statement.setDate(3, mascota.getFecha_nacimiento());
			statement.setString(4, mascota.getDolencia());
			statement.setInt(5, mascota.getId());
			cambios = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cambios;
	}

	@Override
	public Mascota leerMascota(int id) {
		Mascota mascotaRespuesta = null;
		try {
			String consulta = "SELECT * FROM mascota WHERE id = ?";
			PreparedStatement statement = conex.prepareStatement(consulta);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String nombre = resultSet.getString("nombre");
				String especie = resultSet.getString("especie");
				Date fecha_nacimiento = resultSet.getDate("fecha_nacimiento");
				String dolencia = resultSet.getString("dolencia");
				mascotaRespuesta = new Mascota(id, nombre,especie,fecha_nacimiento,dolencia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mascotaRespuesta;
	}

	@Override
	public int actualizarMascota(Mascota mascota) {
		int filasAlteras = 0;
		String consulta = "UPDATE mascota SET  nombre = ?, especie = ?, fecha_nacimiento = ?, dolencia = ? WHERE ID = ?";
		try {
			PreparedStatement statement = conex.prepareStatement(consulta);
			statement.setString(1, mascota.getNombre());
			statement.setString(2, mascota.getEspecie());
			statement.setDate(3, mascota.getFecha_nacimiento());
			statement.setString(4, mascota.getDolencia());
			statement.setInt(5, mascota.getId());
			filasAlteras = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasAlteras;
	}

	@Override
	public int eliminarMascota(int id) {
		int filasAlteradas = 0;
        String consulta = "DELETE FROM mascota WHERE id = ?";
        try {
            PreparedStatement statement = conex.prepareStatement(consulta);
            statement.setInt(1, id);
            filasAlteradas = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAlteradas;
	}

}
