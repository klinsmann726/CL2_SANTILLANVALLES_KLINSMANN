package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Veterinario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import dao.DaoVeterinario;

/**
 * Servlet implementation class ControladorVeterinario
 */
public class ControladorVeterinario extends HttpServlet {
	
	private PrintWriter salida;
	DaoVeterinario veterinarioadmin = new DaoVeterinario();
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorVeterinario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = 0;
		Veterinario veterinario = null;
		if(request.getParameter("botonActualizar")!=null) {
			String nombre=request.getParameter("nombre");
			String apellido=request.getParameter("apellido");
			Date fecha_nacimiento=Date.valueOf(request.getParameter("fecha_nacimiento"));
			Double sueldo=Double.parseDouble(request.getParameter("sueldo"));
			String especialidad=request.getParameter("especialidad");
			veterinario=new Veterinario(id, nombre, apellido, fecha_nacimiento, sueldo, especialidad);
			actualizarVeterinario(veterinario,request, response);
		}
		if(request.getParameter("botonEliminar")!=null) {
			id=Integer.parseInt(request.getParameter("id"));
			eliminarPez(id,request, response);
		}
		if(request.getParameter("botonEnvio")!=null) {
			mantenimientoPez(request, response);
		}
		if(request.getParameter("botonCrear")!=null) {
			crearPez(request, response);
		}
		protected void crearPez(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			System.out.println("Entraste a controlador para crear veterinario.");
			String mensaje="";
			String url="MantenimientoVeterinario.jsp";
			
			
			String especie=request.getParameter("especie");
			Date fecnac=Date.valueOf(request.getParameter("fecnac"));
			int esperanza_vida=Integer.parseInt(request.getParameter("esperanza"));
			double precio=Double.parseDouble(request.getParameter("precio"));
			double peso=Double.parseDouble(request.getParameter("peso"));
			int id_categoria=Integer.parseInt(request.getParameter("id_categoria"));
			
			if(0!=veterinarioadmin.crearVeterinarioa(new veterinario(0, especie, fecnac, esperanza_vida, precio, peso,id_categoria))) {
				mensaje=" La inserción fue realizada correctamente.";
			}
			else {
				mensaje="La inserción no fue realizada.";
			}
			ArrayList<Veterinario> lista=veterinarioadmin.listarPez();
			request.setAttribute("lista", lista);
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher(url).forward(request, response);
		}
		protected void actualizarVeterinario(Veterinario pez,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("Entraste a controlador para actualizar peces.");
			String mensaje="";		
			String url="Mantenimiento.jsp";		
			
			if(0!=pezAdmin.actualizarPez(pez)) {
				mensaje+=" La actualización fue realizada correctamente.";
			}
			else {
				mensaje+="La actualización no fue realizada.";
			}
			ArrayList<Pez> lista=pezAdmin.listarPez();
			request.setAttribute("lista", lista);
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher(url).forward(request, response);
		}
		protected void eliminarPez(int id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("Entraste a controlador para eliminar peces.");
			int eliminacion=pezAdmin.eliminarPez(id);
			ArrayList<Pez> lista=pezAdmin.listarPez();
			request.setAttribute("lista", lista);
			String mensaje="";
			String url="Mantenimiento.jsp";
			
			if(eliminacion!=0) {
				mensaje+=" La eliminación fue realizada correctamente.";
			}
			else {
				mensaje+="La eliminación no fue realizada.";
			}
			
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher(url).forward(request, response);
		} 
		protected void leerPez(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("Entraste a controlador para leer peces.");
			int id=Integer.parseInt(request.getParameter("id"));
			String mensaje="<br><br><br>";
			
			Pez pez=pezAdmin.leerPez(id);
			String url="index.jsp";
			if(pez!=null) {
				request.setAttribute("pez", pez);
				request.getRequestDispatcher(url).forward(request, response);
			}
			else {
				mensaje+="Pez no encontrado.";
				request.setAttribute("respuesta", mensaje);
				request.getRequestDispatcher(url).forward(request, response);
			}
		} 
		protected void listarPez(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			System.out.println("Entraste a controlador para lista de peces.");
			Object [] tablaPez;
			String url="dem.jsp";
			String mensaje="<br><br><br>";
			
			ArrayList<Pez> tablaCompletaPez=pezAdmin.listarPez();	
			
			if(!tablaCompletaPez.isEmpty()) {
				request.setAttribute("lista", tablaCompletaPez);
				request.getRequestDispatcher(url).forward(request, response);
			}
			else {
				mensaje+="No hay registros en dicha tabla.";
				request.setAttribute("respuesta", mensaje);
				request.getRequestDispatcher(url).forward(request, response);			
			}
			System.out.println(mensaje);
		} 
		protected void mantenimientoPez(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url="Mantenimiento.jsp";
			String mensaje="";
			ArrayList<Pez> lista=pezAdmin.listarPez();
			request.setAttribute("lista", lista);
			request.getRequestDispatcher(url).forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
