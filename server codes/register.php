<?php

include 'connect.php';


if($_POST){

    $username = filter_input(INPUT_POST , 'username' , FILTER_SANITIZE_STRING);
    $email = filter_input(INPUT_POST , 'email' , FILTER_SANITIZE_STRING);
    $phone =filter_input(INPUT_POST , 'phone' , FILTER_SANITIZE_STRING);
    $password=filter_input(INPUT_POST , 'password' , FILTER_SANITIZE_STRING);


    $response = [];


    $userQuery = $connection->prepare("SELECT * FROM users WHERE phone = ?  OR username = ? OR email = ? ");
    $userQuery->execute(array($phone , $username , $email));
//$query= $userQuery->fetch();

    if($userQuery->rowCount()!=0){

        $response['status'] = false;
        $response['message'] = "کاربری با این مشخصات در سیستم ثبت شده";

    }else{

        $insertAccount = 'INSERT INTO users (username,email , phone, password) VALUES (:username , :email , :phone , :password)';
        $statment = $connection->prepare($insertAccount);

        try{


            $statment->execute([

                ':username' => $username,
                ':email' => $email,
                ':phone' => $phone,
                ':password' => md5($password)
            ]);


            $response['status'] = true;
            $response['message'] = "ثبت نام با موفقیت انجام شد";
            $response['data'] = [

                'username' => $username,
                'email' => $email,
                'phone' => $phone
            ];


        }catch(Exception $e){
            die($e->getMessage());
        }

    }

    $json = json_encode($response , JSON_PRETTY_PRINT);

    echo $json;

}

