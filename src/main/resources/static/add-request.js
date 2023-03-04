const toastLiveExample = document.getElementById('liveToast')

calcFormSubmit.addEventListener('click', calc);

function calc() {

    let calcDist = dist.value;
    console.log(calcDist)
    let calcTypeId = typeId.value;
    console.log(calcTypeId)
    let calcTransportId = transportId.value;
    console.log(calcTransportId)

    let type = {"productType": typeId.value}
    let productSize = {"height": height.value, "width": width.value, "length": len.value, "weight": weight.value}
    let product = {"name": namee.value, "description": description.value, "type": type, "size": productSize}
    let addresser = {
        "addresser_name": addresser_name.value,
        "addresser_address": addresser_address.value,
        "addresser_state": addresser_state.value,
        "addresser_city": addresser_city.value,
        "addresser_postalCode": addresser_postalCode.value,
        "addresser_email": addresser_email.value,
        "addresser_phoneNumber": addresser_phoneNumber.value
    };
    let receiver = {
        "receiver_name": receiver_name.value,
        "receiver_address": receiver_address.value,
        "receiver_city": receiver_city.value,
        "receiver_postalCode": receiver_postalCode.value,
        "receiver_email": receiver_email.value,
        "receiver_phoneNumber": receiver_phoneNumber.value
    };
    let request = {"addresser": addresser, "receiver": receiver, "product": product, "transport": calcTransportId}
    let allBodies = {productSize, product, addresser, receiver, request}
    console.log(allBodies)
    var body = JSON.stringify(allBodies);
    console.log(body)
    var price = new XMLHttpRequest();
    price.open('Post', '/request/' + calcTypeId + '/' + calcTransportId + '/' + calcDist, true)
    price.setRequestHeader("Content-Type", "application/json");
    price.onreadystatechange = () => {
        if (price.status === 200 && price.readyState === XMLHttpRequest.DONE) {
            console.log(price.responseText);
            addresser_name.value = ""
            addresser_address.value = ""
            addresser_state.value = ""
            addresser_city.value = ""
            addresser_postalCode.value = ""
            addresser_email.value = ""
            addresser_phoneNumber.value = ""
            receiver_name.value = ""
            receiver_address.value = ""
            receiver_city.value = ""
            receiver_postalCode.value = ""
            receiver_email.value = ""
            receiver_phoneNumber.value = ""
            height.value = ""
            width.value = ""
            len.value = ""
            weight.value = ""
            dist.value = ""
            namee.value = ""
            description.value = ""
            typeId.value = "-1"
            transportId.value = "-1"

        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    price.onerror = function () {
        console.log("Connection error");
    }
    price.send(body);
    const toast = new bootstrap.Toast(toastLiveExample)
    toast.show()
}