/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import GestionCatalogue.Offre;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface OffreFacadeLocal {

    void create(Offre offre);

    void edit(Offre offre);

    void remove(Offre offre);

    Offre find(Object id);

    List<Offre> findAll();

    List<Offre> findRange(int[] range);

    int count();

    Offre creerOffre(String libelle, Date dateDebutValidite, Date dateFinValidite);

    Offre modifierOffre(Offre o, String libelle, Date dateDebutValidite, Date dateFinValidite);

    Offre supprimerOffre(Offre o);

    Offre rechercheOffre(long id);

    List<Offre> rechercheOffre();
    
}
