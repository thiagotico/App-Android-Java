<?php

/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['usuario']) && isset($_POST['nome']) && isset($_POST['nome_filho']) && isset($_POST['senha'])) {

    $pid = $_POST['pid'];
    $usuario = $_POST['usuario'];
    $nome = $_POST['nome'];
    $nome_filho = $_POST['nome_filho'];
    $senha = $_POST['senha'];

 // Parametros para conexão
	require_once('db_connect.php');


    // mysql update row with matched pid
    $result = mysqli_query($conexao, "UPDATE dados_alunos SET usuario = '{$usuario}', nome = '{$nome}', nome_filho = '{$nome_filho}', senha = '{$senha}' WHERE pid = {$pid}");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";

        // echoing JSON response
        echo json_encode($response);
    } else {
		$response["success"] = 0;
        $response["message"] = mysqli_errno() + "  " + mysqli_error();
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
