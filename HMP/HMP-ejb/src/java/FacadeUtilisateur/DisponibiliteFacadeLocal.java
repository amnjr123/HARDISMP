/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Disponibilite;
import GestionUtilisateur.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface DisponibiliteFacadeLocal {

    void create(Disponibilite disponibilite);

    void edit(Disponibilite disponibilite);

    void remove(Disponibilite disponibilite);

    Disponibilite find(Object id);

    List<Disponibilite> findAll();

    List<Disponibilite> findRange(int[] range);

    int count();

    Disponibilite creerDisponibilite(Date dateDebut, Date dateFin, UtilisateurHardis uh);

    Disponibilite modifierDisponibilite(Disponibilite d, Date dateDebut, Date dateFin);

    Disponibilite supprimerDisponibilite(Disponibilite d);

    Disponibilite rechercheDisponibilite(long id);

    List<Disponibilite> rechercheDisponibilite();
    
}
