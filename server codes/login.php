<?php

include 'connect.php';


if($_POST){

    $username=@$_POST['username'] ?? '';
    $password=@$_POST['password'] ?? '';

    $response = [];


    $userQuery = $connection->prepare("SELECT * FROM users WHERE username = ? ");
    $userQuery->execute(array($username));
    $query= $userQuery->fetch();

    if($userQuery->rowCount()==0){

        $response['status'] = false;
        $response['message'] = "حساب کاربری یافت نشد";


    }else{

        $passwordDB = $query['password'];

        if(strcmp(md5($password),$passwordDB)===0){

            $response['status'] = true;
            $response['message'] = "Login Ok";
            $response['data'] = [
                'id' => $query['id'],
                'email' => $query['email'],
                'phone' => $query['phone'],
                'username' => $query['username']
            ];

        }else{

            $response['status'] = false;
            $response['message'] = "رمز عبور اشتباه است";

        }

    }


    $json = json_encode($response,JSON_PRETTY_PRINT);

    echo $json;

}

?>