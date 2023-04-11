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
if (isset($_POST['usuario']) && isset($_POST['nome']) && isset($_POST['nome_filho']) && isset($_POST['senha'])) {

    $usuario = $_POST['usuario'];
    $nome = $_POST['nome'];
    $nome_filho = $_POST['nome_filho'];
    $senha = $_POST['senha'];

// Parametros para conexão
	require_once('db_connect.php');


    // mysql inserting a new row
    $result = mysqli_query($conexao,"INSERT INTO dados_alunos(usuario, nome, nome_filho, senha) VALUES('{$usuario}', '{$nome}', '{$nome_filho}', '{$senha}')");

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
