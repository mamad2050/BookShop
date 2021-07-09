<?php

include 'connect.php';

$username_user = @$_GET['username_user'];

$products = array();
$query = "SELECT cart.cart_id , book.id , book.name , book.link_img , book.price , book.discount
FROM cart JOIN book ON cart.id_book=book.id WHERE cart.username_user=:username_user AND pay_off = 0";

$res = $connection->prepare($query);
$res->bindParam(":username_user" , $username_user);
$res->execute();
while($row=$res->fetch(PDO::FETCH_ASSOC)){

    
        
	$record = array();


    $discount = $row['discount'];
    $price = $row['price'];

    $value_discount = $price * $discount / 100;
    $final_price = $price - $value_discount;
    $record['final_price'] = $final_price;

	$record ["cart_id"] = $row["cart_id"];
	$record ["id_book"] = $row["id"];
	$record ["name"] = $row["name"];
	$record ["link_img"] = $row["link_img"];
	$record ["price"] = $row["price"];
	$record ["discount"] = $row["discount"];
	
	$products [] = $record;
	
}

echo json_encode($products);

?>