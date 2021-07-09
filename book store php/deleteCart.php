<?php

include 'connect.php';

$cart_id = @$_GET['cart_id'];

$query = "DELETE FROM cart WHERE cart_id=:cart_id ";
$res = $connection->prepare($query);
$res->bindParam(":cart_id" , $cart_id);
$res->execute();

if($res){

echo '{"status":"succes" , "message":"Ok"}';
	
	
}else{
	
	echo '{"status":"failed" , "message":"Nothing"}';

}




