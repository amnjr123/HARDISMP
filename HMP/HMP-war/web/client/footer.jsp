</div>
</div> 
</div>
</div>


<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/feather.min.js"></script>
<script src="${pageContext.request.contextPath}/js/fileinput.js"></script>
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