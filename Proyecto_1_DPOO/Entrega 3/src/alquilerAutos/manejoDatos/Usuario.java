package alquilerAutos.manejoDatos;

public class Usuario {
	private String usuario;
	private String contraseña;
	private String rol;
	
	/**
	 * Crea un usuario nuevo, el rol puede tener 3 posibilidades "Admin total", "Admin local Sede", "Empleado Sede"
	 * @param usuario
	 * @param contraseña
	 * @param rol
	 */
	public Usuario(String usuario, String contraseña, String rol) {
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString() {
		return usuario + ";" + 
				contraseña + ";" + 
				rol;
	}
	
	
	
}
