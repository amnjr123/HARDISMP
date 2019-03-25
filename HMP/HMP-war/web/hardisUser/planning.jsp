<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="GestionUtilisateur.Disponibilite"%>
<%@page import="java.util.Collection"%>

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

<jsp:useBean id="listDispo" scope="request" class="java.util.Collection"></jsp:useBean>
<%Collection<Disponibilite> listDisponibilite = listDispo;%>

<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">

    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Planning</h1>
    </div>
    <center>
        <%--Modal error--%>
        <% String error = (String) request.getAttribute("msgError");
            if (request.getAttribute("msgError") != null) {%>
        <div class="alert alert-danger alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attention !</strong>&nbsp;<%=error%>.
        </div>
        <%}%>

        <% String success = (String) request.getAttribute("msgSuccess");
            if (request.getAttribute("msgSuccess") != null) {%>
        <div class="alert alert-success alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <%=success%>.
        </div>
        <%}%>
        <button class="btn btn-primary btn-lg" type="button" data-toggle="collapse" href="#collapseModifier" role="button" aria-expanded="false" aria-controls="collapseModifier" ><i style="width:32px; height:32px" data-feather="edit"></i></button>
    </center>
    <div class="container collapse" id="collapseModifier">
        <form novalidate class="form needs-validation" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletUtilisateurHardis">
            <div class="form-group">
                <label for="jourDisponible">Jour *</label>
                <input type="date" class="form-control" id="jourDisponible" name="jourDisponible">
            </div>
            <center>
                <div class="form-group">
                    <input name="choix" data-toggle="toggle" data-size="lg" type="checkbox" value="oui" required="true" data-onstyle="warning" data-on="Matin" data-off="Après-midi" data-width="220">
                    <input type="hidden" name="action" value="ajouterDisponibilite">
                </div>
            </center>
            <div class="form-group">
                <button type="submit" class="btn btn-success btn-block">Ajouter disponibilité</button>
            </div>
        </form>

        <form class="needs-validation form" novalidate role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletUtilisateurHardis">  

            <div class="form-group">
                <label for="selectOffre">Les disponibilités : </label>
                <select name="disponibilite" class="form-control" id="selectOffre" data-width="auto" show-tick>
                    <option disabled>Choisir les disponibilités</option>
                    <%java.text.DateFormat df = new java.text.SimpleDateFormat("EEEEE dd MMMM  à HH:mm (yyyy)", Locale.FRENCH);
                        for (Disponibilite d : listDisponibilite) {%>
                    <option value="<%=d.getId()%>">
                        <%=df.format(d.getDateDebut())%>
                    </option>
                    <%}%>                       
                </select>
            </div>          
            <div class="form-group">
                <input type="hidden" name="action" value="supprimerDisponibilite">
                <button type="submit" class="btn btn-warning btn-block">Supprimer la disponibilité</button>
            </div>
        </form>
                <%--
        <form class="needs-validation form" novalidate role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletUtilisateurHardis" enctype="multipart/form-data" multiple>  
            <input type="file" name="files" accept=".xlsx,.xls,image/*,.doc, .docx,.ppt, .pptx,.txt, .pdf, application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"  accept="">
            <input type="hidden" name="action" value="upload">
            <button type="submit" class="btn btn-danger btn-block">Upload files</button>
        </form>
                --%>
    </div>

    <hr/>
    <div id='calendar'></div>

</main>
<jsp:include page="footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/bootstrap4-toggle.min.js" type="text/javascript"></script>

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
        for (Disponibilite disponibilitesJava : listDisponibilite) {
            Calendar calDebut = Calendar.getInstance();
            calDebut.setTime(disponibilitesJava.getDateDebut());
            int yearDebut = calDebut.get(Calendar.YEAR);
            int monthDebut = calDebut.get(Calendar.MONTH);
            int dayDebut = calDebut.get(Calendar.DAY_OF_MONTH);
            int hourDebut = calDebut.get(Calendar.HOUR_OF_DAY);
            int minuteDebut = calDebut.get(Calendar.MINUTE);
            System.out.println(hourDebut);
            Calendar calFin = Calendar.getInstance();
            calFin.setTime(disponibilitesJava.getDateFin());
            int yearFin = calFin.get(Calendar.YEAR);
            int monthFin = calFin.get(Calendar.MONTH);
            int dayFin = calFin.get(Calendar.DAY_OF_MONTH);
            int hourFin = calFin.get(Calendar.HOUR_OF_DAY);
            int minuteFin = calFin.get(Calendar.MINUTE);
    %>
                {
                    title: 'Disponible',
                    start: new Date(<%=yearDebut%>,<%=monthDebut%>, <%=dayDebut%>, <%=hourDebut%>, <%=minuteDebut%>),
                    end: new Date(<%=yearFin%>, <%=monthFin%>, <%=dayFin%>, <%=hourFin%>, <%=minuteFin%>),
                    allDay: false,
                    className: 'info'
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