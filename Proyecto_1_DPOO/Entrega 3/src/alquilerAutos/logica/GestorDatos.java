package alquilerAutos.logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import alquilerAutos.manejoDatos.*;

public class GestorDatos {
	private File sedes = new File("Datos/datosSedes.txt"); 
	private File vehiculos = new File("Datos/datosVehiculos.txt");
	private File usuarios = new File ("Datos/datosUsuarios.txt");
	private File clientes = new File ("Datos/datosClientes.txt");
	private File licencias = new File ("Datos/datosLicencias.txt");
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
	private GuardadorDatos guardador = new GuardadorDatos();

	
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
		
		
		retorno = cargarVehiculos(retorno);
		
		return retorno;
	}
	
	public HashMap<String, Sede> cargarVehiculos(HashMap<String, Sede> sedes){
		
		try {
			vehiculosTxt = cargador.cargarLista(vehiculos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String carrito: vehiculosTxt) {
			Vehiculo nuevoVehiculo = cargador.cargarVehiculo(carrito);
			ArrayList<String> ubicacion = nuevoVehiculo.getUbicacion();
			Sede sede = sedes.get(ubicacion.get(0));
			sede.agregarVehiculo(nuevoVehiculo);
			sedes.put(sede.getNombre(), sede);
		}

		return sedes;
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
	
	public void guardarSede (Sede sede) {
		sedesTxt.add(sede.toString());
		try {
			guardarSedes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarVehiculo (Vehiculo vehiculo) {
		vehiculosTxt.add(vehiculo.toString());
		try {
			guardarVehiculos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarUsuario (Usuario usuario) {
		usuariosTxt.add(usuario.toString());
		try {
			guardarUsuarios();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarCliente (Cliente cliente) {
		clientesTxt.add(cliente.toString());
		try {
			guardarClientes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarLicencia (LicenciaConducir licencia) {
		licenciasTxt.add(licencia.toString());
		try {
			guardarLicencias();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarReserva (Reserva reserva) {
		licenciasTxt.add(reserva.toString());
		try {
			guardarReservas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarTarifa (Tarifa tarifa) {
		tarifasTxt.add(tarifa.toString());
		try {
			guardarTarifas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quitarSede (Sede sede) {
		sedesTxt.remove(sede.toString());
		try {
			guardarSedes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quitarVehiculo(Vehiculo vehiculo) {
		vehiculosTxt.remove(vehiculo.toString());
		try {
			guardarVehiculos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quitarUsuario(Usuario usuario) {
		usuariosTxt.remove(usuario.toString());
		try {
			guardarUsuarios();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quitarCliente (Cliente cliente) {
		clientesTxt.remove(cliente.toString());
		try {
			guardarClientes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quitarLicencia (LicenciaConducir licencia) {
		licenciasTxt.remove(licencia.toString());
		try {
			guardarLicencias();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quitarReserva(Reserva reserva) {
		reservasTxt.remove(reserva.toString());
		try {
			guardarReservas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quitarTarifa (Tarifa tarifa) {
		tarifasTxt.remove(tarifa.toString());
		try {
			guardarTarifas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Verifica que no exista ningun vehiculo con placa igual
	 * @param placa
	 * @return true de existir un vehiculo con misma placa, falso de lo contrario
	 */
	public boolean verificarExistenciaVehiculo (String placa) {
		boolean retorno = false;
		
		for (String data: vehiculosTxt) {
			if (data.contains(placa + ";")) {
				retorno = true;
				break;
			}
		}
		
		return retorno;
	}
	
	public void guardarSedes() throws IOException {
		guardador.guardarDatosLista(sedesTxt, sedes);
	}
	
	public void guardarVehiculos () throws IOException {
		guardador.guardarDatosLista(vehiculosTxt, vehiculos);
	}
	
	public void guardarUsuarios () throws IOException{
		guardador.guardarDatosLista(usuariosTxt, usuarios);
	}
	
	public void guardarClientes() throws IOException{
		guardador.guardarDatosLista(clientesTxt, clientes);
	}
	
	public void guardarLicencias() throws IOException{
		guardador.guardarDatosLista(licenciasTxt, licencias);
	}
	
	public void guardarReservas() throws IOException{
		guardador.guardarDatosLista(reservasTxt, reservas);
	}
	
	public void guardarTarifas () throws IOException{
		guardador.guardarDatosLista(tarifasTxt, tarifas);
	}
	
	public void generarLogVehiculo(String placa, String data) throws IOException {
		guardador.guardarLog(placa, data);
	}
}



