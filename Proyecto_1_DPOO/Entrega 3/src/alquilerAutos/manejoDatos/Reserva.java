package alquilerAutos.manejoDatos;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import alquilerAutos.logica.FormateadorDatos;

public class Reserva
{
	private static int cantidad = 0;
	private int id;
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFinal;
	private String tipoVehiculo;
	private int cliente;
	private List<LicenciaConducir> licencias = new ArrayList<>();
	private int precio;
	private FormateadorDatos formateador = new FormateadorDatos();
	private boolean pendiente = true;
	private String vehiculo; // es la placa del vehiculo asignado a la reserva
	private String sede;

	/**
	 * Al crear una nueva reserva, utilizar como id 0
	 * 
	 * @param id
	 * @param fechaInicio
	 * @param fechaFinal
	 * @param tipoVehiculo
	 * @param cliente
	 * @param precio
	 */
	public Reserva(int id, String fechaInicio, String fechaFinal,
			String tipoVehiculo, int cliente, int precio, String sede)
	{
		Reserva.nuevaReserva();
		if (id != cantidad && id != 0)
		{
			this.id = id;
		} else
		{
			this.id = cantidad;
		}
		this.fechaHoraInicio = formateador.generarHoraFecha(fechaInicio);
		this.fechaHoraFinal = formateador.generarHoraFecha(fechaFinal);
		this.tipoVehiculo = tipoVehiculo;
		this.cliente = cliente;
		this.precio = precio;
	}

	/**
	 * Agrega una licencia a la lista de licencias adicionales de la reserva en
	 * el caso de que no este
	 * 
	 * @param licencia
	 *            a agregar
	 * @return true si se pudo agregar la licencia, false de lo contrario
	 */
	public boolean agregarLicencia(LicenciaConducir licencia,
			int licenciaCliente)
	{

		boolean retorno = true;

		for (LicenciaConducir agregada : licencias)
		{
			if (agregada.getNumero() == licencia.getNumero())
			{
				retorno = false;
				break;
			}
		}

		if (retorno && (licencia.getNumero() == licenciaCliente))
		{
			retorno = false;
		}

		if (retorno)
		{
			licencias.add(licencia);
		}

		return retorno;
	}

	/**
	 * Quita una licencia de la lista de licencias registradas en la reserva
	 * 
	 * @param licencia
	 *            a quitar
	 * @return true si la licencia estaba en la lista y quito, false de lo
	 *         contrario
	 */
	public boolean quitarLicencia(LicenciaConducir licencia)
	{
		for (int i = 0; i < licencias.size(); i++)
		{
			LicenciaConducir agregada = licencias.get(i);
			if (agregada.getNumero() == licencia.getNumero())
			{
				licencias.remove(i);
				return true;

			}

		}
		return false;
	}

	public int getId()
	{
		return id;
	}

	public LocalDateTime getFechaHoraInicio()
	{
		return fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFinal()
	{
		return fechaHoraFinal;
	}

	public void setFechaHoraFinal(String fechaHoraFinal)
	{

		this.fechaHoraFinal = formateador.generarHoraFecha(fechaHoraFinal);
	}

	public String getTipoVehiculo()
	{
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo)
	{
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getCliente()
	{
		return cliente;
	}

	public void setCliente(int cliente)
	{
		this.cliente = cliente;
	}

	public int getPrecio()
	{
		return precio;
	}

	public void setPrecio(int precio)
	{
		this.precio = precio;
	}

	public boolean isPendiente()
	{
		return pendiente;
	}

	public void setPendiente(boolean pendiente)
	{
		this.pendiente = pendiente;
	}

	public void asignarVehiculo(String placa)
	{
		vehiculo = placa;
	}

	public String getVehiculo()
	{
		return vehiculo;
	}

	public String getSede()
	{
		return sede;
	}

	public void setSede(String sede)
	{
		this.sede = sede;
	}

	private static void nuevaReserva()
	{
		cantidad++;
	}

	@Override
	public String toString()
	{
		String fechaHoraInicioStr = formateador
				.generarFechaHoraStr(fechaHoraInicio);
		String fechaHoraFinalStr = formateador
				.generarFechaHoraStr(fechaHoraFinal);
		String licenciasStr = "";
		String booleano;
		String vehiculoStr;

		if (vehiculo == null)
		{
			vehiculoStr = "nulo";
		} else
		{
			vehiculoStr = vehiculo;
		}

		if (pendiente == true)
		{
			booleano = "1";
		} else
		{
			booleano = "0";
		}

		for (LicenciaConducir licencia : licencias)
		{
			licenciasStr = licenciasStr + " " + licencia.getNumero();
		}

		if (licenciasStr.equals(""))
		{
			licenciasStr = "nulo";
		}

		return id + ";" + fechaHoraInicioStr + ";" + fechaHoraFinalStr + ";"
				+ tipoVehiculo + ";" + cliente + ";" + licenciasStr + ";"
				+ precio + ";" + booleano + ";" + vehiculoStr;
	}

	public void setVehiculo(String vehiculo)
	{
		this.vehiculo = vehiculo;
	}
	
	public void setFechaHoraInicio(String fechaHoraInicio) 
	{
		this.fechaHoraInicio = formateador.generarHoraFecha(fechaHoraInicio);
	}
	
	public void setLicencias(ArrayList<LicenciaConducir> licencias)
	{
		this.licencias = licencias;
	}

	// METODOS PARA EL SISTEMA DE RESERVAS

	public void crearReserva()
	{

	}

	public boolean rangoCruzado(String fechaInicioComprobar,
			String fechaFinalComprobar)
	{
		LocalDateTime fechaInicio = formateador
				.generarHoraFecha(fechaInicioComprobar);
		LocalDateTime fechaFinal = formateador
				.generarHoraFecha(fechaFinalComprobar);

		return fechaInicio.isAfter(this.fechaHoraFinal)
				|| this.fechaHoraInicio.isBefore(fechaFinal);
	}

}
