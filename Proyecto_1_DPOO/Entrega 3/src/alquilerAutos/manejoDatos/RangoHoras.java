package alquilerAutos.manejoDatos;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class RangoHoras {
	private LocalTime horaInicio;
	private LocalTime horaFinal;
	
	public RangoHoras (String horaInicial, String horaFinalizar) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.horaInicio = LocalTime.parse(horaInicial, formatter);
        this.horaFinal = LocalTime.parse(horaFinalizar, formatter);
	}
	
	/**
	 * Retorna true si la hora indicada esta en el rango de horas, false de lo contrario
	 * @param hora en formato HH:MM
	 * @return true si la hora esta en el rango, false de lo contrario
	 */
	public boolean contieneHora(String hora) {
        LocalTime horaLocal = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
        return !horaLocal.isBefore(horaFinal) && !horaLocal.isAfter(horaInicio);
    }
	
    public LocalTime getInicio() {
        return horaInicio;
    }

    public LocalTime getFin() {
        return horaFinal;
    }
}
