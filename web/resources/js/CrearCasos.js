$(document).on('click', '.casoRowTable input[type=checkbox]', function(){

    $('.casoRowTable input[type=checkbox]').filter(':checked').not(this).removeAttr('checked');
});
                        
function evaluarDialogs(){
                
    var checkedInput = $('.casoRowTable input[type=checkbox]').filter(':checked');
               
    if(checkedInput != null && checkedInput != undefined && checkedInput.length == 1){
                    
        var x = checkedInput.parent().data('row');
        var y = checkedInput.parent().data('column');
                    
        if(x != null && x != undefined && y != null && y != undefined){
                    
            document.getElementById('casosForm:xPosition').value = x;
            document.getElementById('casosForm:yPosition').value = y;
            remoteChangeCommand();

            if(checkedInput.hasClass('actorCheckbox')){

                dlgActores.show();

            }else if(checkedInput.hasClass('cduCheckbox')){

                dlgCdus.show();

            }else if(checkedInput.hasClass('relCheckbox')){

                dlgRels.show();
            }
        }
    }
}


