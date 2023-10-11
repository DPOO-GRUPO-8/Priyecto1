package alquilerAutos.manejoDatos;
import java.io.File;
import java.time.*;
import java.time.format.DateTimeFormatter;

import alquilerAutos.logica.FormateadorDatos;

public class LicenciaConducir {
	private int numero;
	private String paisExpedicion;
	private LocalDate fechaVencimiento;
	private File imagenLicencia;
	private FormateadorDatos Formateador = new FormateadorDatos();
	
	public LicenciaConducir (int numero, String pais, String fecha, String rutaImagen) {
		this.numero = numero;
		this.paisExpedicion = pais;
		this.fechaVencimiento = Formateador.generarFecha(fecha);
		this.imagenLicencia = new File (rutaImagen);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPaisExpedicion() {
		return paisExpedicion;
	}

	public void setPaisExpedicion(String paisExpedicion) {
		this.paisExpedicion = paisExpedicion;
	}

	public LocalDate getFechaExpedicion() {
		return fechaVencimiento;
	}

	public void setFechaExpedicion(LocalDate fechaExpedicion) {
		this.fechaVencimiento = fechaExpedicion;
	}

	public File getImagenLicencia() {
		return imagenLicencia;
	}

	public void setImagenLicencia(File imagenLicencia) {
		this.imagenLicencia = imagenLicencia;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaVencimientoStr = fechaVencimiento.format(formatter);
		return numero +";" +
				paisExpedicion + ";" +
				fechaVencimientoStr;
	}
	

	
}
