/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import GestionUtilisateur.Client;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import javax.ejb.Local;

/**
 *
 * @author pedago
 */
@Local
public interface SessionLocal {

    Utilisateur authentification(String mail, String mdp);

    String getTypeUser(Utilisateur utilisateur);

    Client rechercheClient(long id);

    UtilisateurHardis rechercheUtilisateurHardis(Long id);

    void test();

    Client creerClient(String nom, String prenom, String mail, String mdp, String telephone);

    Utilisateur rechercherUtilisateurExistant(String mail);
    
}
