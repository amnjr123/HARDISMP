<%@page import="java.util.Locale"%>
<%@page import="GestionDevis.Communication"%>
<%@page import="GestionDevis.Conversation"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listConversations" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listCommunications" scope="request" class="java.util.Collection"></jsp:useBean>
<%--<jsp:useBean id="conversation" scope="request" class="GestionDevis.Conversation"></jsp:useBean>--%>
<jsp:include page="header.jsp"/>
<%
    Collection<Conversation> listeConversations = listConversations;
    Collection<Communication> listeMessages = listCommunications;
    Conversation conversationActive =  (Conversation) request.getAttribute("conversation");

%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Ma messagerie</h1>
    </div>
    <div class="container">
        <div class="messaging">
            <div class="inbox_msg">
                <div class="inbox_people">
                    <div class="inbox_chat" id="zoneConversations">
                        <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/mm/yyyy à HH:mm", Locale.FRENCH);
                            for (Conversation c : listeConversations) {%>
                        <a href="${pageContext.request.contextPath}/ServletClient?action=messages&idConversation=<%=c.getId()%>">
                            <div class="chat_list <% if (conversationActive != null) {
                                    if (c.getId() == conversationActive.getId()) {%>active_chat<%}
                                        }%>" id="conversation<%=c.getId()%>">
                                <div class="chat_people">
                                    <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                                    <div class="chat_ib">
                                        <h5><%if (c.getUtilisateurHardis() != null) {
                                                out.print(c.getUtilisateurHardis().getNom() + " " + c.getUtilisateurHardis().getPrenom());
                                            } else {%>Consultant Hardis<%}%> <span class="chat_date"><%=df.format(c.getCommunications().get(c.getCommunications().size() - 1).getDateEnvoi())%></span></h5>
                                            <p><span style="float:left"><%=c.getCommunications().get(c.getCommunications().size() - 1).getContenu()%></span><%if(c.getDevis()!=null){%><span style="color:#3498db;float:right">Devis n°<%=c.getDevis().getId()%></span><%}%></p>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <%}%>
                    </div>
                    <button id="newConversation" class="btn" type="button"><i data-feather="plus" aria-hidden="true"></i> Nouvelle conversation</button>
                </div>
                <div class="mesgs">
                    <div class="msg_history" id="zoneMessages">
                        <%if (conversationActive != null) {
                                for (Communication comm : listeMessages) {
                                    if (comm.getClient() != null) {%>
                                        <div class="outgoing_msg">
                                            <div class="sent_msg">
                                                <p><%=comm.getContenu()%></p>
                                                <span class="time_date"><%=df.format(comm.getDateEnvoi())%></span> </div>
                                        </div>
                                    <%} else {%>
                                        <div class="incoming_msg">
                                            <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                                            <div class="received_msg">
                                                <div class="received_withd_msg">
                                                    <p><%=comm.getContenu()%></p>
                                                    <span class="time_date"><%=df.format(comm.getDateEnvoi())%></span></div>
                                            </div>
                                        </div>
                        <%}
                                }
                            }%>
                    </div>
                    <div class="type_msg">
                        <div class="input_msg_write" id="newMessage">
                            <form method="POST" action="${pageContext.request.contextPath}/ServletClient" id="formulaire">
                                <%if (conversationActive == null) {%>
                                <input type="hidden" name="action" value="nouvelleConversation">
                                <input name="message" type="text" class="write_msg" placeholder="Posez votre question ici pour contacter un consultant Hardis" />
                                <%} else {%>
                                <input type="hidden" name="action" value="repondreMessage">
                                <input type="hidden" name="idConversation" value="<%=conversationActive.getId()%>">
                                <input name="message" maxlength="254" type="text" class="write_msg" placeholder="Ecrivez votre message ici" />
                                <%}%>
                                <button class="msg_send_btn" type="submit"><i data-feather="send" aria-hidden="true"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
<jsp:include page="footer.jsp"/>
<script>
    window.addEventListener("load", function (event) {
        $('#newConversation').on("click", function (e) {
            $('#zoneConversations').append('<div class="chat_list active_chat"><div class="chat_people"><div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div><div class="chat_ib"><p>Consultant Hardis</p></div></div></div>');

            var incomming = document.querySelectorAll(".incoming_msg");
            incomming.forEach(function(e){
                e.remove();
            });
            var outgoing = document.querySelectorAll(".outgoing_msg");
            outgoing.forEach(function(e){
                e.remove();
            });

            var formulaire = document.querySelector("#formulaire");
            formulaire.remove();
            $('#newMessage').append('<form method="POST" action="${pageContext.request.contextPath}/ServletClient" id="formulaire"><input type="hidden" name="action" value="nouvelleConversation"><input name="message" type="text" class="write_msg" placeholder="Posez votre question ici pour contacter un consultant Hardis" /><button class="msg_send_btn" type="submit"><i data-feather="send" aria-hidden="true"></i></button></form>');
        })
    })
</script>