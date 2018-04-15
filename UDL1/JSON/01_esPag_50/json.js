var libri = { 
        "informatica" : [
        { "linguaggio" : "C++", "prezzo" : 15.50},
        { "linguaggio" : "JavaScript", "prezzo" : 17.50},
        { "linguaggio" : "Java", "prezzo" : 12.50}
        ],
    
        "cucina" : [
            { "portata" : "antipasti", "prezzo" : 25.50  },
            { "portata" : "dolci", "prezzo" : 13.00  }
        ]
}

document.write("<table border='3' >");
document.write("<tr>");

for(var i=0; i < libri.informatica.length; i++)
{
    document.write("<td>");
        document.write("<table border='1' width='170'>");
            document.write("<tr>");
                document.write("<td>" + "<b>Linguaggio</b>" + "</td>");
                document.write("<td>" + libri.informatica[i].linguaggio + "</td>");
            document.write("</tr>");
            document.write("<tr>");
                document.write("<td>" + "<b>Prezzo" + "</td>");
                document.write("<td>" + libri.informatica[i].prezzo + "</td>");
            document.write("</tr>");
        document.write("</table>");
    document.write("</td>");
}

for(var i=0; i < libri.cucina.length; i++)
{
    document.write("<td>");
        document.write("<table border='1' width='170'>");
            document.write("<tr>");
                document.write("<td>" + "<b>Portata</b>" + "</td>");
                document.write("<td>" + libri.cucina[i].portata + "</td>");
            document.write("</tr>");
            document.write("<tr>");
                document.write("<td>" + "<b>Prezzo</b>" + "</td>");
                document.write("<td>" + libri.cucina[i].prezzo + "</td>");
            document.write("</tr>");
        document.write("</table>");
    document.write("</td>");
}
document.write("</tr>");
document.write("</table>");


                        