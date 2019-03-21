package servlet;

import GestionCatalogue.Offre;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import SessionUtilisateur.SessionHardisLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletUtilisateurHardis", urlPatterns = {"/ServletUtilisateurHardis"})
public class ServletUtilisateurHardis extends HttpServlet {

    @EJB
    private SessionHardisLocal sessionHardis;

    private final String ATT_SESSION_HARDIS = "sessionHardis";

    private String jspClient = "/hardisUser/index.jsp";

    private UtilisateurHardis uh;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessionHttp = request.getSession();

        if (sessionHttp.getAttribute(ATT_SESSION_HARDIS) != null) {
            uh = (UtilisateurHardis) sessionHttp.getAttribute(ATT_SESSION_HARDIS);
            if (request.getParameter("action") != null && !request.getParameter("action").isEmpty()) {
                String act = request.getParameter("action");

                if (act.equals("monProfil")) {
                    jspClient = "/hardisUser/monProfil.jsp";
                }

                //MODIFIER LE MOT DE PASSE DE LUTILISATEUR HARDIS
                if (act.equals("modifierMDPClient")) {
                    if (request.getParameter("ancienMDP") != null && request.getParameter("nouveauMDP") != null && !request.getParameter("ancienMDP").isEmpty() && !request.getParameter("nouveauMDP").isEmpty()) {
                        String nouveau = request.getParameter("nouveauMDP");
                        String ancien = request.getParameter("ancienMDP");
                        Utilisateur uti = sessionHardis.modifierUtilisateurMDP(uh.getId(), ancien, nouveau);
                        if (uti != null) {
                            request.setAttribute("msgSuccess", "Le mot de passe a bien été modifié");
                            jspClient = "/hardisUser/monProfil.jsp";
                        } else {
                            request.setAttribute("msgError", "Le mot de passe n'a pas été modifié, l'ancien mot de passe est incorrect");
                            jspClient = "/hardisUser/monProfil.jsp";
                        }

                    }
                }
                
                /*CATALOGUE*/
                 if (act.equals("offres")) {
                    request.setAttribute("listOffres", sessionHardis.afficherOffres());
                    jspClient = "/hardisUser/offres.jsp";
                }
                if (act.equals("services")) {
                    Long id = Long.parseLong(request.getParameter("id").trim());
                    Offre offre = sessionHardis.afficheOffre(id);
                    request.setAttribute("offre", offre);
                    request.setAttribute("listeServicesStandards", sessionHardis.afficherServicesStandards(id));
                    request.setAttribute("listeServicesNonStandards", sessionHardis.afficherServicesNonStandards(id));
                    jspClient = "/hardisUser/services.jsp";
                }

            }
        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd = getServletContext().getRequestDispatcher(jspClient);
        rd.forward(request, response);

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
