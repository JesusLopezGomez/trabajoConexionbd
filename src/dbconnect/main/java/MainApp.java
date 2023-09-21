package dbconnect.main.java;

import java.sql.SQLException;
import java.util.Scanner;

import dbconnect.main.java.api.Connector;

public class MainApp {

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			Connector c = new Connector();
			int opcion = 0;
			
			final String MENU =" ---------------------------------------------"
			+ "\n | 1. Mostrar información sobre los clientes |"
			+ "\n | 2. Mostrar pedidos                        |"
			+ "\n | 3. Añadir cliente                         |"
			+ "\n | 4. Actualizar un cliente existente        |"
			+ "\n | 5. Eliminar cliente                       |"
			+ "\n | 6. Añadir Pedido                          |"
			+ "\n | 7. Incluir Líneas                         |"
			+ "\n | 8. Salir                                  |"
			+"\n ----------------------------------------------";
			
			do { 
				do {
					System.out.println(MENU);
					opcion = Integer.valueOf(sc.nextLine());
				} while(opcion!=1 && opcion!=2 && opcion!=3 && opcion!=4 && opcion!=5 && opcion!=6 && opcion!=7 && opcion!=8);
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
					System.out.println("Introduce el id de la persona que quieres modificar: ");
					int id = Integer.valueOf(sc.nextLine());
					System.out.println("Introduce el nombre:");
					String nombre = sc.nextLine();
					System.out.println("Introduce el apellido:");
					String apellido = sc.nextLine();
					
					try {
						c.actualizarCliente(id, nombre, apellido);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else if (opcion==5) {
					System.out.println("Introduce el id de la persona que quieres eliminar: ");
					int id = Integer.valueOf(sc.nextLine());
					
					try {
						c.eliminarCliente(id);
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}else if (opcion==6) {
					System.out.println("Introduce el id:");
					int id = Integer.valueOf(sc.nextLine());
					System.out.println("Introduce el codigo:");
					String cod = sc.nextLine();
					System.out.println("Introduce el status: ");
					String status = sc.nextLine();
					System.out.println("Introduce el idCliente: ");
					int idCli = Integer.valueOf(sc.nextLine());
					
					try {
						c.anniadirPedido(id, cod,status, idCli);
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}else if (opcion==7) {
					System.out.println("Introduce el id:");
					int id = Integer.valueOf(sc.nextLine());
					System.out.println("Introduce el codigo:");
					String codigo = sc.nextLine();
					System.out.println("Introduce el nombre de producto: ");
					String nombreProducto = sc.nextLine();
					System.out.println("Introduce el id pedido: ");
					int idPedido = Integer.valueOf(sc.nextLine());
					System.out.println("Introduce la cantidad: ");
					int cantidad = Integer.valueOf(sc.nextLine());
					System.out.println("Introduce el precio: ");
					int precio = Integer.valueOf(sc.nextLine());
					try {
						c.anniadirLinea(id, codigo, nombreProducto, idPedido, cantidad, precio);
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			} while(opcion!=8);
			System.out.println("Programa finalizado.");
	
		}
}
