<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JSON_Decode</title>
    </head>
    <body>
        <div align="center" style="font-size:30px;">
       <?php  
            $url = 'array.json';
            $data = file_get_contents($url);
            $caratteri = json_decode($data, true); 
            echo $caratteri['PC_ASSEMBLATI']['PC1']['marca']; //parentesi quadre = Array, il resto è un oggetto JSON
        ?>
        </div><hr><br>
        <table border='1' align="center">
            <tr><th colspan="3">PC1</th></tr>
            <tr><th colspan="3">HARDWARE</th></tr>
            <tr><th>ID</th><th colspan="1">TIPO</th></tr>
            
            <?php //Stampa elementi hardware
            foreach ($caratteri['PC_ASSEMBLATI']['PC1']['Hardware'] as $prova)
            { echo "<tr><td class='center id'>".$prova['id']."</td>
                        <td class='tipo'>".$prova['tipo']."</td></tr>"; } ?>
        </table>
        
        <style>
            .center{
                text-align: center;
            }
            table td{
                padding-left:5px;
            }
            table{
                width: 400px;
                padding: 20px;
                border-radius: 30px;
            }
            body{
                font-family: Arial;
            }
            table tr:nth-child(1) {
                background-color: red;
            }
            table tr:nth-child(2) {
                background-color: #F62323;
            }
            table tr:nth-child(3) {
                background-color: #F66464;
            }
            .id{
                background-color: #FBB3B3;
            }
            .tipo{
                background-color: #F8D0D0;
            }
        </style>
    </body>
</html>
