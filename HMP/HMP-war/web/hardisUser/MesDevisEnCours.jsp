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
<jsp:include page="header.jsp"/>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Mes devis en cours de réponse</h1>
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
                            <th scope="col">Coût</th>
                            <th scope="col" class="text-center">Gérer le devis</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td scope="col">1032</td>
                            <td scope="col">Marius Desjardins</td>
                            <td scope="col">Bio'c'Bon</td>
                            <td scope="col">Serious Gaming</td>
                            <td scope="col">5000 &euro;</td>
                            <td scope="col" class="text-center"><a href="#">Voir le détail</a></td>
                        </tr>
                        <tr>
                            <td scope="col">50</td>
                            <td scope="col">Océane Guignon</td>
                            <td scope="col">Orange</td>
                            <td scope="col">Campagne de test > 5 jours</td>
                            <td scope="col">15000 &euro;</td>
                            <td scope="col" class="text-center"><a href="#">Voir le détail</a></td>
                        </tr>
                        <tr>
                            <td scope="col">50</td>
                            <td scope="col">Océane Guignon</td>
                            <td scope="col">Orange</td>
                            <td scope="col">Conception d'une stratégie de test > 5 jours</td>
                            <td scope="col">5000 &euro;</td>
                            <td scope="col" class="text-center"><a href="#">Voir le détail</a></td>
                        </tr>
                        <tr>
                            <td scope="col">5</td>
                            <td scope="col">Jérémy Chesneau</td>
                            <td scope="col">Magic Yoyo</td>
                            <td scope="col">Réalisation d'un diagnostic test</td>
                            <td scope="col">5016 &euro;</td>
                            <td scope="col" class="text-center"><a href="#">Voir le détail</a></td>
                        </tr>
                        <tr>
                            <td scope="col">1032</td>
                            <td scope="col">Laurent-Xavier Gusse</td>
                            <td scope="col">Mont Rouscous</td>
                            <td scope="col">Serious Gaming</td>
                            <td scope="col">5000 &euro;</td>
                            <td scope="col" class="text-center"><a href="#">Voir le détail</a></td>
                        </tr>
                        <tr>
                            <td scope="col">301</td>
                            <td scope="col">Francois Perusse</td>
                            <td scope="col">Radio Associative</td>
                            <td scope="col">Conception d'une stratégie de test > 5 jours</td>
                            <td scope="col">5000 &euro;</td>
                            <td scope="col" class="text-center"><a href="#">Voir le détail</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>