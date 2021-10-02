package ticketera;

public class Cliente extends Persona {
	
	public Cliente(String rut, String nombre, String fechaNacimiento) {
		super(rut, nombre, fechaNacimiento);
	}

	@Override
	public String toString() {
		return "Cliente [rut=" + rut + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad
				+ "]";
	}
	
}
