<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>nomClient</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/custom/dashboard.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/simple-sidebar.css" rel="stylesheet">

    </head>

    <body>  





        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
            <button class="btn" style="background-color:transparent"  id="menu-toggle"><span data-feather="menu" color="white"></span></button>
            <a class="navbar-brand col-sm-3 col-md-3 col-lg-2 auto0" href="${pageContext.request.contextPath}/Servlet">Hardis Work Place</a>
          <!--  <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">-->
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
                    <!-- /#sidebar-wrapper -->

                    <!-- Page Content -->
                    <div id="page-content-wrapper">
                        <br>




                        <!--nav class="col-md-2 d-none d-md-block bg-light sidebar">
                          <div class="sidebar-sticky">
                            <ul class="nav flex-column" id="myTab" >
                              <li class="nav-item">
                                <a class="nav-link active" href="#">
                                  <span data-feather="home"></span>
                                  Menu
                                </a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link active" href="${pageContext.request.contextPath}/client/index.jsp">
                                  <span data-feather="monitor"></span>
                                  Tableau de bord
                                </a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/client/monProfil.jsp">
                                  <span data-feather="user"></span>
                                  Mon profil
                                </a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/client/devis.jsp">
                                  <span data-feather="file-text"></span>
                                  Devis
                                </a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/client/commandes.jsp">
                                  <span data-feather="shopping-cart"></span>
                                  Commandes
                                </a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/client/factures.jsp">
                                  <span data-feather="square"></span>
                                  Factures
                                </a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/client/calendrier.jsp">
                                  <span data-feather="calendar"></span>
                                  Calendrier
                                </a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/client/inbox.jsp">
                                  <span data-feather="inbox"></span>
                                  Inbox
                                </a>
                              </li>
                            </ul>
                
                
                          </div>
                        </nav-->
