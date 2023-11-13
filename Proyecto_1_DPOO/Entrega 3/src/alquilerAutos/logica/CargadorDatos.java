package alquilerAutos.logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import alquilerAutos.manejoDatos.Cliente;
import alquilerAutos.manejoDatos.LicenciaConducir;
import alquilerAutos.manejoDatos.Reserva;
import alquilerAutos.manejoDatos.Sede;
import alquilerAutos.manejoDatos.Tarifa;
import alquilerAutos.manejoDatos.Usuario;
import alquilerAutos.manejoDatos.Vehiculo;

public class CargadorDatos {
	
	private FormateadorDatos formateador = new FormateadorDatos();

	public ArrayList<String> cargarLista(File archivo) throws Exception {
		FileReader fr = new FileReader(archivo);
		BufferedReader in = new BufferedReader(fr);
		String linea = in.readLine();
		ArrayList<String> retorno = new ArrayList<>();

		while (linea != null) {
			String lineaFinal = linea.replaceAll("\n", linea);
			retorno.add(lineaFinal);
			linea = in.readLine();
		}

		in.close();
		
		return retorno;
	}
	
	public Sede cargarSede (String info) {
		String[] data = info.split(";");
		Sede retorno = new Sede(data[0], data[1], data[2]);
		return retorno;	
	}
	
	public Vehiculo cargarVehiculo (String info) {
		String[] data = info.split(";");
		Vehiculo retorno = new Vehiculo(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
		retorno.cargarHistorial(data[7]);
		
		return retorno;
	}
	
	public Usuario cargarUsuario(String info) {
		String[] data = info.split(";");
		Usuario retorno = new Usuario(data[0], data[1], data[2]);
		return retorno;
	}
	
	public Cliente cargarCliente (String info) {
		String[] data = info.split(";");
		String rutaImagen = "Datos/imagenDocumento/" + data[1] + ".jpg";
		Cliente retorno = new Cliente(data[0], Integer.parseInt(data[1]), data[2], data[3], rutaImagen, Integer.parseInt(data[7]), data[8], data[4], data[5], Integer.parseInt(data[6]));
		if (data[8].equals("1")) {
			retorno.setBloqueoPago(true);
		}
		return retorno;
	}
	
	public LicenciaConducir cargarLicencia (String info) {
		String[] data = info.split(";");
		String rutaImagen = "Datos/imagenLicencia/" + data[1] + ".jpg";
		LicenciaConducir retorno = new LicenciaConducir(Integer.parseInt(data[0]), data[1], data[2], rutaImagen);
		return retorno;
	}
	
	public Reserva cargarReserva (String info) {
		String[] data = info.split(";");
		Reserva retorno = new Reserva (Integer.parseInt(data[0]), data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[6]), info);
		if (data[7].equals("0")) {
			retorno.setPendiente(false);
		}
		retorno.setVehiculo(data[8]);
		retorno.setSedeInicio(data[9]);
		retorno.setSedeFinal(data[10]);
		
		return retorno;
	}
	
	public Tarifa cargarTarifas (String info) {
		String[] data = info.split(";");
		Tarifa retorno = new Tarifa (data[0], data[1], data[2], Integer.parseInt(data[3]));
		return retorno;
	}
	
	public LocalDate cargarFecha() throws IOException {
		FileReader fr = new FileReader("Datos/fecha.txt");
		BufferedReader in = new BufferedReader(fr);
		String linea = in.readLine();
		
		LocalDate retorno = formateador.generarFecha(linea);
		in.close();
		
		return retorno;
		
	}
}
