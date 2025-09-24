<!DOCTYPE html>
<html>
<head>
<title>Stat Submission and Viewing</title>
<link href="rlstatchecker.css" type="text/css" rel="stylesheet">
</head>
<body>
    <?php
    //Creates the variables
    if ($_POST["gamertag"] && $_POST["wins"] && $_POST["goals"] && $_POST["assists"] && $_POST["mvps"] && $_POST["goalshotratio"] && $_POST["shots"] && $_POST["saves"]) {

        $gamertag = $_POST["gamertag"];
        $wins = $_POST["wins"];
        $goals = $_POST["goals"];
        $assists = $_POST["assists"];
        $mvps = $_POST["mvps"];
        $goalshotratio = $_POST["goalshotratio"];
        $shots = $_POST["shots"];
        $saves = $_POST["saves"];
    }

    $user_name=$_REQUEST["gamertag"];
    //Created database
    $servername = "sql5.freemysqlhosting.net";
    $database = "sql5750157";
    $username = "sql5750157";
    $password = "TrL3uXES2k";

    $conn = mysqli_connect ($servername,$username,$password,$database);
    if (!$conn) {
        die ("Connection Failed: " . mysqli_connect_error());
    }
    echo "Connected successfully";
    //Add data to database
    $sql = "INSERT INTO stats (gamertag, wins, goals, assists, mvps, goalshotratio, shots, saves)
        VALUES ('$gamertag', $wins, $goals, $assists, $mvps, $goalshotratio, $shots, $saves)";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
    ?>
    <h1>Submitted Stats</h1>
    <p><?= $gamertag ?>'s data has been added</p>
    <table>
            <tr>
                Gamertag:
                <td>
                    <?= $gamertag ?>
                </td>
            </tr>
            <tr>
                Wins:
                <td>
                    <?= $wins ?>
                </td>
            </tr>
            <tr>
                Goals:
                <td>
                    <?= $goals ?>
                </td>
            </tr>
            <tr>
                Assists:
                <td>
                    <?= $assists ?>
                </td>
            </tr>
            <tr>
                MVPs:
                <td>
                    <?= $mvps ?>
                </td>
            </tr>
            <tr>
                GoalShotRatio:
                <td>
                    <?= $goalshotratio ?>
                </td>
            </tr>
            <tr>
                Shots:
                <td>
                    <?= $shots ?>
                </td>
            </tr>
            <tr>
                Saves:
                <td>
                    <?= $saves ?>
                </td>
            </tr>       
        </table>
</body>
</html>
