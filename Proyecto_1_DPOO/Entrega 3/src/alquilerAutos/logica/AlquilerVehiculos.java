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

public class AlquilerVehiculos
{
	private HashMap<String, Tarifa> tarifas = new HashMap<>();
	private HashMap<String, HashMap<String, ArrayList<Vehiculo>>> inventario = new HashMap<>();
	private HashMap<Integer, Reserva> reservas = new HashMap<>();
	private HashMap<String, Sede> sedes = new HashMap<>();
	private HashMap<Integer, Cliente> clientes = new HashMap<>();
	private HashMap<String, Usuario> usuarios = new HashMap<>();
	private HashMap<String, LicenciaConducir> licencias = new HashMap<>();
	private GestorDatos gestor = new GestorDatos();

	public void cargarDatos()
	{
		tarifas = gestor.cargarTarifas();
		inventario = gestor.cargarVehiculos();
		/*
		 * Inventario esta dividido en dos mapas, el mas grande tiene como llave
		 * la ubicacion, ya sea el nombre de una sede o "Cliente", que se
		 * refiere a cuando el vehiculo anda en mitad de servicio con un cliente
		 */
		reservas = gestor.cargarReservas();
		sedes = gestor.cargarSedes();
		clientes = gestor.cargarClientes();
		usuarios = gestor.cargarUsuarios();
		licencias = gestor.cargarLicencias();

	}
	/**
	 * Funcion que busca si existe un vehiculo con la misma placa
	 * 
	 * @param placa
	 * @return true de existir uno con placa igual, false de lo contrario
	 */

	/**
	 * Agrega un vehiculo mientras que la placa no esté repetida
	 * 
	 * @param vehiculo
	 * @return true si logró hacer el cambio, false de lo contrario
	 */
	public boolean agregarVehiculo(Vehiculo vehiculo)
	{
		boolean repetido = gestor
				.verificarExistenciaVehiculo(vehiculo.getPlaca());

		if (!repetido)
		{
			gestor.guardarVehiculo(vehiculo);
			HashMap<String, ArrayList<Vehiculo>> dataUbicacion = inventario
					.get(vehiculo.getUbicacion().get(0));
			ArrayList<Vehiculo> dataTipo = dataUbicacion
					.get(vehiculo.getCategoria());
			dataTipo.add(vehiculo);
			dataUbicacion.put(vehiculo.getCategoria(), dataTipo);
			inventario.put(vehiculo.getUbicacion().get(0), dataUbicacion);
		}

		return !repetido;
	}
	/**
	 * Quita un vehiculo del inventario
	 * 
	 * @param vehiculo
	 * @return true si logró quitarlo, false de lo contrario
	 */
	public boolean quitarVehiculo(Vehiculo vehiculo)
	{
		boolean repetido = gestor
				.verificarExistenciaVehiculo(vehiculo.getPlaca());

		if (repetido)
		{
			gestor.quitarVehiculo(vehiculo);
			HashMap<String, ArrayList<Vehiculo>> dataUbicacion = inventario
					.get(vehiculo.getUbicacion().get(0));
			ArrayList<Vehiculo> dataTipo = dataUbicacion
					.get(vehiculo.getCategoria());
			dataTipo.remove(vehiculo);
			dataUbicacion.put(vehiculo.getCategoria(), dataTipo);
			inventario.put(vehiculo.getUbicacion().get(0), dataUbicacion);
		}

		return repetido;
	}

	/**
	 * Quita un vehiculo antiguo de la base de datos e introduce el nuevo con
	 * los cambios hechos
	 * 
	 * @param viejo
	 *            datos viejos del vehiculo
	 * @param nuevo
	 *            datos nuevos del vehiculo
	 * @return true si logro hacer el reemplazo, false de lo contrario
	 */

	public boolean modificarVehiculo(Vehiculo viejo, Vehiculo nuevo)
	{
		boolean retorno = true;
		if (quitarVehiculo(viejo))
		{
			if (!agregarVehiculo(nuevo))
			{
				retorno = false;
				agregarVehiculo(viejo);
			}
		} else
		{
			retorno = false;
		}

		return retorno;
	}

	public boolean agregarTarifa(Tarifa tarifa)
	{
		boolean retorno = !tarifas.containsKey(tarifa.getNombre());
		if (retorno)
		{
			gestor.guardarTarifa(tarifa);
			tarifas.put(tarifa.getNombre(), tarifa);
		}

		return retorno;
	}

	public boolean quitarTarifa(Tarifa tarifa)
	{
		boolean retorno = tarifas.containsKey(tarifa.getNombre());
		if (retorno)
		{
			gestor.quitarTarifa(tarifa);
			tarifas.remove(tarifa.getNombre());
		}

		return retorno;
	}

	public boolean modificarTarifa(Tarifa viejo, Tarifa nuevo) {
		boolean retorno = true;
		if (quitarTarifa(viejo)) {
			if(!agregarTarifa(nuevo)) {
				retorno = false;
				agregarTarifa(viejo);
			}
		} else {
			retorno = false;
		}
		
		return retorno;
	}

	// FUNCIONES PARA EL SISTEMA DE RESERVAS

	public Reserva crearReserva(int documento, String fechaHoraInicio, String fechaHoraFin,
			String categoria, String sedeI)
	{
		/**PARAMETROS
		 * documento: Un int del numero de documento del cliente.
		 * fechaInicio: La fecha de incio de la reserva.
		 * fechaFinal: La fecha final de la reserva.
		 * categoriaVehiculo: La categoria que desea reservar el cliente.
		 * sede: La sede en la que se espera recoger el automovil
		 * RETORNO
		 * reserva: La reserva realizada con los datos ingresados
		 */
		
		Cliente cliente = clientes.get(documento);
		Sede sede = sedes.get(sedeI);
		boolean disponible = true;
		ArrayList<Vehiculo> invetarioCategoria = this.inventario.get(sede).get(categoria);
		for(Vehiculo vehiculo : invetarioCategoria ) 
		{	
			String historial = vehiculo.getHistorial();
			String[] historialReservas = historial.split(" ");
			for(String id:historialReservas)
			{
				Integer idReserva = Integer.parseInt(id);
				Reserva reserva = reservas.get(idReserva);
				disponible = reserva.rangoCruzado(fechaHoraInicio, fechaHoraFin);
			}
			// TODO Crear una forma de que se recorran los rangos de fechas del historia, esto se podria hacer accediendo a cada
			// reserva con el id del historial y obteniendo el rango de fechas, luego se revisa si la fecha inicial y final de
			// cada rango se encuentran en el otro rango
			
		}
		
		if (sede.revisarDisponibilidad(fechaHoraInicio) && !disponible)
		{
			Reserva reserva = cliente.crearReserva(fechaHoraInicio, fechaHoraFin, categoria, sedeI);
			return reserva;
		}else {
			return null;
		}
			
		
	}
	
}
