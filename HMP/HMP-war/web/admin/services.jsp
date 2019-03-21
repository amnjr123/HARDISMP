<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
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
                <h1 class="h2">Services standards</h1>
                <div class="btn-toolbar">
                    <button  class="btn btn-sm btn-success " data-toggle="modal" data-target="#serviceStandard">
                        <span data-feather="folder-plus"></span>
                        Ajouter un service Standard
                    </button>
                </div>
            </div>
        </div>
        <%--Warning Or Sucess--%>   
        <% String error = (String) request.getAttribute("msgError");
            if (request.getAttribute("msgError") != null) {%>
        <div class="alert alert-danger alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attention !</strong>&nbsp;<%=(error)%>.
        </div>
        <%}%>

        <% String success = (String) request.getAttribute("msgSuccess");
            if (request.getAttribute("msgSuccess") != null) {%>
        <div class="alert alert-success alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <%=(success)%>.
        </div>
        <%}%>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Description</th>
                            <th scope="col">Lieu</th>
                            <th scope="col">Co�t</th>
                            <th scope="col" class="text-center">Actif ou obsol�te</th>
                            <th scope="col" class="text-center">Voir le d�tail</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int i = 0;
                            Calendar cal = Calendar.getInstance();
                            cal.set(Calendar.MILLISECOND, 0);
                            cal.set(Calendar.SECOND, 0);
                            cal.set(Calendar.MINUTE, 0);
                            cal.set(Calendar.HOUR_OF_DAY, 0);
                            cal.set(Calendar.DAY_OF_MONTH, 1);
                            cal.set(Calendar.MONTH, 0);
                            cal.set(Calendar.YEAR, 2099);
                            Date date = cal.getTime();
                            for (ServiceStandard st : listServicesStandards) {%>                                      
                        <tr>
                            <td><%=st.getId()%></td>
                            <td><%=st.getNom()%></td>
                            <td><%=st.getDescriptionService()%></td>
                            <td><%if (st.getLieuIntervention().toString().equals("Agence_Hardis")) {%>Agence Hardis<%} else if (st.getLieuIntervention().toString().equals("Site_Client")) {%>Site Client<%} else {%>Mixte<%}%></td>
                            <td><%=st.getCout()%></td>
                            <td class="text-center"><%if (st.getDateFinValidite().after(date)) {%><i data-feather="check-circle" style="color:green"></i><%} else {%><i data-feather="x" style="color:red"></i><%}%></td>
                            <td class="text-center"><a data-toggle="modal" data-target="#detailServiceStandard<%=(st.getId())%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="list"></i></a></td>
                            <td>
                                <div class="dropdown">
                                    <a href="#" data-toggle="modal" data-target="#modificationserviceStandard<%=(st.getId())%>" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                    <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=supprimerService&idServiceStandard=<%=st.getId()%>" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a>
                                </div>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Services Non Standards</h1>
                <div class="btn-toolbar">
                    <button class="btn btn-sm btn-success " data-toggle="modal" data-target="#serviceNonStandard">
                        <span data-feather="folder-plus"></span>
                        Ajouter un service Personnalis�
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
                            <th scope="col">Co�t</th>
                            <th class="text-center" scope="col">Actif ou obsol�te</th>
                            <th class="text-center" scope="col">Voir le d�tail</th>
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
                            <td><%if (st.getLieuIntervention().toString().equals("Agence_Hardis")) {%>Agence Hardis<%} else if (st.getLieuIntervention().toString().equals("Site_Client")) {%>Site Client<%} else {%>Mixte<%}%></td>
                            <td><%=st.getCout()%></td>
                            <td class="text-center"><%if (st.getDateFinValidite().after(date)) {%><i data-feather="check-circle" style="color:green"></i><%} else {%><i data-feather="x" style="color:red"></i><%}%></td>
                            <td class="text-center"><a data-toggle="modal" data-target="#detailServiceNonStandard<%=(st.getId())%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="list"></i></a></td>
                            <td>
                                <div class="dropdown">
                                    <a href="#" data-toggle="modal" data-target="#modificationserviceStandard<%=(st.getId())%>" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                    <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=supprimerService&idServiceNonStandard=<%=st.getId()%>" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a>
                                </div>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
        <div class="modal fade" id="serviceStandard" tabindex="-2" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">

                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouveau service standard</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nom" >Nom du service *</label>
                            <input name="nom" type="text" id="nom" class="form-control" placeholder="Nom du service" required autofocus>
                            <div class="invalid-feedback">
                                Le nom du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description">Description *</label>
                            <textarea rows="2"  name="description" type="text" id="description" class="form-control" placeholder="Description du service" required autofocus></textarea>
                            <div class="invalid-feedback">
                                Une description du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description">Lieu intervention *</label>
                            <select name="lieu" class="custom-select">
                                <option disabled selected>Choisir le lieu</option>
                                <option value="Agence_Hardis">Agence Hardis</option>
                                <option value="Site_Client">Site Client</option>
                                <option value="Mixte">Mixte</option>
                            </select>
                            <div class="invalid-feedback">
                                Le lieu de l'intervention est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cout">Co�t</label>
                            <input name="cout" pattern="[0-9]+" maxlength="7" id="cout" class="form-control" placeholder="Montant du service" required autofocus>
                            <div class="invalid-feedback">
                                Le prix du service est obligatoire.
                            </div>
                        </div>
                        <!-- Default unchecked -->
                        <div class="form-group">
                            <label for="fraisInclus">Frais inclus ?</label><br>
                            <div class="text-center">
                                <input type="radio" style=""  id="defaultUnchecked" name="fraisInclus" value="true">
                                <label for="defaultUnchecked">Oui</label>
                                <!-- Default checked -->
                                <input style="margin-left: 2em" type="radio" id="defaultChecked" name="fraisInclus" checked value="false">
                                <label for="defaultChecked">Non</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="delai">D�lai de relance (en jours)</label>
                            <input name="delai" pattern="[0-9]+" maxlength="3" type="tel" id="delai" class="form-control" placeholder="d�lai" required autofocus>
                            <div class="invalid-feedback">
                                Le d�lai de relance est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nbjours">Nombre de jours requis :</label>
                            <input name="senior" pattern="[0-9]+" maxlength="3" type="tel" id="senior" class="form-control mb-2" placeholder="Consultant senior" required autofocus>
                            <input name="confirme" pattern="[0-9]+" maxlength="3" type="tel" id="confirme" class="form-control mb-2" placeholder="Consultant confirm�" required autofocus>
                            <input name="junior" pattern="[0-9]+" maxlength="3" type="tel" id="junior" class="form-control mb-2" placeholder="Consultant junior" required autofocus>
                            <div class="invalid-feedback">
                                Le nombre de jours pour chaque consultant sont obligatoires.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nbjours">Nombre d'heures :</label>
                            <input name="atelier" pattern="[0-9]+" maxlength="3" type="tel" id="atelier" class="form-control mb-2" placeholder="Ateliers et entretiens" required autofocus>
                            <input name="supporttel" pattern="[0-9]+" maxlength="3" type="tel" id="supporttel" class="form-control mb-2" placeholder="Support t�l�phonique" required autofocus>
                            <div class="invalid-feedback">
                                Les nombres d'heures sont obligatoires.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descriptiondetail" >Description d�taill�e </label>

                            <textarea rows="2" name="descriptiondetail" type="text" id="descriptiondetail" class="form-control" placeholder="Description d�taill�e du service" required autofocus></textarea>
                            <div class="invalid-feedback">
                                Les d�tails sont obligatoires.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="conditions" >Conditions *</label>
                            <textarea rows="2" name="conditions" type="text" id="conditions" class="form-control" placeholder="conditions" required autofocus></textarea>
                            <div class="invalid-feedback">
                                Le conditions sont obligatoires.
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer ">
                        <button  class="btn btn-sm btn-success " data-toggle="modal" data-target="#creerLivrableSS">
                            <span data-feather="folder-plus"></span>
                            Suivant
                        </button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="idOffre" value="<%=o.getId()%>">
                        <input type="hidden" name="action" value="creerServiceStandard">
                    </div>
                </div>
            </div>

        </div>
        <div class="modal fade" id="creerLivrableSS" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">

                <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ajouter des livrables au services</h5>
                            <button type="button" class="btn btn-success" id="addLivrableSS"><i data-feather="plus"></i></button>
                            <button type="button" class="btn btn-danger" id="removeLivrableSS"><i data-feather="minus"></i></button>    
                        </div>
                        <div class="modal-body">
                            <div id="zoneAjoutInputsSS" class="form-group">
                                <input name="livrable" type="text" id="livrableSS" class="form-control mb-2" placeholder="Renseigner un livrable" required autofocus>
                            </div>
                        </div>
                        <div class="modal-footer ">
                            <button type="submit" class="btn btn-success">Valider</button>
                            <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        </div>
                </div>
            </div>

        </div>
    </form>

<form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">                     
    <div class="modal fade" id="serviceNonStandard" tabindex="-2" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouveau service non standard</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nom">Nom du service *</label>
                            <input name="nom" type="text" id="nom" class="form-control" placeholder="Nom du service" required autofocus>
                            <div class="invalid-feedback">
                                Le nom du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description">Description *</label>
                            <textarea rows="2"  name="description" type="text" id="description" class="form-control" placeholder="Description du service" required autofocus></textarea>
                            <div class="invalid-feedback">
                                Une description du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description">Lieu intervention *</label>
                            <select name="lieu" class="custom-select">
                                <option disabled selected>Choisir le lieu</option>
                                <option value="Agence_Hardis">Agence Hardis</option>
                                <option value="Site_Client">Site Client</option>
                                <option value="Mixte">Mixte</option>
                            </select>
                            <div class="invalid-feedback">
                                Le lieu de l'intervention est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cout">Co�t</label>
                            <input name="cout" pattern="[0-9]+" maxlength="7" id="cout" class="form-control" placeholder="Montant du service" required autofocus>
                            <div class="invalid-feedback">
                                Le prix du service est obligatoire.
                            </div>
                        </div>
                        <!-- Default unchecked -->
                        <div class="form-group">
                            <label for="fraisInclus">Frais inclus ?</label><br>
                            <div class="text-center">
                                <input type="radio" style="" id="defaultUnchecked" name="fraisInclus" value="true">
                                <label for="defaultUnchecked">Oui</label>
                                <!-- Default checked -->
                                <input style="margin-left: 2em" type="radio" id="defaultChecked" name="fraisInclus" checked value="false">
                                <label for="defaultChecked">Non</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="delai">D�lai de relance (en jours)</label>
                            <input name="delai" pattern="[0-9]+" maxlength="3" type="tel" id="delai" class="form-control" placeholder="d�lai" required autofocus>
                            <div class="invalid-feedback">
                                Le d�lai de relance est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="conditions" >Conditions *</label>
                            <textarea rows="2" name="conditions" type="text" id="conditions" class="form-control" placeholder="conditions" required autofocus></textarea>
                            <div class="invalid-feedback">
                                Le conditions sont obligatoires.
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer ">
                        <button  class="btn btn-sm btn-success " data-toggle="modal" data-target="#creerLivrableSNS">
                            <span data-feather="folder-plus"></span>
                            Suivant
                        </button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="idOffre" value="<%=o.getId()%>">
                        <input type="hidden" name="action" value="creerServiceStandard">
                    </div>
                </div>
            </div>

        </div>
        <div class="modal fade" id="creerLivrableSNS" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">

                <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ajouter des livrables au services</h5>
                            <button type="button" class="btn btn-success" id="addLivrableSNS"><i data-feather="plus"></i></button>
                            <button type="button" class="btn btn-danger" id="removeLivrableSNS"><i data-feather="minus"></i></button>    
                        </div>
                        <div class="modal-body">
                            <div id="zoneAjoutInputsSNS" class="form-group">
                                <input name="livrable" type="text" id="livrableSNS" class="form-control mb-2" placeholder="Renseigner un livrable" required autofocus>
                            </div>
                        </div>
                        <div class="modal-footer ">
                            <button type="submit" class="btn btn-success">Valider</button>
                            <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        </div>
                </div>
            </div>

        </div>
    </form>

    <%
        for (ServiceStandard st : listServicesStandards) {
    %>
    <div class="modal fade" id="modificationserviceStandard<%=(st.getId())%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modifier le service standard</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nom" >Nom du service *</label>
                            <input name="nom" type="text" id="nom" class="form-control" placeholder="Nom du service" required autofocus value="<%=(st.getNom())%>">
                            <div class="invalid-feedback">
                                Le nom du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description">Description *</label>
                            <textarea rows="2"  name="description" type="text" id="description" class="form-control" placeholder="Description du service" required autofocus><%=(st.getDescriptionService())%></textarea>
                            <div class="invalid-feedback">
                                Une description du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lieu">Lieu intervention *</label>
                            <select name="lieu" class="custom-select" >
                                <option <%if (st.getLieuIntervention().toString().equals("Agence_Hardis")) {%>selected<%}%> value="Agence_Hardis">Agence Hardis</option>
                                <option <%if (st.getLieuIntervention().toString().equals("Site_Client")) {%>selected<%}%>  value="Site_Client">Site Client</option>
                                <option <%if (st.getLieuIntervention().toString().equals("Mixte")) {%>selected<%}%> value="Mixte">Mixte</option>
                            </select>
                            <div class="invalid-feedback">
                                Le lieu de l'intervention est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cout">Co�t</label>
                            <input name="cout" pattern="[0-9]+" maxlength="7" id="cout" class="form-control" placeholder="Montant du service" required autofocus value="<%=(st.getCout())%>">
                            <div class="invalid-feedback">
                                Le prix du service est obligatoire.
                            </div>
                        </div>
                        <!-- Default unchecked -->
                        <div class="form-group">
                            <label for="fraisInclus">Frais inclus ?</label><br>
                            <div class="text-center">
                                <input type="radio" style="" <%if (st.getFraisInclus() == true) {%>checked<%}%> id="defaultUnchecked" name="fraisInclus" value="true">
                                <label for="defaultUnchecked">Oui</label>
                                <!-- Default checked -->
                                <input style="margin-left: 2em" type="radio" <%if (st.getFraisInclus() == false) {%>checked<%}%> id="defaultChecked" name="fraisInclus" value="false">
                                <label for="defaultChecked">Non</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="delai">D�lai de relance (en jours)</label>
                            <input name="delai" pattern="[0-9]+" maxlength="3" type="tel" id="delai" class="form-control" placeholder="d�lai" required autofocus value="<%=(st.getDelaiRelance())%>">
                            <div class="invalid-feedback">
                                Le d�lai de relance est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nbjours">Nombre de jours requis :</label>
                            <input name="senior" pattern="[0-9]+" maxlength="3" type="tel" id="senior" class="form-control mb-2" placeholder="Consultant senior" required autofocus value="<%=(st.getNbrJoursConsultantSenior())%>">
                            <input name="confirme" pattern="[0-9]+" maxlength="3" type="tel" id="confirme" class="form-control mb-2" placeholder="Consultant confirm�" required autofocus value="<%=(st.getNbrJoursConsultantConfirme())%>">
                            <input name="junior" pattern="[0-9]+" maxlength="3" type="tel" id="junior" class="form-control mb-2" placeholder="Consultant junior" required autofocus value="<%=(st.getNbrJoursConsultantJunior())%>">
                            <div class="invalid-feedback">
                                Le nombre de jours pour chaque consultant sont obligatoires.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nbjours">Nombre d'heures :</label>
                            <input name="atelier" pattern="[0-9]+" maxlength="3" type="tel" id="atelier" class="form-control mb-2" placeholder="Ateliers et entretiens" required autofocus value="<%=(st.getNbrHeuresAtelierEntretienPrevu())%>">
                            <input name="supporttel" pattern="[0-9]+" maxlength="3" type="tel" id="supporttel" class="form-control mb-2" placeholder="Support t�l�phonique" required autofocus value="<%=(st.getNbrHeuresSupportTel())%>">
                            <div class="invalid-feedback">
                                Les nombres d'heures sont obligatoires.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descriptiondetail" >Description d�taill�e </label>
                            <textarea rows="2" name="descriptiondetail" type="text" id="descriptiondetail" class="form-control" placeholder="Description d�taill�e du service" required autofocus><%=(st.getDescriptionPrestation())%></textarea>
                            <div class="invalid-feedback">
                                Les d�tails sont obligatoires.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="conditions" >Conditions *</label>
                            <textarea rows="2" name="conditions" type="text" id="conditions" class="form-control" placeholder="conditions" required autofocus><%=(st.getConditions())%></textarea>
                            <div class="invalid-feedback">
                                Les conditions sont obligatoires.
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success">Modifier le service</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="idOffre" value="<%=o.getId()%>">
                        <input type="hidden" name="idServiceStandard" value="<%=st.getId()%>">
                        <input type="hidden" name="action" value="modifierServiceStandard">
                    </div>
                </form>
            </div>
        </div>

    </div>
    <%

        }
    %>
    <%
        for (ServiceNonStandard st : listServicesNonStandards) {
    %>
    <div class="modal fade" id="modificationserviceNonStandard<%=(st.getId())%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modifier le service non standard</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nom" >Nom du service *</label>
                            <input name="nom" type="text" id="nom" class="form-control" placeholder="Nom du service" required autofocus value="<%=(st.getNom())%>">
                            <div class="invalid-feedback">
                                Le nom du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description">Description *</label>
                            <textarea rows="2"  name="description" type="text" id="description" class="form-control" placeholder="Description du service" required autofocus><%=(st.getDescriptionService())%></textarea>
                            <div class="invalid-feedback">
                                Une description du service est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lieu">Lieu intervention *</label>
                            <select name="lieu" class="custom-select" >
                                <option <%if (st.getLieuIntervention().toString().equals("Agence_Hardis")) {%>selected<%}%> value="Agence_Hardis">Agence Hardis</option>
                                <option <%if (st.getLieuIntervention().toString().equals("Site_Client")) {%>selected<%}%>  value="Site_Client">Site Client</option>
                                <option <%if (st.getLieuIntervention().toString().equals("Mixte")) {%>selected<%}%> value="Mixte">Mixte</option>
                            </select>
                            <div class="invalid-feedback">
                                Le lieu de l'intervention est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cout">Co�t</label>
                            <input name="cout" pattern="[0-9]+" maxlength="7" id="cout" class="form-control" placeholder="Montant du service" required autofocus value="<%=(st.getCout())%>">
                            <div class="invalid-feedback">
                                Le prix du service est obligatoire.
                            </div>
                        </div>
                        <!-- Default unchecked -->
                        <div class="form-group">
                            <label for="fraisInclus">Frais inclus ?</label><br>
                            <div class="text-center">
                                <input type="radio" style="" <%if (st.getFraisInclus() == true) {%>checked<%}%> id="defaultUnchecked" name="fraisInclus" value="true">
                                <label for="defaultUnchecked">Oui</label>
                                <!-- Default checked -->
                                <input style="margin-left: 2em" type="radio" <%if (st.getFraisInclus() == false) {%>checked<%}%> id="defaultChecked" name="fraisInclus" value="false">
                                <label for="defaultChecked">Non</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="delai">D�lai de relance (en jours)</label>
                            <input name="delai" pattern="[0-9]+" maxlength="3" type="tel" id="delai" class="form-control" placeholder="d�lai" required autofocus value="<%=(st.getDelaiRelance())%>">
                            <div class="invalid-feedback">
                                Le d�lai de relance est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="conditions" >Conditions *</label>
                            <textarea rows="2" name="conditions" type="text" id="conditions" class="form-control" placeholder="conditions" required autofocus><%=(st.getConditions())%></textarea>
                            <div class="invalid-feedback">
                                Les conditions sont obligatoires.
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success">Modifier le service</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="idOffre" value="<%=o.getId()%>">
                        <input type="hidden" name="idServiceNonStandard" value="<%=st.getId()%>">
                        <input type="hidden" name="action" value="modifierServiceNonStandard">
                    </div>
                </form>
            </div>
        </div>

    </div>
    <%

        }
    %>

    <%
        for (ServiceStandard st : listServicesStandards) {
    %>
    <div class="modal fade" id="detailServiceStandard<%=(st.getId())%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><%=(st.getNom())%></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">

                    <p class="font-weight-bold">Description du service</p>
                    <p class="font-weight-light"><%=(st.getDescriptionService())%></p>
                    <p class="font-weight-light"><%=(st.getDescriptionPrestation())%></p>
                    <p><span class="font-weight-bold">Lieu de l'intervention : </span><span class="font-weight-light"><%if (st.getLieuIntervention().toString().equals("Agence_Hardis")) {%>Agence Hardis<%} else if (st.getLieuIntervention().toString().equals("Site_Client")) {%>Site Client<%} else {%>Mixte<%}%></span></p>
                    <p><span class="font-weight-bold">Prix : </span><span class="font-weight-light"><%=(st.getCout())%> euros, <%if (st.getFraisInclus() == true) {%>Frais inclus<%} else {%>Frais non inclus<%}%></span></p>
                    <p><span class="font-weight-bold">D�lai de relance : </span><span class="font-weight-light"><%=(st.getDelaiRelance())%> jours</span></p>
                    <p class="font-weight-bold">Nombre de jours de travail</p>
                    <p class="font-weight-light"> Consultant Senior : <%=(st.getNbrJoursConsultantSenior())%> jours</p>
                    <p class="font-weight-light">Consultant Confirm� : <%=(st.getNbrJoursConsultantConfirme())%> jours</p>
                    <p class="font-weight-light">Consultant Junior : <%=(st.getNbrJoursConsultantJunior())%> jours</p>
                    <p class="font-weight-bold">Nombre d'heures</p>
                    <p class="font-weight-light">Ateliers et entretiens : <%=(st.getNbrHeuresAtelierEntretienPrevu())%> heures</p>
                    <p class="font-weight-light">Support t�l�phonique : <%=(st.getNbrHeuresSupportTel())%> heures</p>
                    <p class="font-weight-bold">Conditions g�n�rales</p>

                    <div style="overflow-y: scroll; height:4em"> 
                        <p class="font-weight-light"><%=(st.getConditions())%></p>
                    </div>
                </div>
                <div class="modal-footer ">
                    <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>

    </div>
    <%
        }
    %>
    <%
        for (ServiceNonStandard st : listServicesNonStandards) {
    %>
    <div class="modal fade" id="detailServiceNonStandard<%=(st.getId())%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><%=(st.getNom())%></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <p class="font-weight-bold">Description du service</p>
                    <p class="font-weight-light"><%=(st.getDescriptionService())%></p>
                    <p><span class="font-weight-bold">Lieu de l'intervention : </span><span class="font-weight-light"><%if (st.getLieuIntervention().toString().equals("Agence_Hardis")) {%>Agence Hardis<%} else if (st.getLieuIntervention().toString().equals("Site_Client")) {%>Site Client<%} else {%>Mixte<%}%></span></p>
                    <p><span class="font-weight-bold">Prix : </span><span class="font-weight-light"><%=(st.getCout())%> euros, <%if (st.getFraisInclus() == true) {%>Frais inclus<%} else {%>Frais non inclus<%}%></span></p>
                    <p><span class="font-weight-bold">D�lai de relance : </span><span class="font-weight-light"><%=(st.getDelaiRelance())%> jours</span></p>
                    <p class="font-weight-bold">Conditions g�n�rales</p>
                    <div style="overflow-y: scroll; height:4em"> 
                        <p class="font-weight-light"><%=(st.getConditions())%></p>
                    </div>
                </div>
                <div class="modal-footer ">
                    <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>

    </div>
    <%
        }
    %>
</main>

<jsp:include page="footer.jsp"/>
<script>
    window.addEventListener("load", function (event) {            /*Toggle deuxieme Modal*/
/*Cracher du html dans DOM*/
        $('#addLivrableSS').on("click", function (e) {
            $('#zoneAjoutInputsSS').append('<input name="livrable" type="text" id="livrableSS" class="form-control mb-2" placeholder="Renseigner un livrable" required autofocus>');
        })

        $('#removeLivrableSS').on("click", function (e) {
            $('#zoneAjoutInputsSS').children().last().remove();
        })
        $('#addLivrableSNS').on("click", function (e) {
            $('#zoneAjoutInputsSNS').append('<input name="livrable" type="text" id="livrableSNS" class="form-control mb-2" placeholder="Renseigner un livrable" required autofocus>');
        })

        $('#removeLivrableSNS').on("click", function (e) {
            $('#zoneAjoutInputsSNS').children().last().remove();
        })

/*Toggle Class*/
        $("#creerLivrableSS").on('show.bs.modal', function (e) {
            $('#serviceStandard').modal('hide')
        })
        $("#creerLivrableSNS").on('show.bs.modal', function (e) {
            $('#serviceNonStandard').modal('hide')
        })

      


    })
</script>