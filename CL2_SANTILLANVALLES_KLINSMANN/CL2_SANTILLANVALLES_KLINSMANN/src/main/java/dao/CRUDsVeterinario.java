package dao;

import java.util.ArrayList;

import modelo.Veterinario;

public interface CRUDsVeterinario {
	
	int crearVeterinario(Veterinario veterinario);
	Veterinario leerVeterinario(int id);
	int actualizarVeterinario(Veterinario veterinario);
	int eliminarVeterinario(int id);
	ArrayList<Veterinario> listarVeterinario();
}
