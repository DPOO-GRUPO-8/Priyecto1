package alquilerAutos.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import alquilerAutos.manejoDatos.Cliente;
import alquilerAutos.manejoDatos.LicenciaConducir;
import alquilerAutos.manejoDatos.Reserva;
import alquilerAutos.manejoDatos.Sede;
import alquilerAutos.manejoDatos.Tarifa;
import alquilerAutos.manejoDatos.Usuario;
import alquilerAutos.manejoDatos.Vehiculo;

public class AlquilerVehiculos {
	private HashMap<String, Tarifa> tarifas = new HashMap<>();
	private HashMap<String, HashMap<String, ArrayList<Vehiculo>>> inventario = new HashMap<>();
	private HashMap<Integer, Reserva> reservas = new HashMap<>();
	private HashMap<String, Sede> sedes = new HashMap<>();
	private HashMap<Integer, Cliente> clientes = new HashMap<>();
	private HashMap<String, Usuario> usuarios = new HashMap<>();
	private HashMap<String, LicenciaConducir> licencias = new HashMap<>();
	private GestorDatos gestor = new GestorDatos();
	
	
	public void cargarDatos() {
		tarifas = gestor.cargarTarifas();
		inventario = gestor.cargarVehiculos();
		/*
		 * Inventario esta dividido en dos mapas, el mas grande tiene como llave la ubicacion, ya sea el nombre de
		 * una sede o "Cliente", que se refiere a cuando el vehiculo anda en mitad de servicio con un cliente
		 */
		reservas = gestor.cargarReservas();
		sedes = gestor.cargarSedes();
		clientes = gestor.cargarClientes();
		usuarios = gestor.cargarUsuarios();
		licencias = gestor.cargarLicencias();

	}
	
	
}
