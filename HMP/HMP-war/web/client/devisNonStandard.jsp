<%@page import="GestionDevis.Intervention"%>
<%@page import="java.util.Calendar"%>
<%@page import="Enum.StatutDevis"%>
<%@page import="GestionDevis.Communication"%>
<%@page import="GestionDevis.Proposition"%>
<%@page import="java.util.Locale"%>
<%@page import="GestionDevis.HistoriqueUtilisateurDevis"%>
<%@page import="GestionUtilisateur.UtilisateurHardis"%>
<%@page import="GestionDevis.Devis"%>
<%@page import="GestionDevis.DevisStandard"%>
<%@page import="GestionDevis.DevisNonStandard"%>
<%@page import="GestionCatalogue.Livrable"%>
<%@page import="java.util.Date"%>
<%@page import="GestionCatalogue.Offre"%>
<%@page import="GestionCatalogue.ServiceStandard"%>
<%@page import="GestionCatalogue.ServiceNonStandard"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listHistoriqueUtilisateurDevis" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listCommunications" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listInterv" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:include page="header.jsp"/>
<style>
    <jsp:include page="../css/bootstrap4-toggle.css"/>
    <jsp:include page="../css/custom/fullcalendar/main.css"/>
    <jsp:include page="../css/custom/fullcalendar/daygrid.css"/>
    <jsp:include page="../css/custom/fullcalendar/timegrid.css"/>
    <jsp:include page="../css/custom/fullcalendar/list.css"/>
    hr {
        margin-top: 1rem;
        margin-bottom: 1rem;
        border: 0;
        border-top: 1px solid rgba(0, 0, 0, 0.1);
    }
</style>

<%DevisNonStandard d = (DevisNonStandard) request.getAttribute("devisNonStandard");
    Collection<Communication> listeMessages = listCommunications;
    Collection<HistoriqueUtilisateurDevis> listeHistoriqueUtilisateurDevis = listHistoriqueUtilisateurDevis;
    java.text.DateFormat dfjour = new java.text.SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
    java.text.DateFormat dfheure = new java.text.SimpleDateFormat("dd/MM/yyyy à HH:mm", Locale.FRENCH);
%>
<%Collection<Intervention> listIntervention = listInterv;%>

