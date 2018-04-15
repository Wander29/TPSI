<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Document</title>
        <link rel="stylesheet" href="">
    </head>
    <body>
        
        <?php  
            $cars = [ 
              array("Volvo", 100, 55, 0), //nome, ordinate, vendute, rimanenti  
              array("Ferrari", 50, 3, 0),  
              array("Maserati", 75, 74, 0)  
            ];
        
            echo "<table border=1>";
            echo "<tr>
                     <td>Tipo</td>  <td>Ordinate</td>  <td>Vendute</td>  <td>Rimanenti</td>
                  </tr>";
            for($i=0; $i < count($cars); $i++){
                $cars[$i][3] = rimanenti($cars[$i][1], $cars[$i][2]);
                if($cars[$i][3] >= 10){ echo "<tr bgcolor=red>"; } else{ echo "<tr>"; }
                for($j=0; $j < count($cars[$i]); $j++){
                    echo "<td>".$cars[$i][$j]."</td>";
                }
                echo "</tr>";
            } 
            echo "</table><br><br><hr><br>";
         
        sort($cars);
        for($b=0; $b< count($cars); $b++){
            echo $cars[$b][0]. "<br>";
        }
         echo "<br><hr><br>";
        
        /* funzioni */
                function rimanenti($o, $v){
                    return $o - $v;
                }
        ?>
        
    </body>
</html>