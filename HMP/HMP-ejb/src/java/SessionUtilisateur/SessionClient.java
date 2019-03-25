/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import Enum.Helpers;
import Enum.StatutDevis;
import FacadeCatalogue.LivrableFacadeLocal;
import FacadeCatalogue.OffreFacadeLocal;
import FacadeCatalogue.ServiceFacadeLocal;
import FacadeCatalogue.ServiceNonStandardFacadeLocal;
import FacadeCatalogue.ServiceStandardFacadeLocal;
import FacadeDevis.DevisFacadeLocal;
import FacadeDevis.DevisNonStandardFacadeLocal;
import FacadeDevis.DevisStandardFacadeLocal;
import FacadeDevis.HistoriqueUtilisateurDevisFacadeLocal;
import FacadeUtilisateur.AgenceFacadeLocal;
import FacadeUtilisateur.ClientFacadeLocal;
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
import FacadeUtilisateur.InterlocuteurFacadeLocal;
import GestionCatalogue.Livrable;
import GestionCatalogue.ServiceNonStandard;
import GestionDevis.Devis;
import GestionDevis.DevisNonStandard;
import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
import GestionUtilisateur.ReferentLocal;
import GestionUtilisateur.Utilisateur;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SessionClient implements SessionClientLocal {

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
    public String DemandeCreationOuRattachement(Long idClient, String nom, String siret, String adresse, Long idAgence){
        Client c = clientFacade.rechercheClient(idClient);
        Agence a = null;
        if(idAgence!=null){
            a = agenceFacade.rechercheAgence(idAgence);
        }
        Entreprise e = entrepriseFacade.rechercheEntrepriseSiret(siret);
        if(e==null){
            return "hmp/demandeEntreprise/creation/"+creerDemandeEntreprise(idClient, nom, siret, adresse, idAgence).getId();
        }
        else{
            return "hmp/demandeEntreprise/rattachement/"+creerDemandeRattachement(idClient, siret).getId();
        }
    }

    @Override
    public DemandeCreationEntreprise creerDemandeEntreprise(Long idClient, String nom, String siret, String adresse, Long idAgence) {
        Client c = clientFacade.rechercheClient(idClient);
        Agence a = null;
        if(idAgence!=null){
           a = agenceFacade.rechercheAgence(idAgence); 
        }
        DemandeCreationEntreprise e = demandeCreationEntrepriseFacade.creerDemandeCreationEntreprise(nom, siret, adresse, a,c);
        clientFacade.demanderCreationEntreprise(c, e);
        return e;
    }
    
    @Override
    public DemandeRattachement creerDemandeRattachement(Long idClient, String siret){
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
    public List<Interlocuteur> rechercherInterlocuteur(Long idEntreprise){
        Entreprise e = entrepriseFacade.rechercheEntreprise(idEntreprise);
        return interlocuteurFacade.rechercheInterlocuteur(e);
    }
    
    @Override
    public Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone,String mail, String fonction, long idEntreprise) {
        return interlocuteurFacade.creerInterlocuteur(nom, prenom, mail, telephone, fonction, entrepriseFacade.rechercheEntreprise(idEntreprise));
    }
    
    @Override
    public Interlocuteur modifierInterlocuteur(Long idInterlocuteur, String nom, String prenom, String mail, String telephone, String fonction) {
        Interlocuteur i = interlocuteurFacade.rechercheInterlocuteur(idInterlocuteur);
        return interlocuteurFacade.modifierInterlocuteur(i,nom, prenom, mail, telephone, fonction);
    }
    
    @Override
    public Interlocuteur supprimerInterlocuteur(Long idInterlocuteur) {
        Interlocuteur i = interlocuteurFacade.rechercheInterlocuteur(idInterlocuteur);
        return interlocuteurFacade.supprimerInterlocuteur(i);
    }
/*GESTION DES DEVIS*/
    
    @Override
    public List<Offre> rechercherOffres(){
        return offreFacade.rechercheOffresActuelles();
    }
    
    @Override
    public Offre rechercherOffre(Long idOffre){
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
    public List<Livrable> afficherLivrables(Long idService){
        Service service = serviceFacade.rechercherService(idService);
        return livrableFacade.rechercheLivrable(service);
    }

    @Override
    public DevisStandard creerDevisStandard(String commentaireClient, Long idServiceStandard, Long idClient) {
        //ATTENTION DANS LES SERVLETS : LE COMMENTAIRE CLIENT PEUT ETRE NUL
        Client c = clientFacade.rechercheClient(idClient);
        ServiceStandard s = serviceStandardFacade.rechercheServiceStandard(idServiceStandard);
        ReferentLocal referentLocal = referentLocalFacade.rechercheReferentLocal(c.getEntreprise().getAgence(), s.getOffre());
        DevisStandard d = devisStandardFacade.creerDevisStandard(s.getCout(), commentaireClient, s, referentLocal, c.getEntreprise().getAgence(),c);
        historiqueUtilisateurDevisFacade.creerPremierHistoriqueUtilisateurDevis(d, referentLocal);
        return d;
    }
    
    @Override
    public DevisNonStandard creerDevisNonStandard(String commentaireClient, Long idServiceNonStandard, Long idClient) {
        //ATTENTION DANS LES SERVLETS : LE COMMENTAIRE CLIENT PEUT ETRE NUL
        Client c = clientFacade.rechercheClient(idClient);
        ServiceNonStandard s = serviceNonStandardFacade.rechercheServiceNonStandard(idServiceNonStandard);
        ReferentLocal referentLocal = referentLocalFacade.rechercheReferentLocal(c.getEntreprise().getAgence(), s.getOffre());
        DevisNonStandard dns = devisNonStandardFacade.creerDevisNonStandard(s.getCout(), commentaireClient, s, referentLocal, c.getEntreprise().getAgence(),c);
        historiqueUtilisateurDevisFacade.creerPremierHistoriqueUtilisateurDevis(dns, referentLocal);
        return dns;
    }
    
    @Override
    public Devis modifierDevisIncomplet(Long idDevis, String commentaireClient) {
        //ATTENTION DANS LES SERVLETS : LE COMMENTAIRE CLIENT PEUT ETRE NUL
        Devis d = devisFacade.rechercherDevis(idDevis);
        if(d.getStatut()==StatutDevis.Incomplet){
            if(d.getDtype().equalsIgnoreCase("devisstandard")){
                DevisStandard ds = devisStandardFacade.rechercheDevisStandard(idDevis);
                return devisStandardFacade.modifierDevisStandard(ds, commentaireClient);
            }
            else{
                DevisNonStandard dns = devisNonStandardFacade.rechercheDevisNonStandard(idDevis);
                return devisNonStandardFacade.modifierDevisNonStandard(dns, commentaireClient);
            }
        }
        else{
            return null;
        }
    }
    
    @Override
    public List<Devis> rechercherDevis(Long idClient, String statutDevis){
        //A TESTER
        //Une seule méthode de recherche
        //Envoyer null pour les paramètres non utilisés pour votre recherche
        if(idClient==null && statutDevis==null){
            //Afficher tous les devis
            return devisFacade.rechercherDevis();
        }
        else if(idClient!=null && statutDevis==null){
            //Afficher tous les devis d'un client
            Client c = clientFacade.rechercheClient(idClient);
            return devisFacade.rechercherDevis(c);
        }
        else if(idClient==null && statutDevis!=null){
            //Afficher tous les devis qui ont un statut
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(statut);
        }
        else {
            //Afficher tous les devis d'un client et un statut
            Client c = clientFacade.rechercheClient(idClient);
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(c,statut);
        }
    }
    

/*GESTION DU COMPTE*/
    
    @Override
    public Client modifierClient(Long id, String nom, String prenom, String mail, String tel) {
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
    public Utilisateur modifierClientMDP(Long id, String ancienMdp, String nouveauMdp) {
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
    public Client rechercheClient(long id) {
        return clientFacade.rechercheClient(id);
    }
    
}
