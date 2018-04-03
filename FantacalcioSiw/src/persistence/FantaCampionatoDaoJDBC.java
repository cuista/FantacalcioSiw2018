package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.FantaCampionato;
import persistence.dao.FantaCampionatoDao;

public class FantaCampionatoDaoJDBC implements FantaCampionatoDao
{

	private DataSource dataSource;

	public FantaCampionatoDaoJDBC(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@Override
	public void save(FantaCampionato fantaCampionato)
	{
		if (fantaCampionato.getTotalePartecipanti() < 2)
		{
			throw new PersistenceException(
					"FantaCampionato non memorizzato: un campionato deve avere almeno due partecipanti");
		}
		Connection connection = this.dataSource.getConnection();
		try
		{
			Long id = IdBroker.getId(connection);
			fantaCampionato.setId(id);
			String insert = "insert into fantacampionato(id, nome, totale_partecipanti, stato) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, fantaCampionato.getId());
			statement.setString(2, fantaCampionato.getNome());
			statement.setInt(3, fantaCampionato.getTotalePartecipanti());
			statement.setInt(4, fantaCampionato.getStato());

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
	public FantaCampionato findByPrimaryKey(Long idFantaCampionato)
	{
		Connection connection = this.dataSource.getConnection();
		FantaCampionato fantaCampionato = null;
		try
		{
			PreparedStatement statement;
			String query = "select * from fantacampionato where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, idFantaCampionato);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				fantaCampionato = new FantaCampionato(result.getString("nome"), result.getInt("totale_partecipanti"));
				fantaCampionato.setId(idFantaCampionato);
				fantaCampionato.setStato(1);
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
		return fantaCampionato;
	}

	@Override
	public List<FantaCampionato> findAll()
	{
		Connection connection = this.dataSource.getConnection();
		List<FantaCampionato> lista_fantaCampionati = new LinkedList<>();
		try
		{
			PreparedStatement statement;
			String query = "select * from fantacampionato";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				FantaCampionato fantaCampionato = new FantaCampionato(result.getString("nome"),
						result.getInt("totale_partecipanti"));
				fantaCampionato.setId(result.getLong("id"));
				fantaCampionato.setStato(result.getInt("stato"));

				lista_fantaCampionati.add(fantaCampionato);
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
		return lista_fantaCampionati;
	}

	@Override
	public void update(FantaCampionato fantaCampionato)
	{
		Connection connection = this.dataSource.getConnection();
		try
		{
			String update = "update fantacampionato SET nome = ?, totale_partecipanti = ?, stato = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, fantaCampionato.getNome());
			statement.setInt(2, fantaCampionato.getTotalePartecipanti());
			statement.setInt(3, fantaCampionato.getStato());
			statement.setLong(4, fantaCampionato.getId());
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
	public void delete(FantaCampionato fantaCampionato)
	{
		Connection connection = this.dataSource.getConnection();
		try
		{
			String delete = "delete FROM fantacampionato WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, fantaCampionato.getId());
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
