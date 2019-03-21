<jsp:include page="header.jsp"/>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Utilisateurs</h1>
    </div>
    <%@page import="GestionUtilisateur.Client"%>
    <%@page import="java.util.List"%>
    <jsp:useBean id="listeClients" scope="request" class="java.util.List"></jsp:useBean>
    <% List<Client> listeCli = listeClients; %>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Clients</h1>

            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <div class="input-group mb-3">

                            <label for="nom" class="sr-only">Rechercher</label>
                            <input type="text" id="nom" class="form-control" placeholder="Nom, prénom, identifiant ou email" required autofocus>
                            <div class="input-group-prepend">
                                <a href="#" type="button" class="btn btn-primary"><i data-feather="search"></i></a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="card-body">
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
            <div class="table-responsive">
                <table class="table">
                    <caption>Liste des clients </caption>
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Email</th>
                            <th scope="col">Téléphone</th>
                            <th scope="col">Entreprise</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Client c : listeCli) {
                        %>
                        <tr>
                            <th scope="row"><%=(c.getId())%></th>
                            <td><%=(c.getNom())%> <%=(c.getPrenom())%></td>
                            <td><%=(c.getMail())%></td>
                            <td><%=(c.getTelephone())%></td>

                            <td>
                                <% if (c.getEntreprise() != null) {%>
                                <form method="post" action="${pageContext.request.contextPath}/ServletAdministrateur">
                                    <input type="hidden" name="action" value="entreprises">
                                    <input type="hidden" name="recherche" value="<%=(c.getEntreprise().getId().toString())%>">
                                    <button type='submit' class="btn btn-link btn-sm"><%=(c.getEntreprise().getNom())%></button>
                                </form>
                                <% } else if (c.getEntreprise() == null && c.getDemandeCreationEntreprise() == null && c.getDemandeRattachement() == null) { %>
                                <span style="color: #c0392b">Aucune entreprise associée</span>
                                <%
                                    } else if ((c.getDemandeCreationEntreprise() != null || c.getDemandeRattachement() != null) && c.getEntreprise() == null) {
                                        if (c.getDemandeCreationEntreprise() != null) {
                                            out.print("<span style='color: #e67e22'>Demande création (" + c.getDemandeCreationEntreprise().getNom() + ") en cours</span>");
                                        } else if (c.getDemandeRattachement() != null) { %>
                                            <form method="post" action="${pageContext.request.contextPath}/ServletAdministrateur">
                                                <input type="hidden" name="action" value="entreprises">
                                                <input type="hidden" name="recherche" value="<%=(c.getDemandeRattachement().getEntreprise().getId().toString())%>">
                                                Demande rattachement (<button type='submit' class="btn btn-link btn-sm"><%=(c.getDemandeRattachement().getEntreprise().getNom())%></button>) en cours
                                            </form>
                                            
                                        <% }
                                    }
                                %>
                            </td>
                            <td>
                                <a href="#" type="button" data-toggle="modal" data-target="#modalClient<%=(c.getId())%>" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a>
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
</main>

<!--MODALS-->
<%
    for (Client c : listeCli) {
%>
<div class="modal fade" id="modalClient<%=(c.getId())%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modifier les données de l'utilisateur <%=(c.getId())%> </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="firstName">Prénom *</label>
                        <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Prénom du client" value="<%=(c.getPrenom())%>" required>
                        <div class="invalid-feedback">
                            Le prénom est obligatoire.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Nom *</label>
                        <input name="nom" type="text" class="form-control" id="lastName" placeholder="Nom du client" value="<%=(c.getNom())%>" required>
                        <div class="invalid-feedback">
                            Le nom est obligatoire.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email *</label>
                        <div class="input-group">
                            <input name="email" type="email" class="form-control" id="email" placeholder="Email du client" value="<%=(c.getMail())%>" required>
                            <div class="invalid-feedback" style="width: 100%;">
                                Veuillez entrer une adresse mail valide.
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="telephone">Téléphone *</label>
                        <div class="input-group">
                            <input name="tel" type="tel" id="telephone" class="form-control" placeholder="(+33)6xxxxxxxxx ou 00336xxxxxxxxx ou 0xxxxxxxxx" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" value="<%=(c.getTelephone())%>" required>
                            <div class="invalid-feedback" style="width: 100%;">
                                Le numéro de téléphone est obligatoire et doit être conforme
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Enregistrer les modifications</button>
                    <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                    <input type="hidden" name="action" value="modifierDonneesClient">
                    <input type="hidden" name="id" value="<%=(c.getId())%>">
                </div>
            </form>
        </div>

    </div>
</div>
<%
    }
%>




<jsp:include page="footer.jsp"/>
