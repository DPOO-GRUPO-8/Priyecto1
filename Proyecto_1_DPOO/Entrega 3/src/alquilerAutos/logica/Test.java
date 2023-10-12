package alquilerAutos.logica;

import java.io.IOException;

public class Test {
	
	private GestorDatos gestor = new GestorDatos();
	private AlquilerVehiculos alquiler = new AlquilerVehiculos();

	public void ejecutar () throws IOException {
		/*gestor.guardarClientes();
		gestor.guardarLicencias();
		gestor.guardarReservas();
		gestor.guardarSedes();
		gestor.guardarTarifas();
		gestor.guardarUsuarios();
		gestor.guardarUsuarios();
		gestor.guardarVehiculos();*/
		
		alquiler.cargarDatos();
		String test = "para aqui";
	}
	
	public static void main (String[] args) {
		Test test = new Test();
		try {
			test.ejecutar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
