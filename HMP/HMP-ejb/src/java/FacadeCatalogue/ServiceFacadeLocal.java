/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface ServiceFacadeLocal {

    void create(Service service);

    void edit(Service service);

    void remove(Service service);

    Service find(Object id);

    List<Service> findAll();

    List<Service> findRange(int[] range);

    int count();

    List<Service> rechercherService(Offre o);

    List<Service> rechercherService();

    List<Service> rechercheServicesActuels(Offre o);

    List<Service> rechercheServicesAnciens(Offre o);

    List<Service> rechercheServicesActuels();

    List<Service> rechercheServicesAnciens();
    
}
