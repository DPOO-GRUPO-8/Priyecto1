package alquilerAutos.interfaz;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import alquilerAutos.manejoDatos.Usuario;

@SuppressWarnings("serial")

public class PanelMenu extends JPanel
{
	private VentanaPrincipal ventanaPrincipal;
	private JPanel panelActual;
	private JPanel panelBotones;
	private JPanel mensajeBienvenida;
	private JPanel panelReserva;
	private JPanel panelUsuarios;
	private JPanel panelVehiculos;
	private JPanel panelSedes;
	private Color color;
	private String usuario;
	private String rolUsuario;

	public PanelMenu(Color color, VentanaPrincipal ventanaPrincipal, String rol, String nombre)
	{
		panelBotones = new JPanel();
		mensajeBienvenida = new JPanel();
		this.color = color;
		this.ventanaPrincipal = ventanaPrincipal;
		
		usuario = nombre;
		
		if (!rol.equals("Admin total")) {
			String[] dataRol = rol.split(" ");
			rolUsuario = dataRol[0];
		}
		
		configurarPaneles();
	}

	private void configurarPaneles()
	{
		this.setLayout(new GridBagLayout());

		GridBagConstraints constraintsBotones = new GridBagConstraints();
		configurarPanelMensajeBienvenida();
		configurarPanelBotones();

		constraintsBotones.fill = GridBagConstraints.BOTH;
		constraintsBotones.gridx = 0;
		constraintsBotones.weightx = 2;
		constraintsBotones.weighty = 1.0;
		panelBotones.setMinimumSize(new Dimension(50000, 50000));
		this.add(panelBotones, constraintsBotones);

		GridBagConstraints constraintsPanel = new GridBagConstraints();
		constraintsPanel.fill = GridBagConstraints.BOTH;
		constraintsPanel.gridx = 1;
		constraintsPanel.weightx = 8;

		panelActual = mensajeBienvenida;
		this.add(panelActual, constraintsPanel);
		this.setVisible(true);
	}

	private void configurarPanelBotones()
	{
		panelBotones.setLayout(new GridBagLayout());
		GridBagConstraints innerConstraints = new GridBagConstraints();
		innerConstraints.fill = GridBagConstraints.BOTH;
		panelBotones.setBackground(color);

		innerConstraints.insets = new Insets(10, 10, 10, 10);

		innerConstraints.gridx = 0;
		innerConstraints.gridy = 1;
		innerConstraints.gridwidth = 2;
		JButton botonReservas = boton("Reservas");
		panelBotones.add(botonReservas, innerConstraints);
		if (!rolUsuario.equals("Empleado Sede"))
		{
			innerConstraints.gridx = 0;
			innerConstraints.gridy = 2;
			innerConstraints.gridwidth = 2;
			JButton botonUsuarios = boton("Usuarios");
			panelBotones.add(botonUsuarios, innerConstraints);
		}
		if (rolUsuario.equals("Admin total"))
		{
			innerConstraints.gridx = 0;
			innerConstraints.gridy = 3;
			innerConstraints.gridwidth = 2;
			JButton botonVehiculos = boton("Vehiculos");
			panelBotones.add(botonVehiculos, innerConstraints);

		}
		if (!rolUsuario.equals("Empleado Sede"))
		{
			innerConstraints.gridx = 0;
			innerConstraints.gridy = 4;
			innerConstraints.gridwidth = 2;
			JButton botonSedes = boton("Sedes");
			panelBotones.add(botonSedes, innerConstraints);
		}
		innerConstraints.gridx = 0;
		innerConstraints.gridy = 5;
		innerConstraints.gridwidth = 2;
		innerConstraints.insets = new Insets(100, 10, 10, 10);
		JButton botonSalir = boton("Salir");
		panelBotones.add(botonSalir, innerConstraints);

	}

	private void configurarPanelMensajeBienvenida()
	{
		mensajeBienvenida.setLayout(new GridBagLayout());

		GridBagConstraints innerConstraints = new GridBagConstraints();
		innerConstraints.fill = GridBagConstraints.BOTH;
		innerConstraints.insets = new Insets(10, 10, 10, 10);
		innerConstraints.gridx = 0;
		innerConstraints.gridy = 0;
		innerConstraints.gridwidth = 1;

		JLabel bienvenidadLabel = new JLabel("Bienvenido");
		bienvenidadLabel.setFont(new Font("Roboto Mono", Font.PLAIN, 24));
		bienvenidadLabel.setForeground(color);

		JLabel usuarioLabel = new JLabel("Admin");
		usuarioLabel.setFont(new Font("Roboto Mono", Font.PLAIN, 60));
		usuarioLabel.setForeground(color);

		mensajeBienvenida.add(bienvenidadLabel, innerConstraints);

		innerConstraints.gridx = 1;
		innerConstraints.gridy = 1;
		innerConstraints.gridwidth = 1;

		mensajeBienvenida.add(usuarioLabel, innerConstraints);
	}

