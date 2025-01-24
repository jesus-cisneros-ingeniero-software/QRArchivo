package cisneros.qrarchivo;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;

@WebServlet("/portada")
public class PortadaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strTribunal = request.getParameter("strTribunal");
        int antecedente = Integer.parseInt(request.getParameter("antecedente"));
        int numExpediente = Integer.parseInt(request.getParameter("numExpediente"));
        int year = Integer.parseInt(request.getParameter("year"));
        String nombreUsuaria = request.getParameter("nombreUsuaria");
        boolean estatusM = Boolean.parseBoolean(request.getParameter("estatusM"));
        Date fechaPrestamo = Date.valueOf(request.getParameter("fechaPrestamo"));
        Date fechaDevolucion = Date.valueOf(request.getParameter("fechaDevolucion"));
        Time hora = Time.valueOf(request.getParameter("hora"));

        response.setContentType("text/html");
        response.getWriter().println("<h1>Portada</h1>");
        response.getWriter().println("<p>Tribunal: " + strTribunal + "</p>");
        response.getWriter().println("<p>Antecedente: " + antecedente + "</p>");
        response.getWriter().println("<p>Número de Expediente: " + numExpediente + "</p>");
        response.getWriter().println("<p>Año: " + year + "</p>");
        response.getWriter().println("<p>Nombre de la Usuaria: " + nombreUsuaria + "</p>");
        response.getWriter().println("<p>Estatus: " + (estatusM ? "Activo" : "Inactivo") + "</p>");
        response.getWriter().println("<p>Fecha de Préstamo: " + fechaPrestamo + "</p>");
        response.getWriter().println("<p>Fecha de Devolución: " + fechaDevolucion + "</p>");
        response.getWriter().println("<p>Hora: " + hora + "</p>");

        // Generar QR
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            String qrContent = strTribunal + " " + antecedente + " " + numExpediente + " " + year + " " + nombreUsuaria + " " + estatusM + " " + fechaPrestamo + " " + fechaDevolucion + " " + hora;
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 200, 200);
            response.setContentType("image/png");
            OutputStream outputStream = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            outputStream.close();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}