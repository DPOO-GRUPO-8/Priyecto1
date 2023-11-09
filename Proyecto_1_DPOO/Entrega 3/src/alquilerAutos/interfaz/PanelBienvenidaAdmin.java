package alquilerAutos.interfaz;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")

public class PanelBienvenidaAdmin extends JPanel
{
	JPanel panelBotones;
	JPanel mensajeBienvenida;
	JPanel panelReserva;
	Color color;
	String usuario;
	String rolUsuario;

	PanelBienvenidaAdmin(Color color)
	{
		panelBotones = new JPanel();
		mensajeBienvenida = new JPanel();
		this.color = color;

		configurarPaneles();
	}

	private void configurarPaneles()
	{
		this.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		configurarPanelBotonesAdmin();
		configurarPanelReserva();
		if (rolUsuario == "Admin total")
		{
			configurarPanelBotonesAdmin();
		}
		else if(rolUsuario == "Admin local Sede")
		{
			
		}
		configurarPanelMensajeBienvenida();

		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0.2;
		constraints.weighty = 1.0;
		this.add(panelBotones, constraints);

		constraints.gridx = 1;
		constraints.weightx = 0.8;
		this.add(panelReserva, constraints);
		this.setVisible(true);
	}

	private void configurarPanelBotonesAdmin()
	{
		panelBotones.setLayout(new GridBagLayout());
		GridBagConstraints innerConstraints = new GridBagConstraints();
		innerConstraints.fill = GridBagConstraints.BOTH;
		panelBotones.setBackground(color);

		innerConstraints.insets = new Insets(10, 10, 10, 10);

		innerConstraints.gridx = 0;
		innerConstraints.gridy = 1;
		innerConstraints.gridwidth = 2;
		boton(innerConstraints, "Reservas");

		innerConstraints.gridx = 0;
		innerConstraints.gridy = 2;
		innerConstraints.gridwidth = 2;
		boton(innerConstraints, "Usuarios");

		innerConstraints.gridx = 0;
		innerConstraints.gridy = 3;
		innerConstraints.gridwidth = 2;
		boton(innerConstraints, "Vehiculos");

		innerConstraints.gridx = 0;
		innerConstraints.gridy = 4;
		innerConstraints.gridwidth = 2;
		boton(innerConstraints, "Sedes");

		innerConstraints.gridx = 0;
		innerConstraints.gridy = 5;
		innerConstraints.gridwidth = 2;
		innerConstraints.insets = new Insets(100, 10, 10, 10);
		boton(innerConstraints, "Salir");

		panelBotones.setVisible(true);
	}

	private void boton(GridBagConstraints innerConstraints, String texto)
	{
		JButton boton = new JButton(texto);
		boton.setBackground(Color.WHITE);
		boton.setForeground(color);
		boton.setOpaque(true);
		boton.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
		

		panelBotones.add(boton, innerConstraints);
	}

	private void configurarPanelMensajeBienvenida()
	{
		mensajeBienvenida.setBackground(Color.WHITE);
		mensajeBienvenida.setLayout(new GridBagLayout());

		GridBagConstraints innerConstraints = new GridBagConstraints();
		innerConstraints.fill = GridBagConstraints.BOTH;
		innerConstraints.insets = new Insets(10, 10, 10, 10);
		innerConstraints.gridx = 0;
		innerConstraints.gridy = 0;
		innerConstraints.gridwidth = 1;

		// Agrega el contenido al panel de mensaje de bienvenida
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
		
        // Configurar el label de título
        JLabel labelTitulo = new JLabel("Opciones de Reserva");
        labelTitulo.setFont(new Font("Roboto Mono", Font.PLAIN, 18));
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        labelConstraints.insets = new Insets(10, 10, 10, 10); // Márgenes

        // Configurar el botón de nueva reserva
        JButton botonNuevaReserva = new JButton("Nueva Reserva");
        botonNuevaReserva.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
        botonNuevaReserva.setBackground(color);
        botonNuevaReserva.setForeground(Color.WHITE);
        GridBagConstraints nuevoReservaConstraints = new GridBagConstraints();
        nuevoReservaConstraints.gridx = 0;
        nuevoReservaConstraints.gridy = 1;
        nuevoReservaConstraints.weightx = 0.5; // Ocupa la mitad del ancho
        nuevoReservaConstraints.anchor = GridBagConstraints.CENTER;

        // Configurar el botón de modificar reserva
        JButton botonModificarReserva = new JButton("Modificar Reserva");
        botonModificarReserva.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
        GridBagConstraints modificarReservaConstraints = new GridBagConstraints();
        modificarReservaConstraints.gridx = 1;
        modificarReservaConstraints.gridy = 1;
        modificarReservaConstraints.weightx = 0.5; // Ocupa la mitad del ancho
        modificarReservaConstraints.anchor = GridBagConstraints.CENTER;

        // Agregar componentes al panel
        panelReserva.add(labelTitulo, labelConstraints);
        panelReserva.add(botonNuevaReserva, nuevoReservaConstraints);
        panelReserva.add(botonModificarReserva, modificarReservaConstraints);
    
	}

}
