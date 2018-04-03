package model;

public class FantaCampionato {
	
	private Long id;
	private String nome;
	private int totalePartecipanti;
	private int stato; //0 è in corso, 1 è attivo, 2 è terminato
	
	public FantaCampionato(String nome, int totalePartecipanti)
	{
		stato=1;
		this.nome=nome;
		this.totalePartecipanti=totalePartecipanti;
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

	public int getTotalePartecipanti() {
		return totalePartecipanti;
	}

	public void setTotalePartecipanti(int totalePartecipanti) {
		this.totalePartecipanti = totalePartecipanti;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

}
