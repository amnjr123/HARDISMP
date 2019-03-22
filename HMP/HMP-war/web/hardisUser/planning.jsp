<%@page import="GestionUtilisateur.Disponibilite"%>
<%@page import="java.util.Collection"%>
<jsp:include page="header.jsp"/>

<style>
    <jsp:include page="../css/custom/fullcalendar.css"/>
    <jsp:include page="../css/bootstrap4-toggle.css"/>
</style>
<jsp:useBean id="listDispo" scope="request" class="java.util.Collection"></jsp:useBean>
<%Collection<Disponibilite> listDisponibilite = listDispo;%>

<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">

    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Dashboard</h1>
    </div>

    <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletUtilisateurHardis">
        <div class="form-row">
            <div class="col-md">
                <input type="date" class="form-control" name="jourDisponible">
            </div>
            <div class="col-md">
                <input name="rgpd" data-toggle="toggle" data-size="lg" type="checkbox" value="oui" required="true" data-onstyle="warning" data-on="Matin" data-off="Après-midi" data-width="200">
                <input type="hidden" name="action" value="ajouterDisponibilite">x
                <button type="submit" class="btn btn-success">Ajouter disponibilité</button>
            </div>
        </div>
    </form>
    <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletUtilisateurHardis">  
        <div class="form-row">
            <div class="col-md">
         <div class="form-group">
                        <label for="selectOffre">Offre *</label>
                        <select name="offres" class="form-control selectpicker" id="selectOffre" data-width="auto" multiple show-tick>
                            <option disabled>Choisir les disponibilités</option>
                            <%for (Disponibilite d : listDisponibilite) {%>
                                <option value="<%=d.getId()%>"><%=d.getDateDebut()%></option>
                            <%}%>                       
                        </select>
                    </div>            </div>
            <div class="col-md">
                <input type="hidden" name="action" value="ajouterDisponibilite">x
                <button type="submit" class="btn btn-warning">Supprimer disponibilité</button>
            </div>
        </div>
    </form>

    <div id='calendar'></div>

</main>
<jsp:include page="footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/fullcalendar/jquery-ui.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/fullcalendar/fullcalendar.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/fullcalendar/fr.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap4-toggle.min.js" type="text/javascript"></script>

<script>

    $(document).ready(function () {
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();
        /*  className colors
         className: default(transparent), important(red), chill(pink), success(green), info(blue)
         */

        /* initialize the external events
         -----------------------------------------------------------------*/

        $('#external-events div.external-event').each(function () {

            // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
            // it doesn't need to have a start or end
            var eventObject = {
                title: $.trim($(this).text()) // use the element's text as the event title
            };

            // store the Event Object in the DOM element so we can get to it later
            $(this).data('eventObject', eventObject);

            // make the event draggable using jQuery UI
            $(this).draggable({
                zIndex: 999,
                revert: true, // will cause the event to go back to its
                revertDuration: 0  //  original position after the drag
            });

        });


        /* initialize the calendar
         -----------------------------------------------------------------*/

        var calendar = $('#calendar').fullCalendar({

            header: {
                left: 'title',
                center: 'agendaDay,agendaWeek,month',
                right: 'prev,next today'
            },
            lang: 'fr',
            editable: true,
            firstDay: 1, //  1(Monday) this can be changed to 0(Sunday) for the USA system
            selectable: true,
            defaultView: 'month',

            axisFormat: 'h:mm',
            columnFormat: {
                month: 'ddd', // Mon
                week: 'ddd d', // Mon 7
                day: 'dddd M/d', // Monday 9/7
                agendaDay: 'dddd d'
            },
            titleFormat: {
                month: 'MMMM yyyy', // September 2009
                week: "MMMM yyyy", // September 2009
                day: 'MMMM yyyy'                  // Tuesday, Sep 8, 2009
            },
            allDaySlot: false,
            selectHelper: true,
            select: function (start, end, allDay) {
                var title = prompt('Event Title:');
                if (title) {
                    calendar.fullCalendar('renderEvent',
                            {
                                title: title,
                                start: start,
                                end: end,
                                allDay: allDay
                            },
                            true // make the event "stick"
                            );
                }
                calendar.fullCalendar('unselect');
            },
            droppable: true, // this allows things to be dropped onto the calendar !!!
            drop: function (date, allDay) { // this function is called when something is dropped

                // retrieve the dropped element's stored Event Object
                var originalEventObject = $(this).data('eventObject');

                // we need to copy it, so that multiple events don't have a reference to the same object
                var copiedEventObject = $.extend({}, originalEventObject);

                // assign it the date that was reported
                copiedEventObject.start = date;
                copiedEventObject.allDay = allDay;

                // render the event on the calendar
                // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }

            },

            events: [
                {
                    title: 'All Day Event',
                    start: new Date(y, m, 1)
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: new Date(y, m, d - 3, 16, 0),
                    allDay: false,
                    className: 'info'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: new Date(y, m, d + 4, 16, 0),
                    allDay: false,
                    className: 'info'
                },
                {
                    title: 'Meeting',
                    start: new Date(y, m, d, 10, 30),
                    allDay: false,
                    className: 'important'
                },
                {
                    title: 'Lunch',
                    start: new Date(y, m, d, 12, 0),
                    end: new Date(y, m, d, 14, 0),
                    allDay: false,
                    className: 'important'
                },
                {
                    title: 'Birthday Party',
                    start: new Date(y, m, d + 1, 19, 0),
                    end: new Date(y, m, d + 1, 22, 30),
                    allDay: false,
                },
                {
                    title: 'Click for Google',
                    start: new Date(y, m, 28),
                    end: new Date(y, m, 29),
                    url: 'http://google.com/',
                    className: 'success'
                }
            ],
        });


    });

</script>