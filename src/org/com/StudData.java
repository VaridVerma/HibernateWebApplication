package org.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Servlet implementation class StudData
 */
@WebServlet("/StudData")
public class StudData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StudData() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	PrintWriter  out = response.getWriter();
	
	Configuration configuration = new Configuration();
	configuration.configure();
	SessionFactory factory = configuration.buildSessionFactory();
	
	Session session = factory.openSession();
	
	Transaction transaction = session.beginTransaction();
	
	String operation = request.getParameter("operation");
	if (operation.equals("Show All")){
		Query qr = session.createQuery("from Student"); //name should be of POJO file , here Student.java
		qr.list();
		List<Student> elist = qr.list();
		
		for(Student student : elist){
			out.print("<br><font color='blue' size='6'>"+student.getRoll()+" "+student.getName()+" "+student.getSubject());
		}
	}
	else if (operation.equals("Add Student")){
		
		Student student = new Student();
		int roll=Integer.parseInt(request.getParameter("roll"));
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		
		student.setRoll(roll);
		student.setName(name);
		student.setSubject(subject);
		
		transaction.begin();
		session.save(student);
		transaction.commit();
		out.print("Record added");
	}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
