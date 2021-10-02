package ticketera;

import java.util.ArrayList;

public class Evento {
	private String nombre;
	private int edadMin;
	private ArrayList <Entrada> entradas;
	private ArrayList <EntradaVip> entradasVip;
	private int maxEntradaTotal;
	private int precionormal;
	private int precioVip;
	private boolean enCurso;
	private ArrayList<Integer> espaciosVacios;
	public Evento(String nombre,int edadMin, int maxEntrada) {
		this.nombre = nombre;
		this.edadMin = edadMin;
		this.maxEntradaTotal = maxEntrada;
		this.entradas = new  ArrayList <Entrada>();
		this.entradasVip = new ArrayList <EntradaVip>();
		this.enCurso = false;
		this.espaciosVacios = new ArrayList<Integer>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdadMin() {
		return edadMin;
	}
	public void setEdadMin(int edadMin) {
		this.edadMin = edadMin;
	}
	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}
	public void setEntradas(ArrayList<Entrada> entradas) {
		this.entradas = entradas;
	}
	boolean derechoAdmision(int edad) {
		if(edad<this.edadMin) {
			return false;
		}else {
			return true;
		}
	}
	public int getMaxEntradaTotal() {
		return maxEntradaTotal;
	}
	public void setMaxEntradaTotal(int maxEntradaTotal) {
		this.maxEntradaTotal = maxEntradaTotal;
	}
	@Override
	public String toString() {
		return "Evento [nombre=" + nombre + ", edadMin=" + edadMin + ", entradas=" + entradas + ", maxEntradaTotal="
				+ maxEntradaTotal + "]";
	}
	public void agregarEntrada(Entrada entrada) {
		this.entradas.add(entrada);
	}
	public void agregarEntradaVip(EntradaVip entrada) {
		this.entradasVip.add(entrada);
	}
	public int getPrecionormal() {
		return precionormal;
	}
	public void setPrecionormal(int precionormal) {
		this.precionormal = precionormal;
	}
	public int getPrecioVip() {
		return precioVip;
	}
	public void setPrecioVip(int precioVip) {
		this.precioVip = precioVip;
	}
	public ArrayList<EntradaVip> getEntradasVip() {
		return entradasVip;
	}
	public void setEntradasVip(ArrayList<EntradaVip> entradasVip) {
		this.entradasVip = entradasVip;
	}
	//Revisa si la entrada existe segun su rut.
	public boolean existeEntrada(String rut) {
		boolean existe = false;
		for(Entrada temp :entradas) {
			if(temp.getCliente().getRut().equals(rut)) {
				existe= true;
				}
		}
		for(EntradaVip temp :entradasVip) {
			if(temp.getCliente().getRut().equals(rut)) {
				existe = true;
				}
		}
		return existe;
	}
	public Entrada retornarEntradaNormalSegunRut(String rut) {
		Entrada entradaNueva = new Entrada();
		for(Entrada temp :entradas) {
			if(temp.getCliente().getRut().equals(rut)) {
				entradaNueva= temp;
				}
		}
		return entradaNueva;
	}
	public EntradaVip retornarEntradaVipSegunRut(String rut) {
		EntradaVip entradaNueva = new EntradaVip();
		for(EntradaVip temp :entradasVip) {
			if(temp.getCliente().getRut().equals(rut)) {
				entradaNueva = temp;
				}
		}
		return entradaNueva;
	}
	public boolean isEnCurso() {
		return enCurso;
	}
	public void setEnCurso(boolean enCurso) {
		this.enCurso = enCurso;
	}
	public ArrayList<Integer> getEspaciosVacios() {
		return espaciosVacios;
	}
	public void setEspaciosVacios(ArrayList<Integer> espaciosVacios) {
		this.espaciosVacios = espaciosVacios;
	}
	
	public void llenarConVacios() {
		ArrayList<Integer> vacios = new ArrayList<Integer>();
		for (int i= 0; i< getMaxEntradaTotal(); i++) {
			vacios.add(0);
		}
		setEspaciosVacios(vacios);
		
	}
	
	
}
