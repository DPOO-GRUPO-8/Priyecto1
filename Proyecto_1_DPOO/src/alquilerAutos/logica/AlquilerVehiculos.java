package alquilerAutos.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import alquilerAutos.manejoDatos.Cliente;
import alquilerAutos.manejoDatos.Reserva;
import alquilerAutos.manejoDatos.Sede;
import alquilerAutos.manejoDatos.Usuario;
import alquilerAutos.manejoDatos.Vehiculo;

public class AlquilerVehiculos {
	private Map<String, Integer> tarifas = new HashMap<>();
	private Map<String, ArrayList<Vehiculo>> inventario = new HashMap<>();
	private Map<String, ArrayList<Reserva>> reservas = new HashMap<>();
	private Map<String, ArrayList<Sede>> sedes = new HashMap<>();
	private ArrayList<Cliente> clientes = new ArrayList<>();
	private Map <String, Usuario> usuarios = new HashMap<>();
	
}
