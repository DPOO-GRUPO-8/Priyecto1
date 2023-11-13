package alquilerAutos.interfaz;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

@SuppressWarnings("serial")
public class GestionReservas extends JPanel
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

	GestionReservas(Color color, VentanaPrincipal ventanaPrincipal,
			String[] sedes, String[] categorias)
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

		// Panel de formulario en la sección Center (usando un panel interno con
		// BoxLayout)
		configurarFormulario();

		add(panelFormulario, BorderLayout.CENTER);

		// Panel de botones en la sección South
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
		JLabel labelCedula = label("CEDULA");
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
		JLabel labelCategoria = label("CATEGORIA");
		JComboBox<String> comboBoxCategoria = new JComboBox<>(categorias);
		comboBoxCategoria.setPreferredSize(new Dimension(200, 30));
		panelCategoria.add(labelCategoria, constraintsLabels);
		panelCategoria.add(comboBoxCategoria, constraintsTextFields);
		panelFormulario.add(panelCategoria, constraintsPanel);

		comboBox.add(comboBoxCategoria);
	}

	private void panelSedeRecogida(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 2;
		constraintsPanel.gridy = 0;
		JPanel panelSedeRecogida = new JPanel();
		panelSedeRecogida.setLayout(new GridBagLayout());
		JLabel labelSedeRecogida = label("SEDE RECOGIDA");
		JComboBox<String> comboBoxSedeRecogida = new JComboBox<>(sedes);
		comboBoxSedeRecogida.setPreferredSize(new Dimension(200, 30));
		panelSedeRecogida.add(labelSedeRecogida, constraintsLabels);
		panelSedeRecogida.add(comboBoxSedeRecogida, constraintsTextFields);
		panelFormulario.add(panelSedeRecogida, constraintsPanel);

		comboBox.add(comboBoxSedeRecogida);
	}

	private void panelSedeLlegada(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 0;
		constraintsPanel.gridy = 1;
		JPanel panelSedeLlegada = new JPanel();
		panelSedeLlegada.setLayout(new GridBagLayout());
		JLabel labelSedeLlegada = label("SEDE LLEGADA");
		JComboBox<String> comboBoxSedeLlegada = new JComboBox<>(sedes);
		comboBoxSedeLlegada.setPreferredSize(new Dimension(200, 30));
		panelSedeLlegada.add(labelSedeLlegada, constraintsLabels);
		panelSedeLlegada.add(comboBoxSedeLlegada, constraintsTextFields);
		panelFormulario.add(panelSedeLlegada, constraintsPanel);

		comboBox.add(comboBoxSedeLlegada);
	}

	private void panelFechaInicio(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 1;
		constraintsPanel.gridy = 1;
		JPanel panelInicio = new JPanel();
		panelInicio.setLayout(new GridBagLayout());
		JLabel labelInicio = label("FECHA INICIO");
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
		JLabel labelFinal = label("FECHA FINAL");
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
		JLabel labelHoras = label("HORAS DE ENTREGA");
		JTextField textFieldHoras = textField();
		textFieldHoras.setText("HH:MM - HH:MM");
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
		JLabel labelConductores = label("FECHA FINAL");
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
		
		reserva.put("Cedula", fields.get(0).getText());
		reserva.put("Categoria", (String) comboBox.get(0).getSelectedItem());
		reserva.put("Sede recogida", (String) comboBox.get(1).getSelectedItem());
		reserva.put("Sede llegada", (String) comboBox.get(2).getSelectedItem());
		reserva.put("Fecha inicio", fields.get(1).getText());;
		reserva.put("Fecha final", fields.get(2).getText());;
		reserva.put("Horas", fields.get(3).getText());
		reserva.put("Conductores", fields.get(4).getText());
		
		
		ventanaPrincipal.setReserva(reserva);
	}

}
