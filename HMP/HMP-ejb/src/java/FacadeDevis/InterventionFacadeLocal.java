/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.Devis;
import GestionDevis.Intervention;
import GestionUtilisateur.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface InterventionFacadeLocal {

    void create(Intervention intervention);

    void edit(Intervention intervention);

    void remove(Intervention intervention);

    Intervention find(Object id);

    List<Intervention> findAll();

    List<Intervention> findRange(int[] range);

    int count();

    Intervention creerIntervention(UtilisateurHardis uh, Devis devis, Date dateIntervention);

    Intervention modifierIntervention(Intervention i, UtilisateurHardis uh, Date dateIntervention);

    Intervention supprimerIntervention(Intervention i);

    Intervention rechercheIntervention(long id);

    List<Intervention> rechercheIntervention();
    
}
