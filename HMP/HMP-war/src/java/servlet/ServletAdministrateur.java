package servlet;

import SessionUtilisateur.SessionAdministrateurLocal;
import SessionUtilisateur.SessionClientLocal;
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

@WebServlet(name = "ServletAdministrateur", urlPatterns = {"/ServletAdministrateur"})
public class ServletAdministrateur extends HttpServlet {

    @EJB
    private SessionAdministrateurLocal sessionAdministrateur;

    private final String ATT_SESSION_ADMINISTRATEUR = "sessionAdministrateur";

    private String jspClient = "/admin/indexAdmin.jsp";

    protected void menuCatalogue(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("listOffres", sessionAdministrateur.afficherOffres());
        jspClient = "/admin/offres.jsp";
    }

    protected void menuEntreprise(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("listeEntreprises", sessionAdministrateur.rechercheEntreprises());
        request.setAttribute("listeAgences", sessionAdministrateur.afficherAgences());
        jspClient = "/admin/entreprises.jsp";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sessionHttp = request.getSession();

        if (sessionHttp.getAttribute(ATT_SESSION_ADMINISTRATEUR) != null) {

            if (request.getParameter("action") != null) {

                String act = request.getParameter("action");

                if (act.equals("creerUtilisateurHardis")) {
                    String prenom = request.getParameter("prenom").trim();
                    String nom = request.getParameter("nom").trim();
                    String mail = request.getParameter("email").trim().toLowerCase();
                    String tel = request.getParameter("tel").trim();
                    String mdp = request.getParameter("pw");
                    String mdpV = request.getParameter("pwV");
                    String plafond = request.getParameter("plafond").trim();
                    String profilTechnique = request.getParameter("profilTechnique").trim();
                    String profilMetier = request.getParameter("profilMetier").trim();
                    //String agence =
                   // String offre = re
                   // if (prenom!=null && nom!=null && mail!=null && tel!=null && mdp!=null && mdpV!=null && plafond!=null && profilTechnique!=null && profilMetier!=null && !nom.isEmpty() && !mail.isEmpty() && !tel.isEmpty() && !mdp.isEmpty() && !mdpV.isEmpty()) {
                      //  if(sessionAdministrateur.creer)
                    //}
                }
                if (act.equals("creerAgence")) {
                    String localisation = request.getParameter("localisation").trim().toUpperCase();
                    String adresse =  request.getParameter("adresse").trim();
                    if (localisation != null && adresse != null && !localisation.isEmpty() && !adresse.isEmpty()) {
                        sessionAdministrateur.creerAgence(localisation, adresse);

                    } else {
                        request.setAttribute("MsgError", "Une erreur s'est produite");
                    }
                    request.setAttribute("listAgences", sessionAdministrateur.afficherAgences());
                    jspClient = "/admin/agences.jsp";
                }

                if (act.equals("creerEntreprise")) {
                    String siret = ((String) request.getParameter("siret")).trim().toUpperCase();
                    String nom = ((String) request.getParameter("nom")).trim();
                    String adresse = ((String) request.getParameter("adresse")).trim();
                    String agence = ((String) request.getParameter("agence")).trim();
                    if (siret != null && nom != null && adresse != null && agence != null && !siret.isEmpty() && !nom.isEmpty() && !adresse.isEmpty() && !agence.isEmpty()) {
                        sessionAdministrateur.creerEntreprise(siret, nom, adresse, Long.parseLong(agence));
                        menuEntreprise(request, response);
                    } else {
                        request.setAttribute("MsgError", "Une erreur s'est produite");
                        menuEntreprise(request, response);
                    }
                }
                if (act.equals("creerOffre")) {
                    String libelle = ((String) request.getParameter("libelle")).trim().toUpperCase();
                    if (libelle != null && !libelle.isEmpty()) {
                        sessionAdministrateur.creerOffre(libelle);

                    } else {
                        request.setAttribute("MsgError", "Une erreur s'est produite");
                    }
                    request.setAttribute("listOffres", sessionAdministrateur.afficherOffres());
                    jspClient = "/admin/offres.jsp";
                }
                if (act.equals("offres")) {
                    menuCatalogue(request, response);
                }

                if (act.equals("utilisateursHardis")) {
                    if (request.getParameter("recherche") != null) {
                        //Recherche
                    } else {
                        request.setAttribute("listeUtilisateursHardis", sessionAdministrateur.rechercheUtilisateursHardis());
                        jspClient = "/admin/hardisUsers.jsp";
                    }
                }
                if (act.equals("clients")) {
                    if (request.getParameter("recherche") != null) {
                        //Recherche
                    } else {
                        request.setAttribute("listeClients", sessionAdministrateur.listeClients());
                        jspClient = "/admin/clients.jsp";
                    }
                }

                if (act.equals("agences")) {
                    request.setAttribute("listAgences", sessionAdministrateur.afficherAgences());
                    jspClient = "/admin/agences.jsp";
                }

                if (act.equals("entreprises")) {
                    menuEntreprise(request, response);
                }
            }
        }

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
