package vista;

import java.sql.SQLException;
import java.util.Scanner;

import controller.PeliculaController;

public class Main {

	public static void main(String[] args) {
		menu();
	}
	
	public static void menu() {
		PeliculaController peliculaController= new PeliculaController();
		Scanner entrada = new Scanner(System.in);
		System.out.println("MENU:");
		System.out.println("Introduce el numero que corresponda con la accion que quieres realizar:");
		System.out.println("1. Insetar una pelicula.");
		System.out.println("2. Consultar pelicula por título.");
		System.out.println("3. Actualizar una película.");
		System.out.println("4. Eliminar una pelicula.");
		System.out.println("5. Salir");


		int accion = entrada.nextInt();

		switch (accion) {
		case 1:
			try {
				peliculaController.insetarPelicula();
				menu();
			} catch (SQLException e1) {
				e1.printStackTrace();
			};
			break;
		case 2:
			try {
				peliculaController.peliculaPorTitulo();
				menu();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 
			break;
		case 3:
			try {
				peliculaController.actualizarPelicula();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			menu();
			break;
		case 4:
			try {
				peliculaController.eliminarPelicula();
				menu();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 5:
			break;
		default:
			System.out.println("El numero introducido no corresponde con ninguna accion.");
			menu();
			break;
		}
	}

}
