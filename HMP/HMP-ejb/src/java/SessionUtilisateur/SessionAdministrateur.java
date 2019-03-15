/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import FacadeCatalogue.OffreFacadeLocal;
import FacadeUtilisateur.AgenceFacadeLocal;
import FacadeUtilisateur.CVFacadeLocal;
import FacadeUtilisateur.ClientFacadeLocal;
import FacadeUtilisateur.ConsultantFacadeLocal;
import FacadeUtilisateur.DemandeCreationEntrepriseFacadeLocal;
import FacadeUtilisateur.DisponibiliteFacadeLocal;
import FacadeUtilisateur.EntrepriseFacadeLocal;
import FacadeUtilisateur.InterlocuteurFacadeLocal;
import FacadeUtilisateur.PorteurOffreFacadeLocal;
import FacadeUtilisateur.ReferentLocalFacadeLocal;
import GestionUtilisateur.Agence;
import GestionUtilisateur.DemandeCreationEntreprise;
import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author pedago
 */
@Stateless
public class SessionAdministrateur implements SessionAdministrateurLocal {

    @EJB
    private DemandeCreationEntrepriseFacadeLocal demandeCreationEntrepriseFacade;

    @EJB
    private ReferentLocalFacadeLocal referentLocalFacade;

    @EJB
    private PorteurOffreFacadeLocal porteurOffreFacade;

    @EJB
    private OffreFacadeLocal offreFacade;

    @EJB
    private InterlocuteurFacadeLocal interlocuteurFacade;

    @EJB
    private EntrepriseFacadeLocal entrepriseFacade;

    @EJB
    private DisponibiliteFacadeLocal disponibiliteFacade;

    @EJB
    private ConsultantFacadeLocal consultantFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private CVFacadeLocal cVFacade;

    @EJB
    private AgenceFacadeLocal agenceFacade;

    @Override
    public Agence creerAgence(String localisation) {
        return agenceFacade.creerAgence(localisation);
    }

    /*GESTION ENTREPRISE*/
    public List<DemandeCreationEntreprise> rechercheDemandeCreationEntreprise(){
        return demandeCreationEntrepriseFacade.rechercheDemandeCreationEntreprise();
    }
    
    public Entreprise entrepriseExistante(String siret){
        return entrepriseFacade.rechercheEntrepriseSiret(siret);
    }
    
    @Override
    public Entreprise validerDemandeCreationEntreprise(Long idDemande) {
        DemandeCreationEntreprise d = demandeCreationEntrepriseFacade.rechercheDemandeCreationEntreprise(idDemande);
        //Vérification si entreprise déjà existante
        Entreprise e = entrepriseFacade.rechercheEntrepriseSiret(d.getSiret());
        if(e==null){
            // Si non alors on la crée
            e = entrepriseFacade.creerEntreprise(d.getNom(), d.getSiret(), d.getAdresseFacturation(), d.getAgence());
            //On affecte l'entreprise au client
            clientFacade.affecterEntreprise(d.getClient(), e);
            //Le client devient client Admin de l'enteprise
            clientFacade.modifierAdmin(d.getClient());
            //On supprime la demande de création
            demandeCreationEntrepriseFacade.supprimerDemandeCreationEntreprise(d);
        }
        //Si oui, on renvoie null pour message erreur
        return e;
    }
    
    public DemandeCreationEntreprise refuserDemandeCreationEntreprise(Long idDemande) {
        DemandeCreationEntreprise d = demandeCreationEntrepriseFacade.rechercheDemandeCreationEntreprise(idDemande);
        return demandeCreationEntrepriseFacade.supprimerDemandeCreationEntreprise(d);
    }
    
    
    
    @Override
    public Entreprise creerEntreprise(String nom, String siret, String adresseFacturation, long idAgence) {
        return entrepriseFacade.creerEntreprise(nom, siret, adresseFacturation, agenceFacade.rechercheAgence(idAgence));
    }
    
    @Override
    public Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone, String fonction, long idEntreprise) {
        return interlocuteurFacade.creerInterlocuteur(nom, prenom, nom, telephone, fonction, entrepriseFacade.rechercheEntreprise(idEntreprise));
    }
    
}
