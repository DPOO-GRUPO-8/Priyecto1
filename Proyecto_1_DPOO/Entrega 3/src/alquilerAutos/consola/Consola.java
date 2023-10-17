package alquilerAutos.consola;

import java.util.Scanner;

import alquilerAutos.logica.AlquilerVehiculos;
import alquilerAutos.manejoDatos.Usuario;

public class Consola {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AlquilerVehiculos alquiler = new AlquilerVehiculos();
		Usuario usuario = null;
		boolean usuarioValido = false;

		// Inicio de sesión
		while (!usuarioValido) {
			System.out.print("Usuario: ");
			String nombreUsuario = scanner.nextLine();

			usuario = alquiler.tieneUsuario(nombreUsuario);

			if (!(usuario == null)) {
				String contraseña = scanner.nextLine();
				if (contraseña.equals(usuario.getContraseña())) {
					usuarioValido = true;
				}
			}

			if (!usuarioValido) {
				System.out.println("Credenciales incorrectas. Por favor, inténtelo de nuevo.");
			}
		}

		// Mostrar menú según el perfil
		System.out.println("Inicio de sesión exitoso como " + usuario.getRol() + ".");
		boolean working = true;
		int caso = 0;
		String rol = usuario.getRol();
		while (working) {
			if(rol.contains("Admin total")) {
				caso = 1;
			} else if (rol.contains("Admin local")) {
				caso = 2;
			} else if (rol.contains("Empleado")) {
				caso = 3;
			}
			
			int opcion = 0;
			switch (caso) {

			case (1):
				mostrarMenuAdmin();
				opcion = Integer.parseInt(input("Ingrese la opcion a elegir"));

				switch (opcion) {
				case 1:
					ConsolaAdmin consola = new ConsolaAdmin(usuario);
					break;
				case 2:
					ConsolaAdminLocal consola1 = new ConsolaAdminLocal(usuario);
					break;
				case 3:
					ConsolaUsuario consola2 = new ConsolaUsuario(usuario);
					break;
				case 0:
					working = false;
					break;
				}
				break;
			case (2):
				mostrarMenuAdminLocal();
				opcion = Integer.parseInt(input("Ingrese la opcion a elegir"));

				switch (opcion) {
				case 1:
					ConsolaAdminLocal consola = new ConsolaAdminLocal(usuario);
					break;
				case 2:
					ConsolaUsuario consola1 = new ConsolaUsuario(usuario);
					break;
				case 0:
					working = false;
					break;
				}
				break;
				
			case (3):
				mostrarMenuUsuario();
				opcion = Integer.parseInt(input("Ingrese la opcion a elegir"));
	
				switch (opcion) {
				case 1:
					ConsolaUsuario consola1 = new ConsolaUsuario(usuario);
					break;
				case 0:
					working = false;
					break;
				}
				break;
			}
			
		}

		scanner.close();
	}

	public static void mostrarMenuAdmin() {
		System.out.println("Menu:");
		System.out.println("1. Entrar a opciones de administrar vehiculos");
		System.out.println("2. Entrar a opciones de administrar usuarios");
		System.out.println("3. Entrar a opciones de administrar reservas");
		System.out.println("0. Salir");
	}
	
	public static void mostrarMenuAdminLocal() {
		System.out.println("Menu:");
		System.out.println("1. Entrar a opciones de administrar usuarios");
		System.out.println("2. Entrar a opciones de administrar reservas");
		System.out.println("0. Salir");
	}
	
	public static void mostrarMenuUsuario() {
		System.out.println("Menu:");
		System.out.println("1. Entrar a opciones de administrar reservas");
		System.out.println("0. Salir");
	}

	/**
	 * Recibe un input de un usuario con un mensaje enviado por parametro
	 * 
	 * @param mensaje
	 * @return input del usuario
	 */
	public static String input(String mensaje) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(mensaje);
		String retorno = scanner.nextLine();
		scanner.close();
		return retorno;
	}
}
