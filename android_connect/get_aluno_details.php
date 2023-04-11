<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */


// include db connect class

// check for post data
if (isset($_GET["pid"])) {


 // Parametros para conexão
	require_once('db_connect.php');

    $pid = $_GET['pid'];

    // get a product from products table
    $result = mysqli_query($conexao,"SELECT * FROM dados_alunos WHERE pid = $pid");

    if (!empty($result)) {

			$dados_alunos = array();
			while ($row = mysqli_fetch_assoc($result))
				{
					$dados_alunos[] = $row;
				}
			mysqli_free_result($result);
		// }   //retirar apos teste

			$response = array();
			$response['dados_alunos'] = $dados_alunos;
			// success
			$response["success"] = 1;

            // echoing JSON response
            echo json_encode($response);

    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";

        // echo no users JSON
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
