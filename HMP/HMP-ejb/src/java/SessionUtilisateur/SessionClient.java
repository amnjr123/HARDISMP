package SessionUtilisateur;

import Enum.Helpers;
import Enum.StatutDevis;
import FacadeCatalogue.LivrableFacadeLocal;
import FacadeCatalogue.OffreFacadeLocal;
import FacadeCatalogue.ServiceFacadeLocal;
import FacadeCatalogue.ServiceNonStandardFacadeLocal;
import FacadeCatalogue.ServiceStandardFacadeLocal;
import FacadeDevis.CommunicationFacadeLocal;
import FacadeDevis.ConversationFacadeLocal;
import FacadeDevis.DevisFacadeLocal;
import FacadeDevis.DevisNonStandardFacadeLocal;
import FacadeDevis.DevisStandardFacadeLocal;
import FacadeDevis.HistoriqueUtilisateurDevisFacadeLocal;
import FacadeDevis.InterventionFacadeLocal;
import FacadeUtilisateur.AgenceFacadeLocal;
import FacadeUtilisateur.ClientFacadeLocal;
import FacadeUtilisateur.ConsultantFacadeLocal;
import FacadeUtilisateur.DemandeCreationEntrepriseFacadeLocal;
import FacadeUtilisateur.EntrepriseFacadeLocal;
import FacadeUtilisateur.PorteurOffreFacadeLocal;
import FacadeUtilisateur.ReferentLocalFacadeLocal;
import FacadeUtilisateur.UtilisateurFacadeLocal;
import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import GestionCatalogue.ServiceStandard;
import GestionDevis.DevisStandard;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.DemandeCreationEntreprise;
import GestionUtilisateur.DemandeRattachement;
import FacadeUtilisateur.DemandeRattachementFacadeLocal;
import FacadeUtilisateur.DisponibiliteFacadeLocal;
import FacadeUtilisateur.InterlocuteurFacadeLocal;
import GestionCatalogue.Livrable;
import GestionCatalogue.ServiceNonStandard;
import GestionDevis.Communication;
import GestionDevis.Conversation;
import GestionDevis.Devis;
import GestionDevis.DevisNonStandard;
import GestionDevis.Intervention;
import GestionDevis.HistoriqueUtilisateurDevis;
import GestionUtilisateur.Consultant;
import GestionUtilisateur.Disponibilite;
import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
import GestionUtilisateur.ReferentLocal;
import GestionUtilisateur.Utilisateur;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SessionClient implements SessionClientLocal {

    @EJB
    private DisponibiliteFacadeLocal disponibiliteFacade;

    @EJB
    private InterventionFacadeLocal interventionFacade;

    @EJB
    private ConsultantFacadeLocal consultantFacade;

    @EJB
    private CommunicationFacadeLocal communicationFacade;

    @EJB
    private ConversationFacadeLocal conversationFacade;

    @EJB
    private HistoriqueUtilisateurDevisFacadeLocal historiqueUtilisateurDevisFacade;

    @EJB
    private ServiceNonStandardFacadeLocal serviceNonStandardFacade;

    @EJB
    private DevisNonStandardFacadeLocal devisNonStandardFacade;

    @EJB
    private LivrableFacadeLocal livrableFacade;

    @EJB
    private OffreFacadeLocal offreFacade;

    @EJB
    private InterlocuteurFacadeLocal interlocuteurFacade;

    @EJB
    private DemandeRattachementFacadeLocal demandeRattachementFacade;

    @EJB
    private DemandeCreationEntrepriseFacadeLocal demandeCreationEntrepriseFacade;

    @EJB
    private EntrepriseFacadeLocal entrepriseFacade;

    @EJB
    private AgenceFacadeLocal agenceFacade;

    @EJB
    private ServiceStandardFacadeLocal serviceStandardFacade;

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private ReferentLocalFacadeLocal referentLocalFacade;

    @EJB
    private DevisStandardFacadeLocal devisStandardFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private PorteurOffreFacadeLocal porteurOffreFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    /*GESTION ENTREPRISE*/
    @Override
    public List<Agence> rechercherAgence() {
        return agenceFacade.rechercheAgences();
    }

    @Override
    public DemandeCreationEntreprise rechercherDemandeCreationEntreprise(Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        return demandeCreationEntrepriseFacade.rechercheDemandeCreationEntrepriseClient(c);
    }

    //Méthode lorsque qu'un utilisateur valide le formulaire d'entreprise
    @Override
    public String DemandeCreationOuRattachement(Long idClient, String nom, String siret, String adresse, Long idAgence) {
        Client c = clientFacade.rechercheClient(idClient);
        Agence a = null;
        if (idAgence != null) {
            a = agenceFacade.rechercheAgence(idAgence);
        }
        Entreprise e = entrepriseFacade.rechercheEntrepriseSiret(siret);
        if (e == null) {
            return "hmp/demandeEntreprise/creation/" + creerDemandeEntreprise(idClient, nom, siret, adresse, idAgence).getId();
        } else {
            return "hmp/demandeEntreprise/rattachement/" + creerDemandeRattachement(idClient, siret).getId();
        }
    }

    @Override
    public DemandeCreationEntreprise creerDemandeEntreprise(Long idClient, String nom, String siret, String adresse, Long idAgence) {
        Client c = clientFacade.rechercheClient(idClient);
        Agence a = null;
        if (idAgence != null) {
            a = agenceFacade.rechercheAgence(idAgence);
        }
        DemandeCreationEntreprise e = demandeCreationEntrepriseFacade.creerDemandeCreationEntreprise(nom, siret, adresse, a, c);
        clientFacade.demanderCreationEntreprise(c, e);
        return e;
    }

    @Override
    public DemandeRattachement creerDemandeRattachement(Long idClient, String siret) {
        Client c = clientFacade.rechercheClient(idClient);
        Entreprise e = entrepriseFacade.rechercheEntrepriseSiret(siret);
        DemandeRattachement d = demandeRattachementFacade.creerDemandeRattachement(c, e);
        clientFacade.demanderRattachementEntreprise(c, d);
        return d;
    }

    //Méthode client admin
    @Override
    public List<DemandeRattachement> rechercherDemandeRattachementEntreprise(Long idEntreprise) {
        Entreprise e = entrepriseFacade.rechercheEntreprise(idEntreprise);
        return demandeRattachementFacade.rechercherDemandeRattachement(e);
    }

    //Méthode client admin
    @Override
    public DemandeRattachement rechercherDemandeRattachementClient(Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        return demandeRattachementFacade.rechercherDemandeRattachement(c);
    }

    //Méthode client admin
    @Override
    public DemandeRattachement validerDemandeRattachement(Long idDemande) {
        DemandeRattachement d = demandeRattachementFacade.rechercherDemandeRattachement(idDemande);
        clientFacade.affecterEntreprise(d.getClient(), d.getEntreprise());
        demandeRattachementFacade.supprimerDemandeRattachement(d);
        return d;//A tester si on peut renvoyer une instance supprimée de la bdd sans provoquer de bug
    }

    //Méthode client admin
    @Override
    public DemandeRattachement refuserDemandeRattachement(Long idDemande) {
        DemandeRattachement d = demandeRattachementFacade.rechercherDemandeRattachement(idDemande);
        demandeRattachementFacade.supprimerDemandeRattachement(d);
        return d;//A tester si on peut renvoyer une instance supprimée de la bdd sans provoquer de bug
    }

    @Override
    public List<Interlocuteur> rechercherInterlocuteur(Long idEntreprise) {
        Entreprise e = entrepriseFacade.rechercheEntreprise(idEntreprise);
        return interlocuteurFacade.rechercheInterlocuteur(e);
    }

    @Override
    public Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone, String mail, String fonction, long idEntreprise) {
        return interlocuteurFacade.creerInterlocuteur(nom, prenom, mail, telephone, fonction, entrepriseFacade.rechercheEntreprise(idEntreprise));
    }

    @Override
    public Interlocuteur modifierInterlocuteur(Long idInterlocuteur, String nom, String prenom, String mail, String telephone, String fonction) {
        Interlocuteur i = interlocuteurFacade.rechercheInterlocuteur(idInterlocuteur);
        return interlocuteurFacade.modifierInterlocuteur(i, nom, prenom, mail, telephone, fonction);
    }

    @Override
    public Interlocuteur supprimerInterlocuteur(Long idInterlocuteur) {
        Interlocuteur i = interlocuteurFacade.rechercheInterlocuteur(idInterlocuteur);
        return interlocuteurFacade.supprimerInterlocuteur(i);
    }

    /*GESTION DES DEVIS*/
    @Override
    public List<Offre> rechercherOffresClient() {
        //Le client ne doit avoir accès qu'aux offres actuelles
        return offreFacade.rechercheOffresActuelles();
    }

    @Override
    public Offre rechercherOffre(Long idOffre) {
        return offreFacade.rechercheOffre(idOffre);
    }

    @Override
    public List<ServiceStandard> rechercherServicesStandards(Long idOffre) {
        Offre o = offreFacade.rechercheOffre(idOffre);
        return serviceStandardFacade.rechercheServicesStandardsActuels(o);
    }

    @Override
    public ServiceStandard rechercherServiceStandard(Long idService) {
        return serviceStandardFacade.rechercheServiceStandard(idService);
    }

    @Override
    public ServiceNonStandard rechercherServiceNonStandard(Long idService) {
        return serviceNonStandardFacade.rechercheServiceNonStandard(idService);
    }

    @Override
    public List<ServiceNonStandard> rechercherServicesNonStandards(Long idOffre) {
        Offre o = offreFacade.rechercheOffre(idOffre);
        return serviceNonStandardFacade.rechercheServicesNonStandardsActuels(o);
    }

    @Override
    public List<Livrable> afficherLivrables(Long idService) {
        Service service = serviceFacade.rechercherService(idService);
        return livrableFacade.rechercheLivrable(service);
    }

    @Override
    public DevisStandard creerDevisStandard(Long idServiceStandard, Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        ServiceStandard s = serviceStandardFacade.rechercheServiceStandard(idServiceStandard);
        DevisStandard d;
        d = devisStandardFacade.creerDevisStandard(s, c);
        return d;
    }

    @Override
    public DevisNonStandard creerDevisNonStandard(Long idServiceNonStandard, Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        ServiceNonStandard s = serviceNonStandardFacade.rechercheServiceNonStandard(idServiceNonStandard);
        DevisNonStandard d;
        d = devisNonStandardFacade.creerDevisNonStandard(s, c);
        return d;
    }

    @Override
    public String modifierDevisIncomplet(Long idDevis, String commentaireClient) {
        //ATTENTION DANS LES SERVLETS : LE COMMENTAIRE CLIENT PEUT ETRE NUL
        Devis d = devisFacade.rechercherDevis(idDevis);
        String message = "Afin de comprendre au mieux vos besoins, merci de saisir un commentaire d'au moins 50 caractères.";
        if (commentaireClient != null) {
            if (commentaireClient.length() >= 50) {
                if (d.getStatut() == StatutDevis.Incomplet) {
                    if (d.getDtype().equalsIgnoreCase("devisstandard")) {
                        DevisStandard ds = devisStandardFacade.rechercheDevisStandard(idDevis);
                        ReferentLocal referentLocal = referentLocalFacade.rechercheReferentLocal(ds.getClient().getEntreprise().getAgence(), ds.getServiceStandard().getOffre());
                        if (referentLocal != null) {
                            historiqueUtilisateurDevisFacade.creerPremierHistoriqueUtilisateurDevis(ds, referentLocal);
                            devisStandardFacade.modifierDevisStandard(ds, commentaireClient, referentLocal, ds.getClient().getEntreprise().getAgence());
                            Conversation conv = conversationFacade.creerConversation(d, d.getClient(), referentLocal);
                            communicationFacade.creerCommunication("Bonjour, vous pouvez écrire ici vos questions concernant le devis et je vous répondrai dans les plus bref délais !", null, referentLocal, conv);
                            message = "Votre devis vous a été envoyé. Vous le retrouverez dans la rubrique Devis terminés.";
                        } else {
                            message = "Nous sommes désolés, il n'y a actuellement aucun responsable pour cette offre dans votre agence Hardis. Pour vous aider au mieux dans votre demande, nous vous invitons à contacter un administrateur Hardis.";
                        }
                    } else {
                        DevisNonStandard dns = devisNonStandardFacade.rechercheDevisNonStandard(idDevis);
                        ReferentLocal referentLocal = referentLocalFacade.rechercheReferentLocal(dns.getClient().getEntreprise().getAgence(), dns.getServiceNonStandard().getOffre());
                        if (referentLocal != null) {
                            historiqueUtilisateurDevisFacade.creerPremierHistoriqueUtilisateurDevis(dns, referentLocal);
                            devisNonStandardFacade.modifierDevisNonStandard(dns, commentaireClient, referentLocal, dns.getClient().getEntreprise().getAgence());
                            Conversation conv = conversationFacade.creerConversation(d, d.getClient(), referentLocal);
                            communicationFacade.creerCommunication("Bonjour, vous pouvez écrire ici vos questions concernant le devis et je vous répondrai dans les plus bref délais !", null, referentLocal, conv);
                            message = "Votre devis a été envoyé à un consultant Hardis qui vous contactera prochainement. Vous le retrouverez dans la rubrique Devis en cours.";
                        } else {
                            message = "Nous sommes désolés, il n'y a actuellement aucun responsable pour cette offre dans votre agence Hardis. Pour vous aider au mieux dans votre demande, nous vous invitons à contacter un administrateur Hardis.";
                        }
                    }
                }
            }
        }
        return message;
    }

    @Override
    public List<Devis> rechercherDevis(Long idClient, String statutDevis) {
        //A TESTER
        //Une seule méthode de recherche
        //Envoyer null pour les paramètres non utilisés pour votre recherche
        if (idClient == null && statutDevis == null) {
            //Afficher tous les devis
            return devisFacade.rechercherDevis();
        } else if (idClient != null && statutDevis == null) {
            //Afficher tous les devis d'un client
            Client c = clientFacade.rechercheClient(idClient);
            return devisFacade.rechercherDevis(c);
        } else if (idClient == null && statutDevis != null) {
            //Afficher tous les devis qui ont un statut
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(statut);
        } else {
            //Afficher tous les devis d'un client et un statut
            Client c = clientFacade.rechercheClient(idClient);
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(c, statut);
        }
    }
    
    @Override
    public List<Devis> rechercherDevisEncours(Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        return devisFacade.rechercherDevisEncours(c);
    }
    
    @Override
    public List<Devis> rechercherDevisTermines(Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        return devisFacade.rechercherDevisTermines(c);
    }
    
    @Override
    public DevisNonStandard rechercherDevisNonStandard(Long idDevisNonStandard){
        return devisNonStandardFacade.rechercheDevisNonStandard(idDevisNonStandard);
    }
    
    @Override
    public DevisStandard rechercherDevisStandard(Long idDevisStandard){
        return devisStandardFacade.rechercheDevisStandard(idDevisStandard);
    }
    
    @Override
    public List<HistoriqueUtilisateurDevis> afficherHistoriqueUtilisateurDevis(Long idDevis){
        Devis d = devisFacade.rechercherDevis(idDevis);
        return historiqueUtilisateurDevisFacade.rechercheHistoriqueUtilisateurDevis(d);
    }

    /*GESTION DE LA MESSAGERIE*/
    @Override
    public Conversation creerConversation(Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        return conversationFacade.creerConversation(c);
    }

    @Override
    public Communication creerCommunication(String message, Long idConversation) {
        Conversation conv = conversationFacade.rechercheConversation(idConversation);
        return communicationFacade.creerCommunication(message, conv.getClient(), null, conv);
    }

    @Override
    public List<Conversation> afficherConversations(Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        return conversationFacade.rechercherConversations(c);
    }

    @Override
    public Conversation afficherConversation(Long idConversation) {
        return conversationFacade.rechercheConversation(idConversation);
    }

    @Override
    public List<Communication> afficherCommunications(Long idConversation) {
        Conversation conv = conversationFacade.rechercheConversation(idConversation);
        return communicationFacade.rechercherCommunications(conv);
    }

    /*GESTION DU COMPTE*/
    @Override
    public Client modifierClient(Long id, String nom,
            String prenom, String mail,
            String tel
    ) {
        Client c = clientFacade.rechercheClient(id);
        if (c.getMail().equalsIgnoreCase(mail)) {
            //Si le mail n'a pas changé alors on peut modifier
            return clientFacade.modifierClient(c, nom, prenom, mail, tel);
        } else {
            //Si le mail a changé alors on vérifie qu'il n'est pas déjà utilisé
            Utilisateur u = utilisateurFacade.rechercherUtilisateurParMail(mail);
            if (u == null) {
                //Si pas utilisé alors on peut modifier
                return clientFacade.modifierClient(c, nom, prenom, mail, tel);
            } else {
                //Si déjà utilisé alors on renvoie null pour message erreur
                return null;
            }
        }
    }

    @Override
    public Utilisateur modifierClientMDP(Long id, String ancienMdp,
            String nouveauMdp
    ) {
        Utilisateur u = utilisateurFacade.rechercheUtilisateur(id);
        Utilisateur retour = null;
        try {
            //Vérification si ancien mdp correct
            if (u.getMdp().equals(Helpers.sha1(ancienMdp))) {
                //Si correct alors on peut modifier
                retour = utilisateurFacade.modifierUtilisateurMDP(u, nouveauMdp);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SessionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }

    @Override
    public Client rechercheClient(long id
    ) {
        return clientFacade.rechercheClient(id);

    }

    @Override
    public Devis afficherLeDevis(long id) {
        return devisFacade.find(id);
    }

    @Override
    public void supprimerDevisStandardIncomplet(Long idDevis) {
        devisStandardFacade.supprimerDevisStandard(devisStandardFacade.find(idDevis));
    }

    @Override
    public void supprimerDevisNonStandardIncomplet(Long idDevis) {
        devisNonStandardFacade.supprimerDevisNonStandard(devisNonStandardFacade.find(idDevis));
    }

    @Override
    public List<Consultant> listConsultant(Long idAgence) {
        return consultantFacade.listConsultantParAgence(agenceFacade.find(idAgence));
    }
    
    @Override
    public void creerIntervention(List<Long> disponibilitesEnDemoJournee, Long idDevis) {
        Devis devis = devisFacade.rechercherDevis(idDevis);
        List<Disponibilite> disponibilites = new ArrayList<Disponibilite>();
        for (Long idDispo : disponibilitesEnDemoJournee) {
            disponibilites.add(disponibiliteFacade.find(idDispo));
        }
        
        for (Disponibilite d : disponibilites){
            //Vérification intervention n'existe pas
                Intervention intervention = interventionFacade.rechercheIntervention(d.getUtilisateurHardis(), d.getDateDebut());
                if(intervention==null){
                    interventionFacade.creerIntervention(d.getUtilisateurHardis(), devis, d.getDateDebut());
                }
                disponibiliteFacade.supprimerDisponibilite(d);
        }
    }
       
}