<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Gestion du devis personnalisé</h1>
    </div>

    <div class="card mb-3">
        <div class="card-header" style="background-color: #b8daff;">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2"><i style="width:32px;height: 32px" data-feather="activity"></i>&nbsp;Récapitulatif</h1>
                <div class="btn-toolbar">
                </div>
            </div>
        </div>
        <%--Warning Or Sucess--%>   
        <% String error = (String) request.getAttribute("msgError");
            if (request.getAttribute("msgError") != null) {%>
        <div class="alert alert-danger alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attention !</strong>&nbsp;<%=(error)%>.
        </div>
        <%}%>

        <% String success = (String) request.getAttribute("msgSuccess");
            if (request.getAttribute("msgSuccess") != null) {%>
        <div class="alert alert-success alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <%=(success)%>.
        </div>
        <%}%>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nom Client</th>
                            <th scope="col">Entreprise</th>
                            <th scope="col">Service</th>
                            <th scope="col">Montant</th>
                            <th scope="col">Etat</th>
                            <th scope="col">Date de création</th>
                                <%if (d.getStatut() == StatutDevis.valueOf("Envoye") || d.getStatut() == StatutDevis.valueOf("Valide") || d.getStatut() == StatutDevis.valueOf("Refuse") || d.getStatut() == StatutDevis.valueOf("AcompteRegle") || d.getStatut() == StatutDevis.valueOf("PrestationTerminee")) {%>
                            <th scope="col">Date d'envoi au client</th>
                                <%}
                                    if (d.getStatut() == StatutDevis.valueOf("Valide") || d.getStatut() == StatutDevis.valueOf("Refuse") || d.getStatut() == StatutDevis.valueOf("AcompteRegle") || d.getStatut() == StatutDevis.valueOf("PrestationTerminee")) {%>
                            <th scope="col">Date de réponse</th>
                                <%}
                                    if (d.getStatut() == StatutDevis.valueOf("AcompteRegle") || d.getStatut() == StatutDevis.valueOf("PrestationTerminee")) {%>
                            <th scope="col">Date de versement de l'acompte</th>
                                <%}
                                    if (d.getStatut() == StatutDevis.valueOf("PrestationTerminee")) {%>
                            <th scope="col">Date de versement du restant</th>
                                <%}%>
                            <th scope="col">Reponsable du Devis</th>
                        </tr>
                    </thead>
                    <tbody>                                     
                        <tr>
                            <td><%=d.getId()%></td>
                            <td><%=d.getClient().getNom()%> <%=d.getClient().getPrenom()%></td>
                            <td><%=d.getClient().getEntreprise().getNom()%></td>
                            <td><%=d.getServiceNonStandard().getNom()%></td>
                            <td><%=d.getMontant()%></td>
                            <td><%=d.getStatut()%></td>
                            <td><%=dfjour.format(d.getDateCreation())%></td>
                            <%if (d.getStatut() == StatutDevis.valueOf("Envoye") || d.getStatut() == StatutDevis.valueOf("Valide") || d.getStatut() == StatutDevis.valueOf("Refuse") || d.getStatut() == StatutDevis.valueOf("AcompteRegle") || d.getStatut() == StatutDevis.valueOf("PrestationTerminee")) {%>
                            <td><%=dfjour.format(d.getDateEnvoi())%></td>
                            <%}
                                if (d.getStatut() == StatutDevis.valueOf("Valide") || d.getStatut() == StatutDevis.valueOf("Refuse") || d.getStatut() == StatutDevis.valueOf("AcompteRegle") || d.getStatut() == StatutDevis.valueOf("PrestationTerminee")) {%>
                            <td><%=dfjour.format(d.getDateReponse())%></td>
                            <%}
                                if (d.getStatut() == StatutDevis.valueOf("AcompteRegle") || d.getStatut() == StatutDevis.valueOf("PrestationTerminee")) {%>
                            <td><%=dfjour.format(d.getDateAcompte())%></td>
                            <%}
                                if (d.getStatut() == StatutDevis.valueOf("PrestationTerminee")) {%>
                            <td><%=dfjour.format(d.getDateReglement())%></td>
                            <%}%>
                            <td>
                                <p><%=d.getUtilisateurHardis().getNom() + " " + d.getUtilisateurHardis().getPrenom()%></p>
                                <p><a href="#" data-toggle="modal" data-target="#historiqueUtilisateur" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="list"></i> Voir l'historique</a></p>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <%if (d.getStatut() == StatutDevis.valueOf("Refuse")) {%>
    <div class="card mb-3">
        <div class="card-header"style="background-color: #b8daff;">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2"><i style="width:32px;height: 32px" data-feather="file-text"></i>&nbsp;Documents</h1>
                <div class="btn-toolbar">
                </div>
            </div>
        </div>
        <div class="card-body">
            <p><%=d.getMotifRefus()%></p>
        </div>
    </div>
    <%}%>

    <div class="card mb-3">
        <div class="card-header" style="background-color: #b8daff;">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2"><i style="width:32px;height: 32px" data-feather="file-text"></i>&nbsp;Documents</h1>
                <div class="btn-toolbar">
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Document</td>
                            <th>Date</td>
                            <th>Télécharger</td>
                        </tr>
                    </thead>
                    <tbody>
                        <%if (!d.getPropositions().isEmpty()) {
                            for (Proposition p : d.getPropositions()) {%>
                        <tr>
                            <td>Proposition commerciale n°<%=p.getId()%></td>
                            <td><%=dfjour.format(p.getDateDebutValidite())%></td>
                            <td>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="download"></i></a>
                            </td>
                        </tr>
                        <%}
                            }
                            if (d.getStatut() != StatutDevis.valueOf("Incomplet") && d.getStatut() != StatutDevis.valueOf("ReponseEnCours")) {%>
                        <tr>
                            <td>Devis</td>
                            <td><%=dfjour.format(d.getDateEnvoi())%></td>
                            <td>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="download"></i></a>
                            </td>
                        </tr>
                        <%}
                        if (d.getStatut() != StatutDevis.valueOf("Incomplet") && d.getStatut() != StatutDevis.valueOf("ReponseEnCours") && d.getStatut() != StatutDevis.valueOf("Envoye") && d.getStatut() != StatutDevis.valueOf("Refuse")) {%>         
                        <tr>
                            <td>Bon de commande</td>
                            <td><%=dfjour.format(d.getDateReponse())%></td>
                            <td>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="download"></i></a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="card mb-3">
        <div class="card-header" style="background-color: #b8daff;">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2"><i data-feather="message-square" style="width:32px;height: 32px"></i>&nbsp;Messagerie</h1>
                <div class="btn-toolbar">
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="messaging">
                <div class="mesgs" style="width: 100% !important">
                    <div class="msg_history" id="zoneMessages">
                        <%for (Communication comm : listeMessages) {
                            if (comm.getClient() != null) {%>
                        <div class="outgoing_msg">
                            <div class="sent_msg">
                                <p><%=comm.getContenu()%></p>
                                <span class="time_date"><%=dfheure.format(comm.getDateEnvoi())%></span> </div>
                        </div>
                        <%} else {%>
                        <div class="incoming_msg">
                            <div class="incoming_msg_img"> <img width="50" height="50" src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                            <div class="received_msg">
                                <div class="received_withd_msg">
                                    <p><%=comm.getContenu()%></p>
                                    <span class="time_date"><%=dfheure.format(comm.getDateEnvoi())%></span></div>
                            </div>
                        </div>
                        <%}
                        }%>
                    </div>
                    <div class="type_msg">
                        <div class="input_msg_write" id="newMessage">
                            <form method="POST" action="${pageContext.request.contextPath}/ServletClient" id="formulaire">
                                <input type="hidden" name="action" value="repondreMessageDevisNonStandard">
                                <input type="hidden" name="idConversation" value="<%=d.getConversation().getId()%>">
                                <input type="hidden" name="idDevis" value="<%=d.getId()%>">
                                <input name="message" type="text" class="write_msg" placeholder="Ecrivez votre message ici" />
                                <button class="msg_send_btn" type="submit"><i data-feather="send" aria-hidden="true"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-header"style="background-color: #b8daff;">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2"><i style="width:32px;height: 32px"  data-feather="navigation"></i>Actions</h1>
            </div>
        </div>
        <div class="card-body">
            <div class="row">
                <%if (d.getStatut() == StatutDevis.valueOf("Envoye")) {%>
                <div class="col-md">
                    <a href="${pageContext.request.contextPath}/ServletClient?action=accepterDevisNonStandard&id=<%=d.getId()%>" class="btn btn-lg btn-info btn-block">Accepter le devis&nbsp;<i style="width:24px;height: 24px" data-feather="check-circle"></i></a>&nbsp;
                </div>
                <div class="col-md">
                    <a data-toggle="modal" data-target="#refuserDevisNonStandard>" class="btn btn-lg btn-info btn-block">Refuser le devis&nbsp;<i style="width:24px;height: 24px" data-feather="x-circle"></i></a>&nbsp;
                </div>
                <div class="col-md">
                    <%}%>
                    <a data-toggle="modal" data-target="#replannifier>" class="btn btn-lg btn-info btn-block">Replannifier les interventions&nbsp;<i style="width:24px;height: 24px" data-feather="calendar"></i></a>&nbsp;
                </div>
            </div>
        </div>
    </div>

<%if(!listIntervention.isEmpty()){%>
    <div class="card">
        <div class="card-header"style="background-color: #b8daff;">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2"><i style="width:32px;height: 32px"  data-feather="navigation"></i>Interventions plannifiées</h1>
            </div>
        </div>
        <div class="card-body">
            <div id='calendar'></div>
        </div>
    </div>
<%}%>

<%--MODALS--%>
<form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletClient">  
    <div class="modal fade" id="refuser" tabindex="-2" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Refuser le devis</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="conditions" >Motif du refus</label>
                        <textarea rows="2" maxlength="250" name="motif" type="text" id="motif" class="form-control" placeholder="Merci d'expliquer ici la raison de votre refus du devis" required autofocus></textarea>
                        <div class="invalid-feedback">
                            Ce champ est obligatoire.
                        </div>
                    </div>
                </div>
                <div class="modal-footer ">
                    <input type="hidden" name="id" value="<%=d.getId()%>">
                    <input type="hidden" name="action" value="refuserDevis">
                    <button type="submit" class="btn btn-success">Valider</button>
                    <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>
</form>

<div class="modal fade" id="replannifier" tabindex="-2" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">

        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Repplannifier les interventions</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                </button>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer ">
                <%--<input type="hidden" name="id" value="<%=d.getId()%>">
                <input type="hidden" name="action" value="refuserDevis">
                <button type="submit" class="btn btn-success">Valider</button>
                <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="historiqueUtilisateur" tabindex="-2" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">

        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Historique des responsables du devis</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                </button>
            </div>
            <div class="modal-body">
                <%for (HistoriqueUtilisateurDevis h : listeHistoriqueUtilisateurDevis) {%>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Nom</th>
                            <th scope="col">Du</th>
                            <th scope="col">Au</th>
                        </tr>
                    </thead>
                    <tbody>                                     
                        <tr>
                            <td><%=h.getUtilisateurHardis().getPrenom()%> <%=h.getUtilisateurHardis().getNom()%></td>
                            <td><%=dfjour.format(h.getDateDebut())%></td>
                            <td>
                                <%
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(h.getDateFin());
                                    if (calendar.get(Calendar.YEAR) < 2100) {;
                                        out.print(dfjour.format(h.getDateFin()));
                                    } else {
                                        out.print("-");
                                    }%>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <%}%>
            </div>
        </div>
    </div>
</div>
</main>
<jsp:include page="footer.jsp"/>
<script src='${pageContext.request.contextPath}/js/fullcalendar/main.js'></script>
<script src="${pageContext.request.contextPath}/js/fullcalendar/locale-all.min.js" ></script>
<script src='${pageContext.request.contextPath}/js/fullcalendar/interaction.js'></script>
<script src='${pageContext.request.contextPath}/js/fullcalendar/daygrid.js'></script>
<script src='${pageContext.request.contextPath}/js/fullcalendar/timegrid.js'></script>
<script src='${pageContext.request.contextPath}/js/fullcalendar/list.js'></script>

<script>

    document.addEventListener('DOMContentLoaded', function () {
        var initialLocaleCode = 'fr';
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
            plugins: ['interaction', 'dayGrid', 'timeGrid', 'list'],
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
            },
            //defaultDate: '2019-03-12',
            locale: initialLocaleCode,
            buttonIcons: false, // show the prev/next text
            weekNumbers: true,
            navLinks: true, // can click day/week names to navigate views
            editable: false,
            eventLimit: true, // allow "more" link when too many events
            timeFormat: 'H:mm',
            events: [
    <%
        //Affichage des Interventions dans le calendrier
        for (Intervention interventionJava : listIntervention) {
            Calendar calDebut = Calendar.getInstance();
            calDebut.setTime(interventionJava.getDateInterventionDemandee());
            int yearDebut = calDebut.get(Calendar.YEAR);
            int monthDebut = calDebut.get(Calendar.MONTH);
            int dayDebut = calDebut.get(Calendar.DAY_OF_MONTH);     
    %>
                {
                    title: 'Intervention \n<%=interventionJava.getDevis().getClient().getEntreprise().getNom()%>',
                    start: new Date(<%=yearDebut%>,<%=monthDebut%>, <%=dayDebut%>, 8, 0),
                    end: new Date(<%=yearDebut%>,<%=monthDebut%>, <%=dayDebut%>, 18, 0),
                    allDay: false,
                    className: 'info',
                    backgroundColor : '#e74c3c',
                    displayEventEnd : true
                },
    <%}%>
            ],
            eventTimeFormat: {
                hour: 'numeric',
                minute: '2-digit',
            }
        });

        calendar.render();

    });

</script>