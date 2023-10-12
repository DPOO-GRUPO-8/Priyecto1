package alquilerAutos.logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GuardadorDatos {
	
	public void guardarDatosLista (ArrayList<String> datos, File archivo) throws IOException {
		FileWriter fw = new FileWriter(archivo);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("");
		
		for (String linea: datos) {
			bw.write(linea + "\n");
		}
		
		bw.close();
	}
	
}
