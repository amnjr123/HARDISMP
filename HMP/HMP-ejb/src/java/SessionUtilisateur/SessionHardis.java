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
import FacadeDevis.DevisFacadeLocal;
import FacadeDevis.DevisNonStandardFacadeLocal;
import FacadeDevis.DevisStandardFacadeLocal;
import FacadeDevis.HistoriqueUtilisateurDevisFacadeLocal;
import FacadeDevis.PropositionFacadeLocal;
import FacadeUtilisateur.AgenceFacadeLocal;
import FacadeUtilisateur.CVFacadeLocal;
import FacadeUtilisateur.ClientFacadeLocal;
import FacadeUtilisateur.ConsultantFacadeLocal;
import FacadeUtilisateur.PorteurOffreFacadeLocal;
import FacadeUtilisateur.ReferentLocalFacadeLocal;
import FacadeUtilisateur.UtilisateurFacadeLocal;
import FacadeUtilisateur.UtilisateurHardisFacadeLocal;
import GestionCatalogue.Livrable;
import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import GestionDevis.Devis;
import GestionDevis.DevisNonStandard;
import GestionDevis.DevisStandard;
import GestionDevis.HistoriqueUtilisateurDevis;
import GestionDevis.Proposition;
import GestionUtilisateur.CV;
import GestionUtilisateur.Client;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author 5151882
 */
@Stateless
public class SessionHardis implements SessionHardisLocal {

    @EJB
    private HistoriqueUtilisateurDevisFacadeLocal historiqueUtilisateurDevisFacade;

    @EJB
    private PropositionFacadeLocal propositionFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private DevisStandardFacadeLocal devisStandardFacade;

    @EJB
    private DevisNonStandardFacadeLocal devisNonStandardFacade;

    @EJB
    private LivrableFacadeLocal livrableFacade;

    @EJB
    private CVFacadeLocal cVFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private UtilisateurHardisFacadeLocal utilisateurHardisFacade;

    @EJB
    private ReferentLocalFacadeLocal referentLocalFacade;

    @EJB
    private ConsultantFacadeLocal consultantFacade;

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    @EJB
    private PorteurOffreFacadeLocal porteurOffreFacade;

    @EJB
    private OffreFacadeLocal offreFacade;

    @EJB
    private AgenceFacadeLocal agenceFacade;

    @Override
    public String getTypeUser(Utilisateur utilisateur) {
        return utilisateurFacade.getDType(utilisateur);
    }
    
/*GESTION DU COMPTE*/
    
    @Override
    public UtilisateurHardis modifierCompte(Long id, String mail, String tel, boolean actifInactif){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(id);
        if (uh.getMail().equalsIgnoreCase(mail)) {
            //Si le mail n'a pas changé alors on peut modifier
            return utilisateurHardisFacade.modifierUtilisateurHardis(uh, mail, tel, actifInactif);
        } else {
            //Si le mail a changé alors on vérifie qu'il n'est pas déjà utilisé
            Utilisateur u = utilisateurFacade.rechercherUtilisateurParMail(mail);
            if (u == null) {
                //Si pas utilisé alors on peut modifier
                return utilisateurHardisFacade.modifierUtilisateurHardis(uh, mail, tel, actifInactif);
            } else {
                //Si déjà utilisé alors on renvoie null pour message erreur
                return null;
            }
        }
    }
    
    @Override
    public Utilisateur modifierUtilisateurMDP(Long id, String ancienMdp, String nouveauMdp) {
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
    
/*GESTION DU CATALOGUE*/
    
    @Override
    public List<Offre> rechercherOffres(){
        return offreFacade.rechercheOffresActuelles();
    }
    
    @Override
    public List<Service> rechercherService(Offre o) {
        return serviceFacade.rechercherService(o);
    }
    
    @Override
    public List<Livrable> afficherLivrables(Long idService){
        Service service = serviceFacade.rechercherService(idService);
        return livrableFacade.rechercheLivrable(service);
    }
    
/*GESTION DES CV*/
    
    @Override
    public CV creerCV(String chemin, Long idUtilisateur, Long idOffre){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateur);
        Offre o = offreFacade.rechercheOffre(idOffre);
        return cVFacade.creerCV(chemin, uh, o);
    }
    
    @Override
    public CV creerCV(String chemin, Long idUtilisateurHardis){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        return cVFacade.creerCV(chemin, uh);
    }
    
