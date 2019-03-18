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
import FacadeUtilisateur.DemandeRattachementFacadeLocal;
import FacadeUtilisateur.DisponibiliteFacadeLocal;
import FacadeUtilisateur.EntrepriseFacadeLocal;
import FacadeUtilisateur.InterlocuteurFacadeLocal;
import FacadeUtilisateur.PorteurOffreFacadeLocal;
import FacadeUtilisateur.ReferentLocalFacadeLocal;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.DemandeCreationEntreprise;
import GestionUtilisateur.DemandeRattachement;
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
    private DemandeRattachementFacadeLocal demandeRattachementFacade;

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
    
    public DemandeRattachement transfertCreationEnRattachement(Long idDemande){
        //Méthode pour si lors d'une demande de création d'entreprise, l'entreprise est déjà existante, on transforme la demande de création en demande de rattachement, renvoyée à l'admin Client
        DemandeCreationEntreprise d = demandeCreationEntrepriseFacade.rechercheDemandeCreationEntreprise(idDemande);
        Entreprise e = entrepriseFacade.rechercheEntrepriseSiret(d.getSiret());
        DemandeRattachement dr = demandeRattachementFacade.creerDemandeRattachement(d.getClient(),e);
        demandeCreationEntrepriseFacade.supprimerDemandeCreationEntreprise(d);
        return dr;
    }
    
    @Override
    public Entreprise creerEntreprise(String nom, String siret, String adresseFacturation, long idAgence) {
        //Méthode pour que l'admin crée manuellement une enteprise
        return entrepriseFacade.creerEntreprise(nom, siret, adresseFacturation, agenceFacade.rechercheAgence(idAgence));
    }
    
    @Override
    public Client rattacherClientAdmin(Long idClient, Long idEntreprise) {
        //Méthode pour que l'admin rattache manuellement le Client Admin à une entreprise
        Client c = clientFacade.rechercheClient(idClient);
        Entreprise e = entrepriseFacade.rechercheEntreprise(idEntreprise);
        c = clientFacade.affecterEntreprise(c,e);
        clientFacade.modifierAdmin(c);
        return c;
    }
    
    @Override
    public Client rattacherClient(Long idClient, Long idEntreprise) {
        //Méthode pour que l'admin rattache manuellement les clients à une entreprise
        Client c = clientFacade.rechercheClient(idClient);
        Entreprise e = entrepriseFacade.rechercheEntreprise(idEntreprise);
        c = clientFacade.affecterEntreprise(c,e);
        return c;
    }
    
    public List<Interlocuteur> rechercherInterlocuteur(){
        return interlocuteurFacade.rechercheInterlocuteur();
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
    
    /*GESTION DES COMPTES UTILISATEUR HARDIS*/
    
    
    
}
