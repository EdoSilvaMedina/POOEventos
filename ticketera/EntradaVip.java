package ticketera;

public class EntradaVip extends Entrada{
 private String regalo;

public EntradaVip() {
	
}
public EntradaVip(String regalo) {
	this.regalo = regalo;
	
}
 
public String getRegalo() {
	return regalo;
}

public void setRegalo(String regalo) {
	this.regalo = regalo;
}
}
