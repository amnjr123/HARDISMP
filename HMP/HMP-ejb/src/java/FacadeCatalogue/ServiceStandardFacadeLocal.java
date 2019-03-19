/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import Enum.LieuIntervention;
import GestionCatalogue.Offre;
import GestionCatalogue.ServiceStandard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface ServiceStandardFacadeLocal {

    void create(ServiceStandard serviceStandard);

    void edit(ServiceStandard serviceStandard);

    void remove(ServiceStandard serviceStandard);

    ServiceStandard find(Object id);

    List<ServiceStandard> findAll();

    List<ServiceStandard> findRange(int[] range);

    int count();

    ServiceStandard creerServiceStandard(String nom, String descriptionService, LieuIntervention lieu, float cout, boolean fraisInclus, String conditions, int delaiRelance, Offre o, int nbJoursConsultantSenior, int nbJoursConsultantConfirme, int nbJoursConsultantJunior, int nbHeuresAtelierEntretien, int nbHeuresSupportTel, String description);

    ServiceStandard modifierServiceStandard(ServiceStandard ancienService, String nom, String descriptionService, LieuIntervention lieu, float cout, boolean fraisInclus, String conditions, int delaiRelance, Offre o, int nbJoursConsultantSenior, int nbJoursConsultantConfirme, int nbJoursConsultantJunior, int nbHeuresAtelierEntretien, int nbHeuresSupportTel, String descriptionPrestation);

    ServiceStandard rechercheServiceStandard(long id);

    List<ServiceStandard> rechercheServiceStandard();

    List<ServiceStandard> rechercherServiceStandard(Offre o);
    
}
