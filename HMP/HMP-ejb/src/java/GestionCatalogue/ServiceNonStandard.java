/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCatalogue;

import GestionDevis.DevisNonStandard;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author 5151882
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ServiceNonStandard extends Service implements Serializable {
//Clé étrangères
    @OneToMany(mappedBy = "ServiceNonStandard")
    private List<DevisNonStandard> devisNonStandards;

    public List<DevisNonStandard> getDevisNonStandards() {
        return devisNonStandards;
    }

    public void setDevisNonStandards(List<DevisNonStandard> devisNonStandards) {
        this.devisNonStandards = devisNonStandards;
    }
    
}
