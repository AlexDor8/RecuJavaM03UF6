package model;

public class Pelicula {
	private int codigo;
	private String titulo;
	private int codigoGenero;
	private Integer segundaParte;
	
	public Pelicula(int codigo, String titulo, int codigoGenero, Integer segundaParte) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.codigoGenero = codigoGenero;
		this.segundaParte = segundaParte;
	}

	public Pelicula() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getCodigoGenero() {
		return codigoGenero;
	}

	public void setCodigoGenero(int codigoGenero) {
		this.codigoGenero = codigoGenero;
	}

	

	public Integer getSegundaParte() {
		return segundaParte;
	}

	public void setSegundaParte(Integer segundaParte) {
		this.segundaParte = segundaParte;
	}

	@Override
	public String toString() {
		return "Pelicula [codigo=" + codigo + ", titulo=" + titulo + ", codigoGenero=" + codigoGenero
				+ ", segundaParte=" + segundaParte + "]";
	}

	
	
	
	
}
