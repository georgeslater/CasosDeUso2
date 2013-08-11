/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
   //agregar tooltip ya que p:rowEditor no tiene
   $('.ui-icon.ui-icon-pencil').each(function(){
       
      $(this).attr('title', 'Renombrar'); 
   });
   
    $(".fancybox").fancybox();
});


