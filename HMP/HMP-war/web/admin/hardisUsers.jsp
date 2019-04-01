<%@page import="Enum.ProfilTechnique"%>
<%@page import="GestionCatalogue.Offre"%>
<%@page import="GestionUtilisateur.Agence"%>
<%@page import="GestionUtilisateur.ReferentLocal"%>
<%@page import="GestionUtilisateur.Consultant"%>
<%@page import="GestionUtilisateur.PorteurOffre"%>
<%@page import="GestionUtilisateur.UtilisateurHardis"%>
<%@page import="java.util.Collection"%>
<jsp:include page="header.jsp"/>
<jsp:useBean id="listeAgences" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listeOffres" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listeUtilisateursHardis" scope="request" class="java.util.Collection"></jsp:useBean>
    <style>
    <jsp:include page="../css/bootstrap4-toggle.css"/>   
</style>
<%
    Collection<Agence> listAgences = listeAgences;
    Collection<Offre> listOffres = listeOffres;
    Collection<UtilisateurHardis> listeUH = listeUtilisateursHardis;
%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Gestion des Utilisateurs HARDIS</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Utilisateurs HARDIS</h1>                
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
        <div class="card">
            <div class="card-body">
                <div class="row ">
                    <div class="col-md-6 mb-3">
                        <div class="input-group mb-3">
                            <label for="nom" class="sr-only">Rechercher</label>
                            <input type="text" id="nom" class="form-control" placeholder="Nom, prénom, identifiant ou email" required autofocus>
                            <div class="input-group-prepend">
                                <a href="#" type="button" class="btn btn-primary"><i data-feather="search"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="btn-toolbar">
                            <button  class="btn btn-sm btn-success " data-toggle="modal" data-target="#addRL">
                                <span data-feather="user-plus"></span>
                                Référent Local
                            </button>
                            <button  style="margin-left:1em" class="btn btn-sm btn-success " data-toggle="modal" data-target="#addPO">
                                <span data-feather="folder-plus"></span>
                                Porteur d'offre
                            </button>
                            <button style="margin-left:1em" class="btn btn-sm btn-success " data-toggle="modal" data-target="#addConsultant">
                                <span data-feather="user-plus"></span>
                                Consultant
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Email</th>
                            <th scope="col">Téléphone</th>
                            <th scope="col">Profil technique</th>
                            <th scope="col">Plafond de délégation</th>
                            <th scope="col">Type</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (UtilisateurHardis uh : listeUH) {
                        %>
                        <tr>
                            <th scope="row"><%=(uh.getId())%></th>
                            <td><%=(uh.getNom())%> <%=(uh.getPrenom())%></td>
                            <td><%=(uh.getMail())%></td>
                            <td><%=(uh.getTelephone())%></td>
                            <td><%=(uh.getProfilTechnique())%></td>
                            <%
                                if (uh.getDtype().equals("Consultant")) {
                                    Consultant consultant = (Consultant) uh;
                            %>
                            <td><%=(consultant.getPlafondDelegation())%> EUR</td>
                            <%
                            } else if (uh.getDtype().equals("ReferentLocal")) {
                                ReferentLocal refLoc = (ReferentLocal) uh;
                            %>
                            <td><%=(refLoc.getPlafondDelegation())%> EUR</td>
                            <%
                            } else {
                            %>
                            <td> - </td>
                            <%
                                }
                            %>
                            <td><%=(uh.getDtype())%></td>
                            <td><a href="#" data-toggle="modal" data-target="#modifierUser<%=(uh.getId())%>" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                            </td>
                        </tr>
                        <%
                            }
                        %>   
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!--Modals-->

    <%
        for (UtilisateurHardis uh : listeUH) {
    %>
    <div class="modal fade" id="modifierUser<%=(uh.getId())%>" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form class="needs-validation form" novalidate role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title">Modifier l'utilisateur</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">X</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="firstName">Prénom *</label>
                            <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Prénom référent" value="<%=(uh.getPrenom())%>" required>
                            <div class="invalid-feedback">
                                Le prénom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Nom *</label>
                            <input name="nom" type="text" class="form-control" id="lastName" placeholder="Nom référent" value="<%=(uh.getNom())%>" required>
                            <div class="invalid-feedback">
                                Le nom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <div class="input-group">
                                <input name="mail" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" required value="<%=(uh.getMail())%>">
                                <div class="invalid-feedback" style="width: 100%;">
                                    Veuillez entrer une adresse mail valide.
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telephone">Téléphone *</label>
                            <div class="input-group">
                                <input name="tel" type="tel" id="telephone" class="form-control" placeholder="(+33)6xxxxxxxxx ou 00336xxxxxxxxx ou 0xxxxxxxxx" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" required value="<%=(uh.getTelephone())%>">
                                <div class="invalid-feedback" style="width: 100%;">
                                    Le numéro de téléphone est obligatoire et doit être conforme
                                </div>
                            </div>
                        </div>
                        <%if (uh.getDtype().equals("ReferentLocal")) {
                                ReferentLocal refLoc = (ReferentLocal) uh;
                        %>
                        <div class="form-group">
                            <label for='plafond' >Plafond de déléguation</label>
                            <input pattern="^\d*\.?\d*$" name="plafond" type='tel' id='plafond' class='form-control' placeholder='Plafond de déléguation' required autofocus value="<%=(refLoc.getPlafondDelegation())%>">
                            <div class="invalid-feedback">
                                Le plafond de délégation doit être supérieur à 0.
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <%if (uh.getDtype().equals("Consultant")) {
                                Consultant c = (Consultant) uh;
                        %>
                        <div class="form-group">
                            <label for='plafond' >Plafond de déléguation</label>
                            <input pattern="^\d*\.?\d*$" name="plafond" type='tel' id='plafond' class='form-control' placeholder='Plafond de déléguation' required autofocus value="<%=(c.getPlafondDelegation())%>">
                            <div class="invalid-feedback">
                                Le plafond de délégation ne doit pas être négatif.
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <div class="form-group">
                            <label for='Profil'>Profil *</label>
                            <select name="profilTechnique" class="selectpicker" data-width="auto" show-tick>
                                <option <%if (uh.getProfilTechnique().equals(ProfilTechnique.Administrateur)) { %>selected <% } %> value="Administrateur">Administrateur</option>
                                <option <%if (uh.getProfilTechnique().equals(ProfilTechnique.Gestionnaire)) { %>selected <% } %> value="Gestionnaire">Gestionnaire</option>
                                <option <%if (uh.getProfilTechnique().equals(ProfilTechnique.Visualisation)) { %>selected<% } %> value="Visualisation">Visualisation</option>
                            </select>
                        </div>                 
                        <div class="form-group">
                            <%if (uh.getDtype().equals("ReferentLocal")) {
                                    ReferentLocal rl = (ReferentLocal) uh;
                            %>
                            <label for="selectOffre">Offre *</label>
                            <select name="offre" class="form-control selectpicker" id="selectOffre" data-width="auto" show-tick>
                                <%for (Offre o : listOffres) {%>
                                <option <%if (rl.getOffre() == o) {%>selected="selected"<% }%> value="<%=(o.getId())%>"><%=(o.getLibelle())%></option>
                                <%
                                    }
                                %>                       
                            </select>
                            <%} else if (uh.getDtype().equals("PorteurOffre")) {
                                PorteurOffre po = (PorteurOffre) uh;
                            %>
                            <label for="selectOffre">Offre *</label>
                            <select name="offre" class="form-control selectpicker" id="selectOffre" data-width="auto" show-tick>
                                <%for (Offre o : listOffres) {%>
                                <option <%if (po.getOffre() == o) {%>selected="selected"<% }%> value="<%=o.getId()%>"><%=o.getLibelle()%></option>
                                <% } %>                       
                            </select>
                            <%--Error is here--%>
                            <% } else if (uh.getDtype().equals("Consultant")) {
                                Consultant c = (Consultant) uh;
                            %>
                            <label for="selectOffre">Offre *</label>
                            <select name="offres" class="form-control selectpicker" id="selectOffre" data-width="auto" multiple show-tick>
                                <%for (Offre o : listOffres) {%>
                                <option <%for (Offre oc : c.getOffres()) {
                                        if (oc.getId() == o.getId()) {%> selected="selected" <% }
                                            }%> value="<%=o.getId()%>"><%=o.getLibelle()%></option>
                                <% } %>                       
                            </select>
                            <% } %> 
                        </div>
                        <%--Fin erreur--%>
                        <div class="form-group">
                            <label for="selectAgence">Agence *</label>
                            <select name="agence" class="form-control selectpicker" id="selectAgence" data-width="auto" show-tick>
                                <%for (Agence a : listAgences) {%>
                                <option <%if (uh.getAgence() == a) {%>selected="selected"<% }%> value="<%=a.getId()%>">Agence de&nbsp;<%=a.getLocalisation()%></option>
                                <% } %>                       
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Statut du compte&nbsp;&nbsp;&nbsp;&nbsp;</label>

                            <%
                                if (uh.getActifInactif() == true) {
                            %>
                            <input checked name="actifInactif" data-toggle="toggle" data-size="lg" type="checkbox" value="actif" data-onstyle="success" data-on="Actif" data-off="Inactif" data-width="200" >
                            <%
                            } else {
                            %>
                            <input name="actifInactif" data-toggle="toggle" data-size="lg" type="checkbox" value="actif"  data-onstyle="success" data-on="Actif" data-off="Inactif" data-width="200" >
                            <%
                                }
                            %>

                        </div>
                        <div class="modal-footer">
                            <input type="hidden" name="id" value="<%=(uh.getId())%>">
                            <%if (uh.getDtype().equals("PorteurOffre")) { %>
                            <input type="hidden" name="action" value="modifierPorteurOffre">
                            <% } else if (uh.getDtype().equals("ReferentLocal")) { %>
                            <input type="hidden" name="action" value="modifierReferentLocal">
                            <% } else { %>
                            <input type="hidden" name="action" value="modifierConsultant">
                            <% } %>
                            <button type="submit" class="btn btn-success">Valider</button>
                            <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <% } %>

    <div class="modal fade" id="addRL" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouveau Référent Local</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">X</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="firstName">Prénom *</label>
                            <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Prénom référent" value="" required>
                            <div class="invalid-feedback">
                                Le prénom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Nom *</label>
                            <input name="nom" type="text" class="form-control" id="lastName" placeholder="Nom référent" value="" required>
                            <div class="invalid-feedback">
                                Le nom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <div class="input-group">
                                <input name="email" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Veuillez entrer une adresse mail valide.
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telephone">Téléphone *</label>
                            <div class="input-group">
                                <input name="tel" type="tel" id="telephone" class="form-control" placeholder="(+33)6xxxxxxxxx ou 00336xxxxxxxxx ou 0xxxxxxxxx" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Le numéro de téléphone est obligatoire et doit être conforme
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for='plafond' >Plafond de déléguation</label>
                            <input pattern="^[1-9][0-9]*$" name="plafond" type='tel' id='plafond' class='form-control' placeholder='Plafond de déléguation' required autofocus>
                            <div class="invalid-feedback">
                                Le plafond de délégation doit être plus de 0.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for='profil'>Profil *</label>
                            <select name="profilTechnique" class="selectpicker" data-width="auto" show-tick>
                                <option disabled selected>Choisir Profil Technique</option>
                                <option value="Administrateur">Administrateur</option>
                                <option value="Gestionnaire">Gestionnaire</option>
                                <option value="Visualisation">Visualisation</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="selectOffre">Offre *</label>
                            <select name="offre" class="form-control selectpicker" id="selectOffre" data-width="auto" show-tick>
                                <option disabled selected>Choisir l'offre</option>
                                <%for (Offre o : listOffres) {%>
                                <option value="<%=o.getId()%>"><%=o.getLibelle()%></option>
                                <%}%>                       
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="selectAgence">Agence *</label>
                            <select name="agence" class="form-control selectpicker" id="selectAgence" data-width="auto" show-tick>
                                <option disabled selected>Choisir l'agence</option>
                                <%for (Agence a : listAgences) {%>
                                <option value="<%=a.getId()%>">Agence de&nbsp;<%=a.getLocalisation()%></option>
                                <%}%>                       
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success">Créer l'Utilisateur Hardis</button>
                            <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                            <input type="hidden" name="action" value="creerRL">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="addPO" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouveau Porteur d'offre</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">X</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="firstName">Prénom *</label>
                            <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Prénom référent" value="" required>
                            <div class="invalid-feedback">
                                Le prénom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Nom *</label>
                            <input name="nom" type="text" class="form-control" id="lastName" placeholder="Nom référent" value="" required>
                            <div class="invalid-feedback">
                                Le nom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <div class="input-group">
                                <input name="email" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Veuillez entrer une adresse mail valide.
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telephone">Téléphone *</label>
                            <div class="input-group">
                                <input name="tel" type="tel" id="telephone" class="form-control" placeholder="(+33)6xxxxxxxxx ou 00336xxxxxxxxx ou 0xxxxxxxxx" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Le numéro de téléphone est obligatoire et doit être conforme
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for='siret'>Profil *</label>
                            <select name="profilTechnique" class="selectpicker" show-tick data-width="auto">
                                <option disabled selected>Choisir Profil Technique</option>
                                <option value="Administrateur">Administrateur</option>
                                <option value="Gestionnaire">Gestionnaire</option>
                                <option value="Visualisation">Visualisation</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="selectOffre ">Offre *</label>
                            <select name="offre" class="form-control selectpicker" id="selectOffre" data-width="auto" show-tick>
                                <option disabled selected>Choisir l'offre</option>
                                <%for (Offre o : listOffres) {%>
                                <option value="<%=o.getId()%>"><%=o.getLibelle()%></option>
                                <%}%>                       
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="selectAgence">Agence *</label>
                            <select name="agence" class="form-control selectpicker" id="selectAgence" data-width="auto" show-tick>
                                <option disabled selected>Choisir l'agence</option>
                                <%for (Agence a : listAgences) {%>
                                <option value="<%=a.getId()%>">Agence de&nbsp;<%=a.getLocalisation()%></option>
                                <%}%>                       
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success">Créer l'Utilisateur Hardis</button>
                            <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                            <input type="hidden" name="action" value="creerPO">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="addConsultant" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouveau Consultant</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">X</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="firstName">Prénom *</label>
                            <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Prénom consultant" value="" required>
                            <div class="invalid-feedback">
                                Le prénom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Nom *</label>
                            <input name="nom" type="text" class="form-control" id="lastName" placeholder="Nom consultant" value="" required>
                            <div class="invalid-feedback">
                                Le nom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <div class="input-group">
                                <input name="email" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Veuillez entrer une adresse mail valide.
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telephone">Téléphone *</label>
                            <div class="input-group">
                                <input name="tel" type="tel" id="telephone" class="form-control" placeholder="(+33)6xxxxxxxxx ou 00336xxxxxxxxx ou 0xxxxxxxxx" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Le numéro de téléphone est obligatoire et doit être conforme
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for='plafond' >Plafond de déléguation</label>
                            <input pattern="[0-9]+" name="plafond" type='tel' id='plafond' class='form-control' placeholder='Plafond de déléguation' required autofocus>
                            <div class="invalid-feedback">
                                Le Profil métier est obligatoire.
                            </div>
                        </div>          
                        <div class="form-group">
                            <label for='prix'>Prix à la journée</label>
                            <input pattern="[0-9]+" name="prix" type='tel' id='prix' class='form-control' placeholder='Prix à la journée' required autofocus>
                            <div class="invalid-feedback">
                                Le prix est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for='siret'>Profil *</label>
                            <select name="profilTechnique" class="form-control selectpicker" data-width="auto" show-tick>
                                <option disabled selected>Choisir Profil Technique</option>
                                <option value="Administrateur">Administrateur</option>
                                <option value="Gestionnaire">Gestionnaire</option>
                                <option value="Visualisation">Visualisation</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="selectOffre">Offre *</label>
                            <select name="offres" class="form-control selectpicker" id="selectOffre" data-width="auto" multiple show-tick>
                                <option disabled>Choisir l'offre</option>
                                <%for (Offre o : listOffres) {%>
                                <option value="<%=o.getId()%>"><%=o.getLibelle()%></option>
                                <%}%>                       
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="selectAgence">Agence *</label>
                            <select name="agence" class="form-control selectpicker" data-width="auto" id="selectAgence" show-tick>
                                <option disabled selected>Choisir l'agence</option>
                                <%for (Agence a : listAgences) {%>
                                <option value="<%=a.getId()%>">Agence de&nbsp;<%=a.getLocalisation()%></option>
                                <%}%>                       
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success">Créer l'Utilisateur Hardis</button>
                            <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                            <input type="hidden" name="action" value="creerConsultant">
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>