<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();



// Parametros para conexão
require_once('db_connect.php');

// get all products from products table
$resultado = mysqli_query($conexao, "SELECT * FROM dados_alunos");

// check for empty result

if (mysqli_num_rows($resultado) > 0) {

	$dados_alunos = array();
	while ($row = mysqli_fetch_assoc($resultado))
		{
			$dados_alunos[] = $row;
		}
	mysqli_free_result($resultado);
// }   //retirar apos teste

	$response = array();
	$response['dados_alunos'] = $dados_alunos;
//	print_r($response);

    // success
    $response["success"] = 1;

//}   //retirar apos teste

    // echoing JSON response

	echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";

    // echo no users JSON
    echo json_encode($response);
}


?>
