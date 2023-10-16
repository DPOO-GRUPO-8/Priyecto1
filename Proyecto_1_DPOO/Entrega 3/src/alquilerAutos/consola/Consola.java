package alquilerAutos.consola;

import java.util.Scanner;


public class Consola {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Datos de prueba para los perfiles (usuario, contraseña, tipo de perfil)
        String[][] usuarios = {
            {"admin", "admin123", "admin"},
            {"localadmin", "localadmin123", "admin local"},
            {"usuario1", "usuario123", "usuario"}
        };

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

            for (String[] usuario : usuarios) {
                if (usuario[0].equals(nombreUsuario) && usuario[1].equals(contrasena)) {
                    usuarioValido = true;
                    perfilUsuario = usuario[2];
                    break;
                }
            }

            if (!usuarioValido) {
                System.out.println("Credenciales incorrectas. Por favor, inténtelo de nuevo.");
            }
        }

        // Mostrar menú según el perfil
        System.out.println("Inicio de sesión exitoso como " + perfilUsuario + ".");
        if (perfilUsuario.equals("admin") || perfilUsuario.equals("admin local")) {
            // Crear una instancia de Admin
        	ConsolaAdmin admin = new ConsolaAdmin(nombreUsuario, contrasena);

            // Autenticar al administrador
            if (admin.autenticar(nombreUsuario, contrasena)) {
                System.out.println("Inicio de sesión exitoso como administrador.");
                admin.mostrarMenu(); // Mostrar el menú especial de administrador
            } else {
                System.out.println("Inicio de sesión fallido como administrador. Credenciales incorrectas.");
            }
        } else {
            // Lógica para el menú de otros perfiles de usuario
            // ...
        }

        scanner.close();
    }
}


	

	

	
    
	