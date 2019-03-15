/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDevis;

import GestionCatalogue.ServiceNonStandard;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;


/**
 *
 * @author 5151882
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class DevisNonStandard extends Devis implements Serializable {
//Clés étrangères
    @ManyToOne
    private ServiceNonStandard ServiceNonStandard;

    public ServiceNonStandard getServiceNonStandard() {
        return ServiceNonStandard;
    }

    public void setServiceNonStandard(ServiceNonStandard ServiceNonStandard) {
        this.ServiceNonStandard = ServiceNonStandard;
    }

    @OneToMany(mappedBy = "devisNonStandard")
    private List<Proposition> propositions;

    public List<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Proposition> propositions) {
        this.propositions = propositions;
    }
}
