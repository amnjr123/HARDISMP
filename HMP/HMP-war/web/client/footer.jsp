</div>
</div> 
</div>
</div>


<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/feather.min.js"></script>
<script src="${pageContext.request.contextPath}/js/fileinput.js"></script>
<script src="https://fr.snatchbot.me/sdk/webchat.min.js"></script>
<script> Init('?botID=49391&appID=webchat', 600, 600, 'https://dvgpba5hywmpo.cloudfront.net/media/image/JIod5vjYEQFaz3yMV5FgTC2GG', 'bubble', '#00AFF0', 90, 90, 62.99999999999999, '', '1', '#FFFFFF', '#FFFFFF', 0); /* for authentication of its users, you can define your userID (add &userID={login}) */</script>
<script>
    //Icons
    feather.replace()

    //ProfilClient
    $(document).ready(function () {


        var readURL = function (input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('.avatar').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }


        $(".file-upload").on('change', function () {
            readURL(this);
        });
    });
</script>

<script>
    /*menu sidebar*/
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>


<!--script>
    $('#myTab a').on('click', function (e) {
e.preventDefault()
$(this).tab('show')
})
</script-->
</body>
</html>