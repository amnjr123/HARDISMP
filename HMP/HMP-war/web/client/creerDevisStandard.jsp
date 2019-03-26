<%@page import="java.util.Calendar"%>
<%@page import="GestionCatalogue.Livrable"%>
<%@page import="java.util.Date"%>
<%@page import="GestionCatalogue.Offre"%>
<%@page import="GestionCatalogue.ServiceStandard"%>
<%@page import="GestionCatalogue.ServiceNonStandard"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="service" scope="request" class="GestionCatalogue.ServiceStandard"></jsp:useBean>
<jsp:include page="header.jsp"/>
<%ServiceStandard  s = service;%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Demander un devis <%=(s.getNom())%></h1>
    </div>
</main>
    <div class="container">
        
    </div>

<jsp:include page="footer.jsp"/>
