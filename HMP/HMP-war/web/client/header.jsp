<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="GestionUtilisateur.Client"%>
<% Client c = (Client) session.getAttribute("sessionClient");%>
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
                        <div class="sidebar-heading"><span style="width:24px;height: 28px;color : grey;" data-feather="user" ></span>&nbsp;<%=(c.getNom() + ' ' + c.getPrenom())%></div>
                        <div class="list-group list-group-flush">
                            <a href="${pageContext.request.contextPath}/client/index.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="monitor"></span> Tableau de bord
                            </a>
                            <a href="${pageContext.request.contextPath}/ServletClient?action=monProfil" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="user"></span> Mon profil
                            </a>
                            <a href="${pageContext.request.contextPath}/ServletClient?action=offres" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="layers"></span> Catalogue
                            </a>
                            <a href="${pageContext.request.contextPath}/client/devis.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="file-text"></span> Mes devis
                            </a>
                            <a href="${pageContext.request.contextPath}/client/factures.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="square"></span> Mes factures
                            </a>
                            <a href="${pageContext.request.contextPath}/client/calendrier.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="calendar"></span> Calendrier
                            </a>
                            <a href="${pageContext.request.contextPath}/client/inbox.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="inbox"></span> Inbox
                            </a>
                            <%
                                if( c.getEntreprise()!=null && c.getAdministrateur()){
                                
                            %> 
                            <a href="${pageContext.request.contextPath}/client/inbox.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="inbox"></span> Demandes de rattachement
                            </a>
                            <%
                                }
                            %>  
                            
                        </div>
                    </div>
                    <div id="page-content-wrapper">
                        <br>
