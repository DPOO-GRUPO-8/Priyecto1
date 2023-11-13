package alquilerAutos.manejoDatos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
		retorno.add(this.ubicacion);
		
		return retorno;
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
		String categoria = vehiculo.getCategoria();
		
		ArrayList<Vehiculo> datos = inventario.get(categoria);
		
		datos.add(vehiculo);
		
		inventario.put(categoria, datos);
	
	}
	
	public void quitarVehiculo(Vehiculo vehiculo) {
		String categoria = vehiculo.getCategoria();
		
		ArrayList<Vehiculo> datos = inventario.get(categoria);
		
		datos.remove(vehiculo);
		
		inventario.put(categoria, datos);
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
	
	public Vehiculo checkDisponibilidadCategoria(HashMap<Integer, Reserva> dataReservas, String categoria, String fechaHoraInicio,
			String fechaHoraFin) {
		Vehiculo retorno = null;
		ArrayList<Vehiculo> invetarioCategoria = inventario.get(categoria);
		Iterator<Vehiculo> iterador = invetarioCategoria.iterator();
		boolean parar = false;
		boolean noDisponible = false;
		
		while (iterador.hasNext() && !parar )
		{
			retorno = iterador.next();

			String historial = retorno.getHistorial();
			String[] historialReservas = historial.split(" ");
			
			int i = 0;
			
			
			while (i < historialReservas.length && !noDisponible)
			{
				String id = historialReservas[i];

				Integer idReserva = Integer.parseInt(id);
				Reserva reserva = dataReservas.get(idReserva);
				noDisponible = reserva.rangoCruzado(fechaHoraInicio,
						fechaHoraFin);

				i++;
			}
			if (i== historialReservas.length || !noDisponible)
				{
					parar = true;
				}
		}
		
		if (noDisponible) {
			retorno = null;
		} else {
			quitarVehiculo(retorno);
		}
		
		return retorno;
	}
	
    public static String diaSemana(String fecha)
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate fechaLocalDate = LocalDate.parse(fecha, formatter);
    	DayOfWeek diaSemana = fechaLocalDate.getDayOfWeek();
    	
    	return diaSemana.toString();
    }
    
    public int getVehiculosDisponiblesHoy(LocalDate fecha, HashMap<Integer, Reserva> dataReservas) {
    	int retorno = 0;
    	Set<String> dataCategorias = inventario.keySet();
    	String fechaVerificar = formateador.generarFechaStr(fecha);
    	for (String categoria: dataCategorias) {
    		
			ArrayList<Vehiculo> dataCategoria = inventario.get(categoria);
    		for (Vehiculo vehiculo: dataCategoria) {
    			String[] reservas = vehiculo.getHistorial().split(" ");
    			
    			int i = reservas.length - 1;
    			boolean terminado = false;
    			boolean valido = true;
    			while (i>0 && !terminado && valido) {
    				int idReserva = Integer.parseInt(reservas[i]);
    				Reserva check = dataReservas.get(idReserva);
    				
    				if (check.isPendiente()) {
    					
    					valido = !check.rangoCruzado(fechaVerificar, fechaVerificar);
    					
    				} else {
    					terminado = true;
    				}
    				
    				i--;
    				
    			}
    			
    			if (valido) {
    				retorno ++;
    			}
    		}
    		
    	}
    	
    	return retorno;
    }

}
