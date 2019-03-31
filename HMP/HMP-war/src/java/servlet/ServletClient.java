package servlet;

import Enum.SFTPConnexion;
import Enum.StatutDevis;
import GestionCatalogue.Offre;
import GestionCatalogue.ServiceNonStandard;
import GestionCatalogue.ServiceStandard;
import GestionDevis.Communication;
import GestionDevis.Conversation;
import GestionDevis.Devis;
import GestionDevis.DevisNonStandard;
import GestionDevis.DevisStandard;
import GestionUtilisateur.Client;
import GestionUtilisateur.Utilisateur;
import SessionUtilisateur.SessionClientLocal;
import com.jcraft.jsch.JSchException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "ServletClient", urlPatterns = {"/ServletClient"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class ServletClient extends HttpServlet {

    @EJB
    private SessionClientLocal sessionClient;

    private final String ATT_SESSION_CLIENT = "sessionClient";

    private String jspClient = "/client/index.jsp";

    private Client c;

    protected void calculNombreDemande(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("nbrDemandesRattachementClientAdmin", sessionClient.rechercherDemandeRattachementEntreprise(c.getEntreprise().getId()).size());
        } catch (Exception e) {
            request.getSession().setAttribute("nbrDemandesRattachementClientAdmin", 0);
        }
    }

    protected void devisEnCours(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("listDevis", sessionClient.rechercherDevisEncours(c.getId()));
        jspClient = "/client/devisEnCours.jsp";

    }

    protected void monProfil(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listeInterlocuteurs", sessionClient.rechercherInterlocuteur(c.getEntreprise().getId()));
        } catch (Exception e) {
            request.setAttribute("listeInterlocuteurs", new ArrayList());
        }

        try {
            request.setAttribute("demandesRattachement", sessionClient.rechercherDemandeRattachementEntreprise(c.getEntreprise().getId()));
        } catch (Exception e) {
            request.setAttribute("demandesRattachement", new ArrayList());
        }
        calculNombreDemande(request, response);
        request.setAttribute("listeAgences", sessionClient.rechercherAgence());
        jspClient = "/client/monProfil.jsp";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessionHttp = request.getSession();
        jspClient = "/client/index.jsp";

        if (sessionHttp.getAttribute("sessionClient") != null) {
            c = (Client) sessionHttp.getAttribute("sessionClient");
            calculNombreDemande(request, response);
            if (request.getParameter("action") != null) {
                String act = request.getParameter("action");

                //Consulter le profil
                if (act.equals("monProfil")) {
                    monProfil(request, response);
                }

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

                        if (siret != null && !siret.isEmpty()) {
                            String path = sessionClient.DemandeCreationOuRattachement(id, nom, siret, adresse, idAgence);

                            SFTPConnexion con = new SFTPConnexion();
                            for (Part part : request.getParts()) {
                                if (part.getName().equals("files[]")) {
                                    try {
                                        con.uploadFile(part.getInputStream(), "/home/hardis/" + path + "/" + part.getSubmittedFileName());
                                    } catch (Exception ex) {
                                        request.setAttribute("msgError", "Les fichiers joints n'ont pas pu être envoyés");
                                    }
                                }
                            }
                            con.disconnect();
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
                //CREER UN INTERLOCUTEUR
                if (act.equals("creerInterlocuteur")) {
                    String nom = request.getParameter("nom");
                    String prenom = request.getParameter("prenom");
                    String email = request.getParameter("email");
                    String telephone = request.getParameter("telephone");
                    String fonction = request.getParameter("fonction");
                    String idEntreprise = request.getParameter("idEntreprise");
                    if (nom != null && prenom != null && email != null && telephone != null && fonction != null && idEntreprise != null && !nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !telephone.isEmpty() && !fonction.isEmpty() && !idEntreprise.isEmpty()) {
                        sessionClient.creerInterlocuteur(nom, prenom, telephone, email, fonction, Long.parseLong(idEntreprise));
                        request.setAttribute("msgSuccess", "L'interlocuteur a bien été créé");
                        monProfil(request, response);
                    } else {
                        request.setAttribute("msgError", "Erreurs lors de la création de l'interlocuteur");
                        monProfil(request, response);
                    }
                }
                //MODIFIER UN INTERLOCUTEUR
                if (act.equals("modifierInterlocuteur")) {
                    String nom = request.getParameter("nom");
                    String prenom = request.getParameter("prenom");
                    String email = request.getParameter("email");
                    String telephone = request.getParameter("telephone");
                    String fonction = request.getParameter("fonction");
                    Long idInterlocuteur = Long.parseLong(request.getParameter("idInterlocuteur"));
                    if (nom != null && prenom != null && email != null && telephone != null && fonction != null && !nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !telephone.isEmpty() && !fonction.isEmpty()) {
                        sessionClient.modifierInterlocuteur(idInterlocuteur, nom, prenom, email, telephone, fonction);
                        request.setAttribute("msgSuccess", "L'interlocuteur a bien été créé");
                        monProfil(request, response);
                    } else {
                        request.setAttribute("msgError", "Erreurs lors de la création de l'interlocuteur");
                        monProfil(request, response);
                    }
                }
                //MODIFIER UN INTERLOCUTEUR
                if (act.equals("modifierInterlocuteur")) {
                    String nom = request.getParameter("nom");
                    String prenom = request.getParameter("prenom");
                    String email = request.getParameter("email");
                    String telephone = request.getParameter("telephone");
                    String fonction = request.getParameter("fonction");
                    Long idInterlocuteur = Long.parseLong(request.getParameter("idInterlocuteur"));
                    if (nom != null && prenom != null && email != null && telephone != null && fonction != null && !nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !telephone.isEmpty() && !fonction.isEmpty()) {
                        sessionClient.modifierInterlocuteur(idInterlocuteur, nom, prenom, email, telephone, fonction);
                        request.setAttribute("msgSuccess", "L'interlocuteur a bien été modifié");
                        monProfil(request, response);
                    } else {
                        request.setAttribute("msgError", "Erreurs lors de la modification de l'interlocuteur");
                        monProfil(request, response);
                    }
                }

                //SUPPRIMER L'INTERLOCUTEUR
                if (act.equals("supprimerInterlocuteur")) {
                    Long idInterlocuteur = Long.parseLong(request.getParameter("idInterlocuteur"));
                    sessionClient.supprimerInterlocuteur(idInterlocuteur);
                    request.setAttribute("msgSuccess", "L'interlocuteur a été supprimé");
                    monProfil(request, response);
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

                //GERER DEMANDES RATTACHEMENT
                if (act.equals("validerDemandeRattachementEntreprise")) {
                    String idDemande = request.getParameter("idDemande");
                    sessionClient.validerDemandeRattachement(Long.parseLong(idDemande));
                    request.setAttribute("msgSuccess", "La demande de rattachemet a bien été prise en compte");
                    monProfil(request, response);
                }

                /*CATALOGUE*/
                if (act.equals("offres")) {
                    request.setAttribute("listOffres", sessionClient.rechercherOffresClient());
                    jspClient = "/client/offres.jsp";
                }
                if (act.equals("services")) {
                    Long id = Long.parseLong(request.getParameter("id").trim());
                    Offre offre = sessionClient.rechercherOffre(id);
                    request.setAttribute("offre", offre);
                    request.setAttribute("listeServicesStandards", sessionClient.rechercherServicesStandards(id));
                    request.setAttribute("listeServicesNonStandards", sessionClient.rechercherServicesNonStandards(id));
                    jspClient = "/client/services.jsp";
                }
                /*CREERDEVIS*/

                if (act.equals("DevisEnCours")) {/*Menu devis en cours*/
                    devisEnCours(request, response);
                }

                if (act.equals("consulterFromDevisEnCours")) {/*Choix d'un devis from devis en cours*/
                    Long idDevis = Long.parseLong(request.getParameter("idDevis").trim());
                    String statut = request.getParameter("statut");
                    Devis devis = sessionClient.afficherLeDevis(idDevis);
                    if (statut.equalsIgnoreCase("Incomplet")) {/*Si incomplet renvoi vers la création du devis*/
                        if (devis.getDtype().equalsIgnoreCase("DevisStandard")) {
                            request.setAttribute("devisStandard", (DevisStandard) devis);
                            request.setAttribute("listConsultants", sessionClient.listConsultant(c.getEntreprise().getAgence().getId()));
                            jspClient = "/client/creerDevisStandard.jsp";
                        } else if (devis.getDtype().equalsIgnoreCase("DevisNonStandard")) {
                            request.setAttribute("devisNonStandard", (DevisNonStandard) devis);
                            request.setAttribute("listConsultants", sessionClient.listConsultant(c.getEntreprise().getAgence().getId()));
                            jspClient = "/client/creerDevisNonStandard.jsp";
                        }
                    } else {
                        if (devis.getDtype().equalsIgnoreCase("DevisStandard")) {
                            DevisStandard d = sessionClient.rechercherDevisStandard(idDevis);
                            request.setAttribute("devisStandard", d);
                            Long id = d.getConversation().getId();
                            request.setAttribute("listCommunications", sessionClient.afficherCommunications(id));
                            jspClient = "/client/devisStandard.jsp";
                        } else if (devis.getDtype().equalsIgnoreCase("DevisNonStandard")) {
                            DevisNonStandard d = sessionClient.rechercherDevisNonStandard(idDevis);
                            request.setAttribute("devisNonStandard", d);
                            request.setAttribute("listHistoriqueUtilisateurDevis", sessionClient.afficherHistoriqueUtilisateurDevis(idDevis));
                            Long id = d.getConversation().getId();
                            request.setAttribute("listCommunications", sessionClient.afficherCommunications(id));
                            jspClient = "/client/devisNonStandard.jsp";
                        }
                    }
                }

                if (act.equals("completerDevisStandard") || act.equals("completerDevisNonStandard")) {
                    String commentaire = request.getParameter("commentaire").trim();
                    String idDevis = request.getParameter("idDevis");
                    String[] disponibilites = request.getParameterValues("disponibilites");
                    if (commentaire != null && !commentaire.isEmpty()) {
                        if (disponibilites != null && disponibilites.length != 0) {
                            List<Long> listDisponibilites = new ArrayList<>();
                            for (String idDisponibilites : disponibilites) {
                                String[] demiJourneeDispo = idDisponibilites.split("/");
                                listDisponibilites.add(Long.parseLong(demiJourneeDispo[0]));
                                listDisponibilites.add(Long.parseLong(demiJourneeDispo[1]));
                            }
                            sessionClient.creerIntervention(listDisponibilites, Long.parseLong(idDevis));
                        }
                    }
                    sessionClient.modifierDevisIncomplet(Long.parseLong(idDevis), commentaire);
                    request.setAttribute("msgSuccess", "Votre demande a été prise en compte. Vous pouvez retrouver votre devis dans la section Mes devis en cours");
                    devisEnCours(request, response);
                }

                if (act.equals("DevisTermines")) {
                    request.setAttribute("listDevis", sessionClient.rechercherDevisTermines(c.getId()));;
                    jspClient = "/client/devisTermines.jsp";
                }

                /*Affichage des JSP creerDevisOffre, creerDevisServices, creerDevisStandard & creerDevisNonStandard */
                if (act.equals("creerDevisOffres")) {
                    request.setAttribute("listOffres", sessionClient.rechercherOffresClient());
                    jspClient = "/client/creerDevisOffre.jsp";
                }

                /*Une fois l'offre choisie il choisi le service*/
                if (act.equals("creerDevisServices")) {
                    Long id = Long.parseLong(request.getParameter("id").trim());
                    Offre offre = sessionClient.rechercherOffre(id);
                    request.setAttribute("offre", offre);
                    request.setAttribute("listeServicesStandards", sessionClient.rechercherServicesStandards(id));
                    request.setAttribute("listeServicesNonStandards", sessionClient.rechercherServicesNonStandards(id));
                    jspClient = "/client/creerDevisServices.jsp";
                }

                /*Confirmation création d'un Devis de service standard*/
                if (act.equals("creerDevisStandard")) {
                    Long idService = Long.parseLong(request.getParameter("idService").trim());
                    //request.setAttribute("service", st);
                    DevisStandard d = sessionClient.creerDevisStandard(idService, c.getId());
                    request.setAttribute("devisStandard", d);
                    /*Liste des consultant par agence du client*/
                    request.setAttribute("listConsultants", sessionClient.listConsultant(c.getEntreprise().getAgence().getId()));
                    jspClient = "/client/creerDevisStandard.jsp";
                }

                /*Confirmation création d'un Devis de service non standard*/
                if (act.equals("creerDevisNonStandard")) {
                    Long idService = Long.parseLong(request.getParameter("idService").trim());
                    DevisNonStandard d = sessionClient.creerDevisNonStandard(idService, c.getId());
                    request.setAttribute("devisNonStandard", d);
                    /*Liste des consultant par agence du client*/
                    request.setAttribute("listConsultants", sessionClient.listConsultant(c.getEntreprise().getAgence().getId()));
                    jspClient = "/client/creerDevisNonStandard.jsp";
                }

                if (act.equals("gererDevisNonStandard")) {
                    Long idDevis = Long.parseLong(request.getParameter("idDevis"));
                    DevisNonStandard d = sessionClient.rechercherDevisNonStandard(idDevis);
                    request.setAttribute("devisNonStandard", d);
                    request.setAttribute("listHistoriqueUtilisateurDevis", sessionClient.afficherHistoriqueUtilisateurDevis(idDevis));
                    //Création d'un faux id si l'utilisateur n'a aucun message pour que liste soit créée
                    List<Communication> listeComm = sessionClient.afficherCommunications(d.getConversation().getId());
                    String idString = "-1";
                    Long id = Long.parseLong(idString);
                    if(!listeComm.isEmpty()){
                        id = d.getConversation().getId();
                    }
                    request.setAttribute("listCommunications", sessionClient.afficherCommunications(id));
                    jspClient = "/client/devisNonStandard.jsp";
                }

                
                if(act.equals("gererDevisStandard")){
                    Long idDevis = Long.parseLong(request.getParameter("idDevis"));
                    DevisStandard d = sessionClient.rechercherDevisStandard(idDevis);
                    request.setAttribute("devisStandard", d );
                    //Création d'un faux id si l'utilisateur n'a aucun message pour que liste soit créée
                    List<Communication> listeComm = sessionClient.afficherCommunications(d.getConversation().getId());
                    String idString = "-1";
                    Long id = Long.parseLong(idString);
                    if (!listeComm.isEmpty()) {
                        id = d.getConversation().getId();
                    }
                    request.setAttribute("listCommunications", sessionClient.afficherCommunications(id));
                    jspClient = "/client/devisStandard.jsp";
                }
                
                if(act.equals("repondreMessageDevisNonStandard")){
                    Long convId = Long.parseLong(request.getParameter("idConversation"));
                    Conversation conv = sessionClient.afficherConversation(convId);
                    if (request.getParameter("message") != null && !request.getParameter("message").isEmpty()) {
                        String message = request.getParameter("message");
                        Communication comm = sessionClient.creerCommunication(message, conv.getId());
                    }
                    request.setAttribute("listCommunications", sessionClient.afficherCommunications(conv.getId()));
                    Long idDevis = Long.parseLong(request.getParameter("idDevis"));
                    request.setAttribute("devisNonStandard", sessionClient.rechercherDevisNonStandard(idDevis));
                    request.setAttribute("listHistoriqueUtilisateurDevis", sessionClient.afficherHistoriqueUtilisateurDevis(idDevis));
                    jspClient = "/client/devisNonStandard.jsp";
                }
                if (act.equals("repondreMessageDevisStandard")) {
                    Long convId = Long.parseLong(request.getParameter("idConversation"));
                    Conversation conv = sessionClient.afficherConversation(convId);
                    if (request.getParameter("message") != null && !request.getParameter("message").isEmpty()) {
                        String message = request.getParameter("message");
                        Communication comm = sessionClient.creerCommunication(message, conv.getId());
                    }
                    request.setAttribute("listCommunications", sessionClient.afficherCommunications(conv.getId()));
                    Long idDevis = Long.parseLong(request.getParameter("idDevis"));
                    request.setAttribute("devisStandard", sessionClient.rechercherDevisStandard(idDevis));
                    jspClient = "/client/devisStandard.jsp";
                }


                /*Suppression Devis incomplet*/
                if (act.equals("supprimerDevisStandard")) {
                    Long idDevisStandard = Long.parseLong(request.getParameter("idDevis").trim());
                    sessionClient.supprimerDevisStandardIncomplet(idDevisStandard);
                    devisEnCours(request, response);
                }

                if (act.equals("supprimerDevisNonStandard")) {
                    Long idDevisStandard = Long.parseLong(request.getParameter("idDevis").trim());
                    sessionClient.supprimerDevisNonStandardIncomplet(idDevisStandard);
                    devisEnCours(request, response);
                }

                /*MESSAGERIE*/
                if (act.equals("messages")) {
                    request.setAttribute("listConversations", sessionClient.afficherConversations(c.getId()));
                    Conversation conversationActive = null;
                    //Création d'un faux id si l'utilisateur n'a aucun message pour que liste soit créée
                    String idString = "-1";
                    Long id = Long.parseLong(idString);
                    request.setAttribute("listCommunications", sessionClient.afficherCommunications(id));
                    if (!sessionClient.afficherConversations(c.getId()).isEmpty()) {
                        if (request.getParameter("idConversation") == null) {
                            //La dernière conversation créée devient la conversation active dans le chat, s'il n'y a pas de conversation on laisse null
                            conversationActive = sessionClient.afficherConversations(c.getId()).get(0);
                            request.setAttribute("listCommunications", sessionClient.afficherCommunications(conversationActive.getId()));
                        } else {
                            //Si une conversation est sélectionnée elle devient la conversation active
                            conversationActive = sessionClient.afficherConversation(Long.parseLong(request.getParameter("idConversation")));
                            request.setAttribute("listCommunications", sessionClient.afficherCommunications(conversationActive.getId()));
                        }
                    }
                    request.setAttribute("conversation", conversationActive);
                    jspClient = "/client/inbox.jsp";
                }

                if (act.equals("nouvelleConversation")) {
                    //Conversation active
                    Conversation conv = null;
                    //Création d'un faux id si l'utilisateur n'a aucun message pour que liste soit créée
                    String idString = "-1";
                    Long id = Long.parseLong(idString);
                    request.setAttribute("listCommunications", sessionClient.afficherCommunications(id));
                    if (request.getParameter("message") != null && !request.getParameter("message").isEmpty()) {
                        String message = request.getParameter("message");
                        //Conversation active
                        conv = sessionClient.creerConversation(c.getId());
                        Communication comm = sessionClient.creerCommunication(message, conv.getId());
                        request.setAttribute("listCommunications", sessionClient.afficherCommunications(conv.getId()));
                    } else if (!sessionClient.afficherConversations(c.getId()).isEmpty()) {
                        //La dernière conversation créée devient la conversation active dans le chat, s'il n'y a pas de conversation on laisse null
                        //Conversation active
                        conv = sessionClient.afficherConversations(c.getId()).get(0);
                        request.setAttribute("listCommunications", sessionClient.afficherCommunications(conv.getId()));
                    }
                    request.setAttribute("listConversations", sessionClient.afficherConversations(c.getId()));
                    //Conversation active
                    request.setAttribute("conversation", conv);
                    jspClient = "/client/inbox.jsp";
                }

                if (act.equals("repondreMessage")) {
                    Long convId = Long.parseLong(request.getParameter("idConversation"));
                    Conversation conv = sessionClient.afficherConversation(convId);
                    if (request.getParameter("message") != null && !request.getParameter("message").isEmpty()) {
                        String message = request.getParameter("message");
                        Communication comm = sessionClient.creerCommunication(message, conv.getId());
                    }
                    request.setAttribute("listConversations", sessionClient.afficherConversations(c.getId()));
                    request.setAttribute("listCommunications", sessionClient.afficherCommunications(conv.getId()));
                    request.setAttribute("conversation", conv);
                    jspClient = "/client/inbox.jsp";
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
