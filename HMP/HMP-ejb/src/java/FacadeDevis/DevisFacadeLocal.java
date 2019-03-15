/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.Devis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface DevisFacadeLocal {

    void create(Devis devis);

    void edit(Devis devis);

    void remove(Devis devis);

    Devis find(Object id);

    List<Devis> findAll();

    List<Devis> findRange(int[] range);

    int count();
    
}
