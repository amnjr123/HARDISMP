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
import FacadeDevis.InterventionFacadeLocal;
import FacadeDevis.PropositionFacadeLocal;
import FacadeUtilisateur.AgenceFacadeLocal;
import FacadeUtilisateur.CVFacadeLocal;
import FacadeUtilisateur.ClientFacadeLocal;
import FacadeUtilisateur.ConsultantFacadeLocal;
import FacadeUtilisateur.DisponibiliteFacadeLocal;
import FacadeUtilisateur.PorteurOffreFacadeLocal;
import FacadeUtilisateur.ReferentLocalFacadeLocal;
import FacadeUtilisateur.UtilisateurFacadeLocal;
import FacadeUtilisateur.UtilisateurHardisFacadeLocal;
import GestionCatalogue.Livrable;
import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import GestionCatalogue.ServiceNonStandard;
import GestionCatalogue.ServiceStandard;
import GestionDevis.Devis;
import GestionDevis.DevisNonStandard;
import GestionDevis.DevisStandard;
import GestionDevis.HistoriqueUtilisateurDevis;
import GestionDevis.Intervention;
import GestionDevis.Proposition;
import GestionUtilisateur.CV;
import GestionUtilisateur.Client;
import GestionUtilisateur.Consultant;
import GestionUtilisateur.Disponibilite;
import GestionUtilisateur.PorteurOffre;
import GestionUtilisateur.ReferentLocal;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
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
    private InterventionFacadeLocal interventionFacade;

    @EJB
    private ServiceNonStandardFacadeLocal serviceNonStandardFacade;

    @EJB
    private ServiceStandardFacadeLocal serviceStandardFacade;

    @EJB
    private DisponibiliteFacadeLocal disponibiliteFacade;

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
/*GESTION DES DISPONIBILITES*/
    
    @Override
    public Disponibilite creerDisponibilite(Long idUtilisateurHardis, Date dateDispo,int i){
        //int i = 0 pour la première demi journée (8h 12h) et i=1 pour la deuxième demi-journée (14h 18h)
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        //Disponibilité par demi-journée
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateDispo);
        Disponibilite dispo = null;
        if(i==0){
            //Début de la dispo
            Calendar calDebut = Calendar.getInstance();
            calDebut.setTime(dateDispo);
            calDebut.set(Calendar.MILLISECOND, 0);
            calDebut.set(Calendar.SECOND, 0);
            calDebut.set(Calendar.MINUTE, 0);
            calDebut.set(Calendar.HOUR_OF_DAY, 8);
            Date dateDebut = calDebut.getTime();
            //Fin de la Dispo
            Calendar calFin = Calendar.getInstance();
            calFin.setTime(dateDispo);
            calFin.set(Calendar.MILLISECOND, 0);
            calFin.set(Calendar.SECOND, 0);
            calFin.set(Calendar.MINUTE, 0);
            calFin.set(Calendar.HOUR_OF_DAY, 12);
            Date dateFin = calFin.getTime();
            //On vérifie que la dispo n'existe pas déjà
            dispo = disponibiliteFacade.rechercheDisponibilite(uh,dateDebut);
            if(dispo==null){
                //On vérifie qu'il n'y a pas d'intervention (1 journée) plannifiée à cette date
                Intervention intervention = interventionFacade.rechercheIntervention(uh, dateDebut);
                if(intervention==null){
                    dispo = disponibiliteFacade.creerDisponibilite(dateDebut, dateFin, uh);
                }
                else{
                    return null;
                }
            }else{
                return null;
            }
        }
        
        else if(i==1){
            //Début de la dispo
            Calendar calDebut = Calendar.getInstance();
            calDebut.setTime(dateDispo);
            calDebut.set(Calendar.MILLISECOND, 0);
            calDebut.set(Calendar.SECOND, 0);
            calDebut.set(Calendar.MINUTE, 0);
            calDebut.set(Calendar.HOUR_OF_DAY, 14);
            Date dateDebut = calDebut.getTime();
            //Fin de la Dispo
            Calendar calFin = Calendar.getInstance();
            calFin.setTime(dateDispo);
            calFin.set(Calendar.MILLISECOND, 0);
            calFin.set(Calendar.SECOND, 0);
            calFin.set(Calendar.MINUTE, 0);
            calFin.set(Calendar.HOUR_OF_DAY, 18);
            Date dateFin = calFin.getTime();
            //On vérifie que la dispo n'existe pas déjà
            dispo = disponibiliteFacade.rechercheDisponibilite(uh,dateDebut);
            if(dispo==null){
                //On vérifie qu'il n'y a pas d'intervention (1 journée) plannifiée à cette date
                Intervention intervention = interventionFacade.rechercheIntervention(uh, dateDebut);
                if(intervention==null){
                    dispo = disponibiliteFacade.creerDisponibilite(dateDebut, dateFin, uh);
                }
                else{
                    return null;
                }
            }else{
                return null;
            }
        }
        return dispo;
    }
    
    @Override
    public Disponibilite supprimerDisponibilite(Long idDispo){
        Disponibilite dispo = disponibiliteFacade.rechercheDisponibilite(idDispo);
        return disponibiliteFacade.supprimerDisponibilite(dispo);
    }
    
    @Override
    public List<Disponibilite> afficherDisponibilites(Long idUtilisateur) {
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateur);
        return disponibiliteFacade.rechercheDisponibilites(uh);
    }
    
    @Override
    public List<Intervention> afficherInterventions(Long idUtilisateur) {
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateur);
        return interventionFacade.rechercheInterventions(uh);
    }
    
    
