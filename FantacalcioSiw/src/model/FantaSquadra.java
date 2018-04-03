package model;

import java.util.ArrayList;

public class FantaSquadra {
	
	public static final int TOT_GIOCATORI=25;
	public static final int FANTAMILIONI_INIZIALE=500;
	
	private Long id;
	private String nome;
	private int fantamilioni;
	private ArrayList<Giocatore> giocatori;
	
	public FantaSquadra(String nome)
	{
		this.nome=nome;
		fantamilioni=FANTAMILIONI_INIZIALE;
		giocatori=new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getFantamilioni() {
		return fantamilioni;
	}

	public void setFantamilioni(int fantamilioni) {
		this.fantamilioni = fantamilioni;
	}

	public ArrayList<Giocatore> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(ArrayList<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}
	
	public void addGiocatore(Giocatore g)
	{
		if (this.giocatori==null)
		{
			giocatori=new ArrayList<Giocatore>();
		}
		
		giocatori.add(g);
	}
	
	public void removeGiocatore(Giocatore g)
	{
		if(giocatori!=null)
			giocatori.remove(g);
	}

}
