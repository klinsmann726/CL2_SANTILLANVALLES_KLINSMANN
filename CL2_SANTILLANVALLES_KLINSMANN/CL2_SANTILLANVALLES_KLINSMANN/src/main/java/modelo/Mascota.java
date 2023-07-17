package modelo;

import java.sql.Date;

public class Mascota {
	 
	private int id;
	private String nombre;
	private String especie;
	private Date fecha_nacimiento;
	private String dolencia;
	
	public Mascota(int id, String nombre, String especie, Date fecha_nacimiento, String dolencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.especie = especie;
		this.fecha_nacimiento = fecha_nacimiento;
		this.dolencia = dolencia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getDolencia() {
		return dolencia;
	}

	public void setDolencia(String dolencia) {
		this.dolencia = dolencia;
	}
	
	
	
}
