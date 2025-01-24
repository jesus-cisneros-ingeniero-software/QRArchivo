package cisneros.servlets;

import cisneros.qrarchivo.Registro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/guardar")
public class GuardarServlet extends HttpServlet {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strTribunal = request.getParameter("strTribunal");
        int antecedente = Integer.parseInt(request.getParameter("antecedente"));
        int numExpediente = Integer.parseInt(request.getParameter("numExpediente"));
        int year = Integer.parseInt(request.getParameter("year"));
        String nombreUsuaria = request.getParameter("nombreUsuaria");
        boolean estatusM = request.getParameter("estatusM") != null;
        Date fechaPrestamo = Date.valueOf(request.getParameter("fechaPrestamo"));
        Date fechaDevolucion = Date.valueOf(request.getParameter("fechaDevolucion"));
        Time hora = Time.valueOf(request.getParameter("hora") + ":00");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Registro registro = new Registro();
        registro.setStrTribunal(strTribunal);
        registro.setAntecedente(antecedente);
        registro.setNumExpediente(numExpediente);
        registro.setYear(year);
        registro.setNombreUsuaria(nombreUsuaria);
        registro.setEstatusM(estatusM);
        registro.setFechaPrestamo(fechaPrestamo);
        registro.setFechaDevolucion(fechaDevolucion);
        em.persist(registro);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect("portada?strTribunal=" + strTribunal + "&antecedente=" + antecedente + "&numExpediente=" + numExpediente + "&year=" + year + "&nombreUsuaria=" + nombreUsuaria + "&estatusM=" + estatusM + "&fechaPrestamo=" + fechaPrestamo + "&fechaDevolucion=" + fechaDevolucion + "&hora=" + hora);
    }
}