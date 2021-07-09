<?php

include 'connect.php';

$query = "SELECT * FROM book WHERE news = 1 LIMIT 8";
$stmt = $connection->prepare($query);
$stmt->execute();

$news = array();
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

    $news[] = $prod;

}
echo json_encode($news);