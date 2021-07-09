<?php

include 'connect.php';

$query= " SELECT * FROM question ";

$stmt = $connection->prepare($query);
$stmt-> execute();

$lottie = array();

while($row = $stmt -> fetch(PDO::FETCH_ASSOC)){

    $banner = array();
    $banner['id'] = $row['id'];
    $banner['title'] = $row['title'];
    $banner['description'] = $row['description'];
    $lottie[] = $banner;

}

echo json_encode($lottie);
