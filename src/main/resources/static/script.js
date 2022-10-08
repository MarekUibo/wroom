$(document).ready(function(){
    $(".alert-success, .alert-danger").fadeTo(5000, 1000).slideUp(1000, function () {
        $(".alert-success, .alert-danger").slideUp(1000);
    });

    $(document).on('click', '.nav-item a', function(e) {
        $(this).addClass('active').siblings().removeClass('active');
    });
});

function getTotalAmount() {
    var carId = document.getElementById("car").value;
    var dateFrom = document.getElementById("dateFrom").value;
    var dateTo = document.getElementById("dateTo").value;

    $.ajax({
        url: "/booking/get-total-amount?carId=" + carId + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo,
        type: "GET",
        success: function (data) {
            console.log(data);
            document.getElementById("totalAmount").value = data;
        }
    })
}