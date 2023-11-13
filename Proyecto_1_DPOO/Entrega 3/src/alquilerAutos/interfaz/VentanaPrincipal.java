package alquilerAutos.interfaz;

//import alquilerAutos.logica.AlquilerVehiculos;

import javax.swing.*;

import alquilerAutos.logica.AlquilerVehiculos;
import alquilerAutos.manejoDatos.Reserva;
import alquilerAutos.manejoDatos.Sede;
import alquilerAutos.manejoDatos.Usuario;
import alquilerAutos.manejoDatos.Vehiculo;

import java.awt.*;
import java.util.HashMap;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame
{
	private Color color = new Color(255, 87, 87);
	private PanelLogin panelLogin;
	private PanelMenu panelMenu;
	private GestionReservas gestionReservas;
	private HashMap<String, String> datosReserva;
	private HashMap<String, String> datosNuevoUsuario;
	private HashMap<String, String> datosVehiculo;
	private GestionUsuarios gestionUsuarios;
	private JPanel panelActual;
	private AlquilerVehiculos alquiler = new AlquilerVehiculos();
	private Usuario usuario;
	private GestionVehiculos gestionVehiculos;
	private MostrarInfoSede mostrarInfo;
	private Sede sede;
	private GestionSedes gestionSedes;



	private VentanaPrincipal()
	{
		this.setName("Alquiler De Vehiculos");
		this.setSize(500, 500);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		iniciarAplicacion();
	}
	public void iniciarAplicacion()
	{
		this.panelLogin = new PanelLogin(color, this);

		this.add(panelLogin);
		revalidate();
	}
	public void iniciarBienvenida(String rol, String nombre)
	{	
		this.panelMenu = new PanelMenu(color, this, rol, nombre);

		this.add(panelMenu);
		this.remove(panelLogin);
		revalidate();
	}

	public boolean comprobarLogin(String usuarioA, String contraseña)
	{
		// TODO conectar con la logica en donde se compruebe el login y si es
		// true se llame la funcion iniciar bienvenida
		
		usuario = alquiler.tieneUsuario(usuarioA);
		
		if (usuario != null) {
			
			if (usuario.getContraseña().equals(contraseña)) {
				
				if (!(usuario.getRol().equals("Admin total"))) {
					String[] dataRol = usuario.getRol().split(" ");
					alquiler.setSedeActual(dataRol[1]);
				}
				
				iniciarBienvenida(usuario.getRol(), usuario.getUsuario());
				return true;
			} else {
				return false;
			}
			
		} else {
			return false;
		}
		
		
	}

	public void nuevaReserva()
	{
		// TODO asignar a sedes un arreglo con las sedes igual con categoria
		String[] sedes =
		{""};
		String[] categorias =
		{""};
		gestionReservas = new GestionReservas(color, this, categorias, sedes);
		panelActual = gestionReservas;
		this.add(gestionReservas);
		revalidate();

	}
	private void nuevoCliente()
	{
		//gestionUsuarios = new GestionCliente(color, this);
		//panelActual = gestionUsuarios;
		//this.add(gestionUsuarios);
		//revalidate();

	}

	private void nuevoUsuario()
	{
		gestionUsuarios = new GestionUsuarios(color, this);
		panelActual = gestionUsuarios;
		this.add(gestionUsuarios);
		revalidate();
		
	}
	
	private void nuevaSede()
	{
		gestionSedes = new GestionSedes(color, this);
		panelActual = gestionSedes;
		this.add(gestionSedes);
		revalidate();
		
	}
	
	private void nuevoVehiculo()
	{
		gestionVehiculos = new GestionVehiculos(color, this);
		panelActual = gestionVehiculos;
		this.add(gestionVehiculos);
		
	}
	
	private void verDatosSede()
	{
		mostrarInfo = new MostrarInfoSede(color, this, sede, alquiler);
		this.add(mostrarInfo);
	}
	
	public void cambiarPanel(String accion)
	{
		if (accion == "NUEVA RESERVA")
		{
			this.remove(panelMenu);
			nuevaReserva();
		}
		if (accion == "NUEVO CLIENTE")
		{
			this.remove(panelMenu);
			nuevoCliente();
		}
		if (accion == "NUEVO USUARIO")
		{
			this.remove(panelMenu);
			nuevoUsuario();
		}
		if (accion == "NUEVO VEHICULO")
		{
			this.remove(panelMenu);
			nuevoVehiculo();
		}
		if (accion == "NUEVA SEDE")
		{
			this.remove(panelMenu);
			nuevaSede();
		}
		if (accion == "VOLVER" || accion == "ENVIAR")
		{
			this.remove(panelActual);
			iniciarBienvenida(usuario.getRol(), usuario.getUsuario());
		}
		if (accion == "VER DATOS SEDE")
		{	
			this.remove(panelMenu);
			if (usuario.getRol().equals("Admin total")) {
				String[] opciones = {"Sede_1", "Sede_2", "Sede_3"};
				
				int seleccion = JOptionPane.showOptionDialog(
		                null,
		                "Selecciona una sede:",
		                "Seleccionar sede",
		                JOptionPane.DEFAULT_OPTION,
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                opciones,
		                opciones[0]
		        );

		        // Verificar la opción seleccionada por el usuario
		        if (seleccion >= 0) {
		            String opcionSeleccionada = opciones[seleccion];
		            alquiler.setSedeActual(opcionSeleccionada);
		            this.sede = alquiler.getSedeActual();
		        } else {
		            JOptionPane.showMessageDialog(null, "No seleccionaste ninguna opción");
		        }
			}
			verDatosSede();
		}
	}


	
	
	
	public void setReserva(HashMap<String, String> datosReserva)
	{
		this.datosReserva = datosReserva;
		int precio = alquiler.calcularPrecio(datosReserva.get("Categoria"), Integer.parseInt(datosReserva.get("Conductores")), datosReserva.get("Sede recogida"), datosReserva.get("Sede llegada"), datosReserva.get("Fecha inicio"), datosReserva.get("Fecha final"));
		Reserva nuevaReserva = new Reserva(0,datosReserva.get("Fecha inicio"), datosReserva.get("Fecha final"), datosReserva.get("Categoria"),
				Integer.parseInt(datosReserva.get("Cedula")), precio, datosReserva.get("Sede recogida"));
		nuevaReserva.setSedeInicio(datosReserva.get("Sede recogida"));
		nuevaReserva.setSedeFinal(datosReserva.get("Sede llegada"));
		
		alquiler.agregarReserva(nuevaReserva);
		
		JOptionPane.showMessageDialog(null, "El precio a pagar es" + precio, "Precio a pagar", JOptionPane.INFORMATION_MESSAGE);
		
	}
	public void setNuevoUsuario(HashMap<String, String> datosNuevoUsuario)
	{
		this.datosNuevoUsuario = datosNuevoUsuario;
		
		Usuario nuevoUsuario = new Usuario(datosNuevoUsuario.get("Usuario"), datosNuevoUsuario.get("Contraseña"), datosNuevoUsuario.get("Rol"));
		alquiler.agregarUsuario(nuevoUsuario);
		
		
	}
	public static void main(String[] args)
	{
		new VentanaPrincipal();
	}
	public void setDatosVehiculo(HashMap<String, String> datosVehiculo)
	{
		this.datosVehiculo = datosVehiculo;
		Vehiculo agregar = new Vehiculo(datosVehiculo.get("Placa"), datosVehiculo.get("Categoria"), datosVehiculo.get("Marca"), datosVehiculo.get("Modelo"),
				datosVehiculo.get("Color"),datosVehiculo.get("Transmision"),datosVehiculo.get("Ubicacion"));
		
		alquiler.agregarVehiculo(agregar);
	}

}