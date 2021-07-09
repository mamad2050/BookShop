<?php

include 'connect.php';


$query2 = " SELECT * FROM book ";
$stmt2 = $connection->prepare($query2);
$stmt2->execute();

$books = array();

while ($row2 = $stmt2->fetch(PDO::FETCH_ASSOC)) {

    $prod = array();

    $discount = $row2['discount'];
    $price = $row2['price'];

    $value_discount = $price * $discount / 100;
    $final_price = $price - $value_discount;
    $prod['final_price'] = $final_price;
    $prod['id'] = $row2['id'];
    $prod['name'] = $row2['name'];
    $prod['category_id'] = $row2['category_id'];
    $prod['price'] = $row2['price'];
    $prod['discount'] = $row2['discount'];
    $prod['link_img'] = $row2['link_img'];
    $prod['publish_date'] = $row2['publish_date'];
    $prod['sold'] = $row2['sold'];
    $prod['author'] = $row2['author'];
    $prod['pages'] = $row2['pages'];
    $prod['genre'] = $row2['genre'];
    $prod['description'] = $row2['description'];
    $prod['publisher_id'] = $row2['publisher_id'];
    $prod['publisher'] = $row2['publisher'];

    $books[] = $prod;
}


echo json_encode($books);