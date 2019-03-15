/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import GestionUtilisateur.Agence;
import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
import javax.ejb.Local;

/**
 *
 * @author pedago
 */
@Local
public interface SessionAdministrateurLocal {

    Agence creerAgence(String localisation);

    Entreprise creerEntreprise(String nom, String siret, String adresseFacturation, String mdpRattachement, long idAgence);

    Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone, String fonction, long idEntreprise);
    
}
