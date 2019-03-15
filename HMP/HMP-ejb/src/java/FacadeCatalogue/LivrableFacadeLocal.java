/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import GestionCatalogue.Livrable;
import GestionCatalogue.Service;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface LivrableFacadeLocal {

    void create(Livrable livrable);

    void edit(Livrable livrable);

    void remove(Livrable livrable);

    Livrable find(Object id);

    List<Livrable> findAll();

    List<Livrable> findRange(int[] range);

    int count();

    Livrable creerLivrable(String libelle, Service service);

    Livrable modifierLivrable(Livrable l, String libelle);

    Livrable supprimerLivrable(Livrable l);

    Livrable rechercheLivrable(long id);

    List<Livrable> rechercheLivrable();
    
}
