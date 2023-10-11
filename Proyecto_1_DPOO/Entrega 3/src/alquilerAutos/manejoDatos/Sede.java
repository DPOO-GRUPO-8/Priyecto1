package alquilerAutos.manejoDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import alquilerAutos.logica.FormateadorDatos;

public class Sede implements Ubicacion {
	private String nombre;
	private FormateadorDatos formateador = new FormateadorDatos();
	private Map<String, RangoHoras> horario = new HashMap<>(); // La llave es el dia de la semana, el valor es un rango
																// de horas
	private String ubicacion;

	/**
	 * Crea una nueva sede
	 * @param nombre
	 * @param secuenciaDiasHoras
	 * @param ubicacion
	 */
	public Sede(String nombre, String secuenciaDiasHoras, String ubicacion) {// el formato es Dia horainicio horafin,
																				// cada bloque separado por comas
		this.nombre = nombre;
		this.ubicacion = ubicacion;

		String[] datosDias = secuenciaDiasHoras.split(",");
		for (String datoDia : datosDias) {
			String[] horas = datoDia.split(" ");
			RangoHoras rangoHoras = new RangoHoras(horas[1], horas[2]);
			horario.put(horas[0].toUpperCase(), rangoHoras);
		}

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna un set de strings con los dias que abre la sede
	 * @return	Set de strings con los dias que abre
	 */
	public Set<String> getDiasApertura() {
		return horario.keySet();
	}
	
	
	/**
	 * Retorna el RangoHoras en las que está abierto un dia la sede
	 * @param dia de la semana
	 * @return objeto tipo RangoHoras con los datos de ese dia
	 */
	public RangoHoras getHorasApertura(String dia) {
		RangoHoras retorno = horario.get(dia.toUpperCase());
		return retorno;
	}
	
	/**
	 * retorna 2 elementos en la lista
	 */
	public ArrayList<String> getUbicacion() {
		ArrayList<String> retorno = new ArrayList<>();
		retorno.add(nombre);
		retorno.add(ubicacion);
		return retorno;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	@Override
	public String toString() {
		String horarioString = "";
		for (String dia: horario.keySet()) {
			RangoHoras rango = horario.get(dia);
			String horaInicio = formateador.generarHoraStr(rango.getInicio());
			String horaFinal = formateador.generarHoraStr(rango.getFin());
			if (!horarioString.equals("")) {
				horarioString = horarioString + "," + dia + " " + horaInicio + " " + horaFinal; 
			} else {
				horarioString = dia + " " + horaInicio + " " + horaFinal;
			}
		}
		return nombre + ";" + 
				horarioString + ";" +
				ubicacion;
	}
	

}
