package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.Dao;
import model.Genero;
import model.Pelicula;

public class PeliculaController {
	Dao dao;

	public PeliculaController() {
		dao = new Dao();
	}
	
	public void peliculaPorTitulo() throws SQLException {
		dao.connectar();
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce el numero que corresponda con la pelicula que quieres consultar:");
		ArrayList<Pelicula> todasPeliculas = dao.todasPeliculas();
		for (int i = 0;i<todasPeliculas.size();i++) {
			System.out.println(i+1 + "-->"+todasPeliculas.get(i).getTitulo());
		}
		int pelicula_seleccionada = entrada.nextInt();
		Pelicula pelicula = dao.peliculaPorTitulo(todasPeliculas.get(pelicula_seleccionada-1).getTitulo());
		System.out.println("Informacion de la pelicula:");
		System.out.println(pelicula);
		dao.desconectar();
	}
	
	public void insetarPelicula() throws SQLException {
		dao.connectar();
		Scanner entrada = new Scanner(System.in);
		int codigo =  dao.todasPeliculas().size() + 1;
		System.out.println("Introduce el nombre de la pelicula:");
		String nombre = entrada.nextLine();
		ArrayList<Genero> todosGeneros = dao.todosGeneros();
		System.out.println("A que genero corresponde la pelicula:");
		for (int i= 0;i<todosGeneros.size();i++) {
			System.out.println(i+1 + "-->"+todosGeneros.get(i).getDescripcion());
		}
		int genero_seleccionado = entrada.nextInt();
		int codigoGenero = todosGeneros.get(genero_seleccionado-1).getCodigo();
		System.out.println("Tiene segunda parte?");
		System.out.println("1. Si");
		System.out.println("2. No");
		int tieneSegundaParte = entrada.nextInt();
		Integer segundaParte = null;
		if (tieneSegundaParte == 1) {
			segundaParte = 1;
		}
		Pelicula pelicula = new Pelicula(codigo, nombre, codigoGenero, segundaParte);
		dao.insetarPelicula(pelicula);
		System.out.println("La pelicula ha sido insertada");
		dao.desconectar();
	}
	
	public void eliminarPelicula() throws SQLException {
		dao.connectar();
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce el numero que corresponda con la pelicula que quieres eliminar:");
		ArrayList<Pelicula> todasPeliculas = dao.todasPeliculas();
		for (int i = 0;i<todasPeliculas.size();i++) {
			System.out.println(i+1 + "-->"+todasPeliculas.get(i).getTitulo());
		}
		int pelicula_seleccionada = entrada.nextInt();
		int codigo = todasPeliculas.get(pelicula_seleccionada-1).getCodigo();
		dao.eliminarPelicula(codigo);
		System.out.println("La pelicula ha sido eliminada");
		dao.desconectar();
	}
	
	public void actualizarPelicula() throws SQLException {
		dao.connectar();
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce el numero que corresponda con la pelicula que quieres actualizar:");
		ArrayList<Pelicula> todasPeliculas = dao.todasPeliculas();
		for (int i = 0;i<todasPeliculas.size();i++) {
			System.out.println(i+1 + "-->"+todasPeliculas.get(i).getTitulo());
		}
		int pelicula_seleccionada = entrada.nextInt();
		String titulo = todasPeliculas.get(pelicula_seleccionada-1).getTitulo();
		System.out.println("A que genero quieres acutalizar la pelicula?");
		ArrayList<Genero> todosGeneros = dao.todosGeneros();
		System.out.println("A que genero corresponde la pelicula:");
		for (int i= 0;i<todosGeneros.size();i++) {
			System.out.println(i+1 + "-->"+todosGeneros.get(i).getDescripcion());
		}
		int genero_seleccionado = entrada.nextInt();
		int codigoGenero = todosGeneros.get(genero_seleccionado-1).getCodigo();
		dao.actualizarPelicula(codigoGenero, titulo);
		dao.desconectar();
	}
	
	
}
