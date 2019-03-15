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
import FacadeUtilisateur.DisponibiliteFacadeLocal;
import FacadeUtilisateur.EntrepriseFacadeLocal;
import FacadeUtilisateur.InterlocuteurFacadeLocal;
import FacadeUtilisateur.PorteurOffreFacadeLocal;
import FacadeUtilisateur.ReferentLocalFacadeLocal;
import FacadeUtilisateur.UtilisateurFacadeLocal;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author pedago
 */
@Stateless
public class SessionAdministrateur implements SessionAdministrateurLocal {

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

    @Override
    public Entreprise creerEntreprise(String nom, String siret, String adresseFacturation, String mdpRattachement, long idAgence) {
        return entrepriseFacade.creerEntreprise(nom, siret, siret, nom, agenceFacade.rechercheAgence(idAgence));
    }

    @Override
    public Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone, String fonction, long idEntreprise) {
        return interlocuteurFacade.creerInterlocuteur(nom, prenom, nom, telephone, fonction, entrepriseFacade.rechercheEntreprise(idEntreprise));
    }

    
    
    
}
