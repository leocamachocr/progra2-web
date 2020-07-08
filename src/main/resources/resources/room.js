window.addEventListener("load", function(){
   loadUser();

});

function loadUser(){
  var email = getParameterByName('email');
  validateUserExists(
      email,
      function(){
              document.getElementById('message-input-email').value = email;
              setInterval(loadChatContent, 3000);
      }
  )//sigue a la siguiente línea sin tener la respuesta

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


function validateUserExists(email, nextStep) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        if (this.responseText !== "true") {
         location.replace("welcome")
        } else {
            nextStep()
        }

    }
  };
  xhttp.open("GET", "query?type=userExists&email=" + email, true);
  xhttp.send();
}

function loadChatContent() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        document.getElementById("chatContent").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "query?type=chatContent", true);
  xhttp.send();
}