	private void configurarPanelReserva()
	{
		this.panelReserva = new JPanel();
		panelReserva.setLayout(new GridBagLayout());

		JLabel labelTitulo = new JLabel("Opciones de Reserva");
		labelTitulo.setFont(new Font("Roboto Mono", Font.PLAIN, 60));
		labelTitulo.setForeground(color);
		GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.gridx = 0;
		labelConstraints.gridy = 0;
		labelConstraints.anchor = GridBagConstraints.NORTHWEST;
		labelConstraints.insets = new Insets(0, 0, 250, 0);
		panelReserva.add(labelTitulo, labelConstraints);

		JButton botonNuevaReserva = botonPanel("NUEVA RESERVA");
		GridBagConstraints nuevaReservaConstraints = new GridBagConstraints();
		nuevaReservaConstraints.gridx = 0;
		nuevaReservaConstraints.gridy = 1;
		nuevaReservaConstraints.weightx = 0.5;
		nuevaReservaConstraints.anchor = GridBagConstraints.CENTER;
		nuevaReservaConstraints.insets = new Insets(0, 10, 250, 10);
		panelReserva.add(botonNuevaReserva, nuevaReservaConstraints);

		JButton botonModificarReserva = botonPanel("CERRAR RESERVA");
		GridBagConstraints cerrarReservaConstraints = new GridBagConstraints();
		cerrarReservaConstraints.gridx = 1;
		cerrarReservaConstraints.gridy = 1;
		cerrarReservaConstraints.weightx = 0.5;
		cerrarReservaConstraints.anchor = GridBagConstraints.CENTER;
		cerrarReservaConstraints.insets = new Insets(0, 10, 250, 0);
		panelReserva.add(botonModificarReserva, cerrarReservaConstraints);

	}

	private void configurarPanelUsuarios()
	{
		this.panelUsuarios = new JPanel();
		panelUsuarios.setLayout(new GridBagLayout());

		JLabel labelTitulo = new JLabel("Opciones de Usuarios");
		labelTitulo.setFont(new Font("Roboto Mono", Font.PLAIN, 60));
		labelTitulo.setForeground(color);
		GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.gridx = 0;
		labelConstraints.gridy = 0;
		labelConstraints.anchor = GridBagConstraints.NORTHWEST;
		labelConstraints.insets = new Insets(0, 0, 250, 0);
		panelUsuarios.add(labelTitulo, labelConstraints);

		JButton botonNuevoUsuario = botonPanel("NUEVO USUARIO");
		GridBagConstraints nuevaReservaConstraints = new GridBagConstraints();
		nuevaReservaConstraints.gridx = 0;
		nuevaReservaConstraints.gridy = 1;
		nuevaReservaConstraints.weightx = 0.5; // Ocupa la mitad del ancho
		nuevaReservaConstraints.anchor = GridBagConstraints.CENTER;
		nuevaReservaConstraints.insets = new Insets(0, 10, 250, 10);
		panelUsuarios.add(botonNuevoUsuario, nuevaReservaConstraints);

		JButton botonModificarUsuario = botonPanel("Modificar Usuario");
		GridBagConstraints cerrarReservaConstraints = new GridBagConstraints();
		cerrarReservaConstraints.gridx = 1;
		cerrarReservaConstraints.gridy = 1;
		cerrarReservaConstraints.weightx = 0.5;
		cerrarReservaConstraints.anchor = GridBagConstraints.CENTER;
		cerrarReservaConstraints.insets = new Insets(0, 10, 250, 0);
		panelUsuarios.add(botonModificarUsuario, cerrarReservaConstraints);
	}

