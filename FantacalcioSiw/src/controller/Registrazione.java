package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.DAOFactory;
import persistence.DatabaseManager;
import persistence.UtilDao;
import persistence.dao.UtenteDao;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= request.getParameter("username");
		String mail= request.getParameter("email");
		String password= request.getParameter("password");
		String confirmedPassword= request.getParameter("confirmPassword");
		
		//DAO
		UtenteDao utenteDao= DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		
		//ERRORI
		if(existsUsername(utenteDao, username)||existsMail(utenteDao, mail)||!(password.equals(confirmedPassword)))
		{
			RequestDispatcher requestDispatcher= getServletContext().getRequestDispatcher("/fallimentoRegistrazione.html");
			requestDispatcher.forward(request, response);
		}
		//SUCCESSO
		else
		{
			utenteDao.save(new Utente(username, mail, password));
			RequestDispatcher requestDispatcher= getServletContext().getRequestDispatcher("/successoRegistrazione.html");
			requestDispatcher.forward(request, response);
		}
		
		
	}
	
	private boolean existsUsername(UtenteDao utenteDao, String username)
	{
		for (Utente utente : utenteDao.findAll())
		{
			if(username.equals(utente.getUsername()))
					return true;
		}
		return false;
	}
	
	private boolean existsMail(UtenteDao utenteDao, String mail)
	{
		for (Utente utente : utenteDao.findAll())
		{
			if(mail.equals(utente.getMail()))
				return true;
		}
		return false;
	}

}
