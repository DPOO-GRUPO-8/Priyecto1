package alquilerAutos.logica;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class FormateadorDatos {
	
	/**
	 * Transforma un string con una fecha en formato DD/MM/YYYY en un objeto tipo LocalDate
	 * @param fecha en formato DD/MM/YYYY
	 * @return objeto LocalDate con la fecha introducida
	 */
	public LocalDate generarFecha(String fecha) {
		 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate a = LocalDate.parse(fecha, formatter);
		
		return a;
	}
	
	public LocalDateTime generarHoraFecha (String fechaHora) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime fecha = LocalDateTime.parse(fechaHora, formatter);
		
		return fecha;
		
	}
	
	public String generarHoraStr (LocalTime hora) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        
        String horaString = hora.format(formatter);
        
        return horaString;
	}
	
	public String generarFechaHoraStr (LocalDateTime fechaHora) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        String horaString = fechaHora.format(formatter);
        
        return horaString;
	}
	

}
