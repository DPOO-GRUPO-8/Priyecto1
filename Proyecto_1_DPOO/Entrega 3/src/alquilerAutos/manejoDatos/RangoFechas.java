package alquilerAutos.manejoDatos;

import java.time.LocalDate;


import java.time.format.DateTimeFormatter;

public class RangoFechas
{
	private LocalDate fechaInicio;
	private LocalDate fechaFinal;

	public RangoFechas(String fechaInicial, String fechaFinalizar)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.fechaInicio = LocalDate.parse(fechaInicial, formatter);
		this.fechaFinal = LocalDate.parse(fechaFinalizar, formatter);
	}

	/**
	 * Retorna true si la fecha indicada está en el rango de fechas, false de lo
	 * contrario
	 * 
	 * @param fecha
	 *            en formato dd/MM/yyyy
	 * @return true si la fecha está en el rango, false de lo contrario
	 */
	public boolean contieneFecha(String fecha)
	{
		LocalDate fechaLocal = LocalDate.parse(fecha,
				DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return !fechaLocal.isBefore(fechaInicio)
				&& !fechaLocal.isAfter(fechaFinal);
	}

	public LocalDate getInicio()
	{
		return fechaInicio;
	}

	public LocalDate getFin() 
	{
        return fechaFinal;
    }

	// METODOS PARA EL SISTEMA DE RESERVAS

	public boolean comprobar(LocalDate fechaInicio, LocalDate fechaFinal)
    {
		return fechaInicio.isAfter(this.fechaInicio) && fechaInicio.isBefore(this.fechaFinal);
    }
    
}