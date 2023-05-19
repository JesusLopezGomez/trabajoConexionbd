package dbconnect.main.java;

import java.sql.SQLException;
import java.util.Scanner;

import dbconnect.main.java.api.Connector;

public class MainApp {

		public static void main(String[] args) {
			Connector c = new Connector();
			
			final String MENU ="--------------------------------------------------------------------------"
			+ "\n 1. Mostrar información sobre los clientes"
			+ "\n 2. Mostrar pedidos"
			+ "\n 3. Añadir cliente"
			+ "\n 4. Actualizar un cliente existente"
			+ "\n 5. Eliminar cliente"
			+ "\n 6. Añadir Pedido"
			+ "\n 7. Incluir Líneas"
			+ "\n 8. Salir"
			+"\n -------------------------------------------------------------------------";
			
			Scanner sc = new Scanner(System.in);

			int opcion = 0;


			while (opcion!=8) {

			System.out.println(MENU);

			System.out.println("Introduzca la opcion que más le interese: ");

			opcion = Integer.valueOf(sc.nextLine());

			if (opcion==1) {
				try {
					System.out.println(c.mostrarInfoCliente());
				} catch (ClassNotFoundException  | SQLException e) {
					e.printStackTrace();
				} 
			}else if (opcion==2) {
				try {
					System.out.println(c.mostrarInfoPedidos());
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}else if (opcion==3) {
				System.out.println("Introduce el nombre:");
				String nombre = sc.nextLine();
				System.out.println("Introduce el apellido:");
				String apellido = sc.nextLine();
				System.out.println("Introduce el email: ");
				String email = sc.nextLine();
				System.out.println("Introduce el año de nacimiento: ");
				String annio = sc.nextLine();
				System.out.println("Introduce el mes de nacimiento: ");
				String mes = sc.nextLine();
				System.out.println("Introduce el dia de nacimiento: ");
				String dia = sc.nextLine();
				System.out.println("Introduce el sexo M/F");
				String sexo = sc.nextLine();
				try {
					c.anniadirCliente(nombre, apellido,email, annio+"-"+mes+"-"+dia+"", sexo);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (opcion==4) {


			}else if (opcion==5) {


			}else if (opcion==6) {


			}else if (opcion==7) {


			}

			}System.out.println("Acabando..");

	
		}
}
