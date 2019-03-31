<%@page import="GestionDevis.Devis"%>
<%@page import="GestionDevis.DevisStandard"%>
<%@page import="GestionDevis.DevisNonStandard"%>
<%@page import="GestionCatalogue.Livrable"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="GestionCatalogue.Offre"%>
<%@page import="GestionCatalogue.ServiceStandard"%>
<%@page import="GestionCatalogue.ServiceNonStandard"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listeDevis" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:include page="header.jsp"/>
<%Collection<Devis> listDevis = listeDevis;%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Mes devis terminés</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Devis</h1>
                <div class="btn-toolbar">
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
                            <th scope="col">Nom Client</th>
                            <th scope="col">Entreprise</th>
                            <th scope="col">Service</th>
                            <th scope="col">Type</th>
                            <th scope="col">Etat</th>
                            <th scope="col" class="text-center">Voir le détail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  
                            for (Devis d : listDevis) {%>                                      
                        <tr>
                            <td><%=d.getId()%></td>
                            <td><%=d.getClient().getNom()%> <%=d.getClient().getPrenom()%></td>
                            <td><%=d.getClient().getEntreprise().getNom()%></td>
                            <td>
                                <%if (d.getDtype().toString().equals("DevisStandard")) {
                                    DevisStandard ds = (DevisStandard) d;
                                    out.print(ds.getServiceStandard());
                                } else {
                                    DevisNonStandard dns = (DevisNonStandard) d;
                                    out.print(dns.getServiceNonStandard());
                                }%>
                            </td>
                            <td><%if (d.getDtype().toString().equals("DevisStandard")) {%>Standard<%} else {%>Personnalisé<%}%></td>
                            <td><%=d.getStatut()%></td>
                            <td>
                                <div class="dropdown">
                                    <%if (d.getDtype().toString().equals("DevisStandard")) {%>
                                    <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=gererDevisStandard&idDevis=<%=d.getId()%>" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="zoom-in"></i></a>
                                    <%} else {%>
                                    <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=gererDevisNonStandard&idDevis=<%=d.getId()%>" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="zoom-in"></i></a>
                                    <%}%>
                                </div>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>