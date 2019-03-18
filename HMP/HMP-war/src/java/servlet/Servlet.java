package servlet;

import Enum.ProfilTechnique;
import GestionUtilisateur.Client;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import SessionUtilisateur.SessionLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    @EJB
    private SessionLocal sessionMain;

    private final String ATT_SESSION_CLIENT = "sessionClient";

    private final String ATT_SESSION_HARDIS = "sessionHardis";

    private final String ATT_SESSION_ADMINISTRATEUR = "sessionAdministrateur";

    private String jspClient = "/home.jsp";
    

    private void login(String login, String mdp, HttpSession sessionHttp, HttpServletRequest request) {
        Utilisateur utilisateur = sessionMain.authentification(login, mdp);

        if (utilisateur != null) {
            if (sessionMain.getTypeUser(utilisateur).equalsIgnoreCase("Client")) {//verif type utilisateur
                Client c = sessionMain.rechercheClient(utilisateur.getId());// recherche Client
                sessionHttp.setAttribute(ATT_SESSION_CLIENT, c);//Attribuer le Token
                jspClient = "/client/index.jsp";
            } else {
                jspClient = "/utilisateurHardis/index.jsp";
                UtilisateurHardis uh = sessionMain.rechercheUtilisateurHardis(utilisateur.getId());// Chercher l'utilisateur Hardis
                sessionHttp.setAttribute(ATT_SESSION_HARDIS, uh);//Attribuer le Token
                ProfilTechnique pt = uh.getProfilTechnique();// Profil technique
                if (pt.equals(ProfilTechnique.Administrateur)) {// Verif profil technique
                    sessionHttp.setAttribute(ATT_SESSION_ADMINISTRATEUR, uh);//Attribuer le Token
                }
            }
        } else {
            jspClient = "/login.jsp";
            request.setAttribute("msgError", "Utilisateur inexistant ou mot de passe erroné");
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sessionHttp = request.getSession();
        if (request.getParameter("action") != null) {
            String act = request.getParameter("action");
            /*CREATION CLIENT*/
            if (act.equals("creerClient")) {
                String prenom = request.getParameter("prenom").trim();
                String nom = request.getParameter("nom").trim();
                String mail = request.getParameter("email").trim().toLowerCase();
                String tel = request.getParameter("tel").trim();
                String mdp = request.getParameter("pw");
                if (!prenom.isEmpty() && !nom.isEmpty() && !mail.isEmpty() && !tel.isEmpty() && !mdp.isEmpty()) {
                    Utilisateur u = sessionMain.rechercherUtilisateurExistant(mail);
                    if (u == null) {
                        sessionMain.creerClient(nom, prenom, mail, mdp, tel);
                        login(mail, mdp, sessionHttp, request);
                    } else {
                        jspClient = "/signup.jsp";
                        request.setAttribute("MsgError", "Cette adresse mail est déjà utilisée");
                    }
                } else {
                    jspClient = "/signup.jsp";
                    request.setAttribute("MsgError", "Veuillez saisir tous les champs nécessaires");
                }
            }
            /*FIN CREATION CLIENT*/
 /*AUTHENTIFICATION*/
            if (act.equals("login")) {
                String login = request.getParameter("email").trim();
                String mdp = request.getParameter("pw");
                login(login, mdp, sessionHttp, request);
            }
            /*FIN AUTHENTIFICATION*/
 /*Control Deconnexion*/
            if (act.equals("logout")) {
                sessionHttp.setAttribute(ATT_SESSION_CLIENT, null); //Enlever le Token
                sessionHttp.setAttribute(ATT_SESSION_HARDIS, null); //Enlever le Token
                jspClient = "/home.jsp";
            }
            /*Fin Deconnexion*/

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
