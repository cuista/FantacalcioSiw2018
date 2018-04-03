package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.FantaSquadra;
import persistence.dao.FantaSquadraDao;

public class FantaSquadraDaoJDBC implements FantaSquadraDao {
	
	private DataSource dataSource;

	public FantaSquadraDaoJDBC(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@Override
	public void save(FantaSquadra fantaSquadra) {
		Connection connection = this.dataSource.getConnection();
		try
		{
			Long id = IdBroker.getId(connection);
			fantaSquadra.setId(id);
			String insert = "insert into fantasquadra(id, nome, fantamilioni) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, fantaSquadra.getId());
			statement.setString(2, fantaSquadra.getNome());
			statement.setInt(3, fantaSquadra.getFantamilioni());

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
	public FantaSquadra findByPrimaryKey(Long id) {
		Connection connection = this.dataSource.getConnection();
		FantaSquadra fantaSquadra = null;
		try
		{
			PreparedStatement statement;
			String query = "select * from fantasquadra where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				fantaSquadra = new FantaSquadra(result.getString("nome"));
				fantaSquadra.setId(id);
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
		return fantaSquadra;
	}

	@Override
	public List<FantaSquadra> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<FantaSquadra> lista_fantaSquadre = new LinkedList<>();
		try
		{
			PreparedStatement statement;
			String query = "select * from fantasquadra";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				FantaSquadra fantaSquadra = new FantaSquadra(result.getString("nome"));
				fantaSquadra.setId(result.getLong("id"));

				lista_fantaSquadre.add(fantaSquadra);
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
		return lista_fantaSquadre;
	}

	@Override
	public void update(FantaSquadra fantaSquadra) {
		Connection connection = this.dataSource.getConnection();
		try
		{
			String update = "update fantasquadra SET nome = ?, fantamilioni = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, fantaSquadra.getNome());
			statement.setInt(2, fantaSquadra.getFantamilioni());
			statement.setLong(3, fantaSquadra.getId());
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
	public void delete(FantaSquadra fantaSquadra) {
		Connection connection = this.dataSource.getConnection();
		try
		{
			String delete = "delete FROM fantasquadra WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, fantaSquadra.getId());
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
