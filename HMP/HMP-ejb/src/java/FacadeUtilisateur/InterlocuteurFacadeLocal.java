/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface InterlocuteurFacadeLocal {

    void create(Interlocuteur interlocuteur);

    void edit(Interlocuteur interlocuteur);

    void remove(Interlocuteur interlocuteur);

    Interlocuteur find(Object id);

    List<Interlocuteur> findAll();

    List<Interlocuteur> findRange(int[] range);

    int count();

    Interlocuteur modifierInterlocuteur(Interlocuteur i, String nom, String prenom, String mail, String telephone, String fonction);

    Interlocuteur creerInterlocuteur(String nom, String prenom, String mail, String telephone, String fonction, Entreprise entreprise);

    Interlocuteur supprimerInterlocuteur(Interlocuteur i);

    Interlocuteur rechercheInterlocuteur(long id);

    List<Interlocuteur> rechercheInterlocuteur();
    
}
