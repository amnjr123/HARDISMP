package servlet;

import Enum.SFTPConnexion;
import GestionCatalogue.Offre;
import GestionDevis.Communication;
import GestionDevis.Conversation;
import GestionDevis.DevisNonStandard;
import GestionDevis.DevisStandard;
import GestionDevis.Proposition;
import GestionUtilisateur.CV;
import GestionUtilisateur.Consultant;
import GestionUtilisateur.Disponibilite;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import SessionUtilisateur.SessionHardisLocal;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@WebServlet(name = "ServletUtilisateurHardis", urlPatterns = {"/ServletUtilisateurHardis"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class ServletUtilisateurHardis extends HttpServlet {

    @EJB
    private SessionHardisLocal sessionHardis;

    private final String ATT_SESSION_HARDIS = "sessionHardis";

    private String jspClient = "/hardisUser/index.jsp";

    private UtilisateurHardis uh;

    protected void gererDevisNonStandard(HttpServletRequest request, HttpServletResponse response) {
        Long idDevis = Long.parseLong(request.getParameter("idDevis"));
        DevisNonStandard d = sessionHardis.rechercherDevisNonStandard(idDevis);
        request.setAttribute("devisNonStandard", d);
        request.setAttribute("listHistoriqueUtilisateurDevis", sessionHardis.afficherHistoriqueUtilisateurDevis(idDevis));
        request.setAttribute("listCommunications", sessionHardis.afficherCommunications(d.getConversation().getId()));
        request.setAttribute("listUtilisateurHardis", sessionHardis.utilisateursHardisTransfertDevis(idDevis));
        jspClient = "/hardisUser/devisNonStandard.jsp";
    }

    protected void menuPlanning(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("listDispo", sessionHardis.afficherDisponibilites(uh.getId()));
        request.setAttribute("listInterv", sessionHardis.afficherInterventions(uh.getId()));
        jspClient = "/hardisUser/planning.jsp";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessionHttp = request.getSession();
        jspClient = "/hardisUser/index.jsp";
        if (sessionHttp.getAttribute(ATT_SESSION_HARDIS) != null) {
            uh = (UtilisateurHardis) sessionHttp.getAttribute(ATT_SESSION_HARDIS);
            if (request.getParameter("action") != null && !request.getParameter("action").isEmpty()) {
                String act = request.getParameter("action");

                if (act.equals("monProfil")) {

                    jspClient = "/hardisUser/monProfil.jsp";
                }

                //Joindre ou modifier un cv (Hors cv offres)
                if (act.equals("modifierCV")) {
                    SFTPConnexion con = new SFTPConnexion();
                    boolean fichierJoint = false;
                    for (Part part : request.getParts()) {
                        if (part.getName().equals("file")) {
                            fichierJoint = true;
                            try {
                                if (sessionHardis.afficherCVSansOffre(uh.getId()) == null) {
                                    String chemin = "/home/hardis/hmp/utilisateurHardis/" + uh.getId() + "/" + uh.getId() + ".pdf";
                                    CV cv = sessionHardis.creerCV(chemin, uh.getId());
                                    con.uploadFile(part.getInputStream(), chemin);
                                    UtilisateurHardis updUh = uh;
                                    updUh.getcVs().add(cv);
                                    sessionHttp.setAttribute(ATT_SESSION_HARDIS, updUh);
                                } else {
                                    Long idCV = sessionHardis.afficherCVSansOffre(uh.getId()).getId();
                                    CV cv = sessionHardis.afficherCv(idCV);
                                    con.uploadFile(part.getInputStream(), cv.getCheminCV());
                                }
                                con.disconnect();

                            } catch (Exception ex) {
                                request.setAttribute("msgError", "Les fichiers joints n'ont pas pu être envoyés");
                            }
                        }
                    }
                    if (!fichierJoint) {
                        request.setAttribute("msgError", "Veuillez joindre un fichier");
                    }
                    jspClient = "/hardisUser/monProfil.jsp";

                }

                if (act.equals("ajouterCVOffre")) {
                    String idOffre = request.getParameter("idOffre");
                    String chemin = "/home/hardis/hmp/utilisateurHardis/" + uh.getId() + "/" + idOffre + "/" + uh.getId() + ".pdf";
                    SFTPConnexion con = new SFTPConnexion();
                    for (Part part : request.getParts()) {
                        if (part.getName().equals("file")) {
                            try {
                                if (sessionHardis.afficherCVOffreUtilisateur(uh.getId(), Long.parseLong(idOffre)) == null) {
                                    CV cv = sessionHardis.creerCV(chemin, uh.getId(), Long.parseLong(idOffre));
                                    con.uploadFile(part.getInputStream(), chemin);
                                    request.setAttribute("msgSuccess", "Le CV a bien été ajouté");
                                    UtilisateurHardis updUh = uh;
                                    updUh.getcVs().add(cv);
                                    sessionHttp.setAttribute(ATT_SESSION_HARDIS, updUh);
                                } else {
                                    CV cv = sessionHardis.afficherCVOffreUtilisateur(uh.getId(), Long.parseLong(idOffre));
                                    con.uploadFile(part.getInputStream(), cv.getCheminCV());
                                    request.setAttribute("msgSuccess", "Le CV a bien été modifié");
                                }
                                con.disconnect();

                            } catch (Exception e) {
                                request.setAttribute("msgError", "Les fichiers joints n'ont pas pu être envoyés");
                            }
                        }
                    }
                    jspClient = "/hardisUser/monProfil.jsp";

                }

                //MODIFIER LE MOT DE PASSE DE LUTILISATEUR HARDIS
                if (act.equals("modifierMDPHardis")) {
                    if (request.getParameter("ancienMDP") != null && request.getParameter("nouveauMDP") != null && !request.getParameter("ancienMDP").isEmpty() && !request.getParameter("nouveauMDP").isEmpty()) {
                        String nouveau = request.getParameter("nouveauMDP");
                        String ancien = request.getParameter("ancienMDP");
                        Utilisateur u = sessionHardis.modifierUtilisateurMDP(uh.getId(), ancien, nouveau);
                        if (u != null) {
                            request.setAttribute("msgSuccess", "Le mot de passe a bien été modifié");
                            jspClient = "/hardisUser/monProfil.jsp";
                        } else {
                            request.setAttribute("msgError", "Le mot de passe n'a pas été modifié, l'ancien mot de passe est incorrect");
                            jspClient = "/hardisUser/monProfil.jsp";
                        }
                    }
                }
                //MODIFIER LE TELEPHONE
                if (act.equals("modifierTelephoneHardis")) {
                    if (request.getParameter("nouveauTelephone") != null && !request.getParameter("nouveauTelephone").isEmpty()) {
                        String tel = request.getParameter("nouveauTelephone");
                        UtilisateurHardis u = sessionHardis.modifierCompte(uh.getId(), uh.getMail(), tel, uh.getActifInactif());
                        if (u != null) {
                            sessionHttp.setAttribute(ATT_SESSION_HARDIS, u);
                            request.setAttribute("msgSuccess", "Le téléphone a bien été modifié");
                            jspClient = "/hardisUser/monProfil.jsp";
                        } else {
                            request.setAttribute("msgError", "Le téléphone n'a pas été modifié");
                            jspClient = "/hardisUser/monProfil.jsp";
                        }

                    }
                }
                //MODIFIER LE MAIL
                if (act.equals("modifierMailHardis")) {
                    if (request.getParameter("email") != null && !request.getParameter("email").isEmpty()) {
                        String mail = request.getParameter("email");
                        UtilisateurHardis u = sessionHardis.modifierCompte(uh.getId(), mail, uh.getTelephone(), uh.getActifInactif());
                        if (u != null) {
                            sessionHttp.setAttribute(ATT_SESSION_HARDIS, u);
                            request.setAttribute("msgSuccess", "Le mail a bien été modifié");
                            jspClient = "/hardisUser/monProfil.jsp";
                        } else {
                            request.setAttribute("msgError", "Le mail n'a pas été modifié");
                            jspClient = "/hardisUser/monProfil.jsp";
                        }

                    }
                }

                //MODIFIER LE STATUT ACTIF INACTIF
                if (act.equals("modifierStatutHardis")) {
                    String statut = request.getParameter("actifInactif");
                    boolean actifInactif;
                    if (statut != null) {
                        actifInactif = true;
                    } else {
                        actifInactif = false;
                    }
                    UtilisateurHardis u = sessionHardis.modifierCompte(uh.getId(), uh.getMail(), uh.getTelephone(), actifInactif);
                    if (u != null) {
                        sessionHttp.setAttribute(ATT_SESSION_HARDIS, u);
                        request.setAttribute("msgSuccess", "Votre changement de statut a bien été enregistré");
                        jspClient = "/hardisUser/monProfil.jsp";
                    } else {
                        request.setAttribute("msgError", "Votre changement de statut a échoué");
                        jspClient = "/hardisUser/monProfil.jsp";
                    }
                }

                if (act.equals("ajouterDisponibilite")) {
                    String date = request.getParameter("jourDisponible");
                    String matin = request.getParameter("choix");

                    if (matin == null) {
                        matin = "1";
                    } else if (matin != null && !matin.isEmpty()) {
                        if (matin.equals("oui")) {
                            matin = "0";
                        }
                    }

                    if (date != null && !date.isEmpty()) {
                        date += " 00:00:00";
                        Date d = null;
                        try {
                            d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
                        } catch (ParseException ex) {
                            Logger.getLogger(ServletUtilisateurHardis.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (d != null) {
                            Disponibilite dispo = sessionHardis.creerDisponibilite(uh.getId(), d, Integer.parseInt(matin));
                            if (dispo != null) {
                                request.setAttribute("msgSuccess", "La date a bien été enregistrée");
                            } else {
                                request.setAttribute("msgError", "Vous avez déjà un évènement plannifié à cette date");
                            }
                        } else {
                            request.setAttribute("msgError", "Le champ date est vide.");
                        }
                        menuPlanning(request, response);
                    }
                }

                if (act.equals("planning")) {
                    menuPlanning(request, response);
                }

                if (act.equals("supprimerDisponibilite")) {
                    String idDispo = request.getParameter("disponibilite");
                    if (idDispo != null && !idDispo.isEmpty()) {
                        sessionHardis.supprimerDisponibilite(Long.parseLong(idDispo));
                        request.setAttribute("msgSuccess", "La disponibilité a bien été supprimée");
                    } else {
                        request.setAttribute("msgError", "Erreur lors de la suppression.");
                    }
                    menuPlanning(request, response);
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

                /*GESTION DES DEVIS*/
                if (act.equals("devisEnCours")) {
                    request.setAttribute("listeDevis", sessionHardis.rechercherDevis(uh.getId(), null, "ReponseEnCours"));
                    jspClient = "/hardisUser/devisEnCours.jsp";
                }
                if (act.equals("devisTermines")) {
                    request.setAttribute("listeDevis", sessionHardis.rechercherDevisSaufIncomplets(uh.getId()));
                    jspClient = "/hardisUser/devisTermines.jsp";
                }
                if (act.equals("tousLesDevis")) {
                    request.setAttribute("listeDevis", sessionHardis.rechercherDevisSaufIncomplet());
                    jspClient = "/hardisUser/tousLesDevis.jsp";
                }


                if (act.equals("gererDevisNonStandard")) {
                    gererDevisNonStandard(request, response);
                }

                if (act.equals("transferDevis")) {
                    Long idUH = Long.parseLong(request.getParameter("idUH"));
                    Long idDevis = Long.parseLong(request.getParameter("idDevis"));
                    sessionHardis.transfererDevisNonStandard(idDevis, idUH);
                }

                if (act.equals("gererDevisStandard")) {
                    Long idDevis = Long.parseLong(request.getParameter("idDevis"));
                    DevisStandard d = sessionHardis.rechercherDevisStandard(idDevis);
                    request.setAttribute("devisStandard", d);
                    request.setAttribute("listCommunications", sessionHardis.afficherCommunications(d.getConversation().getId()));
                    jspClient = "/hardisUser/devisStandard.jsp";
                }

                if (act.equals("envoyerDevisAuClient")) {
                    Long idDevis = Long.parseLong(request.getParameter("idDevis"));
                    DevisNonStandard d = sessionHardis.envoyerDevisNonStandard(idDevis,uh.getId());
                    if (d == null) {
                        request.setAttribute("msgError", "Une erreur est survenue, veuillez vérifier qu'il existe au moins une proposition commerciale. Si votre plafond de délégation ne suffit pas a cette action veuillez transférer le devis à un autre utilisateur hardis.");
                        gererDevisNonStandard(request, response);
                    } else {
                        request.setAttribute("msgSuccess", "Le devis a été transmis au client.");
                        gererDevisNonStandard(request, response);
                    }
                }
                
                if (act.equals("creerProposition")) {
                    Long idDevis = Long.parseLong(request.getParameter("idDevis"));
                    DevisNonStandard d = sessionHardis.rechercherDevisNonStandard(idDevis);
                    String debutString = request.getParameter("datedebut");
                    String finString = request.getParameter("datefin");
                    if(debutString != null && finString != null && !debutString.isEmpty() && !finString.isEmpty()){
                        if (d == null) {
                            request.setAttribute("msgError", "Une erreur est survenue.");
                        } else {
                            request.setAttribute("msgSuccess", "La proposition a été ajoutée.");
                        }
                        gererDevisNonStandard(request, response);
                        int numero = d.getPropositions().size();
                        String chemin = "/home/hardis/hmp/devis/propositionCommerciale/proposition"+d.getId()+"_"+numero+".ppt";
                        SFTPConnexion con = new SFTPConnexion();
                        for (Part part : request.getParts()) {
                            if (part.getName().equals("file")) {
                                try {
                                        Date dateDebut = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(debutString);
                                        Date dateFin = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(finString);
                                        Proposition proposition = sessionHardis.creerProposition(dateDebut, dateFin,chemin, uh.getId(), idDevis);
                                        con.uploadFile(part.getInputStream(), chemin);
                                        UtilisateurHardis updUh = uh;
                                        con.uploadFile(part.getInputStream(), proposition.getCheminDocument());
                                        request.setAttribute("msgSuccess", "La proposition a été ajoutée au devis.");
                                        con.disconnect();
                                } catch (Exception e) {
                                    request.setAttribute("msgError", "La proposition n'a pas pu être ajoutée au devis.");
                                }
                            }
                        }
                    }else{
                        request.setAttribute("msgError", "Veuillez sélectionner les dates de validité de la proposition.");
                    }
                    gererDevisNonStandard(request, response);
                }
                
                /*MESSAGERIE*/
                if (act.equals("messages")) {
                    request.setAttribute("listConversations", sessionHardis.afficherConversations(uh.getId()));
                    Conversation conversationActive = null;
                    //Création d'un faux id si l'utilisateur n'a aucun message pour que liste soit créée
                    String idString = "-1";
                    Long id = Long.parseLong(idString);
                    request.setAttribute("listCommunications", sessionHardis.afficherCommunications(id));
                    if (!sessionHardis.afficherConversations(uh.getId()).isEmpty()) {
                        if (request.getParameter("idConversation") == null) {
                            //La dernière conversation créée devient la conversation active dans le chat, s'il n'y a pas de conversation on laisse null
                            conversationActive = sessionHardis.afficherConversations(uh.getId()).get(0);
                            request.setAttribute("listCommunications", sessionHardis.afficherCommunications(conversationActive.getId()));
                        } else {
                            //Si une conversation est sélectionnée elle devient la conversation active
                            conversationActive = sessionHardis.afficherConversation(Long.parseLong(request.getParameter("idConversation")));
                            request.setAttribute("listCommunications", sessionHardis.afficherCommunications(conversationActive.getId()));
                        }
                    }
                    request.setAttribute("conversation", conversationActive);
                    jspClient = "/hardisUser/inbox.jsp";
                }

                if (act.equals("repondreMessage")) {
                    Long convId = Long.parseLong(request.getParameter("idConversation"));
                    Conversation conv = sessionHardis.afficherConversation(convId);
                    if (request.getParameter("message") != null && !request.getParameter("message").isEmpty()) {
                        String message = request.getParameter("message");
                        if (conv.getUtilisateurHardis() == null) {
                            conv = sessionHardis.affecterUHConversation(uh.getId(), conv.getId());
                        }
                        Communication comm = sessionHardis.creerCommunication(message, conv.getId());
                    }
                    request.setAttribute("listConversations", sessionHardis.afficherConversations(uh.getId()));
                    request.setAttribute("listCommunications", sessionHardis.afficherCommunications(conv.getId()));
                    request.setAttribute("conversation", conv);
                    jspClient = "/hardisUser/inbox.jsp";
                }
                if (act.equals("repondreMessageDevisNonStandard")) {
                    Long convId = Long.parseLong(request.getParameter("idConversation"));
                    Conversation conv = sessionHardis.afficherConversation(convId);
                    if (request.getParameter("message") != null && !request.getParameter("message").isEmpty()) {
                        String message = request.getParameter("message");
                        Communication comm = sessionHardis.creerCommunication(message, conv.getId());
                    }
                    request.setAttribute("listCommunications", sessionHardis.afficherCommunications(conv.getId()));
                    Long idDevis = Long.parseLong(request.getParameter("idDevis"));
                    request.setAttribute("devisNonStandard", sessionHardis.rechercherDevisNonStandard(idDevis));
                    request.setAttribute("listHistoriqueUtilisateurDevis", sessionHardis.afficherHistoriqueUtilisateurDevis(idDevis));
                    request.setAttribute("listUtilisateurHardis", sessionHardis.utilisateursHardisTransfertDevis(idDevis));
                    jspClient = "/hardisUser/devisNonStandard.jsp";
                }
                if (act.equals("repondreMessageDevisStandard")) {
                    Long convId = Long.parseLong(request.getParameter("idConversation"));
                    Conversation conv = sessionHardis.afficherConversation(convId);
                    if (request.getParameter("message") != null && !request.getParameter("message").isEmpty()) {
                        String message = request.getParameter("message");
                        Communication comm = sessionHardis.creerCommunication(message, conv.getId());
                    }
                    request.setAttribute("listCommunications", sessionHardis.afficherCommunications(conv.getId()));
                    Long idDevis = Long.parseLong(request.getParameter("idDevis"));
                    request.setAttribute("devisStandard", sessionHardis.rechercherDevisStandard(idDevis));
                    jspClient = "/hardisUser/devisStandard.jsp";
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

    private void ArrayList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
