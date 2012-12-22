$(document).ready(function() {
    $(".tablesorter").tablesorter();

    //When page loads...
    $(".tab_content").hide();
    //Hide all content
    $("ul.tabs li:first").addClass("active").show();
    //Activate first tab
    $(".tab_content:first").show();
    //Show first tab content

    //On Click Event
    $("ul.tabs li").click(function() {
        $("ul.tabs li").removeClass("active");
        //Remove any "active" class
        $(this).addClass("active");
        //Add "active" class to selected tab
        $(".tab_content").hide();
        //Hide all tab content
        var activeTab = $(this).find("a").attr("href");
        //Find the href attribute value to identify the active tab + content
        $(activeTab).fadeIn();
        //Fade in the active ID content
        return false;
    });
    f_disableImageDiv();

});

function f_CLoseMessageBox(e) {
    var parentElement = e.parentNode;
    //alert(parentElement.className);
    parentElement.innerHTML = '';
    parentElement.style.display = 'none';

}

function f_disableImageDiv() {
    var el = document.getElementById('imagediv');
    if (el) {
        var all = el.getElementsByTagName('input');
        var i;
        for ( i = 0; i < all.length; i++) {
            all[i].disabled = true;
        }
    }

}

function f_enableImageDiv() {
    var el = document.getElementById('imagediv'), all = el.getElementsByTagName('input'), i;
    for ( i = 0; i < all.length; i++) {
        all[i].disabled = false;
    }
}

function f_officeImage() {
    $(document).ready(function() {
        $(".officeImg").attr("width", "50px");
        $(".officeImg").attr("height", "50px");
        $(".pdf").attr("src", "images/office_icns/pdf_icn.png");
        $(".xlsx").attr("src", "images/office_icns/xlsx_icn.png");
        $(".docx").attr("src", "images/office_icns/docx_icn.png");
        $(".rtf").attr("src", "images/office_icns/rtf_icn.png");
        $(".csv").attr("src", "images/office_icns/csv_icn.png");
    });
}