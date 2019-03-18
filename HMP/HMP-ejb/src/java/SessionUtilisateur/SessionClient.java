/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import Enum.Helpers;
import FacadeCatalogue.OffreFacadeLocal;
import FacadeCatalogue.ServiceFacadeLocal;
import FacadeCatalogue.ServiceStandardFacadeLocal;
import FacadeDevis.DevisFacadeLocal;
import FacadeDevis.DevisStandardFacadeLocal;
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
import GestionDevis.Devis;
import GestionDevis.DevisStandard;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.DemandeCreationEntreprise;
import GestionUtilisateur.DemandeRattachement;
import FacadeUtilisateur.DemandeRattachementFacadeLocal;
import FacadeUtilisateur.InterlocuteurFacadeLocal;
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
        return agenceFacade.rechercheAgence();
    }
    
    @Override
    public DemandeCreationEntreprise rechercherDemandeCreationEntreprise(Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        return demandeCreationEntrepriseFacade.rechercheDemandeCreationEntrepriseClient(c);
    }
    

    @Override
    public DemandeCreationEntreprise creerDemandeEntreprise(Long idClient, String nom, String siret, String adresse, Long idAgence) {
        Client c = clientFacade.rechercheClient(idClient);
        Agence a = agenceFacade.rechercheAgence(idAgence);
        DemandeCreationEntreprise e = demandeCreationEntrepriseFacade.creerDemandeCreationEntreprise(nom, siret, adresse, a,c);
        return e;
    }
    
    @Override
    public List<Interlocuteur> rechercherInterlocuteur(Long idEntreprise){
        Entreprise e = entrepriseFacade.rechercheEntreprise(idEntreprise);
        return interlocuteurFacade.rechercheInterlocuteur(e);
    }
    
    @Override
    public Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone, String fonction, long idEntreprise) {
        return interlocuteurFacade.creerInterlocuteur(nom, prenom, nom, telephone, fonction, entrepriseFacade.rechercheEntreprise(idEntreprise));
    }
    
    @Override
    public Interlocuteur modifierInterlocuteur(Long idInterlocuteur, String nom, String prenom, String telephone, String fonction) {
        Interlocuteur i = interlocuteurFacade.rechercheInterlocuteur(idInterlocuteur);
        return interlocuteurFacade.modifierInterlocuteur(i,nom, prenom, nom, telephone, fonction);
    }
    
    @Override
    public Interlocuteur supprimerInterlocuteur(Long idInterlocuteur) {
        Interlocuteur i = interlocuteurFacade.rechercheInterlocuteur(idInterlocuteur);
        return interlocuteurFacade.supprimerInterlocuteur(i);
    }
    

    @Override
    public List<DemandeRattachement> rechercherDemandeRattachementEntreprise(Long idEntreprise) {
        Entreprise e = entrepriseFacade.rechercheEntreprise(idEntreprise);
        return demandeRattachementFacade.rechercherDemandeRattachement(e);
    }
    
    @Override
    public DemandeRattachement rechercherDemandeRattachementClient(Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        return demandeRattachementFacade.rechercherDemandeRattachement(c);
    }
    
    @Override
    public DemandeRattachement creerDemandeRattachement(Long idClient, String siret){
        Client c = clientFacade.rechercheClient(idClient);
        Entreprise e = entrepriseFacade.rechercheEntrepriseSiret(siret);
        DemandeRattachement d = demandeRattachementFacade.creerDemandeRattachement(c, e);
        return d;
    }
    
    @Override
    public DemandeRattachement validerDemandeRattachement(Long idDemande) {
        DemandeRattachement d = demandeRattachementFacade.rechercherDemandeRattachement(idDemande);
        clientFacade.affecterEntreprise(d.getClient(), d.getEntreprise());
        demandeRattachementFacade.supprimerDemandeRattachement(d);
        return d;//A tester si on peut renvoyer une instance supprimée de la bdd sans provoquer de bug
    }
    
    @Override
    public DemandeRattachement refuserDemandeRattachement(Long idDemande) {
        DemandeRattachement d = demandeRattachementFacade.rechercherDemandeRattachement(idDemande);
        demandeRattachementFacade.supprimerDemandeRattachement(d);
        return d;//A tester si on peut renvoyer une instance supprimée de la bdd sans provoquer de bug
    }
    
    
    /*GESTION DES DEVIS*/
    public List<Offre> rechercherOffres(){
        return offreFacade.rechercheOffresActuelles();
    }
    
    @Override
    public List<Service> rechercherService(Offre o) {
        return serviceFacade.rechercherService(o);
    }

    @Override
    public DevisStandard creerDevisStandard(String commentaireClient, Long idServiceStandard, Long idClient) {
        Client c = clientFacade.rechercheClient(idClient);
        ServiceStandard s = serviceStandardFacade.rechercheServiceStandard(idServiceStandard);
        ReferentLocal referentLocal = referentLocalFacade.rechercheReferentLocal(c.getEntreprise().getAgence(), s.getOffre());
        return devisStandardFacade.creerDevisStandard(s.getCout(), commentaireClient, s, referentLocal, c.getEntreprise().getAgence(),c);
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
}
