package alquilerAutos.manejoDatos;

import java.util.ArrayList;

public class Vehiculo {
	private String placa;
	private String categoria; // puede ser pequeño, mediano, grande, de lujo
	private String marca;
	private String modelo;
	private String color;
	private String transmision;
	private ArrayList<String> ubicacion = new ArrayList<>();
	private String historial;
	private RangoFechas reservas;
	
	public Vehiculo(String placa, String categoria, String marca, String modelo, String color, String transmision, String ubicacion) {
		this.placa = placa;
		this.categoria = categoria;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.transmision = transmision;
		String[] datosUbicacion = ubicacion.split(" ");
		for (String dato: datosUbicacion) {
			this.ubicacion.add(dato);
		}
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void cargarHistorial(String historial) {
		this.historial = historial;
	}
	
	/**
	 * Añade una reserva completada al historial de un vehiculo
	 * @param reserva
	 */
	public void agregarHistorial(Reserva reserva) {
		historial = historial + " " + reserva.getId();
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTransmision() {
		return transmision;
	}

	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}

	public ArrayList<String> getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(ArrayList<String> ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	@Override
	public String toString() {
		String ubicacionTxt = "";
		String historialAgregar = "";
		for (String elemento: ubicacion) {
			ubicacionTxt = ubicacionTxt + " " + elemento;
		}
		
		if (historial.equals("")) {
			historialAgregar = "nulo"; 
		} else {
			historialAgregar = historial;
		}
		
		return placa + ";" +
				categoria + ";" +
				marca + ";" +
				modelo +";" +
				color + ";"+
				transmision + ";"+
				ubicacionTxt + ";" +
				historialAgregar;
		
	}
	@Override
	public boolean equals(Object objeto) {
		boolean retorno = false;
		if (objeto instanceof Vehiculo){
			Vehiculo objeto1 = (Vehiculo) objeto;
			if (objeto1.getPlaca().equals(this.placa)) {
				retorno = true;
			}
		}
		return retorno;
	}
	
	// FUNCIONES PARA EL SISTEMA DE RESERVAS
	
	public String getHistorial()
	{
		return this.historial;
	}
	

	
}
