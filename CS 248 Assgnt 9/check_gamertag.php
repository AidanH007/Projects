<?php
$servername = "sql5.freemysqlhosting.net";
$database = "sql5750157";
$username = "sql5750157";
$password = "TrL3uXES2k";

$conn = mysqli_connect($servername, $username, $password, $database);
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
//Checks if the gamertag is a duplicate
if (isset($_POST['gamertag']) && !empty($_POST['gamertag'])) {
    $new_gamertag = mysqli_real_escape_string($conn, $_POST['gamertag']);

    $check_sql = "SELECT * FROM stats WHERE gamertag = '$new_gamertag'";
    $check_result = $conn->query($check_sql);

    if ($check_result->num_rows > 0) {
        echo "duplicate";
    } else {
        echo "available";
    }
} else {
    echo "error";
}

mysqli_close($conn);
?>
