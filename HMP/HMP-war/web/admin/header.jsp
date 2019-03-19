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
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="${pageContext.request.contextPath}/Servlet">Hardis Work Place</a>
      <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="${pageContext.request.contextPath}/hardisUser/index.jsp">Quitter l'interface d'administration <span data-feather="log-out"></span> </a>
        </li>
      </ul>
    </nav>

    <div class="container-fluid">
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
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/users.jsp">
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
        </nav>
          