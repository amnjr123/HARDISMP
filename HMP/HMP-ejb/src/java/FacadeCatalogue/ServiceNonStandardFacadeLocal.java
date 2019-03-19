/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import Enum.LieuIntervention;
import GestionCatalogue.Offre;
import GestionCatalogue.ServiceNonStandard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface ServiceNonStandardFacadeLocal {

    void create(ServiceNonStandard serviceNonStandard);

    void edit(ServiceNonStandard serviceNonStandard);

    void remove(ServiceNonStandard serviceNonStandard);

    ServiceNonStandard find(Object id);

    List<ServiceNonStandard> findAll();

    List<ServiceNonStandard> findRange(int[] range);

    int count();

    ServiceNonStandard creerServiceNonStandard(String nom, String description, LieuIntervention lieu, float cout, boolean fraisInclus, String conditions, int delaiRelance, Offre o);

    ServiceNonStandard modifierServiceNonStandard(ServiceNonStandard ancienService, String nom, String description, LieuIntervention lieu, float cout, boolean fraisInclus, String conditions, int delaiRelance, Offre o);

    ServiceNonStandard rechercheServiceNonStandard(long id);

    List<ServiceNonStandard> rechercheServiceNonStandard();

    List<ServiceNonStandard> rechercherServiceNonStandard(Offre o);
    
}
