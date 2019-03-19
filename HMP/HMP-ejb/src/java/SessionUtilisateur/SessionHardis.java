/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import Enum.Helpers;
import FacadeCatalogue.OffreFacadeLocal;
import FacadeCatalogue.ServiceFacadeLocal;
import FacadeUtilisateur.AgenceFacadeLocal;
import FacadeUtilisateur.CVFacadeLocal;
import FacadeUtilisateur.ConsultantFacadeLocal;
import FacadeUtilisateur.PorteurOffreFacadeLocal;
import FacadeUtilisateur.ReferentLocalFacadeLocal;
import FacadeUtilisateur.UtilisateurFacadeLocal;
import FacadeUtilisateur.UtilisateurHardisFacadeLocal;
import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import GestionUtilisateur.CV;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import java.io.UnsupportedEncodingException;
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
}
