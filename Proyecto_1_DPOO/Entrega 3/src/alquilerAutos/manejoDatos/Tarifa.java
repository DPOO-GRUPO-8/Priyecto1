package alquilerAutos.manejoDatos;

import alquilerAutos.logica.FormateadorDatos;

public class Tarifa {
	private String nombre;
	private RangoFechas periodo;
	private int tarifaDiaria;
	private FormateadorDatos formateador;

	public Tarifa(String nombre, String fechaInicial, String fechaFinal, int tarifaDiaria) {
		this.nombre = nombre;
		periodo = new RangoFechas(fechaInicial, fechaFinal);
		this.tarifaDiaria = tarifaDiaria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public RangoFechas getPeriodo() {
		return periodo;
	}

	public void setPeriodo(RangoFechas periodo) {
		this.periodo = periodo;
	}

	public int getTarifaDiaria() {
		return tarifaDiaria;
	}

	public void setTarifaDiaria(int tarifaDiaria) {
		this.tarifaDiaria = tarifaDiaria;
	}

	@Override

	public String toString() {
		String fechaInicialStr = formateador.generarFechaStr(periodo.getInicio());
		String fechaFinalStr = formateador.generarFechaStr(periodo.getFin());
		return nombre + ";" + fechaInicialStr + ";" + fechaFinalStr + ":" + tarifaDiaria;
	}
}
