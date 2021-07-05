<?php

include 'connect.php';

$query= " SELECT * FROM publisher ";

$stmt = $connection->prepare($query);
$stmt-> execute();

$publishers = array();

while($row = $stmt -> fetch(PDO::FETCH_ASSOC)){

    $pub = array();
    $pub['id'] = $row['id'];
    $pub['name'] = $row['name'];
    $pub['link_img'] = $row['link_img'];
    $publishers[] = $pub;

}

echo json_encode($publishers);
