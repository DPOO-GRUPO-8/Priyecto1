package alquilerAutos.interfaz;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class PanelLogin extends JPanel
{
	JPanel panelIzquierdo;
	JPanel panelDerecho;
	Color color;
	VentanaPrincipal ventanaPrincipal;

	PanelLogin(Color color, VentanaPrincipal ventanaPrincipal)
	{
		panelIzquierdo = new JPanel();
		panelDerecho = new JPanel();
		this.color = color;
		this.ventanaPrincipal = ventanaPrincipal;
		iniciarLogin();
	}

	private void iniciarLogin()
	{
		this.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0.5;
		constraints.weighty = 1.0;

		configurarPanelIzquierdo();
		configurarPanelDerecho();

		this.add(panelIzquierdo, constraints);
		constraints.gridx = 1;
		this.add(panelDerecho, constraints);

	}

	private void configurarPanelDerecho()
	{

		panelDerecho.setLayout(new GridBagLayout());
		GridBagConstraints innerConstraints = new GridBagConstraints();
		innerConstraints.fill = GridBagConstraints.BOTH;

		labelIniciarSesion(innerConstraints);

		labelUsuario(innerConstraints);

		innerConstraints.insets = new Insets(10, 10, 10, 10);
		JTextField campoUsuario = textFieldUsuario(innerConstraints);

		labelContraseña(innerConstraints);

		JPasswordField campoContraseña = paswordField(innerConstraints);

		botonIngresar(innerConstraints, campoUsuario, campoContraseña);
	}

	private void botonIngresar(GridBagConstraints innerConstraints,
			JTextField campoUsuario, JPasswordField campoContraseña)
	{
		JButton botonIngresar = new JButton("Ingresar");
		botonIngresar.setBackground(color);
		botonIngresar.setForeground(Color.WHITE);
		botonIngresar.setOpaque(true);
		botonIngresar.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
		botonIngresar.addActionListener(event ->
		{
			String usuario = campoUsuario.getText();
			String contraseña = new String(campoContraseña.getPassword());
			ventanaPrincipal.comprobarLogin(usuario, contraseña);

		});
		innerConstraints.gridx = 0;
		innerConstraints.gridy = 5;
		innerConstraints.gridwidth = 2;
		innerConstraints.insets = new Insets(10, 10, 100, 10);
		panelDerecho.add(botonIngresar, innerConstraints);
	}

	private JPasswordField paswordField(GridBagConstraints innerConstraints)
	{
		JPasswordField campoContraseña = new JPasswordField();
		innerConstraints.gridx = 0;
		innerConstraints.gridy = 4;
		innerConstraints.gridwidth = 2;
		panelDerecho.add(campoContraseña, innerConstraints);
		return campoContraseña;
	}

	private void labelContraseña(GridBagConstraints innerConstraints)
	{
		JLabel labelContraseña = new JLabel("Contraseña");
		innerConstraints.gridx = 0;
		innerConstraints.gridy = 3;
		innerConstraints.gridwidth = 1;
		panelDerecho.add(labelContraseña, innerConstraints);
	}

	private JTextField textFieldUsuario(GridBagConstraints innerConstraints)
	{
		JTextField campoUsuario = new JTextField();
		innerConstraints.gridx = 0;
		innerConstraints.gridy = 2;
		innerConstraints.gridwidth = 2;
		panelDerecho.add(campoUsuario, innerConstraints);
		return campoUsuario;
	}

	private void labelUsuario(GridBagConstraints innerConstraints)
	{
		JLabel labelUsuario = new JLabel("Usuario");
		innerConstraints.insets = new Insets(120, 10, 10, 10);
		innerConstraints.gridx = 0;
		innerConstraints.gridy = 1;
		innerConstraints.gridwidth = 1;
		panelDerecho.add(labelUsuario, innerConstraints);
	}

	private void labelIniciarSesion(GridBagConstraints innerConstraints)
	{
		JLabel labelInicioSesion = new JLabel("Iniciar Sesión");
		labelInicioSesion.setFont(new Font("Roboto Mono", Font.PLAIN, 28));
		labelInicioSesion.setForeground(color);
		innerConstraints.insets = new Insets(0, 10, 10, 10);
		innerConstraints.gridx = 0;
		innerConstraints.gridy = 0;
		innerConstraints.gridwidth = 2;
		panelDerecho.add(labelInicioSesion, innerConstraints);
	}

	private void configurarPanelIzquierdo()
	{
		panelIzquierdo.setBackground(color);

	}
}