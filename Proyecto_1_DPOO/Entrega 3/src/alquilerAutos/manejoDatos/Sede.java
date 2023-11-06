package alquilerAutos.manejoDatos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import alquilerAutos.logica.FormateadorDatos;

public class Sede
{
	private String nombre;
	private FormateadorDatos formateador = new FormateadorDatos();
	private Map<String, ArrayList<Vehiculo>> inventario = new HashMap<>();
	// estan los vehiculos que se encuentran disponibles separados por categoria
	// y separados estan los que se encuentran en Mantenimiento o en Reserva
	private Map<String, RangoHoras> horario = new HashMap<>();// La llave es el
																// dia de la
																// semana, el
																// valor es un
																// rango
																// de horas
	private String ubicacion;
	
	

	/**
	 * Crea una nueva sede
	 * 
	 * @param nombre
	 * @param secuenciaDiasHoras
	 * @param ubicacion
	 */
	public Sede(String nombre, String secuenciaDiasHoras, String ubicacion)
	{// el formato es Dia horainicio horafin,
		// cada bloque separado por comas
		this.nombre = nombre;
		this.ubicacion = ubicacion;

		String[] datosDias = secuenciaDiasHoras.split(",");
		for (String datoDia : datosDias)
		{
			String[] horas = datoDia.split(" ");
			RangoHoras rangoHoras = new RangoHoras(horas[1], horas[2]);
			horario.put(horas[0].toUpperCase(), rangoHoras);
		}
		
		String[] categorias = {"Pequeño", "Mediano", "Grande", "De Lujo", "Mantenimiento", "Reserva"};
		 
		for (String cat: categorias) {
			ArrayList<Vehiculo> agregar = new ArrayList<>();
			inventario.put(cat, agregar);
		}
		

	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Retorna un set de strings con los dias que abre la sede
	 * 
	 * @return Set de strings con los dias que abre
	 */
	public Set<String> getDiasApertura()
	{
		return horario.keySet();
	}

	/**
	 * Retorna el RangoHoras en las que está abierto un dia la sede
	 * 
	 * @param dia
	 *            de la semana
	 * @return objeto tipo RangoHoras con los datos de ese dia
	 */
	public RangoHoras getHorasApertura(String dia)
	{
		RangoHoras retorno = horario.get(dia.toUpperCase());
		return retorno;
	}

	/**
	 * retorna 2 elementos en la lista
	 */
	public ArrayList<String> getUbicacion(Vehiculo vehiculo)
	{
		ArrayList<String> retorno = new ArrayList<>();
		retorno.add(nombre);
		if (checkMantenimiento(vehiculo)) {
			retorno.add("0");
		}else if (checkReservado(vehiculo)) {
			retorno.add("1");
		} else {
			retorno.add("2");
		}
		
		return retorno;
	}
	
	public boolean checkMantenimiento(Vehiculo vehiculo) {
		ArrayList<Vehiculo> vehiculosMantenimiento = inventario.get("Mantenimiento");
		if (vehiculosMantenimiento.contains(vehiculo)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkReservado (Vehiculo vehiculo) {
		ArrayList<Vehiculo> vehiculosReservados = inventario.get("Reservados");
		if (vehiculosReservados.contains(vehiculo)) {
			return true;
		} else {
			return false;
		}
	}

	public void setUbicacion(String ubicacion)
	{
		this.ubicacion = ubicacion;
	}
	
	public Vehiculo getVehiculoCategoria (String cat) {
		Vehiculo retorno = null;
		
		ArrayList<Vehiculo> datos = inventario.get(cat);
		if (datos.size() > 0) {
			retorno = datos.get(0);
		}
		
		return retorno;
		
	}
	
	public Vehiculo getVehiculoPlaca (String placa) {
		Vehiculo retorno = null;
		for (String llave: inventario.keySet()) {
			ArrayList<Vehiculo> datos = inventario.get(llave);
			for (Vehiculo revisar: datos) {
				if (revisar.getPlaca().equals(placa)) {
					retorno = revisar;
					break;
				}
			}
			if (retorno != null) {
				break;
			}
		}
		return retorno;
	}
	
	public void agregarVehiculo(Vehiculo vehiculo) {
		ArrayList<String> dataUbicacion = vehiculo.getUbicacion();
		
		if (dataUbicacion.get(1).equals("2")) {
			ArrayList<Vehiculo> categoria = inventario.get(vehiculo.getCategoria());
			categoria.add(vehiculo);
			inventario.put(vehiculo.getCategoria(), categoria);
		} else if (dataUbicacion.get(1).equals("0")){
			ArrayList<Vehiculo> categoria = inventario.get("Mantenimiento");
			categoria.add(vehiculo);
			inventario.put("Mantenimiento", categoria);
		} else if (dataUbicacion.get(1).equals("1")) {
			ArrayList<Vehiculo> categoria = inventario.get("Reserva");
			categoria.add(vehiculo);
			inventario.put("Reserva", categoria);
		}
	}
	
	public void quitarVehiculo(Vehiculo vehiculo) {
		ArrayList<String> dataUbicacion = vehiculo.getUbicacion();
		if (dataUbicacion.get(1).equals("2")) {
			ArrayList<Vehiculo> datos = inventario.get(vehiculo.getCategoria());
			datos.remove(vehiculo);
			inventario.put(vehiculo.getCategoria(), datos);
		} else if (dataUbicacion.get(1).equals("1")) {
			ArrayList<Vehiculo> datos = inventario.get("Reserva");
			datos.remove(vehiculo);
			inventario.put("Reserva", datos);
		} else if (dataUbicacion.get(1).equals("2")){
			ArrayList<Vehiculo> datos = inventario.get("Mantenimiento");
			datos.remove(vehiculo);
			inventario.put("Mantenimiento", datos);
		}
	}
	
	public void ponerVehiculoReservado(Vehiculo vehiculo) {
		ArrayList<Vehiculo> categoria = inventario.get(vehiculo.getCategoria());
		categoria.remove(vehiculo);
		inventario.put(vehiculo.getCategoria(), categoria);
		ArrayList<String> ubicacion = new ArrayList<>();
		ubicacion.add(this.nombre);
		ubicacion.add("1");
		vehiculo.setUbicacion(ubicacion);
		agregarVehiculo(vehiculo);
	}
	
	public void ponerMantenimientoVehiculo (Vehiculo vehiculo) {
		ArrayList<Vehiculo> categoria = inventario.get(vehiculo.getCategoria());
		categoria.remove(vehiculo);
		inventario.put(vehiculo.getCategoria(), categoria);
		ArrayList<String> ubicacion = new ArrayList<>();
		ubicacion.add(this.nombre);
		ubicacion.add("0");
		vehiculo.setUbicacion(ubicacion);
		agregarVehiculo(vehiculo);
	}
	
	public void quitarVehiculoReservado(Vehiculo vehiculo) {
		ArrayList<Vehiculo> reservados = inventario.get("Reserva");
		reservados.remove(vehiculo);
		inventario.put("Reserva", reservados);
		vehiculo.setUbicacion(getUbicacion(vehiculo));
		agregarVehiculo(vehiculo);
	}
	
	public void quitarVehiculoMantenimiento(Vehiculo vehiculo) {
		ArrayList<Vehiculo> mantenimiento = inventario.get("Mantenimiento");
		mantenimiento.remove(vehiculo);
		inventario.put("Mantenimiento", mantenimiento);
		vehiculo.setUbicacion(getUbicacion(vehiculo));
		agregarVehiculo(vehiculo);
	}
	

	@Override
	public String toString()
	{
		String horarioString = "";
		for (String dia : horario.keySet())
		{
			RangoHoras rango = horario.get(dia);
			String horaInicio = formateador.generarHoraStr(rango.getInicio());
			String horaFinal = formateador.generarHoraStr(rango.getFin());
			if (!horarioString.equals(""))
			{
				horarioString = horarioString + "," + dia + " " + horaInicio
						+ " " + horaFinal;
			} else
			{
				horarioString = dia + " " + horaInicio + " " + horaFinal;
			}
		}
		return nombre + ";" + horarioString + ";" + ubicacion;
	}

	// FUNCIONES PARA EL SISTEMA DE RESERVAS
	
	public boolean revisarDisponibilidad(String fecha)
	{
		String[] diasHora = fecha.split(" ");
		String dia = diaSemana(diasHora[0]);
		RangoHoras horarioDia = this.horario.get(dia);
		
		return horarioDia.contieneHora(diasHora[1]);
	}
	
    public static String diaSemana(String fecha)
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate fechaLocalDate = LocalDate.parse(fecha, formatter);
    	DayOfWeek diaSemana = fechaLocalDate.getDayOfWeek();
    	
    	return diaSemana.toString();
    }

}
