<%@page import="GestionDevis.DevisStandard"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="devisStandard" scope="request" class="GestionDevis.DevisStandard"></jsp:useBean>
<jsp:include page="header.jsp"/>
<%
    DevisStandard devis = devisStandard;
%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Demander un devis pour le service <%=(devis.getServiceStandard().getNom())%> de l'offre <%=(devis.getServiceStandard().getOffre().getLibelle())%></h1>
        <div class="float-right">
            <a href="#"  data-toggle="modal" data-target="#supprimerDevis" type="button" class="btn btn-warning" >Annuler le devis</a>
        </div>
    </div>
</main>
<div class="container">

</div>

<div class="modal fade" id="supprimerDevis" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Supprimer le devis</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">X</button>
            </div>
            <div class="modal-body">
                <form class="needs-validation form" novalidate role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletClient">
                    <div class="text-center">
                        <input name="idDevis" data-toggle="toggle" data-size="lg" type="checkbox" value="<%=devis.getId()%>" required="true" data-onstyle="success" data-on="Je confirme" data-off="Je ne confirme pas" data-width="220" id="devis" >
                        <label for="devis" class="mt-3"> De supprimer le Devis</label>
                        <div class="invalid-feedback" style="width: 100%;">
                            Il est obligatoire d'accepter pour supprimer le devis.
                        </div>
                    </div>
                    <center>
                        <div class="form-group py-2 text-center">
                            <input type="hidden" name="action" value="supprimerDevisStandard">
                            <button type="submit" class="btn btn-warning "><i data-feather='folder-minus'></i>&nbsp; Supprimer</button>
                            <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        </div>
                    </center>
                </form>

            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
