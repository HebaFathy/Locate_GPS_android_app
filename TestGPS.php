<?php
// array for JSON response
 //echo "GPS Location <br>";
 //echo "lat: ".$_GET['latitude'] . "  long:". $_GET['longitude']."<br>";
 // check for required fields
 if (isset($_GET['latitude']) && isset($_GET['longitude']) && isset($_GET['dev_id']) && isset($_GET['dev_mod'])) {
 
    $lat = doubleval($_GET['latitude']);
    $lon = doubleval($_GET['longitude']);
    $dev_id=  $_GET['dev_id'];
    $dev_mod = $_GET['dev_mod'];
    $datetime = /*date("Y-m-d H:i:s")*/date_create()->format('Y-m-d H:i:s');
    
    $response["success"] = 1;
    $conn=new mysqli('localhost','root','root','gpstest');
    if($conn->connect_error){
        die("Connection failed:". $conn->connect_error);
    }
    $sql="INSERT INTO location (dev_id,dev_mod,lat,lon,date) VALUES ('$dev_id','$dev_mod','$lat','$lon','$datetime')";
    if($conn->multi_query($sql)==TRUE)
        $response["result"] = 1;
    else
        $response["result"] = 0;	
    $conn->close();
    // echoing JSON response
    echo json_encode($response);
    
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    // echoing JSON response
    echo json_encode($response);
}
?>