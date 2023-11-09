package alquilerAutos.interfaz;

//import alquilerAutos.logica.AlquilerVehiculos;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame
{
	//AlquilerVehiculos alqulerVehiculos = new AlquilerVehiculos();
	Color color = new Color(255, 87,87);
	
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
	private void iniciarAplicacion()
	{
		JPanel panelLogin = new PanelLogin(color);
		JPanel panelBienvenidaAdmin = new PanelBienvenidaAdmin(color);
		this.add(panelLogin);
		//this.add(panelBienvenidaAdmin);
	}
	public static void main(String[] args)
	{
		new VentanaPrincipal();
	}

}
