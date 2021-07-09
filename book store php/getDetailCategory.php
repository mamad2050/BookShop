<?php

include 'connect.php';

$id = @$_POST["id"];

$query = "SELECT * FROM category WHERE id =:id";
$stmt = $connection->prepare($query);
$stmt->bindParam(":id", $id);
$stmt->execute();

while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {

    $rec["id"] = $row["id"];
}

$query2 = "SELECT * FROM book WHERE category_id =:id ";
$stmt2 = $connection->prepare($query2);
$stmt2->bindParam(":id", $rec["id"]);
$stmt2->execute();

$books = array();

while ($row2 = $stmt2->fetch(PDO::FETCH_ASSOC)) {

    $prod = array();

    $prod['id'] = $row2['id'];
    $prod['name'] = $row2['name'];
    $prod['category_id'] = $row2['category_id'];
    $prod['price'] = $row2['price'];
    $prod['link_img'] = $row2['link_img'];

    $books[] = $prod;

}

echo json_encode($books);