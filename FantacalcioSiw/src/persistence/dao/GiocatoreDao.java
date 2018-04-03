package persistence.dao;

import java.util.List;

import model.Giocatore;

public interface GiocatoreDao {
	public void save(Giocatore giocatore);  // Create
	public Giocatore findByPrimaryKey(String nome);     // Retrieve
	public List<Giocatore> findAll();       
	public void update(Giocatore giocatore); //Update
	public void delete(Giocatore giocatore); //Delete	
}
