<?php

include "connect.php";



$query2 ="SELECT * FROM comment ";
$stmt2 =$connection ->prepare($query2);

$stmt2 ->execute();
$product=array();
while($row2 = $stmt2->fetch(PDO::FETCH_ASSOC)){

  $record=array();
  $record["id"]=$row2["id"];
  $record["id_book"]=$row2["id_book"];
  $record["username_user"]=$row2["username_user"];
  $record["description"]=$row2["description"];
  $record["date"]=$row2["date"];
  $record["rating"]=$row2["rating"];
  $record["positive"]=$row2["positive"];
  $record["negative"]=$row2["negative"];
  $record["confirmation"]=$row2["confirmation"];
  $record["suggest"]=$row2["suggest"];
  $record["title"]=$row2["title"];
  $product[]=$record;
}


 echo JSON_encode($product);

 ?>
