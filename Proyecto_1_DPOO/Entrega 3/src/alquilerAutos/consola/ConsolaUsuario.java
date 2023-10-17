package alquilerAutos.consola;
import java.util.Scanner;
import alquilerAutos.logica.AlquilerVehiculos;
import alquilerAutos.manejoDatos.Reserva;
import alquilerAutos.manejoDatos.Vehiculo;

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
            System.out.println("3. Salir");
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

    public static void main(String[] args) {
        AlquilerVehiculos alquilerVehiculos = new AlquilerVehiculos();
        ConsolaUsuario consolaUsuario = new ConsolaUsuario(alquilerVehiculos);
        consolaUsuario.mostrarMenu();
    }
}
