/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Agence;
import GestionUtilisateur.Entreprise;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface EntrepriseFacadeLocal {

    void create(Entreprise entreprise);

    void edit(Entreprise entreprise);

    void remove(Entreprise entreprise);

    Entreprise find(Object id);

    List<Entreprise> findAll();

    List<Entreprise> findRange(int[] range);

    int count();

    Entreprise supprimerEntreprise(Entreprise e);

    Entreprise rechercheEntreprise(long id);

    List<Entreprise> rechercheEntreprise();

    Entreprise rechercheEntrepriseSiret(String siret);

    Entreprise rechercheEntrepriseNom(String nom);

    Entreprise modifierEntreprise(Entreprise e, String nom, String siret, String adresse, Agence a);

    Entreprise creerEntreprise(String nom, String siret, String adresse, String mdp, Agence a);
    
}
