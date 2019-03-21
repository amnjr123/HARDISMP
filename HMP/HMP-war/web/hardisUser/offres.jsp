<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="GestionCatalogue.Offre"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listOffres" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:include page="header.jsp"/>
<%Collection<Offre> list = listOffres;%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Catalogue des services Hardis</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Offres</h1>
            </div>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Libellé</th>
                            <th scope="col" class="text-center">Actif ou obsolète</th>
                            <th scope="col" class="text-center">Services</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int i = 0;
                            Calendar cal = Calendar.getInstance();
                            cal.set(Calendar.MILLISECOND, 0);
                            cal.set(Calendar.SECOND, 0);
                            cal.set(Calendar.MINUTE, 0);
                            cal.set(Calendar.HOUR_OF_DAY, 0);
                            cal.set(Calendar.DAY_OF_MONTH, 1);
                            cal.set(Calendar.MONTH, 0);
                            cal.set(Calendar.YEAR, 2099);
                            Date date = cal.getTime();
                            for (Offre o : list) {%>                                      
                        <tr>
                            <td><%=o.getId()%></td>
                            <td><%=o.getLibelle()%></td>
                            <td class="text-center"><%if(o.getDateFinValidite().after(date)){%><i data-feather="check-circle" style="color:green"></i><%}else{%><i data-feather="x" style="color:red"></i><%}%></td>
                            <td><a href="${pageContext.request.contextPath}/ServletUtilisateurHardis?action=services&id=<%=o.getId()%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="list"></i></a></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
                    
</main>


<jsp:include page="footer.jsp"/>