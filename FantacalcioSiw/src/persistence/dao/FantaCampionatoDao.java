package persistence.dao;

import java.util.List;

import model.FantaCampionato;

public interface FantaCampionatoDao {
	public void save(FantaCampionato fantaCampionato);  // Create
	public FantaCampionato findByPrimaryKey(Long idFantaCampionato);     // Retrieve
	public List<FantaCampionato> findAll();       
	public void update(FantaCampionato fantaCampionato); //Update
	public void delete(FantaCampionato fantaCampionato); //Delete	
}
