package persistence.dao;

import java.util.List;

import model.FantaSquadra;

public interface FantaSquadraDao {
	public void save(FantaSquadra fantaSquadra);  // Create
	public FantaSquadra findByPrimaryKey(Long id);     // Retrieve
	public List<FantaSquadra> findAll();       
	public void update(FantaSquadra fantaSquadra); //Update
	public void delete(FantaSquadra fantaSquadra); //Delete	
}
