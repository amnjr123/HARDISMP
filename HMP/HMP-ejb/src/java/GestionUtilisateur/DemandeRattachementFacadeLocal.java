/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionUtilisateur;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface DemandeRattachementFacadeLocal {

    void create(DemandeRattachement demandeRattachement);

    void edit(DemandeRattachement demandeRattachement);

    void remove(DemandeRattachement demandeRattachement);

    DemandeRattachement find(Object id);

    List<DemandeRattachement> findAll();

    List<DemandeRattachement> findRange(int[] range);

    int count();
    
}
