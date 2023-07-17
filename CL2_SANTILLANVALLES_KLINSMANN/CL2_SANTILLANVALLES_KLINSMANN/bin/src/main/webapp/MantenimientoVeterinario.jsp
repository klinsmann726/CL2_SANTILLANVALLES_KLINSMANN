<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.*"%>
<%@ page import="dao.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%!Veterinario veterinario = null;%>
	<button type="button" class="btn btn-primary" data-bs-toggle="modal"
		data-bs-target="#ModalCrearPez">Insertar veterinario</button>
	<%
	if (request.getAttribute("mensaje") != null) {
		if (request.getAttribute("mensaje").toString().substring(0, 1).equals(" ")) {
	%>
	<div class="alert alert-success">
		<strong> <%=request.getAttribute("mensaje")%></strong>
	</div>
	<%
	} else {
	%>
	<div class="alert alert-danger">
		<strong> <%=request.getAttribute("mensaje")%></strong>
	</div>
	<%
	}
	}
	%>
	<div class="container mt-3">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Fecha de nacimiento</th>
					<th>Sueldo</th>
					<th>Especialidad</th>
					<th colspan="2">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
				ArrayList<Veterinario> listaVeterinario = (ArrayList<Veterinario>) request.getAttribute("lista");
				if (listaVeterinario != null) {
					for (int i = 0; i < listaVeterinario.size(); i++) {
				%>
				<form action="" method="get">
					<tr>
						<td><label class="form-check-label"> <%=listaVeterinario.get(i).getId()%>
						</label></td>
						<td><label class="form-check-label"> <%=listaVeterinario.get(i).getNombre()%>
						</label></td>
						<td><label class="form-check-label"> <%=listaVeterinario.get(i).getApellido()%>
						</label></td>
						<td><label class="form-check-label"> <%=listaVeterinario.get(i).getFecha_nacimiento()%>
						</label></td>
						<td><label class="form-check-label"> <%=listaVeterinario.get(i).getSueldo()%>
						</label></td>
						<td><label class="form-check-label"> <%=listaVeterinario.get(i).getEspecialidad()%>
						</label></td>
						<td>
							<button type="button" class="btn btn-primary" name="botonAct"
								data-bs-toggle="modal" data-bs-target="#ModalActualizarPez">Actualizar</button>
						</td>
						<td>
							<button type="button" class="btn btn-danger" name="botonEl"
								data-bs-toggle="modal" data-bs-target="#ModalEliminarPez">Eliminar</button>
						</td>
					</tr>
				</form>
				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>


	<div class="modal" id="ModalCrearVeterinario">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Crear registro en la tabla veterinario</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form>
						<input type="text" class="form-control"
							placeholder="Ingrese la nombre" name="nombre"><br>
						<input type="text" class="form-control"
							placeholder="Ingrese la apellido" name="apellido"><br>
						<input type="date" class="form-control" name="fecha_nacimiento"><br>
						<input type="text" class="form-control"
							placeholder="Ingrese el sueldo" name="sueldo"><br>
						<input type="text" class="form-control"
							placeholder="Ingrese la especialidad" name="especialidad"><br>
						<button type="submit" class="btn btn-primary" name="botonCrear"
							value="Añadir pez">Ingresar registro</button>
					</form>
				</div>
			</div>
		</div>
	</div>





	<div class="modal" id="ModalActualizarVeterinario">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Actualizar registro en la tabla Veterinario</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<form action="ControladorPez" method="get">
						<input type="text" class="form-control" name="nombre"><br>
						<input type="text" class="form-control" name="apellido"><br>
						<input type="date" class="form-control" name="fecha_nacimiento"><br>
						<input type="text" class="form-control" name="sueldo"><br>
						<input type="text" class="form-control" name="especialidad"><br>
						<button type="submit" class="btn btn-primary"
							name="botonActualizar" value="Actualizar pez">Actualizar
							registro</button>
					</form>
				</div>
			</div>
		</div>
	</div>





	<div class="modal" id="ModalEliminarVeterinario">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Eliminar registro en la tabla veterinario</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<h3>¿Realmente desea eliminar este veterinario?</h3>
					<form action="ControladorPez" method="get">
						<input type="number" class="form-control" name="id"><br>
						<button type="submit" name="botonEliminar" class="btn-danger">Sí,
							sí deseo aliminar al Veterinario.</button>
					</form>
					<button type="button" class="btn-primary" data-bs-dismiss="modal">No,
						no deseo eliminar al veterinario</button>
				</div>
			</div>
		</div>
	</div>


</body>
</html>