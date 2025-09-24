function submitForm() {
    var fname = document.getElementById("firstname");
    var fnamelabel = document.getElementById("fnamelabel");
    var lname = document.getElementById("lastname");
    var lnamelabel = document.getElementById("lnamelabel");
    var street = document.getElementById("street");
    var streetlabel = document.getElementById("streetlabel");
    var city = document.getElementById("city");
    var citylabel = document.getElementById("citylabel");
    var state = document.getElementById("state");
    var zip = document.getElementById("zip");
    var items = document.getElementsByClassName("items");
    var noitemsselected = true;
    var cc = document.getElementsByName("credit");
    var noccselected = true;
    var orderform = document.forms["orderForm"];
    
    for (i = 0; i < items.length; i++) {
        if (items[i].checked) {
            noitemsselected = false;
        }
    }
    for (i = 0; i < cc.length; i++) {
        if (cc[i].checked) {
            noccselected = false;
        }
    }
    if (fname.value) {
        fnamelabel.style.color = "antiquewhite";
    }
    if (lname.value) {
        lnamelabel.style.color = "antiquewhite";
    }
    if (street.value) {
        streetlabel.style.color = "antiquewhite";
    }
    if (city.value) {
        citylabel.style.color = "antiquewhite";
    }

    if (!fname.value) {
        fnamelabel.style.color = "red";
    }
    else if (!lname.value) {
        lnamelabel.style.color = "red";
    }
    else if (!street.value) {
        streetlabel.style.color = "red";
    }
    else if (!city.value) {
        citylabel.style.color = "red";
    }
    else if (state.value.length != 2) {
        alert ("State must contain at least 2 letters");
    }
    else if (zip.value.length != 5) {
        alert ("Zip must contain 5 numbers");
    }
    else if (noitemsselected) {
        alert ("Please select an item");
    }
    else if (noccselected) {
        alert ("Please select a credit card");
    }
    else {
        if (confirm("Are you ready to submit your order?")) {
            orderform.submit();
        }
    }
}