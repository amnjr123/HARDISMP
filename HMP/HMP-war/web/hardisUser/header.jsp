<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="GestionUtilisateur.UtilisateurHardis"%>
<% UtilisateurHardis u = (UtilisateurHardis) session.getAttribute("sessionHardis"); %>
<% UtilisateurHardis ua = (UtilisateurHardis) session.getAttribute("sessionAdministrateur");%>
<!doctype html>
<html lang="en">
    <head>
        <meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title><%=(u.getNom().toUpperCase() + ' ' + u.getPrenom())%></title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/dashboard.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/simple-sidebar.css" rel="stylesheet">
    </head>
    <body>  

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
                        <div class="sidebar-heading"><%=(u.getNom().toUpperCase() + ' ' + u.getPrenom())%></div>
                        <div class="list-group list-group-flush">
                            <a href="${pageContext.request.contextPath}/hardisUser/index.jsp" class="list-group-item d-flex justify-content-between bg-light">
                                <span><span data-feather="monitor"></span> Tableau de bord</span>
                            </a>
                            <a href="${pageContext.request.contextPath}/ServletUtilisateurHardis?action=monProfil" class="list-group-item d-flex justify-content-between bg-light">
                                <span><span data-feather="user"></span> Mon profil</span>
                            </a>
                            <a href="${pageContext.request.contextPath}/ServletUtilisateurHardis?action=planning" class="list-group-item d-flex justify-content-between bg-light">
                                <span><span data-feather="calendar"></span> Planning</span>
                            </a>
                            <a href="${pageContext.request.contextPath}/ServletUtilisateurHardis?action=offres" class="list-group-item d-flex justify-content-between bg-light">
                                <span><span data-feather="book-open"></span> Catalogue</span>
                            </a>
                            <a  class="list-group-item d-flex justify-content-between align-items-center bg-light" data-toggle="collapse" href="#collapseDevis" role="button" aria-expanded="false" aria-controls="collapseDevis" >
                                <span><span data-feather="clipboard"></span> Devis</span>
                                <span><span data-feather="chevron-down"></span></span>  
                            </a>
                            <div class="collapse" id="collapseDevis">
                                <a href="${pageContext.request.contextPath}/hardisUser/index.jsp" class="list-group-item list-group-item-action bg-light">
                                    <span data-feather="file-plus"></span> Mes devis en cours
                                </a>
                                <a href="${pageContext.request.contextPath}/hardisUser/index.jsp" class="list-group-item list-group-item-action align-items-center bg-light">
                                    <span data-feather="file-text"></span> Mes devis terminés
                                    <span class="badge badge-primary badge-pill"></span>
                                </a>
                                <a href="${pageContext.request.contextPath}/hardisUser/index.jsp" class="list-group-item list-group-item-action bg-light">
                                    <span data-feather="layers"></span> Voir tous les devis
                                    <span class="badge badge-primary badge-pill"></span>
                                </a>
                            </div>
                            <%
                                if (ua != null) {
                            %>
                            <a href="${pageContext.request.contextPath}/ServletAdministrateur" class="list-group-item d-flex justify-content-between bg-light">
                                <span><span data-feather="sliders"></span> Interface d'administration</span>
                            </a>
                            <%
                                }
                            %>
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
