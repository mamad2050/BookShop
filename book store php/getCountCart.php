<?php

include 'connect.php';

$username_user = @$_GET['username_user'];

$query = "SELECT * FROM cart WHERE username_user =:username_user AND pay_off = 0 ";
$res = $connection->prepare($query);

$res ->bindParam(":username_user" , $username_user);
$res->execute();

echo '{"status":"succes" , "message":"'.$res->rowCount().'"}';


?>