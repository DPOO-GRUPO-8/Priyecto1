package alquilerAutos.consola;

import java.util.Scanner;

import alquilerAutos.logica.AlquilerVehiculos;


public class Consola {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlquilerVehiculos alquiler = new AlquilerVehiculos();
        // Datos de prueba para los perfiles (usuario, contraseña, tipo de perfil)
        

        boolean usuarioValido = false;
        String perfilUsuario = "";
        String nombreUsuario = ""; // 
        String contrasena = "";

        // Inicio de sesión
        while (!usuarioValido) {
            System.out.print("Usuario: ");
            nombreUsuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            contrasena = scanner.nextLine();

            

            if (!usuarioValido) {
                System.out.println("Credenciales incorrectas. Por favor, inténtelo de nuevo.");
            }
        }

        // Mostrar menú según el perfil
        System.out.println("Inicio de sesión exitoso como " + perfilUsuario + ".");
        if (perfilUsuario.equals("Admin total") || perfilUsuario.equals("Admin local")) {
            // Crear una instancia de Admin
        	ConsolaAdmin admin = new ConsolaAdmin(nombreUsuario, contrasena);

            // Autenticar al administrador
            if (admin.autenticar(nombreUsuario, contrasena)) {
                System.out.println("Inicio de sesión exitoso como administrador.");
                admin.mostrarMenu(); // Mostrar el menú especial de administrador
            } else {
                System.out.println("Inicio de sesión fallido como administrador. Credenciales incorrectas.");
            }
        } else if (perfilUsuario.equals("usuario")) {
            
            AlquilerVehiculos alquilerVehiculos = new AlquilerVehiculos(); 
            ConsolaUsuario usuario = new ConsolaUsuario(alquilerVehiculos);
            
            // Show the ConsolaUsuario menu
            usuario.mostrarMenu();
        } else {
            // 
        }

        scanner.close();
    }
}

	

	

	
    
	