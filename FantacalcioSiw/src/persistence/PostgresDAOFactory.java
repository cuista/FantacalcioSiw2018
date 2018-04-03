package persistence;

import persistence.dao.FantaCampionatoDao;
import persistence.dao.FantaPartitaDao;
import persistence.dao.FantaSquadraDao;
import persistence.dao.GiocatoreDao;
import persistence.dao.UtenteDao;

class PostgresDAOFactory extends DAOFactory {

	//INFO fabbrica concreta oggetti DAO
	
	private static  DataSource dataSource;
	

	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			//questi vanno messi in file di configurazione!!!	
//			dataSource=new DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");
			dataSource=new DataSource("jdbc:postgresql://localhost:5432/FantacalcioSiw","postgres","root");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	
	// --------------------------------------------
	
	@Override
	public FantaCampionatoDao getFantaCampionatoDAO() {
		return new FantaCampionatoDaoJDBC(dataSource);
	}

	@Override
	public FantaPartitaDao getFantaPartitaDAO()
	{
		return new FantaPartitaDaoJDBC(dataSource);
	}

	@Override
	public FantaSquadraDao getFantaSquadraDAO()
	{
		return new FantaSquadraDaoJDBC(dataSource);
	}

	@Override
	public GiocatoreDao getGiocatoreDAO()
	{
		return new GiocatoreDaoJDBC(dataSource);
	}

	@Override
	public UtenteDao getUtenteDAO()
	{
		return new UtenteDaoJDBC(dataSource);
	}
	
	@Override
	public UtilDao getUtilDAO(){
		return new UtilDao(dataSource);
	}

}
