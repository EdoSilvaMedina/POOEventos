package ticketera;

public class Vendedor extends Persona {
	private int entradasVendidasNormales;
	private int entradasVendidasVip;
	
	public Vendedor(String rut, String nombre, String fechaNacimiento , int entrada, int entradaVip) {
		super(rut, nombre, fechaNacimiento);
		this.entradasVendidasNormales = entrada;
		this.entradasVendidasNormales = entradaVip;
		
	}
	public int getEntradasVendidasNormales() {
		return entradasVendidasNormales;
	}
	public void setEntradasVendidasNormales(int entradasVendidasNormales) {
		this.entradasVendidasNormales = entradasVendidasNormales;
	}
	public int getEntradasVendidasVip() {
		return entradasVendidasVip;
	}
	public void setEntradasVendidasVip(int entradasVendidasVip) {
		this.entradasVendidasVip = entradasVendidasVip;
	}
	
	
}
