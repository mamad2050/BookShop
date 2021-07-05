<?php

include 'connect.php';

$query = "SELECT * FROM book WHERE off = 1 LIMIT 8";
$stmt = $connection->prepare($query);
$stmt->execute();

$product = array();
while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {

    $prod = array();

    $discount = $row['discount'];
    $price = $row['price'];

    $value_discount = $price * $discount / 100;
    $final_price = $price - $value_discount;

//    if ($discount == 0) {
//        $value_discount = 0;
//    }


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


    $product[] = $prod;

}
echo json_encode($product);