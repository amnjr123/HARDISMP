/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import Enum.ProfilTechnique;
import GestionCatalogue.Offre;
import GestionUtilisateur.Agence;
import GestionUtilisateur.PorteurOffre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface PorteurOffreFacadeLocal {

    void create(PorteurOffre porteurOffre);

    void edit(PorteurOffre porteurOffre);

    void remove(PorteurOffre porteurOffre);

    PorteurOffre find(Object id);

    List<PorteurOffre> findAll();

    List<PorteurOffre> findRange(int[] range);

    int count();

    PorteurOffre supprimerConsultant(PorteurOffre c);

    PorteurOffre recherchePorteurOffre(long id);

    List<PorteurOffre> recherchePorteurOffre();

    PorteurOffre creerPorteurOffre(String nom, String prenom, String mail, String tel, ProfilTechnique profil, Offre offre, Agence agence);

    PorteurOffre modifierPorteurOffre(PorteurOffre po, String nom, String prenom, String mail, String tel, ProfilTechnique profil, boolean actifInactif, Offre offre, Agence agence);

    PorteurOffre modifierPorteurOffre(PorteurOffre po, String mail, String tel, boolean actifInactif);

    PorteurOffre creerPO(PorteurOffre po);
    
}
