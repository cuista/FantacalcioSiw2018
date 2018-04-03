package persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.FantaCampionato;
import persistence.dao.FantaCampionatoDao;
import persistence.dao.FantaPartitaDao;
import persistence.dao.FantaSquadraDao;
import persistence.dao.GiocatoreDao;
import persistence.dao.UtenteDao;

public class MainJDBC
{

	public static void main(String args[])
	{
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDAO();
		util.dropDatabase();

		util.createDatabase();

		//DAO
		FantaCampionatoDao fantaCampionatoDao = factory.getFantaCampionatoDAO();
		FantaPartitaDao fantaPartitaDao= factory.getFantaPartitaDAO();
		FantaSquadraDao fantaSquadraDao = factory.getFantaSquadraDAO();
		GiocatoreDao giocatoreDao = factory.getGiocatoreDAO();
		UtenteDao utenteDao = factory.getUtenteDAO();

		//INSTANCES FANTACAMPIONATO
		FantaCampionato serieA = new FantaCampionato("primoCampionato", 4);
		FantaCampionato premierLeague = new FantaCampionato("secondoCampionato", 4);
		FantaCampionato bundesliga = new FantaCampionato("terzoCampionato", 4);
		FantaCampionato laLiga = new FantaCampionato("quartoCampionato", 8);

		//SAVE
		fantaCampionatoDao.save(serieA);
		fantaCampionatoDao.save(premierLeague);
		fantaCampionatoDao.save(bundesliga);
		fantaCampionatoDao.save(laLiga);
		
		//-----------------------------TEST-----------------------------------------
		System.out.println("\n<TEST: SAVE - LISTA CAMPIONATI>");
		String serieATEST = fantaCampionatoDao.findByPrimaryKey(serieA.getId()).getNome();
		String premierLeagueTEST = fantaCampionatoDao.findByPrimaryKey(premierLeague.getId()).getNome();
		String bundesligaTEST = fantaCampionatoDao.findByPrimaryKey(bundesliga.getId()).getNome();
		String laLigaTEST = fantaCampionatoDao.findByPrimaryKey(laLiga.getId()).getNome();
		System.out.println(serieATEST);
		System.out.println(premierLeagueTEST);
		System.out.println(bundesligaTEST);
		System.out.println(laLigaTEST);
		System.out.println("<ENDTEST: SAVE>\n");
		//----------------------FINE-TEST-------------------------------------------
		
		//DELETE
		fantaCampionatoDao.delete(bundesliga);
		
		//UPDATE
		laLiga.setNome("terzoCampionato");
		laLiga.setTotalePartecipanti(4);
		fantaCampionatoDao.update(laLiga);

		//-----------------------------TEST-----------------------------------------		
		System.out.println("\n<TEST: DELETE, UPDATE, FINDALL - LISTA CAMPIONATI>");
		List<FantaCampionato> fantaCampionati= fantaCampionatoDao.findAll();
		for (FantaCampionato fantaCampionato : fantaCampionati)
		{
			System.out.println(fantaCampionato.getNome());
		}
		System.out.println("<ENDTEST: DELETE, UPDATE, FINDALL>\n");		
		//----------------------FINE-TEST-------------------------------------------
	}

}
