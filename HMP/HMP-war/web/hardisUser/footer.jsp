</div>
</div> 
</div>
</div>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/feather.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap4-toggle.min.js"></script>

<script>
    feather.replace()
</script>

<script>
    jQuery(function ($) {

        $(".sidebar-dropdown > a").click(function () {
            $(".sidebar-submenu").slideUp(200);
            if (
                    $(this)
                    .parent()
                    .hasClass("active")
                    ) {
                $(".sidebar-dropdown").removeClass("active");
                $(this)
                        .parent()
                        .removeClass("active");
            } else {
                $(".sidebar-dropdown").removeClass("active");
                $(this)
                        .next(".sidebar-submenu")
                        .slideDown(200);
                $(this)
                        .parent()
                        .addClass("active");
            }
        });

        $("#close-sidebar").click(function () {
            $(".page-wrapper").removeClass("toggled");
        });
        $("#show-sidebar").click(function () {
            $(".page-wrapper").addClass("toggled");
        });




    });
</script>

<script>
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