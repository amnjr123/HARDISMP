/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface UtilisateurFacadeLocal {

    void create(Utilisateur utilisateur);

    void edit(Utilisateur utilisateur);

    void remove(Utilisateur utilisateur);

    Utilisateur find(Object id);

    List<Utilisateur> findAll();

    List<Utilisateur> findRange(int[] range);

    int count();

    public Utilisateur authentification(String mail, String mdp);

    public String getDType(Utilisateur u);

    Utilisateur rechercheUtilisateur(long id);

    Utilisateur rechercherUtilisateurParMail(String mail);

    Utilisateur modifierUtilisateurMDP(Utilisateur u, String mdp);
    
}
