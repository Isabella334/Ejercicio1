//************************************************************
// UNIVERSIDAD DEL VALLE DE GUATEMALA
// Departamento de Ciencias De La Computación
// CC2008 - 50
// Autores: Milton Giovanni Polanco Serrano
//			Isabella Recinos Rodríguez
// Fecha: Agosto 7 2023
// Descripción: Lab1
//************************************************************
import java.util.Scanner;
import java.util.Random;

class Comprador { //Clase comprador
    String nombre;
    int dpi;

    // Constructor
    public Comprador(String nombre, int dpi) {
        this.nombre = nombre;
        this.dpi = dpi;
    }

    // Métodos de acceso
    public String getNombre() {
        return nombre;
    }
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public int getDpi() {
        return dpi;
    }
	public void setDpi(int dpi){
		this.dpi = dpi;
	}
}

class Solicitud { //Solicitud
    int cant_boletos;
	double presupuesto;

	//Constructor
    public Solicitud(int cant_boletos, double presupuesto) {
        this.cant_boletos = cant_boletos;
		this.presupuesto = presupuesto;
    }
	
	// Métodos de acceso
	public int getBoletos() {
        return cant_boletos;
    }
	public void setBoletos(int cant_boletos){
		this.cant_boletos = cant_boletos;
	}
	
	public double getPresupuesto() {
        return presupuesto;
    }
	public void setPresupuesto(double presupuesto){
		this.presupuesto = presupuesto;
	}
}

public class Programa{
	public static void main(String[] args){
		boolean comprador_activo, salida;
		comprador_activo = false; //Indica que al momento de inicial el programa no hay un comprador activo.
		salida = false; // Indica que aún no se ha solicitado la salida
		Scanner scanner = new Scanner(System.in);
		
		//Lugares disponibles en cada localidad al inicio del programa(60/3)
		int localidad1 = 20;
		int localidad5 = 20;
		int localidad10 = 20;
		
		float total_ventas = 0; //Variable que almacena las ganancias de las ventas
		
		while (salida == false){ //Mientras no se solicite la salida
			System.out.println("Seleccione la acción que desea realizar (1-6): \n1. Nuevo comprador\n2.Nueva solicitud de boletos\n3.Consultar disponibilidad total\n4.Consultar disponibilidad individual\n5.Reporte de caja\n6.Salir");
			String num = scanner.nextLine(); //El usuario selecciona la acción a realizar
			
			if (num.equals("1")){ //Nuevo comprador
				System.out.println("Ingrese el nombre del comprador: ");
				String nombre = scanner.nextLine();
				
				System.out.println("Ingrese el DPI del comprador: ");
				int dpi = scanner.nextInt();
				scanner.nextLine();
				
				Comprador comprador = new Comprador(nombre, dpi);
				
				comprador_activo = true;
				
			}else if(num.equals("2") && comprador_activo){ //Solicitud de compra
				System.out.println("Ingrese la cantidad de boletos que desea comprar: ");
				int boletos = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ingrese su presupuesto ($): ");
				double presu = scanner.nextDouble();
				scanner.nextLine();
				
				Solicitud solicitud = new Solicitud(boletos, presu);
				
				Random random = new Random();
				int ticket = random.nextInt(28000);
				
				int a = random.nextInt(15000);
				int b = random.nextInt(15000);
				
				int suma = ticket + a + b;
				
				if (suma%2 == 0){ //Se verifica si el número es par o impar
					System.out.println("Lo sentimos, no podemos otorgarle entradas :( (número impar)");
				}else{
					int[] localidad = {1, 5, 10};
					int[] precios = {300, 565, 1495};
					int indiceAleatorio = random.nextInt(localidad.length);
					int numeroAleatorio = localidad[indiceAleatorio];
		
					
					int precio = precios[indiceAleatorio];
					float total = precio * solicitud.cant_boletos;
					
					if (solicitud.presupuesto >= total){ //Se consulta el presupuesto y se compara con el total a pagar
						if (indiceAleatorio == 0){
							if (localidad1 >= solicitud.cant_boletos){
								localidad1 = localidad1 - solicitud.cant_boletos;
								total_ventas = total_ventas + total;
								System.out.println("¡Felicidades! Acaba de comprar "+solicitud.cant_boletos+" boletos para la localidad 1.");
								System.out.println("Total a pagar: $"+total);
							}else{
								System.out.println("Lo sentimos, no hay espacios disponibles :(");
							}
						}else if (indiceAleatorio == 1){
							if (localidad5 >= solicitud.cant_boletos){
								localidad5 = localidad5 - solicitud.cant_boletos;
								total_ventas = total_ventas + total;
								System.out.println("¡Felicidades! Acaba de comprar "+solicitud.cant_boletos+" boletos para la localidad 5.");
								System.out.println("Total a pagar: $"+total);
							}else{
								System.out.println("Lo sentimos, no hay espacios disponibles :(");
							}
						}else if (indiceAleatorio == 2){
							if (localidad10 >= solicitud.cant_boletos){
								localidad10 = localidad10 - solicitud.cant_boletos;
								total_ventas = total_ventas + total;
								System.out.println("¡Felicidades! Acaba de comprar "+solicitud.cant_boletos+" boletos para la localidad 10.");
								System.out.println("Total a pagar: $"+total);
							}else{
								System.out.println("Lo sentimos, no hay espacios disponibles :(");
							}
						}
					}else{
						System.out.println("Lo sentimos, el precio se pasa de su presupuesto");
					}
				}
				
			}else if(num.equals("3")){ //Consulta de disponibilidad
				System.out.println("Espacios disponibles en: \nLocalidad 1 = "+localidad1+"\nLocalidad 5 = "+localidad5+"\nLocalidad 10 = "+localidad10);	
			}else if(num.equals("4")){ //Consulta de disponibilidad por localidad
				System.out.println("Seleccione alguna localidad (1, 5 o 10): ");
				int op_localidad = scanner.nextInt();
				scanner.nextLine();
				if (op_localidad == 1){
					System.out.println("Espacios disponibles: "+localidad1);
				}else if (op_localidad == 5){
					System.out.println("Espacios disponibles: "+localidad5);
				}else if (op_localidad == 10){
					System.out.println("Espacios disponibles: "+localidad10);
				}else{
					System.out.println("Error, ingreso incorrecto");
				}
			}else if(num.equals("5")){ //Consulta de ganancias
				System.out.println("Se a recaudado un total de $ "+total_ventas+" en caja");
			}else if(num.equals("6")){ //Solicitar salida del programa
				salida = true;
			}else{
				System.out.println("Error :(. Revise que haya un comrador activo o que esté ingresando la opción correcta.");
			}
		}
	}
}