package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilDao
{

	private DataSource dataSource;

	public UtilDao(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public void dropDatabase()
	{

		Connection connection = dataSource.getConnection();
		try
		{
			String delete = "drop SEQUENCE if EXISTS sequenza_id;"
					+ "drop table if exists fantacampionato;"
					+ "drop table if exists fantapartita;" 
					// a cascata perchè c'è la dipendenza con giocatore
					+ "drop table if exists fantasquadra cascade;"
					+ "drop table if exists giocatore;"
					+ "drop table if exists utente;";
			PreparedStatement statement = connection.prepareStatement(delete);

			statement.executeUpdate();
			System.out.println("Executed drop database");

		} catch (SQLException e)
		{

			throw new PersistenceException(e.getMessage());
		} finally
		{
			try
			{
				connection.close();
			} catch (SQLException e)
			{

				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void createDatabase()
	{

		Connection connection = dataSource.getConnection();
		try
		{

			String delete = "create SEQUENCE sequenza_id;"
					+ "create table fantacampionato (\"id\" bigint primary key, nome varchar(255), totale_partecipanti int, stato int);"
					+ "create table fantapartita (\"id\" bigint primary key, squadra_in_casa bigint, squadra_ospite bigint, risultato int, giornata int);"
					+ "create table fantasquadra (\"id\" bigint primary key, nome varchar(255), fantamilioni int);"
					+ "create table giocatore(nome varchar(255) primary key, squadra_provenienza varchar(255), campionato_provenienza varchar(255), ruolo varchar(255), prezzo int);"//, squadra int REFERENCES fantasquadra(\"id\"));"
					+ "create table utente(\"username\" varchar(255) primary key, mail varchar(255), password varchar(255), fantacampionati_vinti int, fantacampionati_giocati int);";
			PreparedStatement statement = connection.prepareStatement(delete);

			statement.executeUpdate();
			System.out.println("Executed create database");

		} catch (SQLException e)
		{

			throw new PersistenceException(e.getMessage());
		} finally
		{
			try
			{
				connection.close();
			} catch (SQLException e)
			{

				throw new PersistenceException(e.getMessage());
			}
		}
	}

}
