package alquilerAutos.consola;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import alquilerAutos.logica.AlquilerVehiculos;
import alquilerAutos.manejoDatos.Usuario;
import alquilerAutos.manejoDatos.Vehiculo;



public class ConsolaAdminLocal  {
    private String nombreUsuario;
    private String contrasena;
    private boolean autenticado;
    
    // Mapa que almacena información de las sedes y sus empleados
    private Map<String, String> sedesYEmpleados;
    
    public ConsolaAdminLocal(Usuario usuario) {
        this.nombreUsuario = usuario.getUsuario();
        this.contrasena = usuario.getContraseña();
        this.autenticado = false;
        this.sedesYEmpleados = new HashMap<>();
    }
    
    public boolean autenticar(String nombreUsuario, String contrasena) {
        if (nombreUsuario.equals(this.nombreUsuario) && contrasena.equals(this.contrasena)) {
            autenticado = true;
            return true;
        }
        return false;
    }
    
    private static void agregarAuto() {
        try (Scanner scanner = new Scanner(System.in)) {
			AlquilerVehiculos alquilerVehiculos = new AlquilerVehiculos();

			System.out.println("Ingrese los datos del vehículo:");
			System.out.print("Placa: ");
			String placa = scanner.nextLine();

			System.out.print("Categoría: ");
			String categoria = scanner.nextLine();

			System.out.print("Marca: ");
			String marca = scanner.nextLine();

			System.out.print("Modelo: ");
			String modelo = scanner.nextLine();

			System.out.print("Color: ");
			String color = scanner.nextLine();

			System.out.print("Transmisión: ");
			String transmision = scanner.nextLine();

			System.out.print("Ubicación: ");
			String ubicacion = scanner.nextLine();

			// Crear un objeto de la clase Vehiculo con los datos ingresados por el usuario
			Vehiculo nuevoVehiculo = new Vehiculo(placa, categoria, marca, modelo, color, transmision, ubicacion);

			
			boolean agregado = alquilerVehiculos.agregarVehiculo(nuevoVehiculo);

			if (agregado) {
			    System.out.println("Vehículo agregado con éxito.");
			} else {
			    System.out.println("El vehículo ya existe en el sistema.");
			}
		}
    }

/*    public void quitarAuto(String placa) {
	    if (autenticado) {
	        // Lógica para quitar un auto
	        boolean eliminado = AlquilerVehiculos.quitarVehiculo(placa);
	        if (eliminado) {
	            System.out.println("Vehículo con placa " + placa + " eliminado del sistema.");
	        } else {
	            System.out.println("No se encontró un vehículo con placa " + placa + " en el sistema.");
	        }
	    } else {
	        System.out.println("Acceso no autorizado.");
	    }
	}
*/ 
    private static AlquilerVehiculos alquilerVehiculos = new AlquilerVehiculos();
    
    private static void modificarAuto() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Ingrese los datos del vehículo antiguo:");
			System.out.print("Placa del vehículo antiguo: ");
			String placaViejo = scanner.nextLine();

			System.out.println("Ingrese los datos del vehículo nuevo:");
			System.out.print("Placa del vehículo nuevo: ");
			String placaNuevo = scanner.nextLine();
			System.out.print("Categoría: ");
			String categoria = scanner.nextLine();
			System.out.print("Marca: ");
			String marca = scanner.nextLine();
			System.out.print("Modelo: ");
			String modelo = scanner.nextLine();
			System.out.print("Color: ");
			String color = scanner.nextLine();
			System.out.print("Transmisión: ");
			String transmision = scanner.nextLine();
			System.out.print("Ubicación: ");
			String ubicacion = scanner.nextLine();

			// Crear objetos de la clase Vehiculo para el vehículo antiguo y el nuevo
			Vehiculo vehiculoViejo = new Vehiculo(placaViejo, categoria, marca, modelo, color, transmision, ubicacion);
			Vehiculo vehiculoNuevo = new Vehiculo(placaNuevo, categoria, marca, modelo, color, transmision, ubicacion);

			// Llamar al método modificarVehiculo y pasar los objetos de vehículo antiguo y nuevo como argumentos
			boolean modificado = alquilerVehiculos.modificarVehiculo(vehiculoViejo, vehiculoNuevo);

			if (modificado) {
			    System.out.println("Vehículo modificado con éxito.");
			} else {
			    System.out.println("No se pudo modificar el vehículo. Verifique los datos ingresados.");
			}
		}
    }
    

    
    public void verEmpleados() {
        if (autenticado) {
            // Lógica para ver la lista de empleados
            System.out.println("Lista de empleados:");
            for (Map.Entry<String, String> sede : sedesYEmpleados.entrySet()) {
                System.out.println("Sede: " + sede.getKey() + ", Empleado: " + sede.getValue());
            }
        } else {
            System.out.println("Acceso no autorizado.");
        }
    }
    
    public void reservarAuto(String modelo) {
        if (autenticado) {
            // Lógica para reservar un auto
            System.out.println("Reserva exitosa del auto modelo " + modelo);
        } else {
            System.out.println("Acceso no autorizado.");
        }
    }
    
    public void agendarAuto(String modelo) {
        if (autenticado) {
            // Lógica para agendar un auto
            System.out.println("Agendamiento exitoso del auto modelo " + modelo);
        } else {
            System.out.println("Acceso no autorizado.");
        }
    }
    
    public void administrarSedes() {
        if (autenticado) {
            // Lógica para administrar las sedes y empleados
            // Se utilizaría el mapa sedesYEmpleados para realizar estas operaciones
            System.out.println("Administración de sedes y empleados");
        } else {
            System.out.println("Acceso no autorizado.");
        }
    }
    
    public void cerrarSesion() {
        autenticado = false;
    }
    


    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú del Administrador:");
            System.out.println("1. Comprar un auto");
            System.out.println("2. Vender un auto");
            System.out.println("3. Modificar un auto");
            System.out.println("4. Reservar un auto");
            System.out.println("5. Agendar un auto");
            System.out.println("6. Administrar sedes y empleados");
            System.out.println("7. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado agregar un auto.");
                    // Lógica para agregar un auto
                    agregarAuto();
                    break;
                case 2:
                    System.out.println("Has seleccionado quitar un auto.");
                    // Lógica para quitar un auto
                    System.out.print("Ingrese la placa del vehículo a quitar: ");
                    String placaQuitar = scanner.nextLine();
                    //quitarAuto(placaQuitar);
                    break;
                    
                 
                case 3:
                    System.out.println("Has seleccionado modificar un auto.");
                    // Lógica para modificar un auto
                    modificarAuto();
                    
                    break;
                case 4:
                    System.out.println("Has seleccionado reservar un auto.");
                    // Lógica para reservar un auto
                    break;
                case 5:
                    System.out.println("Has seleccionado agendar un auto.");
                    // Lógica para agendar un auto
                    break;
                case 6:
                    System.out.println("Has seleccionado administrar sedes y empleados.");
                    // Lógica para administrar sedes y empleados
                    break;
                case 7:
                    System.out.println("Cerrando sesión del administrador.");
                    cerrarSesion();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 7);

        scanner.close();
    
    
}}
