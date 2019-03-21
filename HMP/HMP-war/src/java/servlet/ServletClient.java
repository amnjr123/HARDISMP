package servlet;

import GestionUtilisateur.Client;
import GestionUtilisateur.Utilisateur;
import SessionUtilisateur.SessionClientLocal;
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

@WebServlet(name = "ServletClient", urlPatterns = {"/ServletClient"})
public class ServletClient extends HttpServlet {

    @EJB
    private SessionClientLocal sessionClient;

    private final String ATT_SESSION_CLIENT = "sessionClient";

    private String jspClient = "/client/index.jsp";

    private Client c;

    protected void monProfil(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("listeAgences", sessionClient.rechercherAgence());
        jspClient = "/client/monProfil.jsp";

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessionHttp = request.getSession();

        if (sessionHttp.getAttribute("sessionClient") != null) {
            c = (Client) sessionHttp.getAttribute("sessionClient");
            if (request.getParameter("action") != null) {
                String act = request.getParameter("action");

                //Consulter le profil
                if (act.equals("monProfil")) {
                    monProfil(request, response);
                }
                System.out.print(act);
                //demande de création ou rattachaement à une entreprise
                if (act.equals("creerDemandeEntreprise")) {
                    if (request.getParameter("siret") != null) {
                        Long id = c.getId();
                        String siret = ((String) request.getParameter("siret")).trim().toUpperCase();
                        String nom = request.getParameter("nom");
                        String adresse = request.getParameter("adresse");
                        Long idAgence = null;
                        String agence = request.getParameter("agence");
                        if (agence != null && !agence.isEmpty()) {
                            idAgence = Long.parseLong(agence);
                        }

                        System.out.print(id);
                        if (siret != null && !siret.isEmpty()) {
                            sessionClient.DemandeCreationOuRattachement(id, nom, siret, adresse, idAgence);
                            request.getSession().setAttribute(ATT_SESSION_CLIENT, sessionClient.rechercheClient(id));
                            request.setAttribute("msgSuccess", "La demande a bien été effectuée");
                            monProfil(request, response);
                        } else {
                            request.setAttribute("msgError", "Une erreur s'est produite");
                            monProfil(request, response);
                        }
                    } else {
                        request.setAttribute("msgError", "le SIRET est Obligatoire");
                        monProfil(request, response);
                    }

                }

                //MODIFIER LE PRENOM DU CLIENT
                if (act.equals("modifierPrenomClient")) {
                    if (request.getParameter("nouveauPrenom") != null && !request.getParameter("nouveauPrenom").isEmpty()) {
                        String prenom = request.getParameter("nouveauPrenom");
                        Client cli = sessionClient.modifierClient(c.getId(), c.getNom(), prenom, c.getMail(), c.getTelephone());
                        if (cli != null) {
                            request.getSession().setAttribute(ATT_SESSION_CLIENT, cli);//Attribuer le Token
                            request.setAttribute("msgSuccess", "Le prénom a bien été modifié");
                            monProfil(request, response);
                        } else {
                            request.setAttribute("msgError", "Le prénom n'a pas été modifié");
                            monProfil(request, response);
                        }
                    }
                }
                //MODIFIER LE NOM DU CLIENT
                if (act.equals("modifierNomClient")) {
                    if (request.getParameter("nouveauNom") != null && !request.getParameter("nouveauNom").isEmpty()) {
                        String nom = request.getParameter("nouveauNom");
                        Client cli = sessionClient.modifierClient(c.getId(), nom, c.getPrenom(), c.getMail(), c.getTelephone());
                        if (cli != null) {
                            request.getSession().setAttribute(ATT_SESSION_CLIENT, cli);//Attribuer le Token
                            request.setAttribute("msgSuccess", "Le nom a bien été modifié");
                            monProfil(request, response);
                        } else {
                            request.setAttribute("msgError", "Le nom n'a pas été modifié");
                            monProfil(request, response);
                        }
                    }
                }
                //MODIFIER LE TELEPHONE DU CLIENT
                if (act.equals("modifierTelephoneClient")) {
                    if (request.getParameter("nouveauTelephone") != null && !request.getParameter("nouveauTelephone").isEmpty()) {
                        String tel = request.getParameter("nouveauTelephone");
                        Client cli = sessionClient.modifierClient(c.getId(), c.getNom(), c.getPrenom(), c.getMail(), tel);
                        if (cli != null) {
                            request.getSession().setAttribute(ATT_SESSION_CLIENT, cli);//Attribuer le Token
                            request.setAttribute("msgSuccess", "Le téléphone a bien été modifié");
                            monProfil(request, response);
                        } else {
                            request.setAttribute("msgError", "Le téléphone n'a pas été modifié");
                            monProfil(request, response);
                        }

                    }
                }
                //MODIFIER LE MOT DE PASSE DU CLIENT
                if (act.equals("modifierMDPClient")) {
                    if (request.getParameter("ancienMDP") != null && request.getParameter("nouveauMDP") != null && !request.getParameter("ancienMDP").isEmpty() && !request.getParameter("nouveauMDP").isEmpty()) {
                        String nouveau = request.getParameter("nouveauMDP");
                        String ancien = request.getParameter("ancienMDP");
                        Utilisateur uti = sessionClient.modifierClientMDP(c.getId(), ancien, nouveau);
                        if (uti != null) {
                            Client cli = sessionClient.rechercheClient(uti.getId());
                            request.getSession().setAttribute(ATT_SESSION_CLIENT, cli);//Attribuer le Token
                            request.setAttribute("msgSuccess", "Le mot de passe a bien été modifié");
                            monProfil(request, response);
                        } else {
                            request.setAttribute("msgError", "Le mot de passe n'a pas été modifié, l'ancien mot de passe est incorrect");
                            monProfil(request, response);
                        }

                    }
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
