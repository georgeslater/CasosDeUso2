$(document).on('click', '.casoRowTable input[type=checkbox]', function(){

    $('.casoRowTable input[type=checkbox]').filter(':checked').not(this).removeAttr('checked');
    var checkedInput = $('.casoRowTable input[type=checkbox]').filter(':checked');
               
    if(checkedInput != null && checkedInput != undefined && checkedInput.length == 1){
                    
        var x = checkedInput.parent().data('row');
        var y = checkedInput.parent().data('column');
                    
        if(x != null && x != undefined && y != null && y != undefined){
                    
            document.getElementById('casosForm:xPosition').value = x;
            document.getElementById('casosForm:yPosition').value = y;
            remoteChangeCommand();
        }
    }
});

function dibujar(objetos){
    
    var canvas = document.getElementById("casosDeUsoCanvas");
    canvas.width = 2000;
    canvas.height = 2000;
    var casosDeUsoJSON = objetos;
    var offset = 100;
                 
    var ctx=canvas.getContext("2d");
    ctx.beginPath();
    
    var counter = 0;
    
    //alert(casosDeUsoJSON.length);
    
    var actores = new Array();
    
    for(var i = 0; i < casosDeUsoJSON.length; i++){
        
        var cdu = casosDeUsoJSON[i];
        
        for(var j = 0; j < cdu.length; j++){
            
            if(cdu[j] != null){
                
                var objeto = cdu[j];
                if(j == 0){
                    if($.inArray(objeto.nombre, actores) === -1){
                        counter = 0;
                        dibujarActor(j, i, objeto.nombre);
                        actores.push(objeto.nombre);
                        //alert("pushed "+objeto.nombre);
                    }else{
                        counter++;
                    }                    
                }else if(j == 1){
                    
                    //es una flecha actor -> caso de uso
                    var h = ((i-counter) * 200)+100;
                    var w = (j * 200);
                    var moveToX = w/2;
                    var moveToY = (h/2)+30;
                    ctx.moveTo(moveToX, moveToY);
                    //alert("moved to "+moveToX+", "+moveToY);
                    var lineToX = objeto[0].y*offset+30;
                    var lineToY = objeto[0].x*offset+80
                    ctx.lineTo(lineToX, lineToY);
                    //alert("Did a line to "+lineToX+", "+lineToY);
                    ctx.stroke();
                    
                }else{
                    //es un caso de uso
                    ctx.beginPath();
                    ctx.arc((j*offset)+100,(i*offset)+80,40,0,2*Math.PI);
                    ctx.closePath();
                    ctx.stroke();
                    ctx.fillStyle = "black"; // font color to write the text with
                    var font = "12px serif";
                    ctx.font = font;
                    var width = ctx.measureText(objeto.text).width;
                    var height = ctx.measureText("w").width;
                    ctx.fillText(objeto.text, ((j*offset)+100) - (width/2) ,((i*offset)+80) + (height/2));
                }
            }
        }
    } 
    
    $('.canvasPanel').css('visibility', 'visible');
}

function dibujarActor(x, y, texto){
    
    if(texto.length > 20){
        texto = texto.substring(0, 17)+'...';
    }
    var tamanioTexto = texto.length;
    var canvas = document.getElementById("casosDeUsoCanvas");
    var ctx=canvas.getContext("2d");
    ctx.strokeStyle = "#000000";
    ctx.lineWidth = "1";
    var w = (x * 200)+100;
    var h = (y * 200)+100;
    ctx.beginPath();
    //cabeza
    ctx.arc(w/2, h/2, 10, 0, Math.PI*2, true);
    ctx.moveTo(w/2,(h/2)+10);
    //cuello
    ctx.lineTo(w/2,(h/2)+30);
    //piernas
    ctx.moveTo(w/2, (h/2)+30);
    ctx.lineTo(w/2+(10),(h/2)+50);
    ctx.moveTo(w/2, (h/2)+30);
    ctx.lineTo(w/2-(10),(h/2)+50);
    //brazos
    ctx.moveTo(w/2-(10), (h/2)+20);
    ctx.lineTo(w/2+(10), (h/2)+20);

    ctx.stroke();
    ctx.closePath();
    ctx.textBaseline = "bottom";
    ctx.fillText(texto, (w/2)-(tamanioTexto * 1.6), (h/2)+70);
}

function evaluarDialogsAgregar(){
                
    var checkedInput = $('.casoRowTable input[type=checkbox]').filter(':checked');
               
    if(checkedInput != null && checkedInput != undefined && checkedInput.length == 1){

        if(checkedInput.hasClass('actorCheckbox')){

            dlgActores.show();

        }else if(checkedInput.hasClass('cduCheckbox')){

            dlgCdus.show();

        }else if(checkedInput.hasClass('relCheckbox')){

            dlgRels.show();
        }
    }
}
    
function guardarImagen(){

    var canvas = document.getElementById("casosDeUsoCanvas");
    var dataURL = canvas.toDataURL();
    //alert(dataURL);
    if(dataURL != null && dataURL != undefined && dataURL != ''){

        document.getElementById('casosForm:dataURL').value = dataURL;
        guardarImagenCommand();
    }        
}




