// const alertPlaceholder = document.getElementById('liveAlertPlaceholder')
// const message = "Success"

userUpdateSubmit.addEventListener('click', updateUser);

function updateUser() {

    let user = {"full_name" : full_name.value, "country" : country.value, "company" : company.value, "address" : address.value, "phoneNumber" : phoneNumber.value}
    let body = JSON.stringify(user);
    var update = new XMLHttpRequest();
    update.open('PUT', '/profile', true)
    update.setRequestHeader("Content-Type", "application/json");
    update.onreadystatechange = () => {
        if (update.status === 200 && update.readyState === XMLHttpRequest.DONE) {
            console.log(update.responseText);
            //calculatedPrice.innerHTML = data;

        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    update.onerror = function () {
        console.log("Connection error");
    }
    update.send(body);
}