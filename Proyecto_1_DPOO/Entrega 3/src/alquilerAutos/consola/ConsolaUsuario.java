package alquilerAutos.consola;
import java.util.Scanner;
import alquilerAutos.logica.AlquilerVehiculos;
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
                    mostrarVehiculosDisponibles();
                    break;
                case 2:
                    System.out.println("Has seleccionado reservar un vehículo.");
                    System.out.print("Ingrese la placa del vehículo que desea reservar: ");
                    String placaReserva = scanner.next();
                    reservarVehiculo(placaReserva);
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

    private void mostrarVehiculosDisponibles() {
        // Lógica para mostrar vehículos disponibles
        alquilerVehiculos.mostrarVehiculosDisponibles();
    }

    private void reservarVehiculo(String placa) {
        // Lógica para reservar un vehículo
        boolean reservado = alquilerVehiculos.reservarVehiculo(placa);

        if (reservado) {
            System.out.println("Vehículo con placa " + placa + " reservado con éxito.");
        } else {
            System.out.println("No se pudo reservar el vehículo. Verifique la disponibilidad y la placa ingresada.");
        }
    }

    public static void main(String[] args) {
        AlquilerVehiculos alquilerVehiculos = new AlquilerVehiculos(); // Debes crear una instancia de la lógica de alquiler de autos
        ConsolaUsuario consolaUsuario = new ConsolaUsuario(alquilerVehiculos);
        consolaUsuario.mostrarMenu();
    }
}
