<?php

include 'connect.php';

$query= " SELECT * FROM lottie ";

$stmt = $connection->prepare($query);
$stmt-> execute();

$lottie = array();

while($row = $stmt -> fetch(PDO::FETCH_ASSOC)){

    $banner = array();
    $banner['id'] = $row['id'];
    $banner['link_lottie'] = $row['link_lottie'];
    $banner['description'] = $row['description'];
    $lottie[] = $banner;

}

echo json_encode($lottie);
