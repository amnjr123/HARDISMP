<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="https://fr.snatchbot.me/sdk/webchat.min.js"></script>
<script> Init('?botID=49391&appID=webchat', 600, 600, 'https://dvgpba5hywmpo.cloudfront.net/media/image/JIod5vjYEQFaz3yMV5FgTC2GG', 'bubble', '#00AFF0', 90, 90, 62.99999999999999, '', '1', '#FFFFFF', '#FFFFFF', 0); /* for authentication of its users, you can define your userID (add &userID={login}) */</script>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function () {
            'use strict';

            window.addEventListener('load', function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');

                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>    
</body>
</html>