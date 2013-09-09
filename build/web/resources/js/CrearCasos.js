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

function dibujar(objetos, nombreDiagrama){
    
    diagramaDialog.show();

    var canvas = document.getElementById("casosDeUsoCanvas");
    canvas.width = obtenerWidth(objetos);
    //alert(canvas.width);
    canvas.height = obtenerHeight(objetos);
    //canvas.height = 2000;
    //alert(canvas.height);
    
    var casosDeUsoJSON = objetos;
    var offset = 100;
        
    if (typeof(G_vmlCanvasManager) != 'undefined'){
        canvas = G_vmlCanvasManager.initElement(canvas);
    }
       
    var ctx=canvas.getContext("2d");
    ctx.beginPath();
    
    var counterAct = 0;
    var counterCdu1 = 0;
    var counterCdu2 = 0;
    var counterCdu3 = 0;
    var counterCdu4 = 0;
    
    var nroCdu = 0;
    var tamanio = 0;
    
    //alert(casosDeUsoJSON.length);
    
    var actores = new Array();
    var cdus = new Array();
    
    for(var i = 0; i < casosDeUsoJSON.length; i++){
        
        var cdu = casosDeUsoJSON[i];
        
        for(var j = 0; j < cdu.length; j++){
            
            var nroCduTemp = 0;
            
            if(cdu[j] != null){
                
                var objeto = cdu[j];
                if(j == 0){
                    if($.inArray(objeto.nombre, actores) === -1){
                        counterAct = 0;
                        dibujarActor(j, i, objeto.nombre);
                        actores.push(objeto.nombre);
                    //alert("pushed "+objeto.nombre);
                    }else{
                        counterAct++;
                    }
                    
                    tamanio++;
                    
                }else if(j == 1){
                    
                    //es una flecha actor -> caso de uso
                    var h = ((i-counterAct) * 200)+100;
                    var w = (j * 200);
                    var moveToX = w/2;
                    var moveToY = (h/2)+30;
                    ctx.moveTo(moveToX, moveToY);
                    //alert("moved to "+moveToX+", "+moveToY);
                    var lineToX = (objeto.p.y)*offset+30;
                    var lineToY = (objeto.p.x)*offset+80
                    ctx.lineTo(lineToX, lineToY);
                    var angle = Math.atan2(lineToY-moveToY,lineToX-moveToX);
                    //flecha begin
                    ctx.lineTo(lineToX-10*Math.cos(angle-Math.PI/6),lineToY-10*Math.sin(angle-Math.PI/6));
                    ctx.moveTo(lineToX, lineToY);
                    ctx.lineTo(lineToX-10*Math.cos(angle+Math.PI/6),lineToY-10*Math.sin(angle+Math.PI/6));
                    //alert("Did a line to "+lineToX+", "+lineToY);
                    ctx.stroke();
                    
                }else if(j%2==0){
                    //es un caso de uso
                    if($.inArray(objeto.text, cdus) === -1){
                        
                        if(j == 2){
                            counterCdu1 = 0;
                        }else if(j == 4){
                            counterCdu2 = 0;
                        }else if(j == 6){
                            counterCdu3 = 0;
                        }else if(j == 8){
                            counterCdu4 = 0;
                        }
                        
                        //alert('refreshed counter');
                        dibujarCdu(j, i, objeto.text);
                        cdus.push(objeto.text);
                        nroCduTemp = j;
                    //alert("pushed "+objeto.text);
                    }else{
                        if(j == 2){
                            counterCdu1++;
                        }else if(j == 4){
                            counterCdu2++;
                        }else if(j == 6){
                            counterCdu3++;
                        }else if(j == 8){
                            counterCdu4++;
                        }
                    //alert('increased counter');
                    }
                    
                //alert(objeto.text);
                
                }else if(j%2==1){
                    
                    //es una flecha caso de uso - caso de uso
                    
                    //verificar cuantos hijos tiene el caso anterior
                    var counterCdu = j == 3? counterCdu1: j == 5? counterCdu2: j == 7? counterCdu3: j == 9? counterCdu4: 0;
                    var h = ((i-counterCdu) * 200)+100;
                    //alert("counterCdu "+counterCdu);
                    //alert("h "+h);
                    var w = ((j * 200) + 100);
                    var moveToX = w/2;
                    var moveToY = (h/2)+30;                    
                    //alert('i is -> '+i);
                    //alert('j is -> '+j);
                    
                    //alert('X -> '+moveToX);
                    //alert('Y -> '+moveToY);

                    var lineToX = objeto.p.y*offset+50;
                    var lineToY = objeto.p.x*offset+80;                                        
                    
                    if(objeto.relacion != undefined && objeto.relacion != null && objeto.relacion.toUpperCase() == 'INCLUDES'){
                        
                        var moveToXTemp = moveToX;
                        var moveToYTemp = moveToY;
                        var lineToXTemp = lineToX;
                        var lineToYTemp = lineToY;
                        moveToY = lineToYTemp;
                        moveToX = lineToXTemp;
                        lineToX = moveToXTemp;
                        lineToY = moveToYTemp;
                    }
                    
                    ctx.moveTo(moveToX, moveToY);                    
                    ctx.lineTo(lineToX, lineToY);
                    var angulo = Math.atan2(lineToY-moveToY,lineToX-moveToX);
                    //flecha begin
                    ctx.lineTo(lineToX-10*Math.cos(angulo-Math.PI/6),lineToY-10*Math.sin(angulo-Math.PI/6));
                    ctx.moveTo(lineToX, lineToY);
                    ctx.lineTo(lineToX-10*Math.cos(angulo+Math.PI/6),lineToY-10*Math.sin(angulo+Math.PI/6));
                    //flecha end
                    ctx.stroke();
                    
                    dibujarNombreRelacion(ctx, "«"+objeto.relacion.toLowerCase()+"»", moveToX, moveToY, lineToX, lineToY)
                    
                //alert("stroke from "+moveToX+", "+moveToY+" to "+lineToX+", "+lineToY);

                }
            }
            
            if(nroCduTemp > nroCdu){
                
                nroCdu = nroCduTemp;
            }
        }
    }
    
    dibujarSistema(tamanio, nroCdu, nombreDiagrama);
}

