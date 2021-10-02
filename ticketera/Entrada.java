package ticketera;

public class Entrada {
	private int precio;
	private int numAciento;
	private Cliente cliente;
	private Vendedor vendedor;
	private boolean usada;
	public Entrada() {
		
	}
	public Entrada(int precio, int numAciento) {
		this.precio = precio;
		this.numAciento = numAciento;
		this.usada = false;
	}
	
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getNumAciento() {
		return numAciento;
	}
	public void setNumAciento(int numAciento) {
		this.numAciento = numAciento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	@Override
	public String toString() {
		return "Entrada [precio=" + precio + ", numAciento=" + numAciento + ", cliente=" + cliente + ", vendedor="
				+ vendedor + "]";
	}
	public boolean isUsada() {
		return usada;
	}
	public void setUsada(boolean usada) {
		this.usada = usada;
	}
}
