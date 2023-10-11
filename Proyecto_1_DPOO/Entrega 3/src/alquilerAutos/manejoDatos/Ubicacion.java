package alquilerAutos.manejoDatos;

import java.util.ArrayList;

public interface Ubicacion {
	/**
	 * Retorna la ubicacion, si es una sede, es una ArrayList con dos strings, si es un cliente, tiene 3 strings
	 * @return ArrayList con los datos de la ubicacion
	 */
	public ArrayList<String> getUbicacion();
	
	@Override
	public String toString();
}
