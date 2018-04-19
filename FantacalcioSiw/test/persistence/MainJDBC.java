package persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.FantaCampionato;
import model.FantaPartita;
import model.FantaSquadra;
import model.Giocatore;
import model.Utente;
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

		//INSTANCES UTENTE,GIOCATORE,FANTACAMPIONATO E SQUADRA
		Utente destiny= new Utente("destiny", "destiny@gmail.com", "berlusconiErMeglio");
		Utente chimera= new Utente("chimera", "chimera@gmail.com", "salvenee");
		Utente fragola86= new Utente("fragola86", "fragola86@gmail.com", "pompei");
		Utente banana33= new Utente("banana33", "banana33@gmail.com", "cetriolo");
		
		Giocatore andrea_silva= new Giocatore("Andrea Silva", "milan", "seriaA", "att", 30);
		Giocatore mauro_icardi= new Giocatore("Mauro Icardi", "inter", "seriaA", "att", 30);
		Giocatore paulo_dybala= new Giocatore("Paulo Dybala", "juventus", "seriaA", "att", 30);
		Giocatore lorenzo_insigne= new Giocatore("Lorenzo Insigne", "napoli", "seriaA", "att", 30);
		Giocatore pinuccio= new Giocatore("Lorenzinu Insegna", "napoli", "seriaA", "att", 30);
		
		FantaCampionato serieA = new FantaCampionato("primoCampionato", 4);
		FantaCampionato premierLeague = new FantaCampionato("secondoCampionato", 4);
		FantaCampionato bundesliga = new FantaCampionato("terzoCampionato", 4);
		FantaCampionato laLiga = new FantaCampionato("quartoCampionato", 8);
		
		FantaSquadra fantamilan= new FantaSquadra("fantamilan");
		FantaSquadra fantainter= new FantaSquadra("fantainter");
		FantaSquadra fantajuventus = new FantaSquadra("fantajuve");
		FantaSquadra fantanapoli = new FantaSquadra("fantanapoli");
		FantaSquadra fantanapoliTAMARRI = new FantaSquadra("fantanapoliTAMARRI");

		//SAVE UTENTE,GIOCATORE,FANTACAMPIONATO E FANTASQUADRA
		utenteDao.save(destiny);
		utenteDao.save(chimera);
		utenteDao.save(fragola86);
		utenteDao.save(banana33);
		
		giocatoreDao.save(andrea_silva);
		giocatoreDao.save(mauro_icardi);
		giocatoreDao.save(paulo_dybala);
		giocatoreDao.save(lorenzo_insigne);
		giocatoreDao.save(pinuccio);
		
		fantaCampionatoDao.save(serieA);
		fantaCampionatoDao.save(premierLeague);
		fantaCampionatoDao.save(bundesliga);
		fantaCampionatoDao.save(laLiga);
		
		fantaSquadraDao.save(fantamilan);
		fantaSquadraDao.save(fantainter);
		fantaSquadraDao.save(fantajuventus);
		fantaSquadraDao.save(fantanapoli);
		fantaSquadraDao.save(fantanapoliTAMARRI);
		
		//INSTANCES FANTAPARTITA
		FantaPartita milan_inter = new FantaPartita(fantamilan.getId(), fantainter.getId(), 1);
		FantaPartita juventus_napoli = new FantaPartita(fantajuventus.getId(), fantanapoli.getId(), 1);
		FantaPartita milan_juventus = new FantaPartita(fantamilan.getId(), fantajuventus.getId(), 1);
		FantaPartita napoli_inter = new FantaPartita(fantanapoli.getId(), fantainter.getId(), 1);
		FantaPartita napoli_interScarsi = new FantaPartita(fantanapoli.getId(), fantainter.getId(), 2);
		
		//SAVE FANTAPARTITA
		fantaPartitaDao.save(milan_inter);
		fantaPartitaDao.save(juventus_napoli);
		fantaPartitaDao.save(milan_juventus);
		fantaPartitaDao.save(napoli_inter);
		fantaPartitaDao.save(napoli_interScarsi);
		
		//DELETE
		utenteDao.delete(chimera);
		giocatoreDao.delete(pinuccio);
		fantaCampionatoDao.delete(bundesliga);
		fantaPartitaDao.delete(napoli_interScarsi);
		fantaSquadraDao.delete(fantanapoliTAMARRI);
		
		//UPDATE
		destiny.setUsername("real_destiny");
		utenteDao.update(destiny);
		
		mauro_icardi.setPrezzo(25);;
		giocatoreDao.update(mauro_icardi);
		
		laLiga.setNome("terzoCampionato");
		laLiga.setTotalePartecipanti(4);
		fantaCampionatoDao.update(laLiga);
		
		milan_juventus.setGiornata(2);
		fantaPartitaDao.update(milan_juventus);
		
		fantajuventus.setNome("fantajuventus");
		fantaSquadraDao.update(fantajuventus);

		//-----------------------------TEST-----------------------------------------
		System.out.println("\n<TEST: DELETE, UPDATE, FINDALL - LISTA UTENTI>");
		List<Utente> utenti= utenteDao.findAll();
		for (Utente utente : utenti)
		{
			System.out.println(utente.getUsername());
		}
		System.out.println("<ENDTEST: DELETE, UPDATE, FINDALL>\n");	
		
		System.out.println("\n<TEST: DELETE, UPDATE, FINDALL - LISTA GIOCATORI>");
		List<Giocatore> giocatori= giocatoreDao.findAll();
		for (Giocatore giocatore : giocatori)
		{
			System.out.println(giocatore.getNome() + " - prezzo: "+giocatore.getPrezzo());
		}
		System.out.println("<ENDTEST: DELETE, UPDATE, FINDALL>\n");	
		
		System.out.println("\n<TEST: DELETE, UPDATE, FINDALL - LISTA CAMPIONATI>");
		List<FantaCampionato> fantaCampionati= fantaCampionatoDao.findAll();
		for (FantaCampionato fantaCampionato : fantaCampionati)
		{
			System.out.println(fantaCampionato.getNome());
		}
		System.out.println("<ENDTEST: DELETE, UPDATE, FINDALL>\n");
		
		System.out.println("\n<TEST: DELETE, UPDATE, FINDALL - LISTA PARTITE>");
		List<FantaPartita> fantaPartite= fantaPartitaDao.findAll();
		for (FantaPartita fantaPartita : fantaPartite)
		{
			System.out.println(fantaPartita.getId()+": "+fantaPartita.getSquadraInCasa()+" vs. "+fantaPartita.getSquadraOspite());
		}
		System.out.println("<ENDTEST: DELETE, UPDATE, FINDALL>\n");
		
		System.out.println("\n<TEST: DELETE, UPDATE, FINDALL - LISTA SQUADRE>");
		List<FantaSquadra> fantaSquadre= fantaSquadraDao.findAll();
		for (FantaSquadra fantaSquadra : fantaSquadre)
		{
			System.out.println(fantaSquadra.getNome());
		}
		System.out.println("<ENDTEST: DELETE, UPDATE, FINDALL>\n");	
		//----------------------FINE-TEST-------------------------------------------
	}

}
