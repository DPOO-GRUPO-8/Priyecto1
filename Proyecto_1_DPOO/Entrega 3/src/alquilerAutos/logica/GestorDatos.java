package alquilerAutos.logica;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import alquilerAutos.manejoDatos.*;

public class GestorDatos {
	private File sedes = new File("Datos/datosSedes.txt"); 
	private File vehiculos = new File("Datos/datosVehiculos.txt");
	private File usuarios = new File ("Datos/datosUsuarios.txt");
	private File clientes = new File ("Datos/datosClientes.txt");
	private File licencias = new File ("Datos/datosClientes.txt");
	private File reservas = new File ("Datos/datosReservas.txt");
	private File tarifas = new File ("Datos/datosTarifas.txt");
	
	
	private ArrayList<String> sedesTxt = new ArrayList<>();
	private ArrayList<String> vehiculosTxt = new ArrayList<>();
	private ArrayList<String> usuariosTxt = new ArrayList<>();
	private ArrayList<String> clientesTxt = new ArrayList<>();
	private ArrayList<String> licenciasTxt = new ArrayList<>();
	private ArrayList<String> reservasTxt = new ArrayList<>();
	private ArrayList<String> tarifasTxt = new ArrayList<>();
	
	private CargadorDatos cargador = new CargadorDatos();
	

	public void cargarDatos() {
		
	}
	
	public HashMap<String, Sede> cargarSedes() {
		try {
			sedesTxt = cargador.cargarLista(sedes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, Sede> retorno = new HashMap <>();
		for (String datosSede: sedesTxt) {
			Sede datos = cargador.cargarSede(datosSede);
			retorno.put(datos.getNombre(), datos);
		}
		
		return retorno;
	}
	
	public HashMap<String, HashMap<String, ArrayList<Vehiculo>>> cargarVehiculos(){
		HashMap<String, HashMap<String, ArrayList<Vehiculo>>> retorno = new HashMap<>();
		HashMap<String, ArrayList<Vehiculo>> vehiculosMapa; 
		ArrayList<Vehiculo> listaVehiculos;
		
		try {
			vehiculosTxt = cargador.cargarLista(vehiculos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String carrito: vehiculosTxt) {
			Vehiculo nuevoVehiculo = cargador.cargarVehiculo(carrito);
			String key = "";
			if (nuevoVehiculo.getUbicacion().size()>2) {
				key = "Cliente";
				
			} else {
				key = nuevoVehiculo.getUbicacion().get(0);
			}
			
			boolean check = retorno.containsKey(key);
			
			if (check) {
				vehiculosMapa = retorno.get(key);
			} else {
				vehiculosMapa = new HashMap<>();
			}
			
			String categoria = nuevoVehiculo.getCategoria();
			if (vehiculosMapa.containsKey(categoria)) {
				listaVehiculos = vehiculosMapa.get(categoria);
				
			} else {
				listaVehiculos = new ArrayList<>();
				
			}
			listaVehiculos.add(nuevoVehiculo);
			vehiculosMapa.put(categoria, listaVehiculos);
			retorno.put(key, vehiculosMapa);
		}
		
		return retorno;
	}
	
	public HashMap<String, LicenciaConducir> cargarLicencias(){
		LicenciaConducir licencia;
		HashMap<String, LicenciaConducir> mapaLicencias = new HashMap<>();
		
		try {
			licenciasTxt = cargador.cargarLista(licencias);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String dataLicencia: licenciasTxt) {
			licencia = cargador.cargarLicencia(dataLicencia);
			mapaLicencias.put(String.valueOf(licencia.getNumero()), licencia);
		}
		
		return mapaLicencias;
	}
	
	public HashMap<Integer, Cliente> cargarClientes(){
		Cliente cliente;
		HashMap<Integer, Cliente> retorno = new HashMap<>();
		
		try {
			clientesTxt = cargador.cargarLista(clientes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String dataCliente: clientesTxt) {
			cliente = cargador.cargarCliente(dataCliente);
			retorno.put(cliente.getDocumento(), cliente);
		}
		
		return retorno;
	}
	
	public HashMap<String, Usuario> cargarUsuarios(){
		Usuario usuario;
		HashMap<String, Usuario> retorno = new HashMap<>();
		
		try {
			usuariosTxt = cargador.cargarLista(usuarios);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String dataUsuario: usuariosTxt) {
			usuario = cargador.cargarUsuario(dataUsuario);
			retorno.put(usuario.getUsuario(), usuario);
		}
		
		return retorno;
	}
	
	public HashMap<String, Tarifa> cargarTarifas(){
		Tarifa tarifa;
		HashMap<String, Tarifa> retorno = new HashMap<>();
		
		try {
			tarifasTxt = cargador.cargarLista(tarifas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String dataTarifa: tarifasTxt) {
			tarifa = cargador.cargarTarifas(dataTarifa);
			retorno.put(tarifa.getNombre(), tarifa);
		}
		
		return retorno;
	}
	
	public HashMap<Integer, Reserva> cargarReservas(){
		Reserva reserva;
		HashMap<Integer, Reserva> retorno = new HashMap<>();
		
		try {
			reservasTxt = cargador.cargarLista(reservas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String dataReserva: reservasTxt) {
			reserva = cargador.cargarReserva(dataReserva);
			retorno.put(reserva.getId(), reserva);
		}
		
		return retorno;
	}
	
}
