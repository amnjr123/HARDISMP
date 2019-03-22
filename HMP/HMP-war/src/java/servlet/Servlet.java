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

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("nbrDemandesRattachement", 0);
        request.getSession().setAttribute("nbrDemandesRattachementClientAdmin", 0);
        
        request.getSession().setAttribute(ATT_SESSION_CLIENT, null); //Enlever le Token
        request.getSession().setAttribute(ATT_SESSION_HARDIS, null); //Enlever le Token
        request.getSession().setAttribute(ATT_SESSION_ADMINISTRATEUR, null); //Enlever le Token        
    }

    private void login(String login, String mdp, HttpServletRequest request, HttpServletResponse response) {
        logout(request, response);
        Utilisateur utilisateur = sessionMain.authentification(login, mdp);

        if (utilisateur != null) {
            if (sessionMain.getTypeUser(utilisateur).equalsIgnoreCase("Client")) {//verif type utilisateur
                Client c = sessionMain.rechercheClient(utilisateur.getId());// recherche Client
                request.getSession().setAttribute(ATT_SESSION_CLIENT, c);//Attribuer le Token
                jspClient = "/ServletClient";
            } else {
                jspClient = "/hardisUser/index.jsp";
                UtilisateurHardis uh = sessionMain.rechercheUtilisateurHardis(utilisateur.getId());// Chercher l'utilisateur Hardis
                request.getSession().setAttribute(ATT_SESSION_HARDIS, uh);//Attribuer le Token
                ProfilTechnique pt = uh.getProfilTechnique();// Profil technique
                if (pt.equals(ProfilTechnique.Administrateur)) {// Verif profil technique
                    request.getSession().setAttribute(ATT_SESSION_ADMINISTRATEUR, uh);//Attribuer le Token
                }
            }
        } else {
            jspClient = "/home.jsp";
            request.setAttribute("MsgError", "Utilisateur inexistant ou mot de passe erroné");
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        jspClient = "/home.jsp";
        //sessionMain.test();
        HttpSession sessionHttp = request.getSession();
        if (request.getParameter("action") != null) {
            String act = request.getParameter("action");
            
            /*MOT DE PASSE OUBLIE*/
            
            if(act.equals("motDePasseOublie")){
                if (request.getParameter("mail") != null && !request.getParameter("mail").isEmpty()) {
                    if(sessionMain.motDePasseOublie(request.getParameter("mail"))){
                        request.setAttribute("MsgSuccess", "Le mot de passe de récupération a bien été envoyé");
                    } else {
                        request.setAttribute("MsgError", "L'adresse mail n'existe pas");
                    }
                }
            }
            
            /*CREATION CLIENT*/
            if (act.equals("creerClient")) {
                if (request.getParameter("rgpd") != null && request.getParameter("rgpd").equals("oui")) {
                    String prenom = request.getParameter("prenom").trim();
                    String nom = request.getParameter("nom").trim();
                    String mail = request.getParameter("email").trim().toLowerCase();
                    String tel = request.getParameter("tel").trim();
                    String mdp = request.getParameter("pw");
                    String mdpV = request.getParameter("pwV");
                    if (!prenom.isEmpty() && !nom.isEmpty() && !mail.isEmpty() && !tel.isEmpty() && !mdp.isEmpty() && !mdpV.isEmpty()) {
                        if (mdpV.equals(mdp)) {
                            Utilisateur u = sessionMain.rechercherUtilisateurExistant(mail);
                            if (u == null) {
                                sessionMain.creerClient(nom, prenom, mail, mdp, tel);
                                login(mail, mdp, request, response);
                            } else {
                                jspClient = "/home.jsp";
                                request.setAttribute("MsgError", "Cette adresse mail est déjà utilisée");
                                request.setAttribute("ErrorAdds", true);
                            }
                        } else {
                            jspClient = "/home.jsp";
                            request.setAttribute("MsgError", "Les deux mot de passes ne sont pas identiques");
                        }
                    } else {
                        jspClient = "/home.jsp";
                        request.setAttribute("MsgError", "Veuillez saisir tous les champs nécessaires");
                    }
                } else {
                    jspClient = "/home.jsp";
                    request.setAttribute("MsgError", "Il est obligatoire d'adhérer à notre loi de respect des donnees personnelles pour pouvoir créer un compte");
                }
            }
            /*FIN CREATION CLIENT*/
 /*AUTHENTIFICATION*/
            if (act.equals("login")) {
                String login = request.getParameter("email").trim();
                String mdp = request.getParameter("pw");
                login(login, mdp, request, response);
            }
            /*FIN AUTHENTIFICATION*/
 /*Control Deconnexion*/
            if (act.equals("logout")) {
                logout(request, response);
                jspClient = "/home.jsp";
            }
            /*Fin Deconnexion*/

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
