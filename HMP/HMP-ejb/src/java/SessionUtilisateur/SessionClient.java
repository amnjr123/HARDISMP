/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import Enum.Helpers;
import FacadeCatalogue.ServiceFacadeLocal;
import FacadeCatalogue.ServiceStandardFacadeLocal;
import FacadeDevis.DevisFacadeLocal;
import FacadeDevis.DevisStandardFacadeLocal;
import FacadeUtilisateur.ClientFacadeLocal;
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
import GestionUtilisateur.Entreprise;
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
    public Entreprise creerEntreprise(Long idClient, String nom, String siret, String adresse, String mdp, Agence a){
        return null;
    }
            
        
    
    /*GESTION DES DEVIS*/
    @Override
    public List<Service> rechercherService(Offre o){
        return serviceFacade.rechercherService(o);
    }
    
    @Override
    public DevisStandard creerDevisStandard(String commentaireClient, Long idServiceStandard, Long idClient){
        Client c = clientFacade.rechercheClient(idClient);
        ServiceStandard s = serviceStandardFacade.rechercheServiceStandard(idServiceStandard);
        ReferentLocal referentLocal = referentLocalFacade.rechercheReferentLocal(c.getEntreprise().getAgence(),s.getOffre()); 
        /*RAJOUTER CLIENT*/
        return devisStandardFacade.creerDevisStandard(s.getCout(),commentaireClient,s,referentLocal,c.getEntreprise().getAgence());
    }
    
    /*GESTION DU COMPTE*/
    @Override
    public Client modifierClient(Long id, String nom, String prenom, String mail, String tel){
        Client c = clientFacade.rechercheClient(id);
        if(c.getMail().equalsIgnoreCase(mail)){
            //Si le mail n'a pas changé alors on peut modifier
            return clientFacade.modifierClient(c, nom, prenom, mail, tel);
        }
        else {
            //Si le mail a changé alors on vérifie qu'il n'est pas déjà utilisé
            Utilisateur u = utilisateurFacade.rechercherUtilisateurParMail(mail);
            if(u==null){
                //Si pas utilisé alors on peut modifier
                return clientFacade.modifierClient(c, nom, prenom, mail, tel);
            }
            else{
                //Si déjà utilisé alors on renvoie null pour message erreur
                return null;
            }
        }
    }
    
    @Override
    public Client modifierClientMDP(Long id, String ancienMdp, String nouveauMdp){
        Client c = clientFacade.rechercheClient(id);
        Client retour = null;
        try {
            //Vérification si ancien mdp correct
            if(c.getMdp().equals(Helpers.sha1(ancienMdp))){
                //Si correct alors on peut modifier
                retour = clientFacade.modifierClientMDP(c, nouveauMdp);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SessionClient.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return retour;
    }
}
