<?php

include 'connect.php';

$query = "SELECT * FROM category LIMIT 7";
$stmt = $connection->prepare($query);
$stmt->execute();

$category = array();
while ($row = $stmt -> fetch(PDO::FETCH_ASSOC)) {

    $cat = array();
    $cat['id'] = $row['id'];
    $cat['name_en'] = $row['name_en'];
    $cat['name_fa'] = $row['name_fa'];
    $cat['link_img'] = $row['link_img'];
    $category[] = $cat;
    
}
echo json_encode($category);