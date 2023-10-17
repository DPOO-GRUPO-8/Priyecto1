package alquilerAutos.consola;
import java.util.ArrayList;
import java.util.Scanner;
import alquilerAutos.logica.AlquilerVehiculos;
import alquilerAutos.manejoDatos.LicenciaConducir;
import alquilerAutos.manejoDatos.Reserva;

public class ConsolaUsuario {
    private AlquilerVehiculos alquilerVehiculos;

    public ConsolaUsuario(AlquilerVehiculos alquilerVehiculos) {
        this.alquilerVehiculos = alquilerVehiculos;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de Usuario/Cliente:");
            System.out.println("1. Ver vehículos disponibles");
            System.out.println("2. Reservar un vehículo");
            System.out.println("3. Crear alquiler con reserva");
            System.out.println("4. Crear alquiler sin reserva");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Vehículos disponibles:");
                    //mostrarVehiculosDisponibles();
                    break;
                case 2:
                    System.out.println("Has seleccionado reservar un vehículo.");
                    reservarVehiculo();
                    break;
                case 3:
                    System.out.println("Has seleccionado alquilar un vehiculo con reserva.");
                    alquilarConReserva();
                    break;
                case 4:
                    System.out.println("Has seleccionado alquilar un vehiculo sin reserva.");
                    alquilarVehiculoSinReserva();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema de alquiler de autos.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 3);

        scanner.close();
    }

/*    private void mostrarVehiculosDisponibles() {
        // Lógica para mostrar vehículos disponibles
        alquilerVehiculos.crearAlquilerConReserva();
    }
*/
    private void reservarVehiculo() {
        System.out.println("Has seleccionado reservar un vehículo.");
        // Solicitar datos para la reserva
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de documento del cliente: ");
        int documento = scanner.nextInt();
        System.out.print("Fecha y hora de inicio (YYYY-MM-DD HH:mm): ");
        String fechaHoraInicio = scanner.next();
        System.out.print("Fecha y hora de finalización (YYYY-MM-DD HH:mm): ");
        String fechaHoraFin = scanner.next();
        System.out.print("Categoría del vehículo: ");
        String categoria = scanner.next();
        System.out.print("Sede de recogida: ");
        String sedeI = scanner.next();
        System.out.print("Sede de entrega: ");
        String sedeEntrega = scanner.next();
        scanner.close();

        // Intentar crear la reserva
        Reserva reserva = alquilerVehiculos.crearReserva(documento, fechaHoraInicio, fechaHoraFin, categoria, sedeI, sedeEntrega);

        if (reserva != null) {
            System.out.println("Reserva creada con éxito. ID de reserva: " + reserva.getId());
        } else {
            System.out.println("No se pudo crear la reserva. Verifique la disponibilidad y los datos ingresados.");
        }
    }
    private void alquilarConReserva() {
        System.out.println("Has seleccionado alquilar un vehículo con reserva.");
        // Solicitar datos para alquilar con reserva
        Scanner scanner = new Scanner(System.in);
        ArrayList<LicenciaConducir> conductores = new ArrayList<LicenciaConducir>();
        System.out.print("Ingrese el ID de la reserva: ");
        int idReserva = scanner.nextInt();
        System.out.print("Fecha y hora de entrega (YYYY-MM-DD HH:mm): ");
        String fechaHoraActual = scanner.next();
        System.out.print("Fecha y hora desde (YYYY-MM-DD HH:mm): ");
        String fechaHoraEntrega = scanner.next();
        System.out.print("Ingrese la categoria del vehiculo: ");
        String categoria = scanner.next();
        System.out.print("Ingrese la sede de entrega: ");
        String sede = scanner.next();
        
        Reserva reserva = alquilerVehiculos.tieneReserva(idReserva);

        if (reserva != null) {
            // Llamar a la función para alquilar con reserva
            alquilerVehiculos.crearAlquilerConReserva(reserva, fechaHoraEntrega, fechaHoraActual, conductores, categoria, sede);
            System.out.println("Alquiler con reserva realizado con éxito.");
        } else {
            System.out.println("No se pudo encontrar la reserva con el ID proporcionado.");
        }
    }

    private void alquilarVehiculoSinReserva() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<LicenciaConducir> conductores = new ArrayList<LicenciaConducir>();

        System.out.print("Ingrese la sede de entrega: ");
        String sedeEntrega = scanner.next();

        System.out.print("Ingrese la categoría del vehículo: ");
        String categoria = scanner.next();

        System.out.print("Ingrese la fecha y hora de inicio (Ejemplo: 2023-10-16 10:00): ");
        String fechaHoraInicio = scanner.next();

        System.out.print("Ingrese la fecha y hora de fin (Ejemplo: 2023-10-17 12:00): ");
        String fechaHoraFin = scanner.next();

        // Lógica para alquilar un vehículo sin reserva
        Reserva alquiler = alquilerVehiculos.crearAlquilerSinReserva(12345, fechaHoraInicio, fechaHoraFin, categoria, placa, sedeEntrega, conductores);

        if (alquiler != null) {
            System.out.println("Vehículo con placa " + placa + " alquilado con éxito.");
        } else {
            System.out.println("No se pudo alquilar el vehículo. Verifique la disponibilidad y la placa ingresada.");
        }

        scanner.close();
    }


    

    public static void main(String[] args) {
        AlquilerVehiculos alquilerVehiculos = new AlquilerVehiculos();
        ConsolaUsuario consolaUsuario = new ConsolaUsuario(alquilerVehiculos);
        consolaUsuario.mostrarMenu();
    }
}
