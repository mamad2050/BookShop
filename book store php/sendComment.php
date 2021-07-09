<?php

include 'connect.php';


if($_POST){

$id_book = filter_input(INPUT_POST , 'id_book' , FILTER_SANITIZE_STRING);	
$username_user =filter_input(INPUT_POST , 'username_user' , FILTER_SANITIZE_STRING);
$description=filter_input(INPUT_POST , 'description' , FILTER_SANITIZE_STRING);
$date=filter_input(INPUT_POST , 'date' , FILTER_SANITIZE_STRING);
$rating=filter_input(INPUT_POST , 'rating' , FILTER_SANITIZE_STRING);
$positive=filter_input(INPUT_POST , 'positive' , FILTER_SANITIZE_STRING);
$negative=filter_input(INPUT_POST , 'negative' , FILTER_SANITIZE_STRING);
$title=filter_input(INPUT_POST , 'title' , FILTER_SANITIZE_STRING);
	
$response = [];


	
	$insertAccount = 'INSERT INTO comment (id_book , username_user, description ,date , rating ,  positive , negative , title ) 
	VALUES (:id_book , :username_user , :description , :date , :rating , :positive , :negative , :title)';
	$statment = $connection->prepare($insertAccount);
	
	try{
		
		$statment->execute([
		':id_book' => $id_book,
		':username_user' => $username_user,
		':description' => $description,
		':date' => $date,
		':rating' => $rating,
		':positive' => $positive,
		':negative' => $negative,
		':title' => $title
		]);
		
		
		$response['status'] = true;
	    $response['message'] = "دیدگاه شما با موفقیت ارسال شد";
		
		
	}catch(Exception $e){
		die($e->getMessage());
	}



$json = json_encode($response , JSON_PRETTY_PRINT);

echo $json;

}

?>