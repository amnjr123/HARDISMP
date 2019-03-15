<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>nomClient</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/custom/dashboard.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/simple-sidebar.css" rel="stylesheet">
        
        <link href="${pageContext.request.contextPath}/css/custom/form-validation.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/fileInput.css" rel="stylesheet">

    </head>

    <body>  





        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
            <button class="btn" style="background-color:transparent"  id="menu-toggle"><span data-feather="menu" color="white"></span></button>
            <a class="navbar-brand px-5" href="${pageContext.request.contextPath}/Servlet">Hardis Work Place</a>
            <ul class="navbar-nav px-3">
                <li class="nav-item text-nowrap">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Servlet?action=logout">Se déconnecter <span data-feather="log-out"></span> </a>
                </li>
            </ul>
        </nav>



        <div class="container-fluid">
            <div class="row">

                <div class="d-flex" id="wrapper" style="width: 100vw">

                    <!-- Sidebar -->
                    <div class="bg-light border-right" id="sidebar-wrapper">
                        <div class="sidebar-heading">nomClient </div>
                        <div class="list-group list-group-flush">
                            <a href="${pageContext.request.contextPath}/client/index.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="monitor"></span> Tableau de bord
                            </a>
                            <a href="${pageContext.request.contextPath}/client/monProfil.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="user"></span> Mon profil
                            </a>
                            <a href="${pageContext.request.contextPath}/client/devis.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="file-text"></span> Mes devis
                            </a>
                            <a href="${pageContext.request.contextPath}/client/commandes.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="shopping-cart"></span> Mes commandes
                            </a>
                            <a href="${pageContext.request.contextPath}/client/factures.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="square"></span> Mes factures
                            </a>
                            <a href="${pageContext.request.contextPath}/client/calendrier.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="calendar"></span> Calendrier
                            </a>
                            <a href="${pageContext.request.contextPath}/client/inbox.jsp" class="list-group-item list-group-item-action bg-light">
                                <span data-feather="inbox"></span>Inbox
                            </a>
                        </div>
                    </div>
                    <div id="page-content-wrapper">
                        <br>
