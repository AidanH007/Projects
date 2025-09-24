function submitForm() {
    var gt = document.getElementById("gamertag");
    var gtlabel = document.getElementById("gamertaglabel");
    var wins = document.getElementById("wins");
    var winslabel = document.getElementById("winslabel");
    var goals = document.getElementById("goals");
    var goalslabel = document.getElementById("goalslabel");
    var assists = document.getElementById("assists");
    var assistslabel = document.getElementById("assistslabel");
    var mvps = document.getElementById("mvps");
    var mvpslabel = document.getElementById("mvpslabel");
    var gsr = document.getElementById("goalshotratio");
    var gsrlabel = document.getElementById("goalshotratiolabel");
    var shots = document.getElementById("shots");
    var shotslabel = document.getElementById("shotslabel");
    var saves = document.getElementById("saves");
    var saveslabel = document.getElementById("saveslabel");
    var orderform = document.forms["orderForm"];

    // Validate form fields and change label colors accordingly
    if (gt.value) {
        gtlabel.style.color = "white";
    }
    if (wins.value) {
        winslabel.style.color = "white";
    }
    if (goals.value) {
        goalslabel.style.color = "white";
    }
    if (assists.value) {
        assistslabel.style.color = "white";
    }
    if (mvps.value) {
        mvpslabel.style.color = "white";
    }
    if (gsr.value) {
        gsrlabel.style.color = "white";
    }
    if (shots.value) {
        shotslabel.style.color = "white";
    }
    if (saves.value) {
        saveslabel.style.color = "white";
    }
//Checks for no values, starting from gamertag
    if (!gt.value) {
        gtlabel.style.color = "red";
    } else if (!wins.value) {
        winslabel.style.color = "red";
    } else if (!goals.value) {
        goalslabel.style.color = "red";
    } else if (!assists.value) {
        assistslabel.style.color = "red";
    } else if (!mvps.value) {
        mvpslabel.style.color = "red";
    } else if (!gsr.value) {
        gsrlabel.style.color = "red";
    } else if (!shots.value) {
        shotslabel.style.color = "red";
    } else if (!saves.value) {
        saveslabel.style.color = "red";
    } else if (gt.value.length <= 0) {
        alert("Please enter your gamertag.");
    } else if (Number(wins.value) < 0) {
        alert("Cannot be a negative value");
    } else if (Number(goals.value) < 0) {
        alert("Cannot be a negative value");
    } else if (Number(assists.value) < 0) {
        alert("Cannot be a negative value");
    } else if (Number(mvps.value) < 0) {
        alert("Cannot be a negative value");
    } else if (Number(gsr.value) < 0) {
        alert("Cannot be a negative value");
    } else if (Number(shots.value) < 0) {
        alert("Cannot be a negative value");
    } else if (Number(saves.value) < 0) {
        alert("Cannot be a negative value");
    } else {
        // Perform AJAX request to check for duplicate gamertags
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "check_gamertag.php", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onload = function () {
            if (xhr.status === 200) {
                if (xhr.responseText === "duplicate") {
                    alert("Error: Gamertag already exists. Please choose a different gamertag.");
                } else if (xhr.responseText === "available") {
                    // Proceed with form submission if the gamertag is available
                    if (confirm("Are you sure this is the correct information? If so, please click submit.")) {
                        orderform.submit(); // Submit the form
                    }
                } else {
                    alert("An error occurred while checking the gamertag.");
                }
            }
        };

        xhr.send("gamertag=" + encodeURIComponent(gt.value));
    }
}
