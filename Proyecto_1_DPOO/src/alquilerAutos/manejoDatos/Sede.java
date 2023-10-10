package alquilerAutos.manejoDatos;
import java.time.*;
import java.util.HashMap;
import java.util.Map;

public class Sede  implements Ubicacion{
	private String nombre;
	private Map<String, LocalTime> horario = new HashMap<>();
	private String ubicacion;
	
}