function dibujarNombreRelacion( ctx, text, moveToX, moveToY, lineToX, lineToY){

    var dx = lineToX - moveToX;
    var dy = lineToY - moveToY;   
    var pad;
    pad = 1/2;
    
    var angle = Math.atan2(dy,dx);
    
    if (angle < -Math.PI/2 || angle > Math.PI/2){        
        moveToX = lineToX;
        moveToY = lineToY;
        dx *= -1;
        dy *= -1;
        angle -= Math.PI;
    }
    
    ctx.save();
    ctx.textAlign = "center";
    ctx.translate(moveToX+dx*pad,moveToY+dy*pad);
    ctx.rotate(Math.atan2(dy,dx));
    ctx.fillText(text,0,0);
    ctx.restore();
}

function dibujarSistema(tamanio, numeroCdu, nombreDiagrama){
    
    if(tamanio != undefined && tamanio > 0 && numeroCdu != undefined && numeroCdu > 0){
        
        var canvas = document.getElementById("casosDeUsoCanvas");       
        var ctx=canvas.getContext("2d");
        ctx.rect(200, 0, numeroCdu*100, tamanio*130);
       
        if(nombreDiagrama != undefined && nombreDiagrama != ''){
           
            ctx.font = "bold 12px arial";
            ctx.fillText(nombreDiagrama, 210, 30)
        }
       
        ctx.stroke();
       
    }
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

function dibujarCdu(x, y, texto){
    
    var canvas = document.getElementById("casosDeUsoCanvas");
    var ctx=canvas.getContext("2d");
    ctx.beginPath();
    ctx.arc((x*100)+100,(y*100)+80,40,0,2*Math.PI);
    ctx.closePath();
    ctx.stroke();
    ctx.fillStyle = "black"; // font color to write the text with
    var font = "12px serif";
    ctx.font = font;
    var width = ctx.measureText(texto).width;
    var height = ctx.measureText("w").width;
    ctx.fillText(texto, ((x*100)+100) - (width/2) ,((y*100)+80) + (height/2));
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

function obtenerHeight(losObjetos){
        
    //alert(losObjetos.length);
    for(var i = 0; i < losObjetos.length; i++){
            
        var resultado = 100;
        var cdu = losObjetos[i];
        //alert(cdu);
        //alert(cdu[0]);
        if(cdu[0] == null){
            //alert('£££');
            return (resultado * i)+150;
        }            
    }
        
    return 5000;
}
    
function obtenerWidth(losObjetos){
        
    counter = 0;

    for(var i = 0; i < losObjetos.length; i++){
            
        var cdu = losObjetos[i];
        //alert(cdu);
        //alert(cdu[0]);
            
        for(var j = counter; j < cdu.length; j++){
            if(cdu[j] != null && j > counter){
                counter = j;
            }
        }            
    }
        
    return (180*counter)+100;
}



