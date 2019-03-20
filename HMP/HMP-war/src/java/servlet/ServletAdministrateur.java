package servlet;

import GestionCatalogue.Offre;
import GestionCatalogue.ServiceNonStandard;
import GestionCatalogue.ServiceStandard;
import GestionUtilisateur.ReferentLocal;
import SessionUtilisateur.SessionAdministrateurLocal;
import SessionUtilisateur.SessionClientLocal;
import SessionUtilisateur.SessionHardisLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private SessionClientLocal sessionClient;

    @EJB
    private SessionAdministrateurLocal sessionAdministrateur;

    private final String ATT_SESSION_ADMINISTRATEUR = "sessionAdministrateur";

    private String jspClient = "/admin/indexAdmin.jsp";

    protected void menuClient(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("recherche") != null && !request.getParameter("recherche").isEmpty()) {
            //request.setAttribute("listeClients", );
        } else {
            request.setAttribute("listeClients", sessionAdministrateur.listeClients());
        }
        jspClient = "/admin/clients.jsp";
        
    }

    protected void menuUtilisateurHardis(HttpServletRequest request, HttpServletResponse response) {
        
        request.setAttribute("listeUtilisateursHardis", sessionAdministrateur.rechercheUtilisateursHardis());
        request.setAttribute("listeAgences", sessionAdministrateur.afficherAgences());
        request.setAttribute("listeOffres", sessionAdministrateur.afficherOffres());
        jspClient = "/admin/hardisUsers.jsp";
    }

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

                if (act.equals("creerPO")) {
                    String prenom = request.getParameter("prenom").trim();
                    String nom = request.getParameter("nom").trim();
                    String mail = request.getParameter("email").trim().toLowerCase();
                    String tel = request.getParameter("tel").trim();
                    String profilTechnique = request.getParameter("profilTechnique");
                    String agence = request.getParameter("agence");
                    String offre = request.getParameter("offre");
                    if (prenom != null && nom != null && mail != null && tel != null && profilTechnique != null && !nom.isEmpty() && !mail.isEmpty() && !tel.isEmpty() && !agence.isEmpty() && !offre.isEmpty()) {
                        //  if(sessionAdministrateur.creer)
                        //}
                    }
                }

                if (act.equals("creerRL")) {
                    String prenom = request.getParameter("prenom").trim();
                    String nom = request.getParameter("nom").trim();
                    String mail = request.getParameter("email").trim().toLowerCase();
                    String tel = request.getParameter("tel").trim();
                    String profilTechnique = request.getParameter("profilTechnique");
                    String agence = request.getParameter("agence");
                    String offre = request.getParameter("offre");
                    String plafond = request.getParameter("plafond").trim();//>0 fl front dyali

                    if (prenom != null && nom != null && mail != null && tel != null && plafond != null && profilTechnique != null && agence != null && offre != null && !nom.isEmpty() && !mail.isEmpty() && !tel.isEmpty() && !agence.isEmpty() && !offre.isEmpty()) {
                        sessionAdministrateur.creerReferentLocal(nom, prenom, mail, tel, profilTechnique, Float.parseFloat(plafond), Long.parseLong(offre), Long.parseLong(agence));
                        request.setAttribute("msgSuccess", "Le Référent local  a bien été crée");

                    } else {
                        request.setAttribute("msgError", "Une erreur s'est produite, l'un des champs est vide");
                    }
                    menuUtilisateurHardis(request, response);
                }

                if (act.equals("creerConsultant")) {//plafond >= 0
                    String prenom = request.getParameter("prenom").trim();
                    String nom = request.getParameter("nom").trim();
                    String mail = request.getParameter("email").trim().toLowerCase();
                    String tel = request.getParameter("tel").trim();
                    String profilTechnique = request.getParameter("profilTechnique");
                    String agence = request.getParameter("agence");
                    String[] offres = request.getParameterValues("offre");
                    String plafond = request.getParameter("plafond").trim();

                    if (prenom != null && nom != null && mail != null && tel != null && plafond != null && profilTechnique != null && !nom.isEmpty() && !mail.isEmpty() && !tel.isEmpty() && !agence.isEmpty() && offres.length != 0) {
                        List<Long> listOffre = (ArrayList) Arrays.asList(offres);

//  if(sessionAdministrateur.creer)
                        //}
                    }
                }

                if (act.equals("creerAgence")) {
                    String localisation = request.getParameter("localisation").trim().toUpperCase();
                    String adresse = request.getParameter("adresse").trim();
                    if (localisation != null && adresse != null && !localisation.isEmpty() && !adresse.isEmpty()) {
                        sessionAdministrateur.creerAgence(localisation, adresse);

                    } else {
                        request.setAttribute("msgError", "Une erreur s'est produite");
                    }
                    request.setAttribute("listAgences", sessionAdministrateur.afficherAgences());
                    jspClient = "/admin/agences.jsp";
                }

                if (act.equals("modifierDonneesClient")) {
                    String id = ((String) request.getParameter("id")).trim();
                    String nom = ((String) request.getParameter("nom")).trim();
                    String prenom = ((String) request.getParameter("prenom")).trim();
                    String telephone = ((String) request.getParameter("tel")).trim();
                    String email = ((String) request.getParameter("email")).trim();
                    /*Changer Session Client à SessionAdmin et ajouter la méthode concérnée*/
                    if (sessionClient.modifierClient(Long.parseLong(id), nom, prenom, email, telephone) != null) {
                        request.setAttribute("listeClients", sessionAdministrateur.listeClients());
                        request.setAttribute("msgSuccess", "Les données du client n° " + id + " ont bien été modifiées");
                        jspClient = "/admin/clients.jsp";
                    } else {
                        request.setAttribute("listeClients", sessionAdministrateur.listeClients());
                        request.setAttribute("msgError", "Erreur lors de la modification des données du client n° " + id);
                        jspClient = "/admin/clients.jsp";
                    }
                }

                if (act.equals("creerEntreprise")) {
                    String siret = request.getParameter("siret").trim().toUpperCase();
                    String nom = request.getParameter("nom").trim();
                    String adresse = request.getParameter("adresse").trim();
                    String agence = request.getParameter("agence");
                    if (siret != null && nom != null && adresse != null && agence != null && !siret.isEmpty() && !nom.isEmpty() && !adresse.isEmpty() && !agence.isEmpty()) {
                        sessionAdministrateur.creerEntreprise(nom, siret, adresse, Long.parseLong(agence));
                        menuEntreprise(request, response);
                    } else {
                        request.setAttribute("msgError", "Une erreur s'est produite");
                        menuEntreprise(request, response);
                    }
                }

                if (act.equals("creerServiceStandard")) {
                    String nom = request.getParameter("nom").trim();
                    String description = request.getParameter("description").trim();
                    String lieu = request.getParameter("lieu").trim();
                    String cout = request.getParameter("cout").trim();
                    //String fraisInclus = request.getParameter("fraisInclus").trim();
                    String conditions = request.getParameter("conditions").trim();
                    String delai = request.getParameter("delai").trim();
                    String joursSenior = request.getParameter("senior").trim();
                    String joursConfirme = request.getParameter("confirme").trim();
                    String joursJunior = request.getParameter("junior").trim();
                    String heuresAtelier = request.getParameter("atelier").trim();
                    String heuresSupportTel = request.getParameter("supporttel").trim();
                    String descriptionDetail = request.getParameter("descriptiondetail").trim();
                    Long idOffre = Long.parseLong(request.getParameter("idOffre").trim());
                    Offre offre = sessionAdministrateur.afficheOffre(idOffre);
                    if (nom != null && description != null && lieu != null && cout != null && /*fraisInclus!=null &&*/ conditions != null && delai != null && joursSenior != null && joursConfirme != null && joursJunior != null && heuresAtelier != null && heuresSupportTel != null && descriptionDetail != null && !nom.equalsIgnoreCase("") && !description.equalsIgnoreCase("") && !lieu.equalsIgnoreCase("") && !cout.equalsIgnoreCase("") && !conditions.equalsIgnoreCase("") && !delai.equalsIgnoreCase("") && !joursSenior.equalsIgnoreCase("") && !joursConfirme.equalsIgnoreCase("") && !joursJunior.equalsIgnoreCase("") && !heuresAtelier.equalsIgnoreCase("") && !heuresSupportTel.equalsIgnoreCase("") && !descriptionDetail.equalsIgnoreCase("")) {
                        float coutFloat = Float.parseFloat(cout);
                        int delaiInt = Integer.parseInt(delai);
                        int joursSeniorInt = Integer.parseInt(joursSenior);
                        int joursConfirmeInt = Integer.parseInt(joursConfirme);
                        int joursJuniorInt = Integer.parseInt(joursJunior);
                        int heuresAtelierInt = Integer.parseInt(heuresAtelier);
                        int heuresSupportTelInt = Integer.parseInt(heuresSupportTel);
                        boolean fraisInclusBool = true;
                        ServiceStandard st = sessionAdministrateur.creerServiceStandard(nom, description, lieu, coutFloat, fraisInclusBool, conditions, delaiInt, idOffre, joursSeniorInt, joursConfirmeInt, joursJuniorInt, heuresAtelierInt, heuresSupportTelInt, descriptionDetail);
                        if (st == null) {
                            request.setAttribute("msgError", "Une erreur s'est produite");
                        }
                    } else {
                        request.setAttribute("msgError", "Une erreur s'est produite");
                    }
                    request.setAttribute("offre", offre);
                    request.setAttribute("listeServicesStandards", sessionAdministrateur.afficherServicesStandards(idOffre));
                    request.setAttribute("listeServicesNonStandards", sessionAdministrateur.afficherServicesNonStandards(idOffre));
                    jspClient = "/admin/services.jsp";
                }

                
                if (act.equals("creerServiceNonStandard")) {
                    String nom = request.getParameter("nom").trim();
                    String description = request.getParameter("description").trim();
                    String lieu = request.getParameter("lieu").trim();
                    String cout = request.getParameter("cout").trim();
                    //String fraisInclus = request.getParameter("fraisInclus").trim();
                    String conditions = request.getParameter("conditions").trim();
                    String delai = request.getParameter("delai").trim();
                    Long idOffre = Long.parseLong(request.getParameter("idOffre").trim());
                    Offre offre = sessionAdministrateur.afficheOffre(idOffre);
                    if(nom!=null && description!=null && lieu!=null && cout!=null && /*fraisInclus!=null &&*/ conditions!=null && delai!=null && !nom.equalsIgnoreCase("") && !description.equalsIgnoreCase("") && !lieu.equalsIgnoreCase("") && !cout.equalsIgnoreCase("") && !conditions.equalsIgnoreCase("") && !delai.equalsIgnoreCase("")){
                        float coutFloat = Float.parseFloat(cout);
                        int delaiInt = Integer.parseInt(delai);
                        boolean fraisInclusBool = true;
                        ServiceNonStandard snt = sessionAdministrateur.creerServiceNonStandard(nom, description, lieu, coutFloat, fraisInclusBool, conditions, delaiInt, idOffre);
                        if(snt==null){
                            request.setAttribute("MsgError", "Une erreur s'est produite");
                        }
                    }
                    else{
                        request.setAttribute("MsgError", "Une erreur s'est produite");
                    }
                    request.setAttribute("offre", offre);
                    request.setAttribute("listeServicesStandards", sessionAdministrateur.afficherServicesStandards(idOffre));
                    request.setAttribute("listeServicesNonStandards", sessionAdministrateur.afficherServicesNonStandards(idOffre));
                    jspClient = "/admin/services.jsp";
                }
                
                if (act.equals("creerOffre")) {
                    String libelle = ((String) request.getParameter("libelle")).trim().toUpperCase();
                    if (libelle != null && !libelle.isEmpty()) {
                        sessionAdministrateur.creerOffre(libelle);
                    } else {
                        request.setAttribute("msgError", "Une erreur s'est produite");
                    }
                    request.setAttribute("listOffres", sessionAdministrateur.afficherOffres());
                    jspClient = "/admin/offres.jsp";
                }

                /*Redirections*/
                if (act.equals("offres")) {
                    menuCatalogue(request, response);
                }
                if (act.equals("services")) {
                    Long id = Long.parseLong(request.getParameter("id").trim());
                    Offre offre = sessionAdministrateur.afficheOffre(id);
                    request.setAttribute("offre", offre);
                    request.setAttribute("listeServicesStandards", sessionAdministrateur.afficherServicesStandards(id));
                    request.setAttribute("listeServicesNonStandards", sessionAdministrateur.afficherServicesNonStandards(id));
                    jspClient = "/admin/services.jsp";
                }

                if (act.equals("utilisateursHardis")) {
                    menuUtilisateurHardis(request, response);
                }
                if (act.equals("clients")) {
                    menuClient(request, response);

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
