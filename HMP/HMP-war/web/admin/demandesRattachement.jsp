<%@page import="GestionUtilisateur.DemandeRattachement"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listeDemandesRattachement" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:include page="header.jsp"/>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Demandes de rattachement</h1>
    </div>

    <% 
        Collection<DemandeRattachement> listeDemandes = listeDemandesRattachement; 
    %>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Demandes de rattachement en cours</h1>

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
                    <caption>Liste des demandes de rattachement aux entreprises</caption>
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Client</th>
                            <th scope="col">SIRET</th>
                            <th scope="col">Raison sociale</th>
                            <th scope="col">Date de la demande</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (DemandeRattachement d : listeDemandes) {
                        %>
                        <tr>
                            <th scope="row"><%=(d.getId())%></th>
                            <td><%=(d.getClient().getNom()+" "+d.getClient().getPrenom())%></td>
                            <td><%=(d.getEntreprise().getSiret())%></td>
                            <td><%=(d.getEntreprise().getNom())%></td>
                            <td><%=(d.getDateDemande())%></td>
                            <td>
                                <a href="#" type="button" data-toggle="modal" data-target="#modalDemande<%=(d.getId())%>" class="btn" style="background-color:transparent; color:#27ae60"><i data-feather="check-circle"></i></a>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="x"></i></a>
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
for (DemandeRattachement d : listeDemandes) {
%>
<div class="modal fade" id="modalDemande<%=(d.getId())%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Demande n° <%=(d.getId())%></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <h4 class="modal-title" id="exampleModalLabel">Demande n° <%=(d.getId())%> de rattachement du client <%=(d.getClient().getId()+" : "+d.getClient().getNom()+" "+d.getClient().getPrenom())%> à l'entreprise <%=(d.getEntreprise().getNom())%></h4>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Valider la demande</button>
                    <input type="hidden" name="action" value="validerDemandeRattachementEntreprise">
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
