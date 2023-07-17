package dao;

import modelo.Mascota;

public interface CRUDsMascota {

	int crearMascota(Mascota mascota);
	Mascota leerMascota(int id);
	int actualizarMascota(Mascota mascota);
	int eliminarMascota(int id);
}
