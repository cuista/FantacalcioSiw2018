package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.FantaPartita;
import persistence.dao.FantaPartitaDao;

public class FantaPartitaDaoJDBC implements FantaPartitaDao {
	
	private DataSource dataSource;
	
	public FantaPartitaDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}

	@Override
	public void save(FantaPartita fantaPartita) {
		if(fantaPartita.getSquadraInCasa()==null||fantaPartita.getSquadraOspite()==null)
		{
			throw new PersistenceException(
					"FantaPartita non memorizzata: una partita deve avere due squadre");
		}
		if(fantaPartita.getSquadraInCasa()==fantaPartita.getSquadraOspite())
		{
			throw new PersistenceException(
					"FantaPartita non memorizzata: una partita non può essere giocata da 2 squadre uguali");
		}
		Connection connection = this.dataSource.getConnection();
		try
		{
			Long id = IdBroker.getId(connection);
			fantaPartita.setId(id);
			String insert = "insert into fantapartita(id, squadra_in_casa, squadra_ospite, risultato, giornata) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, fantaPartita.getId());
			statement.setLong(2, fantaPartita.getSquadraInCasa());
			statement.setLong(3, fantaPartita.getSquadraOspite());
			statement.setInt(4, fantaPartita.getRisultato());
			statement.setInt(4, fantaPartita.getGiornata());

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
	public FantaPartita findByPrimaryKey(Long id) {
		Connection connection = this.dataSource.getConnection();
		FantaPartita fantaPartita = null;
		try
		{
			PreparedStatement statement;
			String query = "select * from fantapartita where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				fantaPartita = new FantaPartita(result.getLong("squadra_in_casa"), result.getLong("squadra_in_casa"), result.getInt("giornata"));
				fantaPartita.setId(id);
				fantaPartita.setRisultato(-1);
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
		return fantaPartita;
	}

	@Override
	public List<FantaPartita> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<FantaPartita> lista_fantaPartite = new LinkedList<>();
		try
		{
			PreparedStatement statement;
			String query = "select * from fantapartita";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				FantaPartita fantaPartita = new FantaPartita(result.getLong("squadra_in_casa"),result.getLong("squadra_ospite"),result.getInt("giornata"));
				fantaPartita.setId(result.getLong("id"));
				fantaPartita.setRisultato(result.getInt("risultato"));

				lista_fantaPartite.add(fantaPartita);
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
		return lista_fantaPartite;
	}

	@Override
	public void update(FantaPartita fantaPartita) {
		Connection connection = this.dataSource.getConnection();
		try
		{
			String update = "update fantapartita SET squadra_in_casa = ?, squadra_ospite = ?, risultato = ?, giornata = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setLong(1, fantaPartita.getSquadraInCasa());
			statement.setLong(2, fantaPartita.getSquadraOspite());
			statement.setInt(3, fantaPartita.getRisultato());
			statement.setInt(4, fantaPartita.getGiornata());			
			statement.setLong(5, fantaPartita.getId());
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
	public void delete(FantaPartita fantaPartita) {
		Connection connection = this.dataSource.getConnection();
		try
		{
			String delete = "delete FROM fantapartita WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, fantaPartita.getId());
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