    @Override
    public CV modifierCV(Long idCV, String chemin){
        CV cv = cVFacade.rechercheCV(idCV);
        return cVFacade.modifierCV(cv, chemin);
    }
    
    @Override
    public CV supprimerCV(Long idCV){
        CV cv = cVFacade.rechercheCV(idCV);
        return cVFacade.supprimerCV(cv);
    }
    
    @Override
    public List<CV> afficherCV(){
        return cVFacade.rechercheCV();
    }
    
    @Override
    public List<CV> afficherCVOffre(Long idOffre){
        Offre o = offreFacade.rechercheOffre(idOffre);
        return cVFacade.rechercherCV(o);
    }
    
    @Override
    public List<CV> afficherCVUtilisateur(Long idUtilisateurHardis){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        return cVFacade.rechercherCV(uh);
    }
    
    @Override
    public CV afficherCVOffreUtilisateur(Long idUtilisateurHardis, Long idOffre){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        Offre o = offreFacade.rechercheOffre(idOffre);
        return cVFacade.rechercherCV(o, uh);
    }
    
/*GESTION DES DEVIS*/
    
    @Override
    public List<Devis> rechercherDevis(Long idUtilisateurHardis, Long idClient, String statutDevis){
        //A TESTER
        //Une seule méthode de recherche
        //Envoyer null pour les paramètres non utilisés pour votre recherche
        if(idUtilisateurHardis==null && idClient==null && statutDevis==null){
            //Afficher tous les devis
            return devisFacade.rechercherDevis();
        }
        else if(idUtilisateurHardis!=null && idClient==null && statutDevis==null){
            //Afficher tous les devis d'un utilisateur
            UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
            return devisFacade.rechercherDevis(uh);
        }
        else if(idUtilisateurHardis==null && idClient!=null && statutDevis==null){
            //Afficher tous les devis d'un client
            Client c = clientFacade.rechercheClient(idClient);
            return devisFacade.rechercherDevis(c);
        }
        else if(idUtilisateurHardis==null && idClient==null && statutDevis!=null){
            //Afficher tous les devis qui ont un statut
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(statut);
        }
        else if(idUtilisateurHardis!=null && idClient!=null && statutDevis==null){
            //Afficher tous les devis d'un utilisateur et un client
            UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
            Client c = clientFacade.rechercheClient(idClient);
            return devisFacade.rechercherDevis(uh,c);
        }
        else if(idUtilisateurHardis!=null && idClient==null && statutDevis!=null){
            //Afficher tous les devis d'un utilisateur et un statut
            UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(uh,statut);
        }
        else if(idUtilisateurHardis==null && idClient!=null && statutDevis!=null){
            //Afficher tous les devis d'un client et un statut
            Client c = clientFacade.rechercheClient(idClient);
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(c,statut);
        }
        else {
            //Afficher tous les devis d'un utilisateur pour un client avec un statut
            UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
            Client c = clientFacade.rechercheClient(idClient);
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(uh,c,statut);
        }
    }
    
    public DevisStandard envoyerDevisStandard(Long idDevisStandard){
            DevisStandard d = devisStandardFacade.rechercheDevisStandard(idDevisStandard);
            return devisStandardFacade.envoyerDevisStandard(d);
    }
    
    public Proposition creerProposition(Date dateDebutValidite, Date dateFinValidite, String cheminDocument, Long idUtilisateurHardis, Long idDevisNonStandard ){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        DevisNonStandard dns = devisNonStandardFacade.rechercheDevisNonStandard(idDevisNonStandard);
        return propositionFacade.creerProposition(dateDebutValidite, dateFinValidite, cheminDocument, uh, dns);
    }
    
    public void transfererDevisNonStandard(Long idDevisNonStandard, Long idUtilisateurHardis){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        DevisNonStandard dns = devisNonStandardFacade.rechercheDevisNonStandard(idDevisNonStandard);
        devisNonStandardFacade.transfererDevisNonStandard(dns, uh);
        HistoriqueUtilisateurDevis ancienHistorique = historiqueUtilisateurDevisFacade.rechercheDernierHistoriqueUtilisateurDevis(dns);
        historiqueUtilisateurDevisFacade.creerSuiteHistoriqueUtilisateurDevis(ancienHistorique, uh);
    }
}
