<?php
$servername = "sql5.freemysqlhosting.net";
$database = "sql5750157";
$username = "sql5750157";
$password = "TrL3uXES2k";

$conn = mysqli_connect($servername, $username, $password, $database);
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
//Searches by gamertag inputted
//Creates a table to view stats
if (isset($_POST['search_gamertag']) && !empty($_POST['search_gamertag'])) {
    $search_gamertag = mysqli_real_escape_string($conn, $_POST['search_gamertag']);

    $sql = "SELECT * FROM stats WHERE gamertag = '$search_gamertag'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        echo "<h2>Search Results for Gamertag: $search_gamertag</h2>";
        echo "<table border='1'>
                <tr>
                    <th>Gamertag</th>
                    <th>Wins</th>
                    <th>Goals</th>
                    <th>Assists</th>
                    <th>MVPs</th>
                    <th>GoalShotRatio</th>
                    <th>Shots</th>
                    <th>Saves</th>
                    <th>Submission Time</th>
                </tr>";

        while ($row = $result->fetch_assoc()) {
            echo "<tr>
                    <td>{$row['gamertag']}</td>
                    <td>{$row['wins']}</td>
                    <td>{$row['goals']}</td>
                    <td>{$row['assists']}</td>
                    <td>{$row['mvps']}</td>
                    <td>{$row['goalshotratio']}</td>
                    <td>{$row['shots']}</td>
                    <td>{$row['saves']}</td>
                    <td>{$row['submission_time']}</td>
                  </tr>";
        }
        echo "</table>";
    } else {
        echo "<p>No results found for Gamertag: $search_gamertag</p>";
    }
} else {
    echo "<p>Please enter a Gamertag to search.</p>";
}

mysqli_close($conn);
?>
