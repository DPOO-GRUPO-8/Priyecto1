package alquilerAutos.interfaz;

//import alquilerAutos.logica.AlquilerVehiculos;

import javax.swing.*;

import java.awt.*;
import java.util.HashMap;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame
{
	// AlquilerVehiculos alqulerVehiculos = new AlquilerVehiculos();
	private Color color = new Color(255, 87, 87);
	private PanelLogin panelLogin;
	private PanelMenu panelMenu;
	private GestionReservas gestionReservas;
	private HashMap<String, String> datosReserva;
	private GestionCliente gestionUsuarios;
	private JPanel panelActual;

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
	public void iniciarBienvenida()
	{
		this.panelMenu = new PanelMenu(color, this);

		this.add(panelMenu);
		this.remove(panelLogin);
		revalidate();
	}

	public boolean comprobarLogin(String usuario, String contraseña)
	{
		// TODO conectar con la logica en donde se compruebe el login y si es
		// tru se llame la funcion iniciar bienvenida
		iniciarBienvenida();
		return true;
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
		gestionUsuarios = new GestionCliente(color, this);
		panelActual = gestionUsuarios;
		this.add(gestionUsuarios);
		revalidate();

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
		if (accion == "VOLVER" || accion == "ENVIAR")
		{
			this.remove(panelActual);
			iniciarBienvenida();
		}
	}

	public void setReserva(HashMap<String, String> datosReserva)
	{
		this.datosReserva = datosReserva;
	}
	public static void main(String[] args)
	{
		new VentanaPrincipal();
	}

}