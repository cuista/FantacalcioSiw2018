package persistence.dao;

import java.util.List;

import model.FantaPartita;

public interface FantaPartitaDao {
	public void save(FantaPartita fantaPartita);  // Create
	public FantaPartita findByPrimaryKey(Long id);     // Retrieve
	public List<FantaPartita> findAll();       
	public void update(FantaPartita fantaPartita); //Update
	public void delete(FantaPartita fantaPartita); //Delete	
}
