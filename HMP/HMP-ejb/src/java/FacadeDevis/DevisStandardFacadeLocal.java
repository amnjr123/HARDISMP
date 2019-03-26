/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionCatalogue.ServiceStandard;
import GestionDevis.DevisStandard;
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
public interface DevisStandardFacadeLocal {

    void create(DevisStandard devisStandard);

    void edit(DevisStandard devisStandard);

    void remove(DevisStandard devisStandard);

    DevisStandard find(Object id);

    List<DevisStandard> findAll();

    List<DevisStandard> findRange(int[] range);

    int count();

    DevisStandard modifierDevisStandard(DevisStandard d, float montant);

    DevisStandard transfererDevisStandard(DevisStandard d, UtilisateurHardis uh);

    DevisStandard envoyerDevisStandard(DevisStandard d);

    DevisStandard validerDevisStandard(DevisStandard d);

    DevisStandard refuserDevisStandard(DevisStandard d, String motifRefus);

    DevisStandard acompteDevisStandard(DevisStandard d);

    DevisStandard supprimerDevisStandard(DevisStandard d);

    DevisStandard rechercheDevisStandard(long id);

    List<DevisStandard> rechercheDevisStandard();

    DevisStandard modifierDevisStandard(DevisStandard d, String commentaireClient, ReferentLocal rl, Agence agence);

    DevisStandard creerDevisStandard(ServiceStandard serviceStandard, Client c);
    
}
