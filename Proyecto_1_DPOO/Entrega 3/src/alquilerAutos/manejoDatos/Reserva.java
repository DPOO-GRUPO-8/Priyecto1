package alquilerAutos.manejoDatos;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import alquilerAutos.logica.FormateadorDatos;

public class Reserva {
	private static int cantidad = 0;
	private int id;
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFinal;
	private String tipoVehiculo;
	private Cliente cliente;
	private List<LicenciaConducir> licencias = new ArrayList<>();
	private int precio;
	private FormateadorDatos formateador = new FormateadorDatos();
	private boolean pendiente = true;
	private String vehiculo; // es la placa del vehiculo asignado a la reserva
	
	/**
	 * Al crear una nueva reserva, utilizar como id 0
	 * @param id
	 * @param fechaInicio
	 * @param fechaFinal
	 * @param tipoVehiculo
	 * @param cliente
	 * @param precio
	 */
	public Reserva(int id, String fechaInicio, String fechaFinal, String tipoVehiculo, Cliente cliente, int precio) {
		Reserva.nuevaReserva();
		if (id != cantidad && id != 0) {
			this.id = id;
		}else {
			this.id = cantidad;
		}
		this.fechaHoraInicio = formateador.generarHoraFecha(fechaInicio);
		this.fechaHoraFinal = formateador.generarHoraFecha(fechaFinal);
		this.tipoVehiculo = tipoVehiculo;
		this.cliente = cliente;
		this.precio = precio;
	}
	
	
	/**
	 * Agrega una licencia a la lista de licencias adicionales de la reserva en el caso de que
	 * no este
	 * @param licencia a agregar
	 * @return true si se pudo agregar la licencia, false de lo contrario
	 */
	public boolean agregarLicencia (LicenciaConducir licencia) {
		
		boolean retorno = true;
		
		for (LicenciaConducir agregada: licencias) {
			if (agregada.getNumero() == licencia.getNumero()) {
				retorno = false;
				break;
			}
		}
		
		if (retorno && (licencia.getNumero() == cliente.getNumeroLicencia())) {
			retorno = false;
		}
		
		if (retorno) {
			licencias.add(licencia);
		}
		
		return retorno;
	}
	
	/**
	 * Quita una licencia de la lista de licencias registradas en la reserva
	 * @param licencia a quitar
	 * @return true si la licencia estaba en la lista y quito, false de lo contrario
	 */
	public boolean quitarLicencia (LicenciaConducir licencia) {
		for (int i = 0; i < licencias.size(); i++) {
			LicenciaConducir agregada = licencias.get(i);
			if (agregada.getNumero() == licencia.getNumero()){
				licencias.remove(i);
				return true;
			
			} 
		
		}
		return false;
	}
	
	public int getId() {
		return id;
	}

	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFinal() {
		return fechaHoraFinal;
	}

	public void setFechaHoraFinal(String fechaHoraFinal) {
		
		this.fechaHoraFinal = formateador.generarHoraFecha(fechaHoraFinal);
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}



	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public int getPrecio() {
		return precio;
	}



	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}

	public void asignarVehiculo (String placa) {
		vehiculo = placa;
	}
	
	public String getVehiculo () {
		return vehiculo;
	}

	private static void nuevaReserva() {
		cantidad ++;
	}
	
	@Override
	public String toString() {
		String fechaHoraInicioStr = formateador.generarFechaHoraStr(fechaHoraInicio);
		String fechaHoraFinalStr = formateador.generarFechaHoraStr(fechaHoraFinal);
		String licenciasStr = "";
		String booleano;
		String vehiculoStr;
		
		if (vehiculo == null) {
			vehiculoStr = "nulo";
		} else {
			vehiculoStr = vehiculo;
		}
		
		if (pendiente == true) {
			booleano = "1";
		} else {
			booleano = "0";
		}
		
		for (LicenciaConducir licencia: licencias) {
			licenciasStr = licenciasStr + " " + licencia.getNumero();
		}
		
		return id + ";" + 
				fechaHoraInicioStr + ";" +
				fechaHoraFinalStr + ";" +
				tipoVehiculo + ";" +
				cliente.getDocumento() + ";" + 
				licenciasStr + ";" +
				precio + ";" + 
				booleano + ";" +
				vehiculoStr;
	}
	
	
}
