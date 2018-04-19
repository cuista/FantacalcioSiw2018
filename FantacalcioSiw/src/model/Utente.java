package model;

public class Utente {

	private String username;
	private String mail;
	private String password;
	private int fantaCampionati_vinti;
	private int fantaCampionati_giocati;
	
	public Utente (String username,String mail,String password)
	{
		this.username=username;
		this.mail=mail;
		this.password=password;
		fantaCampionati_vinti=0;
		fantaCampionati_giocati=0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFantaCampionati_vinti() {
		return fantaCampionati_vinti;
	}

	public void setFantaCampionati_vinti(int fantaCampionati_vinti) {
		this.fantaCampionati_vinti = fantaCampionati_vinti;
	}

	public int getFantaCampionati_giocati() {
		return fantaCampionati_giocati;
	}

	public void setFantaCampionati_giocati(int fantaCampionati_giocati) {
		this.fantaCampionati_giocati = fantaCampionati_giocati;
	}
}
