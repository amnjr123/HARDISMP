<%@page import="GestionCatalogue.Offre"%>
<%@page import="GestionCatalogue.ServiceStandard"%>
<%@page import="GestionCatalogue.ServiceNonStandard"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listeServicesStandards" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listeServicesNonStandards" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="offre" scope="request" class="GestionCatalogue.Offre"></jsp:useBean>
<jsp:include page="header.jsp"/>
<%Collection<ServiceNonStandard> listServicesNonStandards = listeServicesNonStandards;%>
<%Collection<ServiceStandard> listServicesStandards = listeServicesStandards;%>
<%Offre o = offre;%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Offre <%=o.getLibelle()%></h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Services associés</h1>
                    <div class="btn-toolbar">
                            <button  class="btn btn-sm btn-success " data-toggle="modal" data-target="#serviceStandard">
                                <span data-feather="folder-plus"></span>
                                Ajouter un service Standard
                            </button>
                            <button  style="margin-left:1em" class="btn btn-sm btn-success " data-toggle="modal" data-target="#serviceNonStandard">
                                <span data-feather="folder-plus"></span>
                                Ajouter un service Personnalisé
                            </button>
                    </div>
            </div>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Description</th>
                            <th scope="col">Lieu</th>
                            <th scope="col">Coût</th>
                            <th scope="col">Frais inclus ou non</th>
                            <th scope="col">Conditions</th>
                            <th scope="col">Délai de relance</th>
                            <th scope="col">Début de validité</th>
                            <th scope="col">Fin de validité</th>
                            <th scope="col">Jours Consultant Senior</th>
                            <th scope="col">Jours Consultant Confirmé</th>
                            <th scope="col">Jours Consultant Junior</th>
                            <th scope="col">Heures Ateliers/Entretiens</th>
                            <th scope="col">Heures support téléphonique</th>
                            <th scope="col">Détails description</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int i = 0;
                            for (ServiceStandard st : listServicesStandards) {%>                                      
                        <tr>
                            <td><%=st.getId()%></td>
                            <td><%=st.getNom()%></td>
                            <td><%=st.getDescriptionService()%></td>
                            <td><%=st.getLieuIntervention()%></td>
                            <td><%=st.getCout()%></td>
                            <td><%=st.getFraisInclus()%></td>
                            <td><%=st.getConditions()%></td>
                            <td><%=st.getDelaiRelance()%></td>
                            <td><%=st.getDateDebutValidite()%></td>
                            <td><%=st.getDateFinValidite()%></td>
                            <td><%=st.getNbrJoursConsultantSenior()%></td>
                            <td><%=st.getNbrJoursConsultantConfirme()%></td>
                            <td><%=st.getNbrJoursConsultantJunior()%></td>
                            <td><%=st.getNbrHeuresAtelierEntretienPrevu()%></td>
                            <td><%=st.getNbrHeuresSupportTel()%></td>
                            <td><%=st.getDescriptionPrestation()%></td>
                            <td><div class="dropdown">
                                    <td><a data-target="#serviceStandard" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                        <a href="#" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a></td>
                                </div></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Description</th>
                            <th scope="col">Lieu</th>
                            <th scope="col">Coût</th>
                            <th scope="col">Frais inclus ou non</th>
                            <th scope="col">Conditions</th>
                            <th scope="col">Délai de relance</th>
                            <th scope="col">Début de validité</th>
                            <th scope="col">Fin de validité</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int n = 0;
                            for (ServiceNonStandard st : listServicesNonStandards) {%>                                      
                        <tr>
                            <td><%=st.getId()%></td>
                            <td><%=st.getNom()%></td>
                            <td><%=st.getDescriptionService()%></td>
                            <td><%=st.getLieuIntervention()%></td>
                            <td><%=st.getCout()%></td>
                            <td><%=st.getFraisInclus()%></td>
                            <td><%=st.getConditions()%></td>
                            <td><%=st.getDelaiRelance()%></td>
                            <td><%=st.getDateDebutValidite()%></td>
                            <td><%=st.getDateFinValidite()%></td>
                            <td><div class="dropdown">
                                    <td><a data-target="#serviceNonStandard" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                        <a href="#" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a></td>
                                </div></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="modal fade" id="serviceStandard" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouveau service standard</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nom" class="sr-only">Nom du service</label>
                            <input name="nom" type="text" id="nom" class="form-control" placeholder="Nom du service" required autofocus>
                            <div class="invalid-feedback">
                                Le nom du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="sr-only">Description</label>
                            <input name="description" type="text" id="description" class="form-control" placeholder="Description du service" required autofocus>
                            <div class="invalid-feedback">
                                Une description du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <select name="lieu" class="custom-select">
                                <option disabled selected>Lieu</option>
                                <option value="Agence_Hardis">Agence Hardis</option>
                                <option value="Site_Client">Site Client</option>
                                <option value="Mixte">Mixte</option>
                            </select>
                            <div class="invalid-feedback">
                                Le lieu de l'intervention est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cout" class="sr-only">Coût</label>
                            <input name="cout" type="text" id="cout" class="form-control" placeholder="Montant du service" required autofocus>
                            <div class="invalid-feedback">
                                Le prix du service est obligatoire.
                            </div>
                        </div>
                        <!-- Default unchecked -->
                        <div class="form-group">
                            <input type="radio" class="custom-control-input" id="defaultUnchecked" name="fraisInclus">
                            <label class="custom-control-label" for="defaultUnchecked">Oui</label>
                            <!-- Default checked -->
                            <input type="radio" class="custom-control-input" id="defaultChecked" name="fraisInclus" checked>
                            <label for="defaultChecked">Non</label>
                        </div>
                        <div class="form-group">
                            <label for="conditions" class="sr-only">Conditions</label>
                            <input name="conditions" type="text" id="conditions" class="form-control" placeholder="conditions" required autofocus>
                            <div class="invalid-feedback">
                                Le conditions sont obligatoires.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="delai" class="sr-only">Délais de relance (en jours)</label>
                            <input name="delai" pattern="[0-9][0-9][0-9]" type="tel" id="delai" class="form-control" placeholder="delai" required autofocus>
                            <div class="invalid-feedback">
                                Le délai de relance est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nbjours" class="sr-only">Nombre de jours requis :</label>
                            <input name="senior" pattern="[0-9][0-9][0-9]" type="tel" id="senior" class="form-control" placeholder="Consultant senior" required autofocus>
                            <input name="confirme" pattern="[0-9][0-9][0-9]" type="tel" id="confirme" class="form-control" placeholder="Consultant confirmé" required autofocus>
                            <input name="junior" pattern="[0-9][0-9][0-9]" type="tel" id="junior" class="form-control" placeholder="Consultant junior" required autofocus>
                            <div class="invalid-feedback">
                                Le nombre de jours pour chaque consultant sont obligatoires.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nbjours" class="sr-only">Nombre d'heures :</label>
                            <input name="atelier" pattern="[0-9][0-9][0-9]" type="tel" id="atelier" class="form-control" placeholder="Ateliers et entretiens" required autofocus>
                            <input name="supporttel" pattern="[0-9][0-9][0-9]" type="tel" id="supporttel" class="form-control" placeholder="Support téléphonique" required autofocus>
                            <div class="invalid-feedback">
                                Les nombres d'heures sont obligatoires.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descriptiondetail" class="sr-only">Description détaillée </label>
                            <input name="descriptiondetail" type="text" id="descriptiondetail" class="form-control" placeholder="Description détaillée du service" required autofocus>
                            <div class="invalid-feedback">
                                Les détails sont obligatoires.
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success">Créer le service</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="idOffre" value="<%=o.getId()%>">
                        <input type="hidden" name="action" value="creerServiceStandard">
                    </div>
                </form>
            </div>
        </div>

    </div>
                        
    <div class="modal fade" id="serviceNonStandard" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouveau service non standard</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nom" class="sr-only">Nom du service</label>
                            <input name="nom" type="text" id="nom" class="form-control" placeholder="Nom du service" required autofocus>
                            <div class="invalid-feedback">
                                Le nom du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="sr-only">Description</label>
                            <input name="description" type="text" id="description" class="form-control" placeholder="Description du service" required autofocus>
                            <div class="invalid-feedback">
                                Une description du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <select name="lieu" class="custom-select">
                                <option disabled selected>Lieu</option>
                                <option value="Agence_Hardis">Agence Hardis</option>
                                <option value="Site_Client">Site Client</option>
                                <option value="Mixte">Mixte</option>
                            </select>
                            <div class="invalid-feedback">
                                Le lieu de l'intervention est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cout" class="sr-only">Coût</label>
                            <input name="cout" type="text" id="cout" class="form-control" placeholder="Montant du service" required autofocus>
                            <div class="invalid-feedback">
                                Le prix du service est obligatoire.
                            </div>
                        </div>
                        <!-- Default unchecked -->
                        <div class="form-group">
                            <input type="radio" class="custom-control-input" id="defaultUnchecked" name="fraisInclus">
                            <label class="custom-control-label" for="defaultUnchecked">Oui</label>
                            <!-- Default checked -->
                            <input type="radio" class="custom-control-input" id="defaultChecked" name="fraisInclus" checked>
                            <label for="defaultChecked">Non</label>
                        </div>
                        <div class="form-group">
                            <label for="conditions" class="sr-only">Conditions</label>
                            <input name="conditions" type="text" id="conditions" class="form-control" placeholder="conditions" required autofocus>
                            <div class="invalid-feedback">
                                Le conditions sont obligatoires.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="delai" class="sr-only">Délais de relance (en jours)</label>
                            <input name="delai" pattern="[0-9][0-9][0-9]" type="tel" id="delai" class="form-control" placeholder="delai" required autofocus>
                            <div class="invalid-feedback">
                                Le délai de relance est obligatoire.
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success">Créer le service</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="idOffre" value="<%=o.getId()%>">
                        <input type="hidden" name="action" value="creerServiceNonStandard">
                    </div>
                </form>
            </div>
        </div>

    </div>

</main>


<jsp:include page="footer.jsp"/>
