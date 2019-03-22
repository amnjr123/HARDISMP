<%@page import="GestionUtilisateur.Agence"%>
<%@page import="GestionUtilisateur.Entreprise"%>
<%@page import="java.util.Collection"%>
<jsp:include page="header.jsp"/>
<jsp:useBean id="listeEntreprises" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listeAgences" scope="request" class="java.util.Collection"></jsp:useBean>
<%
    Collection<Entreprise> listEntreprises = listeEntreprises;
    Collection<Agence> listAgences = listeAgences;
%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Gestion des Entreprises</h1>
    </div>

    <div class="card">
        <% String error = (String) request.getAttribute("msgError");
            if (request.getAttribute("msgError") != null) {%>
        <div class="alert alert-danger alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attention !</strong>&nbsp;<%=error%>.
        </div>
        <%}%>

        <% String success = (String) request.getAttribute("msgSuccess");
            if (request.getAttribute("msgSuccess") != null) {%>
        <div class="alert alert-success alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <%=success%>.
        </div>
        <%}%>
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Entreprises</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <button  class="btn btn-sm btn-success" data-toggle="modal" data-target="#exampleModal">
                        <span data-feather="plus"></span>
                        Nouvelle entreprise
                    </button>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <form method="post" action="${pageContext.request.contextPath}/ServletAdministrateur">
                            <div class="input-group mb-3">
                                <input type="hidden" name="action" value="entreprises">
                                <input type="text" name="recherche" class="form-control" placeholder="Id, Raison sociale ou SIRET">
                                <div class="input-group-prepend">
                                    <button type='submit' class="btn btn-primary"><i data-feather="search"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <caption>Liste des entreprises </caption>
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">SIRET</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Adresse</th>
                            <th scope="col">Agence</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            for (Entreprise e : listEntreprises) {
                        %>
                        <tr>
                            <th scope="row"><%=(e.getId())%></th>
                            <td><%=(e.getSiret())%></td>
                            <td><%=(e.getNom())%></td>
                            <td><%=(e.getAdresseFacturation())%></td>
                            <td><%=(e.getAgence().getLocalisation())%></td>
                            <td><a href="#" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="phone"></i></a>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouvelle entreprise</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>
                            <label for="siret" class="sr-only">SIRET *</label>
                            <input type="text" name="siret" id="siret" class="form-control" placeholder="SIRET" required autofocus>
                        </p>
                        <p>
                            <label for="nom" class="sr-only">Nom *</label>
                            <input type="text" name="nom" id="nom" class="form-control" placeholder="Nom" required>
                        </p>
                        <p>
                            <label for="adresse" class="sr-only">Adresse de facturation *</label>
                            <input type="text" name="adresse" id="adresse" class="form-control" placeholder="Adresse" required>
                        </p>
                        <div class="form-group">
                            <label for="selectAgence"></label>
                            <select name="agence" class="form-control" id="selectAgence">
                                <option disabled selected>Choisir l'agence</option>
                                <%for (Agence a : listAgences) {%>
                                <option value="<%=a.getId()%>">Agence de&nbsp;<%=a.getLocalisation()%></option>
                                <%}%>                       
                            </select>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Créer l'entreprise</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="action" value="creerEntreprise">
                    </div>
                </form>
            </div>
        </div>

    </div>

</main>


<jsp:include page="footer.jsp"/>