	private void configurarPanelVehiculos()
	{
		this.panelVehiculos = new JPanel();
		panelVehiculos.setLayout(new GridBagLayout());

		JLabel labelTitulo = new JLabel("Opciones de Vehiculos");
		labelTitulo.setFont(new Font("Roboto Mono", Font.PLAIN, 60));
		labelTitulo.setForeground(color);
		GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.gridx = 0;
		labelConstraints.gridy = 0;
		labelConstraints.anchor = GridBagConstraints.NORTHWEST;
		labelConstraints.insets = new Insets(0, 0, 250, 0);
		panelVehiculos.add(labelTitulo, labelConstraints);

		JButton botonNuevoVehiculo = botonPanel("Nuevo Vehiculo");
		GridBagConstraints nuevoVehiculoConstraints = new GridBagConstraints();
		nuevoVehiculoConstraints.gridx = 0;
		nuevoVehiculoConstraints.gridy = 1;
		nuevoVehiculoConstraints.weightx = 0.5;
		nuevoVehiculoConstraints.anchor = GridBagConstraints.CENTER;
		nuevoVehiculoConstraints.insets = new Insets(0, 10, 250, 10);
		panelVehiculos.add(botonNuevoVehiculo, nuevoVehiculoConstraints);

		JButton botonModificarVehiculo = botonPanel("Modificar Vehiculo");
		GridBagConstraints modificarVehiculoConstraints = new GridBagConstraints();
		modificarVehiculoConstraints.gridx = 1;
		modificarVehiculoConstraints.gridy = 1;
		modificarVehiculoConstraints.weightx = 0.5;
		modificarVehiculoConstraints.anchor = GridBagConstraints.CENTER;
		modificarVehiculoConstraints.insets = new Insets(0, 10, 250, 0);
		panelVehiculos.add(botonModificarVehiculo,
				modificarVehiculoConstraints);
	}
	private void configurarPanelSedes()
	{
		this.panelSedes = new JPanel();
		panelSedes.setLayout(new GridBagLayout());

		JLabel labelTitulo = new JLabel("Opciones de Sedes");
		labelTitulo.setFont(new Font("Roboto Mono", Font.PLAIN, 60));
		labelTitulo.setForeground(color);
		GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.gridx = 0;
		labelConstraints.gridy = 0;
		labelConstraints.anchor = GridBagConstraints.NORTHWEST;
		labelConstraints.insets = new Insets(0, 0, 250, 0);
		panelSedes.add(labelTitulo, labelConstraints);

		JButton botonNuevaSede = botonPanel("Nueva Sede");
		GridBagConstraints nuevaSedeConstraints = new GridBagConstraints();
		nuevaSedeConstraints.gridx = 0;
		nuevaSedeConstraints.gridy = 1;
		nuevaSedeConstraints.weightx = 0.5;
		nuevaSedeConstraints.anchor = GridBagConstraints.CENTER;
		nuevaSedeConstraints.insets = new Insets(0, 10, 250, 10);
		panelSedes.add(botonNuevaSede, nuevaSedeConstraints);

		JButton botonModificarSede = botonPanel("Modificar Sede");
		GridBagConstraints modificarSedeConstraints = new GridBagConstraints();
		modificarSedeConstraints.gridx = 1;
		modificarSedeConstraints.gridy = 1;
		modificarSedeConstraints.weightx = 0.5;
		modificarSedeConstraints.anchor = GridBagConstraints.CENTER;
		modificarSedeConstraints.insets = new Insets(0, 10, 250, 0);
		panelSedes.add(botonModificarSede, modificarSedeConstraints);
	}

	private void cambiarPanel(String panelNuevo)
	{
		GridBagConstraints constraintsPanel = new GridBagConstraints();
		constraintsPanel.gridx = 1;

		if (panelNuevo == "Reservas")
		{
			constraintsPanel.weightx = 8;
			this.remove(panelActual);
			configurarPanelReserva();
			panelActual = panelReserva;
			this.add(panelReserva, constraintsPanel);

		} else if (panelNuevo == "Usuarios")
		{
			constraintsPanel.weightx = 0.8;
			this.remove(panelActual);
			configurarPanelUsuarios();
			panelActual = panelUsuarios;
			this.add(panelUsuarios, constraintsPanel);

		} else if (panelNuevo == "Vehiculos")
		{
			constraintsPanel.weightx = 0.8;
			this.remove(panelActual);
			configurarPanelVehiculos();
			panelActual = panelVehiculos;
			this.add(panelVehiculos, constraintsPanel);

		} else if (panelNuevo == "Sedes")
		{
			constraintsPanel.weightx = 0.8;
			this.remove(panelActual);
			configurarPanelSedes();
			panelActual = panelSedes;
			this.add(panelSedes, constraintsPanel);

		} else if (panelNuevo == "Salir")
		{
			ventanaPrincipal.iniciarAplicacion();
		}
		revalidate();

	}
	private JButton boton(String texto)
	{
		JButton boton = new JButton(texto);
		boton.setBackground(Color.WHITE);
		boton.setForeground(color);
		boton.setOpaque(true);
		boton.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
		boton.addActionListener(event ->
		{
			String etiqueta = ((JButton) event.getSource()).getText();
			cambiarPanel(etiqueta);
		});
		return boton;

	}
	private JButton botonPanel(String texto)
	{
		JButton boton = new JButton(texto);
		boton.setBackground(color);
		boton.setForeground(Color.WHITE);
		boton.setOpaque(true);
		boton.setFont(new Font("Roboto Mono", Font.PLAIN, 37));
		boton.addActionListener(event ->
		{
			String etiqueta = ((JButton) event.getSource()).getText();
			ventanaPrincipal.cambiarPanel(etiqueta);
		});
		return boton;

	}

}
