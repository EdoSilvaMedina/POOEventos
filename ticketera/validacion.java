package ticketera;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validacion {
	public  boolean esPalabra(String letra) {
		Pattern patronAbc = Pattern.compile("^[a-zA-ZñÑ_]+([a-zA-ZñÑ_]+)*");
		Matcher matcher = patronAbc.matcher(letra);
		return matcher.matches();
		}
	public  boolean esNumero(String letra) {
		Pattern patronNum = Pattern.compile("^[0-9]{1,30}$");
		Matcher matcher = patronNum.matcher(letra);
		return matcher.matches();
		}
	public  String validaFecha () {
		Pattern patronFecha = Pattern.compile("^[0-9]{2}+/[0-9]{2}/+[0-9]{4}$");
		Scanner sc = new Scanner(System.in);
		String dia,mes,anio;
		String fecha;
		boolean formatoValido,fechaValida;
		fechaValida = false;
		mes ="";
		dia="";
		anio="";
		do {
		fecha = sc.nextLine();
		Matcher matcher = patronFecha.matcher(fecha);
		formatoValido=matcher.matches();	
		if(formatoValido==false) {
			System.out.println("El formato es incorrecto, intentalo de nuevo");
		} else {
		
		if(formatoValido==true) {
			dia = fecha.substring(0,2);
			mes = fecha.substring(3,5);
			anio = fecha.substring(6,10);
			fechaValida = true;
		}
		if (mes.equals("02")) {
			if (Integer.parseInt(dia)>29) {
				System.out.println("Fecha incorrecta, intentalo de nuevo");
				fechaValida = false;
		}
		}
		if (Integer.parseInt(dia)>31) {
			System.out.println("Formato día incorrecto, intentalo de nuevo");
			fechaValida = false;
		}
		if (Integer.parseInt(mes)>12) {
			System.out.println("Formato mes incorrecto, intentalo de nuevo");
			fechaValida = false;
			
		}
		if (Integer.parseInt(anio)<=1875 || Integer.parseInt(anio)>2021) {
			System.out.println("Formato año incorrecto, intentalo de nuevo");
			fechaValida = false;
			
		}
		}
		}while(fechaValida==false);
		
		return fecha;
 
	}
	
	 //Valida rut de la forma XXXXXXXX-X 
	public  Boolean validaRut ( String rut ) {
		Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
		Matcher matcher = pattern.matcher(rut);
		if ( matcher.matches() == false ) return false;
		String[] stringRut = rut.split("-");
		return stringRut[1].toLowerCase().equals(dv(stringRut[0]));
	}
	

	 //Valida el dígito verificador
	public String dv ( String rut ) {
		Integer M=0,S=1,T=Integer.parseInt(rut);
		for (;T!=0;T=(int) Math.floor(T/=10))
			S=(S+T%10*(9-M++%6))%11;
		return ( S > 0 ) ? String.valueOf(S-1) : "k";		
	}
}
