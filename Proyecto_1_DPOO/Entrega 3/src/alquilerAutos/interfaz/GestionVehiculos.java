package alquilerAutos.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GestionVehiculos extends JPanel
{
	private VentanaPrincipal ventanaPrincipal;
	private JLabel titulo;
	private JPanel panelFormulario;
	private JPanel panelBotones;
	private Color color;
	private ArrayList<JTextField> fields = new ArrayList<JTextField>();
	private ArrayList<JComboBox<String>> comboBox = new ArrayList<JComboBox<String>>();
	private String[] categorias;
	private String[] sedes;

	GestionVehiculos(Color color, VentanaPrincipal ventanaPrincipal)
	{
		this.color = color;
		this.ventanaPrincipal = ventanaPrincipal;
		this.sedes = sedes;
		this.categorias = categorias;
		configurarPanel();
	}

	private void configurarPanel()
	{
		setLayout(new BorderLayout());

		configurarTitulo();

		configurarFormulario();

		add(panelFormulario, BorderLayout.CENTER);

		panelBotones();
	}

	private void panelBotones()
	{
		panelBotones = new JPanel();
		JButton botonVolver = boton("VOLVER");
		JButton botonEnviar = boton("ENVIAR");
		panelBotones.add(botonVolver);
		panelBotones.add(botonEnviar);
		add(panelBotones, BorderLayout.SOUTH);
	}

	private void configurarFormulario()
	{
		panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridBagLayout());

		GridBagConstraints constraintsPanel = new GridBagConstraints();
		constraintsPanel.gridx = 0;
		constraintsPanel.gridy = 0;
		constraintsPanel.insets = new Insets(25, 50, 0, 0);
		GridBagConstraints constraintsLabels = new GridBagConstraints();
		constraintsLabels.gridx = 0;
		constraintsLabels.gridy = 0;
		GridBagConstraints constraintsTextFields = new GridBagConstraints();
		constraintsTextFields.gridx = 0;
		constraintsTextFields.gridy = 1;

		panelCedula(constraintsPanel, constraintsTextFields);

		panelCategoria(constraintsPanel, constraintsLabels,
				constraintsTextFields);

		panelSedeRecogida(constraintsPanel, constraintsLabels,
				constraintsTextFields);

		panelSedeLlegada(constraintsPanel, constraintsLabels,
				constraintsTextFields);

		panelFechaInicio(constraintsPanel, constraintsLabels,
				constraintsTextFields);

		panelFechaFinal(constraintsPanel, constraintsLabels,
				constraintsTextFields);

		panelHoras(constraintsPanel, constraintsLabels, constraintsTextFields);

		panelConductores(constraintsPanel, constraintsLabels,
				constraintsTextFields);
	}

	private void panelCedula(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsTextFields)
	{
		JPanel panelCedula = new JPanel();
		panelCedula.setLayout(new GridBagLayout());
		JLabel labelCedula = label("PLACA");
		JTextField textFieldCedula = textField();
		panelCedula.add(labelCedula);
		panelCedula.add(textFieldCedula, constraintsTextFields);
		panelFormulario.add(panelCedula, constraintsPanel);

		fields.add(textFieldCedula);
	}

	private void panelCategoria(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 1;
		constraintsPanel.gridy = 0;
		JPanel panelCategoria = new JPanel();
		panelCategoria.setLayout(new GridBagLayout());
		JLabel labelCategoria = label("MARCA");
		JTextField textFieldInicio = textField();
		panelCategoria.add(labelCategoria, constraintsLabels);
		panelCategoria.add(textFieldInicio, constraintsTextFields);
		panelFormulario.add(panelCategoria, constraintsPanel);

		fields.add(textFieldInicio);
	}

	private void panelSedeRecogida(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 2;
		constraintsPanel.gridy = 0;
		JPanel panelSedeRecogida = new JPanel();
		panelSedeRecogida.setLayout(new GridBagLayout());
		JLabel labelSedeRecogida = label("MODELO");
		JTextField textFieldInicio = textField();
		panelSedeRecogida.add(labelSedeRecogida, constraintsLabels);
		panelSedeRecogida.add(textFieldInicio, constraintsTextFields);
		panelFormulario.add(panelSedeRecogida, constraintsPanel);

		fields.add(textFieldInicio);
	}

	private void panelSedeLlegada(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 0;
		constraintsPanel.gridy = 1;
		JPanel panelSedeLlegada = new JPanel();
		panelSedeLlegada.setLayout(new GridBagLayout());
		JLabel labelSedeLlegada = label("NUMERO DE PASAJEROS");
		JTextField textFieldFinal = textField();
		panelSedeLlegada.add(labelSedeLlegada, constraintsLabels);
		panelSedeLlegada.add(textFieldFinal, constraintsTextFields);
		panelFormulario.add(panelSedeLlegada, constraintsPanel);

		fields.add(textFieldFinal);
	}

	private void panelFechaInicio(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 1;
		constraintsPanel.gridy = 1;
		JPanel panelInicio = new JPanel();
		panelInicio.setLayout(new GridBagLayout());
		JLabel labelInicio = label("TRANSMISION");
		JTextField textFieldInicio = textField();
		panelInicio.add(labelInicio, constraintsLabels);
		panelInicio.add(textFieldInicio, constraintsTextFields);
		panelFormulario.add(panelInicio, constraintsPanel);

		fields.add(textFieldInicio);
	}

	private void panelFechaFinal(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 2;
		constraintsPanel.gridy = 1;
		JPanel panelFinal = new JPanel();
		panelFinal.setLayout(new GridBagLayout());
		JLabel labelFinal = label("CATEGORIA");
		JTextField textFieldFinal = textField();
		panelFinal.add(labelFinal, constraintsLabels);
		panelFinal.add(textFieldFinal, constraintsTextFields);
		panelFormulario.add(panelFinal, constraintsPanel);

		fields.add(textFieldFinal);
	}

	private void panelHoras(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 0;
		constraintsPanel.gridy = 2;
		JPanel panelHoras = new JPanel();
		panelHoras.setLayout(new GridBagLayout());
		JLabel labelHoras = label("SEDE");
		JTextField textFieldHoras = textField();
		panelHoras.add(labelHoras, constraintsLabels);
		panelHoras.add(textFieldHoras, constraintsTextFields);
		panelFormulario.add(panelHoras, constraintsPanel);

		fields.add(textFieldHoras);

	}

	private void panelConductores(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 1;
		constraintsPanel.gridy = 2;
		JPanel panelConductores = new JPanel();
		panelConductores.setLayout(new GridBagLayout());
		JLabel labelConductores = label("COLOR");
		JTextField textFieldConductores = textField();
		panelConductores.add(labelConductores, constraintsLabels);
		panelConductores.add(textFieldConductores, constraintsTextFields);
		panelFormulario.add(panelConductores, constraintsPanel);

		fields.add(textFieldConductores);
	}

	private void configurarTitulo()
	{
		titulo = new JLabel("NUEVA RESERVA");
		titulo.setFont(new Font("Roboto Mono", Font.PLAIN, 60));
		titulo.setForeground(color);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(titulo, BorderLayout.NORTH);
	}
	private JLabel label(String texto)
	{
		JLabel label = new JLabel(texto);
		label.setForeground(Color.gray);
		label.setFont((new Font("Roboto Mono", Font.PLAIN, 12)));
		return label;
	}
	private JTextField textField()
	{
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(200, 30));
		return textField;
	}
	private JButton boton(String texto)
	{
		JButton boton = new JButton(texto);
		boton.setBackground(color);
		boton.setForeground(Color.WHITE);
		boton.setOpaque(true);
		boton.setFont(new Font("Roboto Mono", Font.PLAIN, 20));
		boton.addActionListener(event ->
		{
			String etiqueta = ((JButton) event.getSource()).getText();
			if (etiqueta == "ENVIAR")
			{
				guardar();
			}
			ventanaPrincipal.cambiarPanel(etiqueta);
			
		});
		return boton;

	}

	private void guardar()
	{
		HashMap<String, String> reserva = new HashMap<>();
		
		reserva.put("Placa", fields.get(0).getText());
		reserva.put("Marca", fields.get(1).getText());
		reserva.put("Modelo", fields.get(2).getText());
		reserva.put("Nuemero de pasajeros", fields.get(3).getText());
		reserva.put("Transmision", fields.get(4).getText());
		reserva.put("Categoria", fields.get(5).getText());
		reserva.put("Sede", fields.get(6).getText());
		reserva.put("Color", fields.get(7).getText());
		
		
		ventanaPrincipal.setDatosVehiculo(reserva);
	}

}
