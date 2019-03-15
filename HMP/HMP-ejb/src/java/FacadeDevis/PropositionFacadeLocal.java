/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.DevisNonStandard;
import GestionDevis.Proposition;
import GestionUtilisateur.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface PropositionFacadeLocal {

    void create(Proposition proposition);

    void edit(Proposition proposition);

    void remove(Proposition proposition);

    Proposition find(Object id);

    List<Proposition> findAll();

    List<Proposition> findRange(int[] range);

    int count();

    Proposition creerProposition(Date dateDebutValidite, Date dateFinValidite, String cheminDocument, UtilisateurHardis uh, DevisNonStandard devisNonStandard);

    Proposition modifierProposition(Proposition p, Date dateDebutValidite, Date dateFinValidite, String cheminDocument, UtilisateurHardis uh);

    Proposition supprimerProposition(Proposition p);

    Proposition rechercheProposition(long id);

    List<Proposition> rechercheProposition();
    
}
