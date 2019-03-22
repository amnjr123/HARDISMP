<jsp:include page="header.jsp"/>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Demandes de création d'entreprises</h1>
    </div>
    <%@page import="GestionUtilisateur.DemandeCreationEntreprise"%>
    <%@page import="GestionUtilisateur.Agence"%>
    <%@page import="java.util.Collection"%>
    <jsp:useBean id="listeDemandesCreation" scope="request" class="java.util.Collection"></jsp:useBean>
    <jsp:useBean id="listeAgences" scope="request" class="java.util.Collection"></jsp:useBean>
    <% 
        Collection<DemandeCreationEntreprise> listeDemandes = listeDemandesCreation; 
        Collection<Agence> listAgences = listeAgences;
    %>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Demandes en cours de traitement</h1>

            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <div class="input-group mb-3">

                            <label for="nom" class="sr-only">Rechercher</label>
                            <input type="text" id="nom" class="form-control" placeholder="SIRET ou nom d'entreprise" required autofocus>
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
                    <caption>Liste des demandes de création d'entreprise</caption>
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Client</th>
                            <th scope="col">SIRET</th>
                            <th scope="col">Raison sociale</th>
                            <th scope="col">Adresse de facturation</th>
                            <th scope="col">Agence</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (DemandeCreationEntreprise d : listeDemandes) {
                        %>
                        <tr>
                            <th scope="row"><%=(d.getId())%></th>
                            <td><%=(d.getClient().getNom()+" "+d.getClient().getPrenom())%></td>
                            <td><%=(d.getSiret())%></td>
                            <td><%=(d.getNom())%></td>
                            <td><%=(d.getAdresseFacturation())%></td>
                            <%
                                String locAgence="Aucune agence n'a été choisie";
                                if (d.getAgence()!=null){
                                    locAgence="Agence de "+d.getAgence().getLocalisation();
                                }
                            %>
                            <td><%=(locAgence)%></td>

                            <td>
                                <a href="#" type="button" class="btn" style="background-color:transparent;"><i data-feather="paperclip"></i></a>
                                <a href="#" type="button" data-toggle="modal" data-target="#modalDemande<%=(d.getId())%>" class="btn" style="background-color:transparent; color:#27ae60"><i data-feather="check-circle"></i></a>
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
for (DemandeCreationEntreprise d : listeDemandes) {
%>
<div class="modal fade" id="modalDemande<%=(d.getId())%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Valider la demande n° <%=(d.getId())%> du client <%=(d.getClient().getId()+" : "+d.getClient().getNom()+" "+d.getClient().getPrenom())%></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="siret">SIRET</label>
                        <input name="siret" type="text" class="form-control" id="siret" placeholder="SIRET" value="<%=(d.getSiret())%>" required>
                        <div class="invalid-feedback">
                            Le SIRET est obligatoire.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nom">Raison sociale *</label>
                        <input name="nom" type="text" class="form-control" id="lastName" placeholder="Raison sociale" value="<%=(d.getNom())%>" required>
                        <div class="invalid-feedback">
                            La raison sociale est obligatoire.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="adresse">Adresse de facturation *</label>
                        <input name="adresse" type="text" class="form-control" id="adresse" placeholder="Adresse de facturation" value="<%=(d.getAdresseFacturation())%>" required>
                        <div class="invalid-feedback">
                            L'adresse de facturation est obligatoire.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="selectAgence"></label>
                        <select name="agence" class="form-control" id="selectAgence">
                            <%
                                String idAgence = "null";
                                if(d.getAgence()!=null){
                                    idAgence=d.getAgence().getId().toString();
                                }
                            %>
                            <option disabled <% if (idAgence.equals("null")) { out.print("selected"); }%>>Choisir l'agence</option>
                            <%
                                for(Agence a : listAgences){%>
                                <option value="<%=a.getId()%>" <%if(!idAgence.equals("null") && idAgence.equals(a.getId().toString())){out.print("selected");}%> >Agence de&nbsp;<%=a.getLocalisation()%></option>
                            <%}%>                       
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Créer l'entreprise</button>
                    <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                    <input type="hidden" name="action" value="validerDemandeCreationEntreprise">
                    <input type="hidden" name="idDemande" value="<%=(d.getId())%>">
                </div>
            </form>
        </div>

    </div>
</div>
<%
}
%>
                        
                        
                        
                        
<jsp:include page="footer.jsp"/>
