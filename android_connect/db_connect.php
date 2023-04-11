<?php

  require_once('db_config.php');
  
  $conexao = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
  //echo nl2br("$database \n");
  if($conexao->connect_errno > 0){
    die('Unable to connect to database [' . $conexao->connect_error . ']');
  } else {
    mysqli_query($conexao,"SET NAMES 'utf8'");
    mysqli_query($conexao,"SET character_set_connection=utf8");
    mysqli_query($conexao,"SET character_set_client=utf8");
    mysqli_query($conexao,"SET character_set_results=utf8");

  }



/* Inicio Joaquim


// A class file to connect to database

class DB_CONNECT {

    // constructor
    function __construct() {
        // connecting to database
        $this->connect();
    }

    // destructor
    function __destruct() {
        // closing db connection
        $this->close();
    }

     // Function to connect with database
     
    function connect() {
        // import database connection variables
        require_once('db_config.php');

        // Connecting to mysql database
        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die(mysql_error());

        // Selecing database
        $db = mysql_select_db(DB_DATABASE) or die(mysql_error()) or die(mysql_error());

        // returing connection cursor
        return $con; 
    }

    
    //Function to close db connection

    function close() {
        // closing db connection
        mysql_close();
    }

}
*/
?>
