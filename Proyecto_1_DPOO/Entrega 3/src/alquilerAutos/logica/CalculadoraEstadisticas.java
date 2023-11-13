package alquilerAutos.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CalculadoraEstadisticas {

	
	public int calcularPromedio(ArrayList<Integer> datos) {
		int retorno;
		int total = 0;
		
		for (Integer i: datos) {
			
			total = total + i;
			
		}
		
		retorno = total/datos.size();
		
		return retorno;
	}
	
	public HashMap<String, Integer> calcularValoresDisponibilidad (HashMap<String, ArrayList<Integer>> datos){
		
		HashMap<String, Integer> retorno = new HashMap<>();
		
		for (String llave: datos.keySet()) {
			ArrayList<Integer> info = datos.get(llave);
			
			int promedio = calcularPromedio(info);
			
			retorno.put(llave, promedio);
			
		}
		
		
		return retorno;
		
		
	}
	
	public int promedioColeccion(Collection<Integer> datos){
		
		int retorno;
		int total = 0;
		
		for (Integer i: datos) {
			total = total + i;
		}
		
		retorno = total/datos.size();
		
		return retorno;
	}
}
