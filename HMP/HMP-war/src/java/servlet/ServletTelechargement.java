package servlet;

import Enum.SFTPConnexion;
import com.jcraft.jsch.JSchException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletTelechargement", urlPatterns = {"/ServletTelechargement"})
public class ServletTelechargement extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=attachedFile.zip");

        SFTPConnexion con = new SFTPConnexion();

        String act = request.getParameter("action");
        if (act != null && !act.isEmpty()) {

            if (act.equals("telechargerPJDemandeCreation")) {
                String idDemande = request.getParameter("idDemande");
                String rep = "/home/hardis/hmp/demandeEntreprise/creation/" + idDemande;
                List<String> listeFichiers = con.listeFichiersRepertoire(rep);
                if (listeFichiers != null && !listeFichiers.isEmpty()) {
                    List<File> files = new ArrayList();
                    for (String file : listeFichiers) {
                        if (!file.equals(".") && !file.equals("..")) {
                            try {
                                con.downloadFile(rep + "/" + file, "temp\\" + file);
                                files.add(new File("temp\\" + file));
                            } catch (Exception e) {
                                continue;
                            }
                        }

                    }

                    ServletOutputStream out = response.getOutputStream();
                    ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));

                    for (File file : files) {

                        System.out.println("Adding " + file.getAbsolutePath());
                        zos.putNextEntry(new ZipEntry(file.getName()));
                        FileInputStream fis = null;
                        try {
                            fis = new FileInputStream(file);
                        } catch (Exception fnfe) {
                            continue;
                        }
                        BufferedInputStream fif = new BufferedInputStream(fis);
                        int data = 0;
                        while ((data = fif.read()) != -1) {
                            zos.write(data);
                        }
                        fif.close();

                        zos.closeEntry();
                        System.out.println("Finishedng file " + file.getName());
                    }
                    
                    zos.close();
                    out.flush();

                }

            }

        }

        /*
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + filename);
         */
        //OutputStream out = response.getOutputStream();
/*
        try {
            con.downloadFile("/home/hardis/hmp/demandeEntreprise/creation/1752/Logo_Hardis_Group.png", out);
        } catch (JSchException ex) {
            Logger.getLogger(ServletTelechargement.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        //out.flush();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
