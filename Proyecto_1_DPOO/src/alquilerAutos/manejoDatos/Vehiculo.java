package alquilerAutos.manejoDatos;

public class Vehiculo {
	private String placa;
	private String categoria;
	private String marca;
	private String modelo;
	private String transmision;
	private String historial;
	
	public Vehiculo(String placa, String categoria, String marca, String modelo, String transmision) {
		this.placa = placa;
		this.categoria = categoria;
		this.marca = marca;
		this.modelo = modelo;
		this.transmision = transmision;
	}
	
	/**
	 * Añade una reserva completada al historial de un vehiculo
	 * @param reserva
	 */
	public void agregarHistorial(Reserva reserva) {
		
	}
}
