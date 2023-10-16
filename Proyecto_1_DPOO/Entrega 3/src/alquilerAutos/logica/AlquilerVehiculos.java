package alquilerAutos.logica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
		tarifas = gestor.cargarTarifas();
		inventario = gestor.cargarVehiculos();

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

	public boolean modificarTarifa(Tarifa viejo, Tarifa nuevo)
	{
		boolean retorno = true;
		if (quitarTarifa(viejo))
		{
			if (!agregarTarifa(nuevo))
			{
				retorno = false;
				agregarTarifa(viejo);
			}
		} else
		{
			retorno = false;
		}

		return retorno;
	}

	public boolean agregarReserva(Reserva reserva)
	{
		boolean retorno = !reservas.containsKey(reserva.getId());

		if (retorno)
		{
			gestor.guardarReserva(reserva);
			reservas.put(reserva.getId(), reserva);
		}

		return retorno;
	}

	public boolean quitarReserva(Reserva reserva)
	{
		boolean retorno = reservas.containsKey(reserva.getId());

		if (retorno)
		{
			gestor.quitarReserva(reserva);
			reservas.remove(reserva.getId());
		}

		return retorno;
	}

	public boolean modificarReserva(Reserva viejo, Reserva nuevo)
	{
		boolean retorno = true;
		if (quitarReserva(viejo))
		{
			if (!agregarReserva(nuevo))
			{
				retorno = false;
				agregarReserva(viejo);
			}
		} else
		{
			retorno = false;
		}

		return retorno;
	}

	public boolean agregarCliente(Cliente cliente)
	{
		boolean retorno = !clientes.containsKey(cliente.getDocumento());
		if (retorno)
		{
			gestor.guardarCliente(cliente);
			clientes.put(cliente.getDocumento(), cliente);
		}

		return retorno;
	}

	public boolean quitarCliente(Cliente cliente)
	{
		boolean retorno = clientes.containsKey(cliente.getDocumento());
		if (retorno)
		{
			gestor.quitarCliente(cliente);
			clientes.remove(cliente.getDocumento());
		}

		return retorno;
	}

	public boolean modificarCliente(Cliente viejo, Cliente nuevo)
	{
		boolean retorno = true;
		if (quitarCliente(viejo))
		{
			if (!agregarCliente(nuevo))
			{
				retorno = false;
				agregarCliente(viejo);
			}
		} else
		{
			retorno = false;
		}
		return retorno;
	}

	public boolean agregarSede(Sede sede)
	{
		boolean retorno = !sedes.containsKey(sede.getNombre());

		if (retorno)
		{
			gestor.guardarSede(sede);
			sedes.put(sede.getNombre(), sede);
		}

		return retorno;
	}

	public boolean quitarSede(Sede sede)
	{
		boolean retorno = sedes.containsKey(sede.getNombre());

		if (retorno)
		{
			gestor.quitarSede(sede);
			sedes.remove(sede.getNombre());
		}

		return retorno;
	}

	public boolean modificarSede(Sede viejo, Sede nuevo)
	{
		boolean retorno = true;

		if (quitarSede(viejo))
		{
			if (!agregarSede(nuevo))
			{
				retorno = false;
				agregarSede(viejo);
			}
		} else
		{
			retorno = false;
		}

		return retorno;
	}

	public boolean agregarUsuario(Usuario usuario)
	{
		boolean retorno = !usuarios.containsKey(usuario.getUsuario());

		if (retorno)
		{
			gestor.guardarUsuario(usuario);
			usuarios.put(usuario.getUsuario(), usuario);
		}

		return retorno;
	}

	public boolean quitarUsuario(Usuario usuario)
	{
		boolean retorno = usuarios.containsKey(usuario.getUsuario());
		if (retorno)
		{
			gestor.quitarUsuario(usuario);
			usuarios.remove(usuario.getUsuario());
		}

		return retorno;
	}

	public boolean modificarUsuario(Usuario viejo, Usuario nuevo)
	{
		boolean retorno = true;

		if (quitarUsuario(viejo))
		{
			if (!agregarUsuario(nuevo))
			{
				retorno = false;
				agregarUsuario(viejo);
			}
		} else
		{
			retorno = false;
		}

		return retorno;
	}

	public boolean agregarLicencia(LicenciaConducir licencia)
	{
		boolean retorno = !licencias
				.containsKey(String.valueOf(licencia.getNumero()));

		if (retorno)
		{
			gestor.guardarLicencia(licencia);
			licencias.put(String.valueOf(licencia.getNumero()), licencia);
		}

		return retorno;
	}

	public boolean quitarLicencia(LicenciaConducir licencia)
	{
		boolean retorno = licencias
				.containsKey(String.valueOf(licencia.getNumero()));

		if (retorno)
		{
			gestor.quitarLicencia(licencia);
			licencias.remove(String.valueOf(licencia.getNumero()));
		}

		return retorno;
	}

	public boolean modificarLicencia(LicenciaConducir viejo,
			LicenciaConducir nuevo)
	{
		boolean retorno = true;

		if (quitarLicencia(viejo))
		{
			if (!agregarLicencia(nuevo))
			{
				retorno = false;
				agregarLicencia(viejo);
			}
		} else
		{
			retorno = false;
		}

		return retorno;
	}

	// FUNCIONES PARA EL SISTEMA DE RESERVAS

	public Reserva crearReserva(int documento, String fechaHoraInicio,
			String fechaHoraFin, String categoria, String sedeI, String sedeEntrega)
	{
		/**
		 * PARAMETROS documento: Un int del numero de documento del cliente.
		 * fechaInicio: La fecha de incio de la reserva. 
		 * fechaFinal: La fecha final de la reserva.
		 * categoriaVehiculo: La categoria que desea reservar el cliente.
		 * sedeI: La sede en la que se espera recoger el automovil.
		 * sedeEntrega: La Sede en la que se espera entregar el automovil 
		 * RETORNO
		 * reserva: La reserva realizada con los datos
		 * ingresados En caso de que no haya reserva disponible en ese horario
		 * el metodo retorna null
		 */

		Cliente cliente = clientes.get(documento);
		Sede sede = sedes.get(sedeI);
		boolean noDisponible = false;
		ArrayList<Vehiculo> invetarioCategoria = this.inventario.get(sedeI).get(categoria);
		Iterator<Vehiculo> iterador = invetarioCategoria.iterator();
		boolean parar = false;
		Vehiculo vehiculo = null;
		while (iterador.hasNext() && !parar )
		{
			noDisponible = false;
			vehiculo = iterador.next();

			String historial = vehiculo.getHistorial();
			String[] historialReservas = historial.split(" ");

			int i = 0;
			while (i < historialReservas.length && !noDisponible)
			{
				String id = historialReservas[i];

				Integer idReserva = Integer.parseInt(id);
				Reserva reserva = reservas.get(idReserva);
				noDisponible = reserva.rangoCruzado(fechaHoraInicio,
						fechaHoraFin);

				i++;
			}
			if (i== historialReservas.length)
				{
					parar = true;
				}
		}

		if (sede.revisarDisponibilidad(fechaHoraInicio) && sede.revisarDisponibilidad(fechaHoraFin) && !noDisponible)
		{
			int precio = calcularPrecio(categoria, 1, sedeI, sedeEntrega, fechaHoraInicio, fechaHoraFin);
			Reserva reserva = cliente.crearReserva(fechaHoraInicio,
					fechaHoraFin, categoria, sedeI, precio);
			this.reservas.put(reserva.getId(), reserva);
			vehiculo.agregarHistorial(reserva);
			String placa = vehiculo.getPlaca();
			reserva.setVehiculo(placa);
			return reserva;
		} else
		{
			return null;
		}

	}

	public Reserva crearAlquilerConReserva(Reserva reserva, String fechaHoraActual, String fechaHoraEntrega,
			ArrayList<LicenciaConducir> conductores, String categoria, String sedeEntrega)
	{
		/**
		 * PARAMETROS 
		 * reserva: La reserva que ya fue creada.
		 * conductores: Una lista con los conductores que usaran el vehiculo.
		 */
		reserva.setFechaHoraFinal(fechaHoraEntrega);
		reserva.setFechaHoraInicio(fechaHoraActual);
		int documento = reserva.getCliente();
		Cliente cliente = clientes.get(documento);
		int numeroLicencia = cliente.getLicencia();
		LicenciaConducir licenciaCliente = licencias.get(String.valueOf(numeroLicencia));
		conductores.add(licenciaCliente);
		reserva.setLicencias(conductores);
		
		String placa = reserva.getVehiculo();
		HashMap<String, ArrayList<Vehiculo>> vehiculosSede = this.inventario.get(reserva.getSede());
		ArrayList<Vehiculo> vehiculos = vehiculosSede.get(reserva.getTipoVehiculo());
		for(Vehiculo vehiculo : vehiculos) 
		{
			if (vehiculo.getPlaca() == placa);
			{
				ArrayList<String> ubicacion = new ArrayList<String>();
				ubicacion = cliente.getUbicacion();
				
				vehiculo.setUbicacion(ubicacion);
			}
		}
		
		int precio = calcularPrecio(categoria, conductores.size(), reserva.getSede(), sedeEntrega, fechaHoraActual, fechaHoraEntrega);
		
		reserva.setPrecio(precio);
		
		
		return reserva;

	}
	public Reserva crearAlquilerSinReserva(int documento, String fechaHoraInicio,
			String fechaHoraFin, String categoria, String sedeI, String sedeEntrega, ArrayList<LicenciaConducir> conductores) 
	{
		
		Reserva reserva = crearReserva(documento, fechaHoraInicio, fechaHoraFin, categoria, sedeI, sedeEntrega);
		Reserva alquiler = crearAlquilerConReserva(reserva, fechaHoraInicio, fechaHoraFin,
			conductores, categoria, sedeEntrega);
		
		return alquiler;
		
	}
	
	public void terminarReserva(Reserva reserva, String sedeEntrega) 
	{
		String placa = reserva.getVehiculo();
		HashMap<String, ArrayList<Vehiculo>> vehiculosSede = this.inventario.get(reserva.getSede());
		ArrayList<Vehiculo> vehiculos = vehiculosSede.get(reserva.getTipoVehiculo());
		Sede sede = sedes.get(sedeEntrega);
		int i = 0;
		for(Vehiculo vehiculo : vehiculos) 
		{
			if (vehiculo.getPlaca() == placa);
			{
				
				ArrayList<String> ubicacion = new ArrayList<String>();
				ubicacion = sede.getUbicacion();	
				vehiculo.setUbicacion(ubicacion);
				if(sedeEntrega.equals(reserva.getSede()));
				{
					vehiculos.remove(i);
					ArrayList<Vehiculo> vehiculosSedeEntrega = this.inventario.get(sedeEntrega).get(reserva.getTipoVehiculo());
					vehiculosSedeEntrega.add(vehiculo);
				}
			}
			i += 1;
		}
		if(sedeEntrega.equals(reserva.getSede()));
		
		
	}
	public int calcularPrecio(String categoria, int numeroConductores, String sedeOrigen, String sedeEntrega,
			String fechaHoraInicio, String fechaHoraFinal)
	{
		
		return numeroConductores;

	}
}