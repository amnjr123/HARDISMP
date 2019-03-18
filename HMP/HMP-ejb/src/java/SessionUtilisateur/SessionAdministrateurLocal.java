/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pedago
 */
@Local
public interface SessionAdministrateurLocal {

    Agence creerAgence(String localisation);

    Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone, String fonction, long idEntreprise);

    Entreprise creerEntreprise(String nom, String siret, String adresseFacturation, long idAgence);

    Entreprise validerDemandeCreationEntreprise(Long idDemande);

    Interlocuteur modifierInterlocuteur(Long idInterlocuteur, String nom, String prenom, String telephone, String fonction);

    Interlocuteur supprimerInterlocuteur(Long idInterlocuteur);

    List<Interlocuteur> rechercherInterlocuteur(Long idEntreprise);

    Client rattacherClientAdmin(Long idClient, Long idEntreprise);

    Client rattacherClient(Long idClient, Long idEntreprise);
    
}
