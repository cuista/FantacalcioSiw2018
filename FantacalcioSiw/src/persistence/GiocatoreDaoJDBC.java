package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.FantaCampionato;
import model.Giocatore;
import persistence.dao.GiocatoreDao;

public class GiocatoreDaoJDBC implements GiocatoreDao {
	
	private DataSource dataSource;
	
	public GiocatoreDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}

	@Override
	public void save(Giocatore giocatore) {
		Connection connection = this.dataSource.getConnection();
		try
		{
			String insert = "insert into giocatore(nome, squadra_provenienza, campionato_provenienza, ruolo, prezzo) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, giocatore.getNome());
			statement.setString(2, giocatore.getSquadraProvenienza());
			statement.setString(3, giocatore.getCampionatoProvenienza());
			statement.setString(4, giocatore.getRuolo());
			statement.setInt(5, giocatore.getPrezzo());

			statement.executeUpdate();
		} catch (SQLException e)
		{
			if (connection != null)
			{
				try
				{
					connection.rollback();
				} catch (SQLException excep)
				{
					throw new PersistenceException(e.getMessage());
				}
			}
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

	@Override
	public Giocatore findByPrimaryKey(String nome) {
		Connection connection = this.dataSource.getConnection();
		Giocatore giocatore = null;
		try
		{
			PreparedStatement statement;
			String query = "select * from giocatore where nome = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, nome);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				giocatore = new Giocatore(result.getString("nome"), result.getString("squadra_provenienza"),result.getString("campionato_provenienza"),result.getString("ruolo"),result.getInt("prezzo"));
			}
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
		return giocatore;
	}

	@Override
	public List<Giocatore> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Giocatore> lista_giocatore = new LinkedList<>();
		try
		{
			PreparedStatement statement;
			String query = "select * from giocatore";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				Giocatore giocatore = new Giocatore(result.getString("nome"), result.getString("squadra_provenienza"),result.getString("campionato_provenienza"),result.getString("ruolo"),result.getInt("prezzo"));

				lista_giocatore.add(giocatore);
			}
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
		return lista_giocatore;
	}

	@Override
	public void update(Giocatore giocatore) {
		Connection connection = this.dataSource.getConnection();
		try
		{
			String update = "update giocatore SET squadra_provenienza = ?, campionato_provenienza = ?, ruolo = ?, prezzo = ? WHERE nome = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, giocatore.getSquadraProvenienza());
			statement.setString(2, giocatore.getCampionatoProvenienza());
			statement.setString(3, giocatore.getRuolo());
			statement.setInt(4, giocatore.getPrezzo());
			statement.setString(5, giocatore.getNome());
			statement.executeUpdate();
		} catch (SQLException e)
		{
			if (connection != null)
			{
				try
				{
					connection.rollback();
				} catch (SQLException excep)
				{
					throw new PersistenceException(e.getMessage());
				}
			}
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

	@Override
	public void delete(Giocatore giocatore) {
		Connection connection = this.dataSource.getConnection();
		try
		{
			String delete = "delete FROM giocatore WHERE nome = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, giocatore.getNome());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();
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
