<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locations</title>
    </head>
    <body>
    <center>
    <p style=" font-size: 18px">GPS Locations Received From Mobile Phones</p>
    <table border="1" border-spacing="2px"  padding="15px" style="width:50%"> 
    <tr style=" text-align: center;  background-color: #f1f1c1;">
    <!--<th>Device_id</th>-->
    <th>Device_model</th> 
    <th>Latitude</th>
    <th>Longitude</th>
    <th>Date</th>
    </tr>
    <?php
    $conn=new mysqli('localhost','root','root','gpstest');
        if($conn->connect_error){
            die("Connection failed:". $conn->connect_error);
        }
        $sql="select * from location";
        $res = $conn->query($sql);
        if($res->num_rows >0){
            while($row= $res->fetch_assoc())
            {
               echo "<tr style='text-align: center';>";
               //echo "<td>".$row["dev_id"]."</td>";
               echo "<td>".$row["dev_mod"]."</td>";
               echo "<td>".$row["lat"]."</td>";
               echo "<td>".$row["lon"]."</td>";
               echo "<td>".$row["date"]."</td>";
               echo "</tr>";
            }              
        }
        else {
           echo "0 results";      
        }
        $conn->close();

    ?>
    </table>
    </center>
 </body>
</html>