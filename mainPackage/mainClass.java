package mainPackage;
	import ticketera.Evento;
	import ticketera.Vendedor;
	import ticketera.validacion;
	import ticketera.Cliente;
	import ticketera.Entrada;
	import ticketera.EntradaVip;
    import java.util.ArrayList;
    import java.util.Scanner;

    public class mainClass {
	public static void main(String[] args) {
		ArrayList<Evento>eventos = new ArrayList<Evento>();
		ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
		Scanner sc = new Scanner(System.in);
		String op = "";
		do {
		System.out.println("Menu Principal");
		System.out.println("1 - Crear Evento nuevo");
		System.out.println("2 - Revisar Eventos creados");
		System.out.println("3 - Salir");
		op = sc.nextLine();
		switch(op){
			case "1": eventos.add(crearEvento(eventos)); break;
			case "2": RevisarEventos(eventos ,vendedores) ; break;
			case "3": System.out.println("Adios ");break;
			default: System.out.println("Ingrese por favor una opción válida"); break;
		}
		}while(!op.equals("3"));
		
	}
	public static boolean validarNombreEvento(ArrayList<Evento>eventos,String nombre) {
		String comparar ="";
		boolean valido = false;
		if(eventos.size()>0) {
			for(Evento temp : eventos) {
				comparar = temp.getNombre();
				if(!comparar.toLowerCase().equals(nombre.toLowerCase())) {
					valido = true;
				}else if(comparar.toLowerCase().equals(nombre.toLowerCase())) {
					System.out.println("Este evento ya existe, por favor utilice otro nombre");
				}		
			}
		}else {
			valido = true;
		}
		return valido;
	}
	public static void RevisarEventos(ArrayList<Evento>eventos, ArrayList<Vendedor>vendedores) {
		validacion val = new validacion();
		Scanner sc = new Scanner(System.in);
		String op = "";
		if(eventos.size()>0) {
			do {
				do {
				System.out.println("Menu - Eventos");
				int i = 1;
				for(Evento temp: eventos) {
					System.out.println(i+" - "+temp.getNombre());
					i++;
				}
				System.out.println("Escoja un evento");
				op = sc.nextLine();
				}while(!val.esNumero(op));
			}while(Integer.parseInt(op)<0 || Integer.parseInt(op)>eventos.size() );
			menuEventos(eventos.get(Integer.parseInt(op)-1), vendedores);
		}else {
			System.out.println("No existen eventos creados");
		}
	}
	public static void menuEventos(Evento evento, ArrayList<Vendedor>vendedores) {
		Scanner sc = new Scanner(System.in);
		String op = "";
		do {
		System.out.println("Menu - Evento: "+evento.getNombre());
		System.out.println("1 - Iniciar evento");
		System.out.println("2 - Detener evento");
		System.out.println("3 - Ventas del evento");
		System.out.println("4 - Ingresar nuevo vendedor del evento");
		System.out.println("5 - Ingresar con entrada");
		System.out.println("6 - Salir");
		op = sc.nextLine();
		switch(op){
		case "1": iniciarEvento(evento ,true); break;
		case "2": iniciarEvento(evento ,false) ; break;
		case "3": EscogerVendedor(vendedores, evento);break;
		case "4":vendedores.add(ingresarVendedor());break;
		case "5":IngresarTipoDeEntrada(evento) ; break;
		case "6": break;
		default: System.out.println("Ingrese por favor una opción válida"); break;
		}
		}while(!op.equals("6"));
		//Caso 1: Inica evento
		// Caso 2: se detiene
		// Case 3: EscogerVendedor();
		//Case 4: CrearVendedor();
	}
	public static void EscogerVendedor(ArrayList <Vendedor>vendedores , Evento evento) {
		System.out.println("Menu - Vendedores");
		int i = 1;
		String op = "";
		Scanner sc =  new Scanner(System.in);
		validacion val = new validacion();
		if (vendedores.size()>0) {
			
			
			for(Vendedor temp: vendedores) {
				System.out.println(i+" - "+temp.getNombre());
				i++;
			}
			do {
			do {
			System.out.println("Escoge vendedor");
			op = sc.nextLine();
			}while(!val.esNumero(op));
			
			}while(Integer.parseInt(op)<=0 ||Integer.parseInt(op)>vendedores.size() );
			menuVentas(evento,vendedores.get(Integer.parseInt(op)-1), vendedores);
		}else {
			System.out.println("No existen vendedores");
			}
	}
	public static void menuVentas(Evento evento , Vendedor vendedor , ArrayList <Vendedor>vendedores) {
		String op = "";
		Scanner sc =  new Scanner(System.in);
		do {
			System.out.println("Menu - Ventas del evento : "+evento.getNombre());
			System.out.println("Vendedor a cargo: "+vendedor.getNombre());
			System.out.println("1 - Cambiar Vendedor");
			System.out.println("2 - Realizar venta");
			System.out.println("3 - Revisar ventas del vendedor: "+vendedor.getNombre());
			System.out.println("4 - Salir");
			op = sc.nextLine();
			switch(op) {
				case "1": EscogerVendedor(vendedores, evento); break;
				case "2":validarCliente(evento, vendedor) ;break;
				case "3": ventasDeVendedor(vendedor);break;
				case "4": break;
				default: System.out.println("Ingrese por favor una opción válida"); break;
			}
		}while(!op.equals("4"));
		
	}
	public static void validarCliente(Evento evento, Vendedor vendedor) {
			Cliente cliente = crearClientes();
			if(!evento.existeEntrada(cliente.getRut())) {
				if(cliente.getEdad()>= evento.getEdadMin()) {
					realizarVenta(vendedor, evento , cliente);
				}else {
					System.out.println("El cliente no cuenta con la edad mínima para acceder al evento "+evento.getNombre());
				}
			}else {
				System.out.println("El cliente ya posee una entrada");
			}
	}
	public static void realizarVenta(Vendedor vendedor , Evento evento , Cliente cliente) {
		String op = "";
		Scanner sc =  new Scanner(System.in);
		boolean venta = false;
		do {
			System.out.println("Menu - Venta");
			System.out.println("Vendedor a cargo: "+vendedor.getNombre());
			System.out.println("Ingrese entrada a comprar");
			System.out.println("1 - Entrada Normal ($"+evento.getPrecionormal()+")");
			System.out.println("2 - Entrada VIP ($"+evento.getPrecioVip()+")");
			System.out.println("3 - Salir");
		op = sc.nextLine();
		switch(op) {
			case "1": crearEntradaNormal(vendedor,evento, EscojerEspacio(evento.getEspaciosVacios()), cliente); venta = true;break;
			case "2": crearEntradaVIP(vendedor,evento, EscojerEspacio(evento.getEspaciosVacios()), cliente); venta = true; break;
			case "3": break;
			default: System.out.println("Ingrese por favor una opción válida"); break;
		}
	}while(!op.equals("3")&& !venta);
		
		
	}
	public static void IngresarTipoDeEntrada(Evento evento) {
		String op = "";
		Scanner sc =  new Scanner(System.in);
		do {
		System.out.println("Menu  - Ingresar entrada");
		System.out.println("Ingrese tipo de entrada a utilizar");
		System.out.println(" 1 - Entrada Normal");
		System.out.println(" 2 - Entrada VIP");
		System.out.println(" 3 - Salir");
		op = sc.nextLine();
		switch(op) {
		case "1": ingresarConEntradaNormal(evento);break;
		case "2": ingresarConEntradaVip(evento); break;
		case "3": break;
		default: System.out.println("Ingrese por favor una opción válida"); break;
	}
		}while(!op.equals("3"));
		

	}
	public static void ingresarConEntradaNormal(Evento evento) {
		String rut = "";
		Scanner sc =  new Scanner(System.in);
		System.out.println("Menu  - Ingresar entrada");
		System.out.println("Ingrese rut asociado a la entrada");
		Entrada entrada = new Entrada();
		rut = sc.nextLine();
		if(evento.existeEntrada(rut)) {
			entrada = evento.retornarEntradaNormalSegunRut(rut);
			if(!validarEntradaUsada(evento ,entrada)) {
				validarUsoDeEntrada(evento , entrada);
			}
		} else {
			System.out.println("Rut no existe");
		}

	}
	public static void ingresarConEntradaVip(Evento evento) {
		String rut = "";
		Scanner sc =  new Scanner(System.in);
		System.out.println("Menu  - Ingresar entrada");
		System.out.println("Ingrese rut asociado a la entrada");
		EntradaVip entrada = new EntradaVip();
		rut = sc.nextLine();
		if(evento.existeEntrada(rut)) {
			entrada = evento.retornarEntradaVipSegunRut(rut);
			if(!validarEntradaUsada(evento ,entrada)) {
				validarUsoDeEntrada(evento , entrada);
			}
		} else {
			System.out.println("Rut no existe");
		}

	}
	public static Evento crearEvento(ArrayList<Evento>eventos) {
		String nombreEvento = "";
		String edadMin ="" ;
		String entradaMax = "";
		String precio ="";
		Scanner sc = new Scanner (System.in);
		validacion val = new validacion();
		do {
		System.out.println("Ingrese nombre del evento");
		nombreEvento = sc.nextLine();
		}while(!val.esPalabra(nombreEvento)||!validarNombreEvento(eventos,nombreEvento));
		do {
			do {
				System.out.println("Ingrese edad mínima para el evento");
				edadMin = sc.nextLine();
			}while(!val.esNumero(edadMin));
		}while(Integer.parseInt(edadMin)<1||Integer.parseInt(edadMin)>120);
		do {
		System.out.println("Ingrese aforo máximo");
		entradaMax = sc.nextLine();	
		}while(!val.esNumero(entradaMax));
		Evento evento = new Evento(nombreEvento,Integer.parseInt(edadMin),Integer.parseInt(entradaMax));
		do{
			System.out.println("Ingrese precio de la entrada normal");
			precio=sc.nextLine();
		}while(!val.esNumero(precio));
		evento.setPrecionormal(Integer.parseInt(precio));
		do {
		System.out.println("Ingrese precio de la entrada Vip");
		precio=sc.nextLine();
		}while(!val.esNumero(precio));
		evento.setPrecioVip(Integer.parseInt(precio));
		System.out.println("Creando evento: "+evento.getNombre()+"(Edad mínima: "+evento.getEdadMin()+ ", Aforo máximo: "+evento.getMaxEntradaTotal()+")");
		evento.llenarConVacios();
		return evento;
	}
	//Se crea una nueva entrada normal
	public static Entrada crearEntradaNormal(Vendedor vendedor,Evento evento, int numAsiento, Cliente cliente) {
		Entrada entrada = new Entrada(evento.getPrecionormal(),numAsiento); //Entregamos el precio y el numero de asiento
		entrada.setCliente(cliente);
		entrada.setVendedor(vendedor);
		evento.agregarEntrada(entrada);//Agrega una entrada
		vendedor.setEntradasVendidasNormales(vendedor.getEntradasVendidasNormales()+1);
		return entrada;
	}
	//Se crea una nueva entrada VIP
	public static EntradaVip crearEntradaVIP(Vendedor vendedor,Evento evento, int numAsiento, Cliente cliente) {
		EntradaVip entrada = new EntradaVip();
		entrada.setCliente(cliente);
		entrada.setVendedor(vendedor);
		entrada.setPrecio(evento.getPrecioVip());
		entrada.setNumAciento(numAsiento);
		entrada.setRegalo("Recuerdito y copete");
		evento.agregarEntradaVip(entrada);
		vendedor.setEntradasVendidasVip(vendedor.getEntradasVendidasVip()+1);
		return entrada;
	}
	//Se crea un vendedor
	public static Vendedor ingresarVendedor() {
			String rut = "";
			String nombre = "";
			String fechaNacimiento = "";
			int entradaVendida = 0;
			int entradaVip = 0;
			Scanner sc = new Scanner(System.in);
			validacion val = new validacion();
			do{
			System.out.println("Ingrese Rut del vendedor (con la forma XXXXXXXX-X)");
			rut = sc.nextLine();
			}while(!val.validaRut(rut));
			do {
			System.out.println("Ingrese Nombre del vendedor");
			nombre = sc.nextLine();
			}while(!val.esPalabra(nombre));
			System.out.println("Ingrese fecha de nacimiento del vendedor");
			fechaNacimiento=val.validaFecha();
			Vendedor vendedor = new Vendedor(rut,nombre,fechaNacimiento ,entradaVendida, entradaVip);
		return vendedor;
	}
//	public static void persona(String persona,String rut,String nombre,String fecha) {
//		Scanner sc = new Scanner(System.in);
//		validacion val = new validacion();
//		do{
//		System.out.println("Ingrese Rut del "+persona+" (con la forma XXXXXXXX-X)");
//		rut = sc.nextLine();
//		}while(!val.validaRut(rut));
//		do {
//		System.out.println("Ingrese Nombre del "+persona);
//		nombre = sc.nextLine();
//		}while(!val.esPalabra(nombre));
//		System.out.println("Ingrese fecha de nacimiento del "+persona);
//		fecha=val.validaFecha();
		
		
//	}
	//Se crea un nuevo Cliente
	public static Cliente crearClientes() {
		String rut = "";
		String nombre = "";
		String fechaNacimiento = "";
		Scanner sc = new Scanner(System.in);
		validacion val = new validacion();
		do{
		System.out.println("Ingrese Rut del cliente (con la forma XXXXXXXX-X)");
		rut = sc.nextLine();
		}while(!val.validaRut(rut));
		do {
		System.out.println("Ingrese Nombre del cliente");
		nombre = sc.nextLine();
		}while(!val.esPalabra(nombre));
		System.out.println("Ingrese fecha de nacimiento del cliente");
		fechaNacimiento=val.validaFecha();
		Cliente cliente = new Cliente(rut,nombre,fechaNacimiento);
		return cliente;
	}
	
	public static void ventaDeEntrada(Vendedor vendedor, Evento evento, ArrayList<Integer> array , Cliente cliente) {
		Scanner sc = new Scanner (System.in);
		String op = "";
		int asiento = EscojerEspacio(array);
		System.out.println("¿Qué entrada desea comprar?");
		System.out.println("1 - Entrada normal (valor: "+evento.getPrecionormal()+")");
		System.out.println("2 - Entrada VIP (valor: "+evento.getPrecioVip()+")");
		op = sc.nextLine();
		switch(op){
		case "1":
			crearEntradaNormal(vendedor,evento,asiento,cliente);
			System.out.println("Vendiendo entrada Normal al cliente"+cliente.getNombre()+" ("+cliente.getRut()+") para el evento "+ evento.getNombre());
			break;
		case "2":
			crearEntradaVIP(vendedor,evento, asiento, cliente);
			System.out.println("Vendiendo entrada VIp al cliente"+cliente.getNombre()+" ("+cliente.getRut()+") para el evento "+ evento.getNombre());
			break;
		
		}
	}
	
	public static int EscojerEspacio(ArrayList<Integer>array) {
		String op = "";
		Scanner sc = new Scanner (System.in);
		validacion val = new validacion();
		
		do {
			do {
				System.out.println("Escoja asiento a ocupar");
				mostrarAsientos(array);
				op = sc.nextLine();
				if(array.get(Integer.parseInt(op)-1).equals(2)) {
					System.out.println("El asiento ya está ocupado ");
				}
			}while(!val.esNumero(op));
		}while(Integer.parseInt(op)>array.size()||Integer.parseInt(op)<0||array.get(Integer.parseInt(op)-1).equals(2));
		array.set(Integer.parseInt(op)-1, 2);
		return Integer.parseInt(op);
	}
	public static void mostrarAsientos(ArrayList<Integer>array) {
		int i = 1;
		for (int temp : array) {
			if(temp == 0) {
				System.out.println((i) +" - Disponible");
			} else {
				System.out.println((i) +" - Vendido");
			}
			i++;
		}
	}
	
	public static void validarUsoDeEntrada(Evento evento , Entrada entrada) {
		if(evento.existeEntrada(entrada.getCliente().getRut())) {
			if(evento.isEnCurso()) {
				System.out.println("Usando entrada con cliente "+entrada.getCliente().getNombre()+" ("+entrada.getCliente().getRut()+") para evento "+evento.getNombre());
				System.out.println("Entrada encontrada, "+entrada.getCliente().getNombre()+" puede pasar.");
				entrada.setUsada(true);
			}else {
				System.out.println("Usando entrada con cliente "+entrada.getCliente().getNombre()+" ("+entrada.getCliente().getRut()+") para evento "+evento.getNombre());
				System.out.println("No se puede usar la entrada porque el evento "+evento.getNombre()+" no está en curso.");
			}
		}
	}
	public static void validarUsoDeEntrada(Evento evento , EntradaVip entrada) {
		if(evento.existeEntrada(entrada.getCliente().getRut())) {
			if(evento.isEnCurso()) {
				System.out.println("Usando entrada con cliente "+entrada.getCliente().getNombre()+" ("+entrada.getCliente().getRut()+") para evento "+evento.getNombre());
				System.out.println("Entrada encontrada, "+entrada.getCliente().getNombre()+" puede pasar.");
				entrada.setUsada(true);
		
			}else {
				System.out.println("Usando entrada con cliente "+entrada.getCliente().getNombre()+" ("+entrada.getCliente().getRut()+") para evento "+evento.getNombre());
				System.out.println("No se puede usar la entrada porque el evento "+evento.getNombre()+" no está en curso.");
			}
		}
		
	}
	
	public static boolean validarEntradaUsada(Evento evento , Entrada entrada) {
		boolean noPuedePasar = false;
		if(evento.existeEntrada(entrada.getCliente().getRut())) {
			if(evento.isEnCurso()) {
				if(entrada.isUsada()){
					System.out.println("Usando entrada con cliente "+entrada.getCliente().getNombre()+" ("+entrada.getCliente().getRut()+") para evento "+evento.getNombre()+" \r\n"
							+ "Entrada para rut "+entrada.getCliente().getRut()+" ya fue usada, no puede pasar.\r\n"
							+ "");
					noPuedePasar = true;
				}
			}
		}
		return noPuedePasar;
	}
	public static boolean validarEntradaUsada(Evento evento , EntradaVip entrada) {
		boolean noPuedePasar = false;
		if(evento.getEntradasVip().size()>0) {
			if(evento.existeEntrada(entrada.getCliente().getRut())) {
				if(evento.isEnCurso()) {
					if(entrada.isUsada()){
						System.out.println("Usando entrada con cliente "+entrada.getCliente().getNombre()+" ("+entrada.getCliente().getRut()+") para evento "+evento.getNombre()+" \r\n"
							+ "Entrada para rut "+entrada.getCliente().getRut()+" ya fue usada, no puede pasar.\r\n"
							+ "");
					noPuedePasar = true;
				}
			}
			}
		}else {
			System.out.println("No se encontro el cliente");
		}
		return noPuedePasar;
	}
	
	public static void iniciarEvento(Evento evento ,boolean iniciar) {
		System.out.print("El evento "+evento.getNombre() +" se ha cambiado: ");
		if(iniciar) {
			evento.setEnCurso(true);
			System.out.println("En Curso");
		} else{
			evento.setEnCurso(false);
			System.out.println("Detenido");
		}
	}
	public static void ventasDeVendedor(Vendedor vendedor){
		System.out.println("El vendedor "+vendedor.getNombre()+" ha vendido: "+vendedor.getEntradasVendidasNormales()+" entrada/s normal/es "+vendedor.getEntradasVendidasVip()+" entrada/s VIP");
	}	
	
	
	public static boolean validarEventoEnCurso(Evento evento , EntradaVip entrada) {
		boolean entre = false;
		if(evento.existeEntrada(entrada.getCliente().getRut())) {
			if(evento.isEnCurso()) {
				System.out.println("Usando entrada con cliente "+entrada.getCliente().getNombre()+" ("+entrada.getCliente().getRut()+") para evento "+evento.getNombre());
				System.out.println("Entrada encontrada, "+entrada.getCliente().getNombre()+" puede pasar.");
				entrada.setUsada(true);
				entre = true;
			}else {
				System.out.println("Usando entrada con cliente "+entrada.getCliente().getNombre()+" ("+entrada.getCliente().getRut()+") para evento "+evento.getNombre());
				System.out.println("No se puede usar la entrada porque el evento "+evento.getNombre()+" no está en curso.");
			}
		}
		return entre;
	}
	

}
