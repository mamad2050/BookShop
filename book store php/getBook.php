<?php

include 'connect.php';

$id = @$_POST["id"];

$query = "SELECT * FROM book WHERE id =:id";
$stmt = $connection->prepare($query);
$stmt->bindParam(":id", $id);
$stmt->execute();

$books = array();

while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {

    $prod = array();

    $discount = $row['discount'];
    $price = $row['price'];

    $value_discount = $price * $discount / 100;
    $final_price = $price - $value_discount;

    $prod['final_price'] = $final_price;
    $prod['id'] = $row['id'];
    $prod['name'] = $row['name'];
    $prod['category_id'] = $row['category_id'];
    $prod['price'] = $row['price'];
    $prod['discount'] = $row['discount'];
    $prod['link_img'] = $row['link_img'];
    $prod['publish_date'] = $row['publish_date'];
    $prod['sold'] = $row['sold'];
    $prod['author'] = $row['author'];
    $prod['pages'] = $row['pages'];
    $prod['genre'] = $row['genre'];
    $prod['description'] = $row['description'];
    $prod['publisher_id'] = $row['publisher_id'];
    $prod['publisher'] = $row['publisher'];    


    $books[] = $prod;

}

echo json_encode($books);