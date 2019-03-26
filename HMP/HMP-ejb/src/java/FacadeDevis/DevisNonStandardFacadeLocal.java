/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionCatalogue.ServiceNonStandard;
import GestionDevis.DevisNonStandard;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.ReferentLocal;
import GestionUtilisateur.UtilisateurHardis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface DevisNonStandardFacadeLocal {

    void create(DevisNonStandard devisNonStandard);

    void edit(DevisNonStandard devisNonStandard);

    void remove(DevisNonStandard devisNonStandard);

    DevisNonStandard find(Object id);

    List<DevisNonStandard> findAll();

    List<DevisNonStandard> findRange(int[] range);

    int count();

    DevisNonStandard modifierDevisNonStandard(DevisNonStandard d, float montant);

    DevisNonStandard transfererDevisNonStandard(DevisNonStandard d, UtilisateurHardis uh);

    DevisNonStandard validerDevisNonStandard(DevisNonStandard d);

    DevisNonStandard refuserDevisNonStandard(DevisNonStandard d, String motifRefus);

    DevisNonStandard acompteDevisNonStandard(DevisNonStandard d);

    DevisNonStandard supprimerDevisNonStandard(DevisNonStandard d);

    DevisNonStandard rechercheDevisNonStandard(long id);

    List<DevisNonStandard> rechercheDevisNonStandard();

    DevisNonStandard envoyerDevisNonStandard(DevisNonStandard d);

    DevisNonStandard modifierDevisNonStandard(DevisNonStandard d, String commentaireClient, ReferentLocal rl, Agence agence);

    DevisNonStandard creerDevisNonStandard(ServiceNonStandard serviceNonStandard, Client c);
    
}
