window.addEventListener("load", function(){
   loadUser();
});

function loadUser(){
  var email = getParameterByName('email');
  document.getElementById('message-input-email').value = email;
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}
  function refresh(){location.reload(true);}
//Función para actualizar cada 4 segundos(4000 milisegundos)
  setInterval("refresh()",4000);