/*GESTION DU CATALOGUE*/
    
    @Override
    public List<Offre> afficherOffres(){
        return offreFacade.rechercheOffre();
    }
    
    @Override
    public Offre afficheOffre(Long id){
        return offreFacade.rechercheOffre(id);
    }
    
    @Override
    public List<ServiceStandard> afficherServicesStandards(Long idOffre){
        Offre o = offreFacade.rechercheOffre(idOffre);
        return serviceStandardFacade.rechercherServiceStandard(o);
    } 
    
    @Override
    public List<ServiceNonStandard> afficherServicesNonStandards(Long idOffre){
        Offre o = offreFacade.rechercheOffre(idOffre);
        return serviceNonStandardFacade.rechercherServiceNonStandard(o);
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
    
    @Override
    public DevisNonStandard envoyerDevisNonStandard(Long idDevisNonStandard){ 
        DevisNonStandard d = devisNonStandardFacade.rechercheDevisNonStandard(idDevisNonStandard);
        //Vérification s'il y a au moins une proposition commerciale
        if(d.getPropositions().size() > 0){
            //Si oui alors on vérifie le plafond de délégation de l'utilisateur en charge du devis
            UtilisateurHardis uh = d.getUtilisateurHardis();
            if(uh.getDtype()=="PorteurOffre"){
                PorteurOffre po = (PorteurOffre) uh;
                return devisNonStandardFacade.envoyerDevisNonStandard(d);
            }
            else if(uh.getDtype()=="ReferentLocal"){
                ReferentLocal rl = (ReferentLocal) uh;
                if(rl.getPlafondDelegation()>=d.getMontant()){
                    return devisNonStandardFacade.envoyerDevisNonStandard(d);
                }
                else{
                    return null;
                }
            }
            else if(uh.getDtype()=="Consultant"){
                Consultant c = (Consultant) uh;
                if(c.getPlafondDelegation()>=d.getMontant()){
                    return devisNonStandardFacade.envoyerDevisNonStandard(d);
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }
    
    
    @Override
    public Proposition creerProposition(Date dateDebutValidite, Date dateFinValidite, String cheminDocument, Long idUtilisateurHardis, Long idDevisNonStandard ){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        DevisNonStandard dns = devisNonStandardFacade.rechercheDevisNonStandard(idDevisNonStandard);
        return propositionFacade.creerProposition(dateDebutValidite, dateFinValidite, cheminDocument, uh, dns);
    }
    
    @Override
    public void transfererDevisNonStandard(Long idDevisNonStandard, Long idUtilisateurHardis){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        DevisNonStandard dns = devisNonStandardFacade.rechercheDevisNonStandard(idDevisNonStandard);
        devisNonStandardFacade.transfererDevisNonStandard(dns, uh);
        HistoriqueUtilisateurDevis ancienHistorique = historiqueUtilisateurDevisFacade.rechercheDernierHistoriqueUtilisateurDevis(dns);
        historiqueUtilisateurDevisFacade.creerSuiteHistoriqueUtilisateurDevis(ancienHistorique, uh);
    }
    
    
}
