<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="GestionUtilisateur.Client"%>
<%
    Client c = (Client) session.getAttribute("sessionClient");

    int nbrDemandesRattachement = (Integer) session.getAttribute("nbrDemandesRattachementClientAdmin");
    int notifications = 0;
    if (c.getEntreprise() == null) {
        notifications += 1;
    } else if (c.getAdministrateur()) {
        notifications += nbrDemandesRattachement;
    }

%>
<!doctype html>
<html lang="en">
    <head>
        <meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title><%=(c.getNom() + ' ' + c.getPrenom())%></title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/dashboard.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/simple-sidebar.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/custom/fileInput.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/snatchbot.css" rel="stylesheet" type="text/css"> 
    </head>
    <body>  
        <%--href="${pageContext.request.contextPath}/home.jsp"--%>
        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap bg-faded static-top">
            <button class="btn navbar-toggler navbar-toggler-right" style="background-color:transparent"  id="menu-toggle"><span style="width:32px;height: 32px;color : white;" data-feather="menu" ></span></button>
            <ul class="navbar-nav   ml-auto">
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Servlet?action=logout" style="" >Se déconnecter&nbsp;<span data-feather="log-out"></span> </a>
                </li>
            </ul>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="d-flex" id="wrapper" style="width: 100vw">

                    <!-- Sidebar -->
                    <div class="bg-light border-right" id="sidebar-wrapper">
                        <div class="sidebar-heading"><span style="width:24px;height: 28px;color : grey;" data-feather="<% if (c.getAdministrateur()) { out.print("user-check"); } else {out.print("user");}%>" ></span>&nbsp;<%=(c.getNom() + ' ' + c.getPrenom())%></div>
                        <div class="list-group list-group-flush">
                            <a href="${pageContext.request.contextPath}/client/index.jsp" class="list-group-item d-flex justify-content-between bg-light">
                                <span><span data-feather="monitor"></span> Tableau de bord</span>
                            </a>
                            <a href="${pageContext.request.contextPath}/ServletClient?action=monProfil" class="list-group-item d-flex justify-content-between bg-light">
                                <span><span data-feather="user"></span> Mon profil</span>
                                <%
                                    if (notifications > 0) {

                                        if (c.getEntreprise() == null && c.getDemandeCreationEntreprise() == null && c.getDemandeRattachement() == null) {
                                %>
                                <span class="badge badge-danger badge-pill"><%=(notifications)%></span>
                                <%
                                } else if (c.getDemandeCreationEntreprise() != null || c.getDemandeRattachement() != null) {
                                %>
                                <span class="badge badge-warning badge-pill"><%=(notifications)%></span>
                                <%
                                } else {
                                %>
                                <span class="badge badge-primary badge-pill"><%=(notifications)%></span>
                                <%
                                        }
                                    }
                                %>

                            </a>
                            <a href="${pageContext.request.contextPath}/ServletClient?action=offres" class="list-group-item d-flex justify-content-between bg-light">
                                <span><span data-feather="book-open"></span> Catalogue</span>
                            </a>
                            <a  class="list-group-item d-flex justify-content-between align-items-center bg-light" data-toggle="collapse" href="#collapseDevis" role="button" aria-expanded="false" aria-controls="collapseDevis" >
                                <span><span data-feather="clipboard"></span> Devis</span>
                                <span><span data-feather="chevron-down"></span></span>  
                            </a>
                            <div class="collapse" id="collapseDevis">
                                <a href="${pageContext.request.contextPath}/ServletClient?action=creerDevisOffres" class="list-group-item list-group-item-action bg-light">
                                    <span data-feather="file-plus"></span> Demander un devis
                                </a>
                                <a href="${pageContext.request.contextPath}//ServletClient?action=DevisEnCours" class="list-group-item list-group-item-action align-items-center bg-light">
                                    <span data-feather="file-text"></span> Devis en cours
                                    <span class="badge badge-primary badge-pill"></span>
                                </a>
                                <a href="${pageContext.request.contextPath}/ServletClient?action=DevisTermines" class="list-group-item list-group-item-action bg-light">
                                    <span data-feather="check-square"></span> Devis terminés
                                    <span class="badge badge-primary badge-pill"></span>
                                </a>
                            </div>
                            <a href="${pageContext.request.contextPath}/client/factures.jsp" class="list-group-item d-flex justify-content-between bg-light">
                                <span><span data-feather="shopping-cart"></span> Mes factures</span>
                            </a>
                            <a href="${pageContext.request.contextPath}/client/inbox.jsp" class="list-group-item d-flex justify-content-between bg-light">
                                <span><span data-feather="inbox"></span> Inbox</span>
                            </a>
                        </div>
                    </div>
                    <div id="page-content-wrapper">
                        <br>
