var prodotti = {
    "hardware" : [
        { "prodotto" : "Hard disk", "prezzo" : 60},
        { "prodotto" : "Scheda madre", "prezzo" : 115},
        { "prodotto" : "Mouse", "prezzo" : 10},
        { "prodotto" : "Tastiera", "prezzo" : 15}
    ],
    "software" : [
        { "categoria" : "giochi", "titolo" : "Destiny 2", "prezzo" : 55.50},
        { "categoria" : "professionale", "titolo" : "Autocad 2018", "prezzo" : 999},
        { "categoria" : "officeautomation", "titolo" : "Office 2016", "prezzo" : 120},
    ]
}

document.write("<div style='color: red;'><h1>JSON e TABELLE</h1></div>");
document.write("<h2>HARDWARE</h2>");
document.write("<table border = '1'>");
    document.write("<tr align='center'>");
        document.write("<td width='150'>");
            document.write("<b>Prodotto</b>");
        document.write("</td>");
        document.write("<td width='80'>");
            document.write("<b>Prezzo</b>");
        document.write("</td>");
    document.write("</tr>");

for(var i = 0; i < prodotti.hardware.length; i++){
     bigger60(prodotti.hardware[i].prezzo);
        document.write("<td>");
            document.write(prodotti.hardware[i].prodotto);
        document.write("</td>");
        document.write("<td align='center'>");
            document.write(addEuro(prodotti.hardware[i].prezzo.toFixed(2)));
        document.write("</td>");
    document.write("</tr>");
}
document.write("</table>");

document.write("<h2>SOFTWARE</h2>");
document.write("<table border = '1'>");
    document.write("<tr align='center'>");
        document.write("<td width='150'>");
            document.write("<b>Categoria</b>");
        document.write("</td>");
        document.write("<td width='150'>");
            document.write("<b>Titolo</b>");
        document.write("</td>");
        document.write("<td width='80'>");
            document.write("<b>Prezzo</b>");
        document.write("</td>");
    document.write("</tr>");

for(i = 0; i < prodotti.software.length; i++){
    bigger60(prodotti.software[i].prezzo);
                document.write("<td >");
                    document.write(toTitleCase(prodotti.software[i].categoria));
                document.write("</td>");
                document.write("<td>");
                    document.write(prodotti.software[i].titolo);
                document.write("</td>");
                document.write("<td align='center'>");
                    document.write(addEuro(prodotti.software[i].prezzo.toFixed(2)));
                document.write("</td>");
            document.write("</tr>");
}
document.write("</table>");

/*---------- FUNCTIONS --------------*/
function toTitleCase(str)
{   return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();}); }

function addEuro(str)
{   return(str + ' €'); }

function bigger60(str)
{   if(parseInt(str) >= 60){ document.write("<tr style='background-color:red'>"); } else { document.write("<tr>"); }  }

/*function selectorPrezzo(str)
{
    if(parseInt(str) > 60)
        {   
            document.write("<div style='color:red;'>" + addEuro(str) + "</div>");
        }
    else{
        document.write(addEuro(str));
    }
}*/