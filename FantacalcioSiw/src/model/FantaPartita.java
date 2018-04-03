package model;

public class FantaPartita {
	private Long id;
	private Long squadraInCasa;
	private Long squadraOspite;
	private int risultato; // 1-> vince casa, 0-> pareggio, 2-> vince ospite, [-1]-> da giocare
	private int giornata;
	
	public FantaPartita(Long squadraInCasa, Long squadraOspite, int giornata)
	{
		this.squadraInCasa=squadraInCasa;
		this.squadraOspite=squadraOspite;
		this.giornata=giornata;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSquadraInCasa() {
		return squadraInCasa;
	}

	public void setSquadraInCasa(Long squadraInCasa) {
		this.squadraInCasa = squadraInCasa;
	}

	public Long getSquadraOspite() {
		return squadraOspite;
	}

	public void setSquadraOspite(Long squadraOspite) {
		this.squadraOspite = squadraOspite;
	}

	public int getRisultato() {
		return risultato;
	}

	public void setRisultato(int risultato) {
		this.risultato = risultato;
	}

	public int getGiornata() {
		return giornata;
	}

	public void setGiornata(int giornata) {
		this.giornata = giornata;
	}
}
