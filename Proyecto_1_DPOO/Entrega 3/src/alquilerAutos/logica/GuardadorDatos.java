package alquilerAutos.logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import alquilerAutos.manejoDatos.Sede;

public class GuardadorDatos {
	
	private FormateadorDatos formateador;
	
	public void guardarDatosLista (ArrayList<String> datos, File archivo) throws IOException {
		FileWriter fw = new FileWriter(archivo);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("");
		
		for (String linea: datos) {
			bw.write(linea + "\n");
		}
		
		bw.close();
	}
	
	public void guardarLog (String placa, String data) throws IOException {
		File archivo = new File ("Datos/logVehiculos/" + placa + ".log");
		FileWriter fw = new FileWriter (archivo);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("");
		
		bw.write(data);
		
		bw.close();
		
	}
	
	public void guardarFecha(LocalDate fecha) throws IOException {
		File archivo = new File ("Datos/fecha.txt");
		FileWriter fw = new FileWriter (archivo);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("");
		String guardar = formateador.generarFechaStr(fecha);
		bw.write(guardar);
		
		bw.close();
	}
	
	public void guardarAutosDisponiblesSedeFecha(int cantidad, LocalDate fecha, Sede sede) throws IOException {
		File archivo = new File ("Datos/datosDisponibilidad/" + sede.getNombre() + " " + LocalDate.now().getYear() + ".txt");
		boolean existe = archivo.exists();
		
		if (!existe) {
			archivo.createNewFile();
		}
		
		FileWriter fw = new FileWriter (archivo, true);
		BufferedWriter bw = new BufferedWriter(fw);
		String guardar = formateador.generarFechaStr(fecha) + ";" + cantidad;
		
		bw.write(guardar);
		bw.newLine();
		bw.close();
	}
	
}
