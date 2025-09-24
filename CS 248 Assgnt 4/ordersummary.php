<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtmlll/DTD/xhtmlll.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Order Summary</title>
        <link href="ordersummary.css" type="text/css" rel="stylesheet" />
    </head>

    <body>

    <?php
    // Checks if information was put into all parts of the form since they are necessary
    if($_POST["items"] && $_POST["firstname"] && $_POST["lastname"] && $_POST["street"] && $_POST["city"] && $_POST["state"] && $_POST["zip"])
    {
        // Setting up the variables
        $items=$_POST["items"];
        $firstname=$_POST["firstname"];
        $lastname=$_POST["lastname"];
        $street=$_POST["street"];
        $city=$_POST["city"];
        $state=$_POST["state"];
        $zip=$_POST["zip"];
        $total=0;
        $shipping=0;
        ?>
        <h1>Order Summary</h1>
        <p>Thank you <?= $firstname ?> for your order to:</p>
        <p><?= $street ?></p>
        <p><?= $city ?>, <?= $state ?> <?= $zip ?></p>

        <p>You ordered the following:</p>
        <table>
            <tr>
                <th>Item</th>
                <th>Price</th>
            </tr>
        <?php
        // Checks the items that utilize the item and price variables, prints them and the total price out
        foreach($items as $item=>$price)
        {
        ?>
        <tr>
            <td><?= $item ?></td>
            <td><?= $price ?></td>
        </tr>
        <?php
        $total+=$price;
        }
        // Changes the shipping price based on total price
        if ($total<30) {
            $shipping=3;
        } elseif ($total<60) {
            $shipping=6;
        }
        else {
            $shipping=4;
        }
        $total+=$shipping;
        ?>
        <tr>
            <td>Shipping</td>
            <td><?= $shipping ?></td>
        </tr>
        <tr>
            <td>Total</td>
            <td><?= $total ?></td>
        </tr>
    </table>
        
        <?php
    }
    // If a required part of the form isn't filled out, the user is linked back to the html page to correct it
    else
    {
        ?>
        <a href="controllersforsale.html">Go back and complete the form properly</a>
        <?php
    }
    ?>

    </body>
</html>