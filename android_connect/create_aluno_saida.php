<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

// check for required fields
if (isset($_POST['aluno'])) {

    $aluno = $_POST['aluno'];

// Parametros para conexão
	require_once('db_connect.php');


    // mysql inserting a new row
    $result = mysqli_query($conexao,"INSERT INTO alunos_temp(aluno) VALUES('{$aluno}')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";

        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
