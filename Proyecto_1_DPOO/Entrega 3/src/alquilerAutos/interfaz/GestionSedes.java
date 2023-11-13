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
public class GestionSedes extends JPanel
{
	private VentanaPrincipal ventanaPrincipal;
	private JLabel titulo;
	private JPanel panelFormulario;
	private JPanel panelBotones;
	private Color color;
	private ArrayList<JTextField> fields = new ArrayList<JTextField>();


	GestionSedes(Color color, VentanaPrincipal ventanaPrincipal)
	{
		this.color = color;
		this.ventanaPrincipal = ventanaPrincipal;
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
		panelHoraFin(constraintsPanel, constraintsLabels,
				constraintsTextFields);
		panelDias(constraintsPanel, constraintsLabels,
				constraintsTextFields);

	}

	private void panelCedula(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsTextFields)
	{
		JPanel panelCedula = new JPanel();
		panelCedula.setLayout(new GridBagLayout());
		JLabel labelCedula = label("NOMBRE DE LA SEDE");
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
		constraintsPanel.gridx = 0;
		constraintsPanel.gridy = 1;
		JPanel panelCategoria = new JPanel();
		panelCategoria.setLayout(new GridBagLayout());
		JLabel labelCategoria = label("DIRECCION");
		JTextField textFieldFechaNacimiento = textField();
		panelCategoria.add(labelCategoria, constraintsLabels);
		panelCategoria.add(textFieldFechaNacimiento, constraintsTextFields);
		panelFormulario.add(panelCategoria, constraintsPanel);

		fields.add(textFieldFechaNacimiento);
	}

	private void panelSedeRecogida(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 1;
		constraintsPanel.gridy = 0;
		JPanel panelSedeRecogida = new JPanel();
		panelSedeRecogida.setLayout(new GridBagLayout());
		JLabel labelSedeRecogida = label("HORA INICIO");
		JTextField textFieldFechaNacimiento = textField();
		panelSedeRecogida.add(labelSedeRecogida, constraintsLabels);
		panelSedeRecogida.add(textFieldFechaNacimiento, constraintsTextFields);
		panelFormulario.add(panelSedeRecogida, constraintsPanel);

		fields.add(textFieldFechaNacimiento);
	}
	private void panelHoraFin(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 2;
		constraintsPanel.gridy = 0;
		JPanel panelHoraFin = new JPanel();
		panelHoraFin.setLayout(new GridBagLayout());
		JLabel labelSedeRecogida = label("HORA INICIO");
		JTextField textFieldFechaNacimiento = textField();
		panelHoraFin.add(labelSedeRecogida, constraintsLabels);
		panelHoraFin.add(textFieldFechaNacimiento, constraintsTextFields);
		panelFormulario.add(panelHoraFin, constraintsPanel);

		fields.add(textFieldFechaNacimiento);
	}

	private void panelDias(GridBagConstraints constraintsPanel,
			GridBagConstraints constraintsLabels,
			GridBagConstraints constraintsTextFields)
	{
		constraintsPanel.gridx = 1;
		constraintsPanel.gridy = 1;
		JPanel panelHoraFin = new JPanel();
		panelHoraFin.setLayout(new GridBagLayout());
		JLabel labelSedeRecogida = label("DIAS");
		JTextField textFieldFechaNacimiento = textField();
		panelHoraFin.add(labelSedeRecogida, constraintsLabels);
		panelHoraFin.add(textFieldFechaNacimiento, constraintsTextFields);
		panelFormulario.add(panelHoraFin, constraintsPanel);

		fields.add(textFieldFechaNacimiento);
	}
	private void configurarTitulo()
	{
		titulo = new JLabel("CUENTA NUEVA");
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

		reserva.put("Nombre de la sede", fields.get(0).getText());
		reserva.put("Direccion", fields.get(1).getText());
		reserva.put("Hora inicio", fields.get(2).getText());
		reserva.put("Hora fin", fields.get(2).getText());
		reserva.put("Dias", fields.get(2).getText());

		ventanaPrincipal.setNuevoUsuario(reserva);
	}

}
