/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface SessionClientLocal {

    void creerClient();

    void creerPO();
    
}
