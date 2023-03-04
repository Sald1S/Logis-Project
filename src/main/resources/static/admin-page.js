loadRequest();

function loadRequest() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let newsList = JSON.parse(this.responseText);
            var html = "";
            html+="<table class='table table-striped'>"
            html+="<thead>"
            html+="<tr>"
            html+="<th>"+"id"+"</th>"
            html+="<th>"+"Addresser"+"</th>"
            html+="<th>"+"Receiver"+"</th>"
            html+="<th>"+"Product name"+"</th>"
            html+="<th>"+"Request Status"+"</th>"
            html+="<th style='width: 10%'>"+"Edit"+"</th>"
            html+="</tr>"
            html+="</thead>"
            html+="<tbody>"
            for (i = 0; i < newsList.length; i++) {
                html+="<tr>"
                html+="<td>"+newsList[i].id+"</td>"
                html+="<td>"+newsList[i].addresser.addresser_name+"</td>"
                html+="<td>"+newsList[i].receiver.receiver_name+"</td>"
                html+="<td>"+newsList[i].product.name+"</td>"
                html+="<td>"+newsList[i].requestStatus+"</td>"
                html+="<td>"+"<a id='modalOpen' class='btn btn-link' onclick='openEditModal("+newsList[i].id+")' >"+"Edit"+"</a>"+"</td>"
                html+="</tr>"
            }
            html+="</tbody>"
            html+="</table>"
        }
        content.innerHTML = html;
    }
    xmlHttp.open("GET", "/request", true);
    xmlHttp.send();
}

function openEditModal(id) {
    var details = new XMLHttpRequest();
    details.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let request = JSON.parse(this.responseText);
            console.log(request)
            var myModal = new bootstrap.Modal(document.getElementById("editModal"), {})
            myModal.show();
            requestId.value = request.id
            addresserName.value = request.addresser.addresser_name
            receiverName.value = request.receiver.receiver_name
            senderCity.value = request.addresser.addresser_city
            deliveryCity.value = request.receiver.receiver_city
            productName.value = request.product.name
            productType.value = request.product.type.productType
            transportType.value =request.transport.type
            requestPrice.value = request.price.requestPrice
            requestStatus.value = request.requestStatus
            loadManagers();
        }
    }
    details.open("GET", '/request/'+id, true);
    details.send();
}



























// const myModalAlternative = new bootstrap.Modal('#editModal', {backdrop: "static", keyboard: false})
// editModal+="<div class=\"modal fade\" id=\"editModal\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">"
// editModal+="<div class=\"modal-dialog\">"
// editModal+="<div class=\"modal-content\">"
// editModal+="<div class=\"modal-header\">"
// editModal+="<h5 class=\"modal-title fs-5\" id=\"exampleModalLabel\">"+"ll"+"</h5>"
// editModal+="<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>"
// editModal+="</div>"
// editModal+="<div class=\"modal-body\">"
// editModal+="<input type='hidden' name='request_id' id='request_id'>"
// editModal+="<p>"+request.id+"</p>"
// editModal+="</div>"
// editModal+="<div class=\"modal-footer\">"
// editModal+="<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">"+"Close"+"</button>"
// editModal+="<button type=\"button\" class=\"btn btn-primary\">"+"Save Changes"+"</button>"
// editModal+="</div>"
// editModal+="</div>"
// editModal+="</div>"
// editModal+="</div>"
