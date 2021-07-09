<?php

include 'connect.php';


if($_POST){

$id_book = filter_input(INPUT_POST , 'id_book' , FILTER_SANITIZE_STRING);	
$username_user =filter_input(INPUT_POST , 'username_user' , FILTER_SANITIZE_STRING);

	
$response = [];


	
	$insertAccount = 'INSERT INTO cart (id_book , username_user) VALUES (:id_book , :username_user)';
	$statment = $connection->prepare($insertAccount);
	
	try{
		
		$statment->execute([
		':id_book' => $id_book,
		':username_user' => $username_user
		]);
		
		
		$response['status'] = true;
	    $response['message'] = "اضافه شد";
		
		
	}catch(Exception $e){
		die($e->getMessage());
	}



$json = json_encode($response , JSON_PRETTY_PRINT);

echo $json;

}

?>