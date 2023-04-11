<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();



// Parametros para conexão 
require_once('db_connect.php');

// get all products from products table
$resultado = mysqli_query($conexao, "SELECT * FROM products");

// check for empty result

if (mysqli_num_rows($resultado) > 0) {

	$products = array();
	while ($row = mysqli_fetch_assoc($resultado))
		{
			$products[] = $row;
		}			
	mysqli_free_result($resultado);
// }   //retirar apos teste

	$response = array();
	$response['products'] = $products;
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
