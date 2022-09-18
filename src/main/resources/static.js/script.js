$(document).ready(function(){
    $(".alert-success, .alert-danger").fadeTo(5000, 1000).slideUp(1000, function () {
        $(".alert-success, .alert-danger").slideUp(1000);
    });

    $(document).on('click', '.nav-item a', function(e) {
        $(this).addClass('active').siblings().removeClass('active');
    });
});