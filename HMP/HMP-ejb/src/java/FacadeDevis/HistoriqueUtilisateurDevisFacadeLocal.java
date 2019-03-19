/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.Devis;
import GestionDevis.HistoriqueUtilisateurDevis;
import GestionUtilisateur.UtilisateurHardis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface HistoriqueUtilisateurDevisFacadeLocal {

    void create(HistoriqueUtilisateurDevis historiqueUtilisateurDevis);

    void edit(HistoriqueUtilisateurDevis historiqueUtilisateurDevis);

    void remove(HistoriqueUtilisateurDevis historiqueUtilisateurDevis);

    HistoriqueUtilisateurDevis find(Object id);

    List<HistoriqueUtilisateurDevis> findAll();

    List<HistoriqueUtilisateurDevis> findRange(int[] range);

    int count();

    HistoriqueUtilisateurDevis creerPremierHistoriqueUtilisateurDevis(Devis devis, UtilisateurHardis uh);

    HistoriqueUtilisateurDevis creerSuiteHistoriqueUtilisateurDevis(HistoriqueUtilisateurDevis ancienHistorique, UtilisateurHardis nouvelUtilisateur);

    List<HistoriqueUtilisateurDevis> rechercheHistoriqueUtilisateurDevis(UtilisateurHardis uh);

    List<HistoriqueUtilisateurDevis> rechercheHistoriqueUtilisateurDevis(Devis devis);

    List<HistoriqueUtilisateurDevis> rechercheHistoriqueUtilisateurDevis();

    HistoriqueUtilisateurDevis rechercheHistoriqueUtilisateurDevis(long id);

    HistoriqueUtilisateurDevis rechercheDernierHistoriqueUtilisateurDevis(Devis devis);
    
}
