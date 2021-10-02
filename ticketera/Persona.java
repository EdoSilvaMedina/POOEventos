package ticketera;
	import java.time.LocalDate;
	import java.time.Period;
	import java.time.format.DateTimeFormatter;

public class Persona {
	protected String rut;
	protected String nombre;
	protected String fechaNacimiento;
	protected int edad;
		
	public Persona(String rut, String nombre, String fechaNacimiento) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		calcularEdad();
	}

	public String getRut() {
		return rut;
	}
	
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public void calcularEdad() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(getFechaNacimiento(), fmt);
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNac, ahora);
		setEdad(periodo.getYears());
	}
	
	
}
