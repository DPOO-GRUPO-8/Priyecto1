package alquilerAutos.manejoDatos;

import java.io.File;
import java.util.ArrayList;

public class Cliente implements Ubicacion
{

	// Atributos

	private String nombre;
	private int documento;
	private String nacionalidad;
	private String fechaNacimiento;
	private File imagenDocumento;
	private int numeroPago;
	private boolean bloqueoPago = false; // si está en true, está bloqueada
	private int licencia;
	private String numeroCelular;
	private String correoElectronico;

	public Cliente(String nombre, int documento, String nacionalidad,
			String fechaNacimiento, String rutaImagen, int numeroTarjeta,
			String bloqueo, String numeroCelular, String correoElectronico,
			int numeroLicencia)
	{
		this.nombre = nombre;
		this.documento = documento;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		imagenDocumento = new File(rutaImagen);
		numeroPago = numeroTarjeta;
		this.numeroCelular = numeroCelular;
		this.correoElectronico = correoElectronico;

		if (bloqueo.equals("1"))
		{
			bloqueoPago = true;
		} else
		{
			bloqueoPago = false;
		}

	}

	public String getFechaNacimiento()
	{
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento)
	{
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad()
	{
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad)
	{
		this.nacionalidad = nacionalidad;
	}

	public String getNumeroCelular()
	{
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular)
	{
		this.numeroCelular = numeroCelular;
	}

	public String getCorreoElectronico()
	{
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico)
	{
		this.correoElectronico = correoElectronico;
	}

	public void agregarLicencia(int licencia)
	{
		this.licencia = licencia;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getDocumento()
	{
		return documento;
	}

	public void setDocumento(int documento)
	{
		this.documento = documento;
	}

	public File getImagenDocumento()
	{
		return imagenDocumento;
	}

	public void setImagenDocumento(File imagenDocumento)
	{
		this.imagenDocumento = imagenDocumento;
	}

	public int getNumeroPago()
	{
		return numeroPago;
	}

	public void setNumeroPago(int numeroPago)
	{
		this.numeroPago = numeroPago;
	}

	public boolean isBloqueoPago()
	{
		return bloqueoPago;
	}

	public void setBloqueoPago(boolean bloqueoPago)
	{
		this.bloqueoPago = bloqueoPago;
	}

	@Override
	public String toString()
	{
		int estadoTarjeta;
		if (bloqueoPago)
		{
			estadoTarjeta = 1;
		} else
		{
			estadoTarjeta = 0;
		}
		return nombre + ";" + 
				documento + ";"+
				nacionalidad + ";"+
				fechaNacimiento + ";"+
				numeroCelular + ";"+
				correoElectronico + ";"+
				licencia + ";"+
				numeroPago + ";"+ estadoTarjeta
				;
	}

	public int getLicencia()
	{
		return licencia;
	}

	public void setLicencia(int licencia)
	{
		this.licencia = licencia;
	}

	/**
	 * retorna 3 elementos en la lista
	 */
	public ArrayList<String> getUbicacion()
	{
		ArrayList<String> retorno = new ArrayList<>();
		retorno.add(nombre);
		retorno.add(correoElectronico);
		retorno.add("" + numeroCelular);
		return retorno;

	}

	// FUNCIONES PARA EL SISTEMA DE RESERVAS

	public Reserva crearReserva(String fechaHoraInicio, String fechaHoraFin,
			String categoria, String sede, int precio)
	{
		/**
		 * PARAMETROS fechaInicio: La fecha de incio de la reserva. fechaFinal:
		 * La fecha final de la reserva. categoriaVehiculo: La categoria que
		 * desea reservar el cliente. RETORNO reserva: La reserva realizada con
		 * los datos ingresados
		 */
		
		Reserva reserva = new Reserva(0, fechaHoraInicio, fechaHoraFin, categoria,
				this.documento, precio, sede);
		
		return reserva;
	}
	public void modificarReserva(String fechaHoraInicio, String fechaHoraFin,
			String categoria)
	{

	}

}
