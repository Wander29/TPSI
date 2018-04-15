<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<?php 
    /*dichiarazione array*/
    $cars = ["volvo", "masegaltti",  "cazoz"];
    $cars2 = array("gatto", "micio"); //stessa cosa
    $cars[3] = 3; //aray non solo dello stesso tipo

    echo $cars[3];
    echo "<br>";
    echo"<h1>E ora la magia</h1>";
    echo var_dump($cars[3]);

    for($i=0; $i<count($cars); $i++){
	
	echo "<h1>array n°" . $i . " </h1>";
	echo "<br>";
	echo $cars[$i];
	echo "<br>";
    }
?>
</body>
</html>