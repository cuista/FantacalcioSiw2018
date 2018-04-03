package model;

public class Giocatore {
	
	private String nome;
	private String squadraProvenienza;
	private String campionatoProvenienza;
	private String ruolo;
	private int prezzo;
	
	public Giocatore(String nome, String squadraProvenienza, String campionatoProvenienza, String ruolo, int prezzo)
	{
		this.nome=nome;
		this.squadraProvenienza=squadraProvenienza;
		this.campionatoProvenienza=campionatoProvenienza;
		this.ruolo=ruolo;
		this.prezzo=prezzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSquadraProvenienza() {
		return squadraProvenienza;
	}

	public void setSquadraProvenienza(String squadraProvenienza) {
		this.squadraProvenienza = squadraProvenienza;
	}

	public String getCampionatoProvenienza() {
		return campionatoProvenienza;
	}

	public void setCampionatoProvenienza(String campionatoProvenienza) {
		this.campionatoProvenienza = campionatoProvenienza;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

}
