<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="GestionCatalogue.Offre"%>
<%@page import="java.util.Collection"%>
<%@page import="GestionUtilisateur.Client"%>

<jsp:useBean id="listOffres" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:include page="header.jsp"/>
<%
    Collection<Offre> list = listOffres;
    Client c = (Client) session.getAttribute("sessionClient");
%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Devis en cours</h1>
    </div>

</main>
<div class="container">
    <%
        if ((c.getDemandeCreationEntreprise() != null || c.getDemandeRattachement() != null) && c.getEntreprise() == null) {
    %>
    <div class="card text-white bg-warning mb-3">
        <div class="card-header"><h4>Mes Devis</h4></div>
        <div class="card-body">
            <h4 class="card-title p-3 mb-2">L'attachement à votre entreprise est en cours de traitement, vous devez attendre l'approbation votre entreprise pour pouvoir effectuer un devis ou bien contactez un de nos administrateurs actifs en cas de problème.</h4>
        </div>
    </div>
    <%
        }
    %>
    <%
        if (c.getEntreprise() == null && c.getDemandeCreationEntreprise() == null && c.getDemandeRattachement() == null) {
    %>
        <div class="card text-white bg-danger mb-3">
        <div class="card-header"><h4>Mon entreprise</h4></div>
        <div class="card-body">
            <h4 class="card-title p-3 mb-2"><a style="color: white" onmouseout="this.style.color='white'" onmouseover="this.style.color='black'" href="${pageContext.request.contextPath}/ServletClient?action=monProfil">Veuillez vous rattacher à une entreprise avant de procéder à la demande de devis</a></h4>
        </div>
    </div>
    <%}%>
    
    
    
    <%
    if (c.getEntreprise() != null) {
%>
<div class="card">
    <div class="card-header">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
            <h1 class="h2">Choisir une offre</h1>
        </div>
    </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Libellé</th>
                            <th scope="col">Choisir</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int i = 0;
                            for (Offre o : list) {%>                                      
                        <tr>
                            <td><%=o.getLibelle()%></td>
                            <td><a href="${pageContext.request.contextPath}/ServletClient?action=creerDevisServices&id=<%=o.getId()%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="check-circle"></i></a></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
     <%
            
        }
    %>
    
    
</div>





<jsp:include page="footer.jsp"/>