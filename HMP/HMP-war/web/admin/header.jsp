<%@page import="GestionUtilisateur.UtilisateurHardis"%>
<% UtilisateurHardis u = (UtilisateurHardis) session.getAttribute("sessionHardis");%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Interface d'administration HARDIS Work Place</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/dashboard.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/simple-sidebar.css" rel="stylesheet"> 
    </head>


    <body>
        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap bg-faded static-top">
            <button class="btn navbar-toggler navbar-toggler-right" style="background-color:transparent"  id="menu-toggle"><span style="width:32px;height: 32px;color : white;" data-feather="menu" ></span></button>
            <ul class="navbar-nav   ml-auto">
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Servlet" style="" >Quitter l'interface d'administration&nbsp;<span data-feather="log-out"></span> </a>
                </li>
            </ul>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="d-flex" id="wrapper" style="width: 100vw">

                    <!-- Sidebar -->
                    <div class="bg-light border-right" id="sidebar-wrapper">
                        <div class="sidebar-heading"><span style="width:24px;height: 28px;color : grey;" data-feather="user" ></span>&nbsp;<%=(u.getNom() + ' ' + u.getPrenom())%></div>
                        <div class="list-group list-group-flush">
                            <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=utilisateursHardis" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="user"></span> Utilisateurs HARDIS
                            </a>
                            <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=clients" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="user"></span> Clients
                            </a>
                            <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=entreprises&p=0" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="file-text"></span> Entreprises
                            </a>
                            <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=agences" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="layers"></span> Agences
                            </a>

                                <!--href a modifier-->
                            <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=offres" class="list-group-item list-group-item-action bg-light">

                                <span data-feather="file-text"></span> Catalogues
                            </a>

                        </div>
                    </div>
                    <div id="page-content-wrapper">
                        <br>


                        <!--div class="container-fluid">
                            <div class="row">
                                <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                                    <div class="sidebar-sticky">
                                        <ul class="nav flex-column" id="myTab" >
                                            <li class="nav-item">
                                                <a class="nav-link" href="#">
                                                    <span data-feather="home"></span>
                                                    Menu
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="indexAdmin.jsp">
                                                    <span data-feather="monitor"></span>
                                                    Compte administrateur
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="${pageContext.request.contextPath}/ServletAdministrateur?action=utilisateurs">
                                                    <span data-feather="user"></span>
                                                    Utilisateurs
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="${pageContext.request.contextPath}/ServletAdministrateur?action=entreprises&p=0">
                                                    <span data-feather="file-text"></span>
                                                    Entreprises
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="${pageContext.request.contextPath}/ServletAdministrateur?action=agences">
                                                    <span data-feather="shopping-cart"></span>
                                                    Agences
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="catalogues.jsp">
                                                    <span data-feather="square"></span>
                                                    Catalogues
                                                </a>
                                            </li>
                
                                        </ul>
                
                                    </div>
                                </nav-->
