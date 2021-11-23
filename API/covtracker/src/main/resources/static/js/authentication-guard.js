function handleIsLoggedIn(){
    const usuario = JSON.parse(localStorage.getItem("usuario"));
    if(!usuario){
        window.location.href = window.location.origin + "/login.html";
    }
}

handleIsLoggedIn();