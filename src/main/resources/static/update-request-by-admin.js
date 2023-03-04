
requestUpdateSubmit.addEventListener('click', updateRequest);

function updateRequest() {

    let id = requestId.value;
    console.log(id)
    let requestDTO = {"requestStatus": requestStatus.value}
    var body = JSON.stringify(requestDTO);
    console.log(body)
    var updateRequest = new XMLHttpRequest();
    updateRequest.open('PUT', '/request/'+id, true)
    updateRequest.setRequestHeader("Content-Type", "application/json");
    updateRequest.onreadystatechange = () => {
        if (updateRequest.status === 200 && updateRequest.readyState === XMLHttpRequest.DONE) {
            loadRequest();
            //calculatedPrice.innerHTML = data;

        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    updateRequest.onerror = function () {
        console.log("Connection error");
    }
    updateRequest.send(body);
}

function loadManagers() {
    let id = requestId.value;
    console.log(id);
    var managerList = new XMLHttpRequest();
    managerList.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let managersList = JSON.parse(this.responseText);
            var html = "";
            html+="<table class='table table-striped'>"
            html+="<thead>"
            html+="<tr>"
            html+="<th>"+"ID"+"</th>"
            html+="<th>"+"Full name"+"</th>"
            html+="<th style='width: 10%'>"+"Select"+"</th>"
            html+="</tr>"
            html+="</thead>"
            html+="<tbody>"
            for (i = 0; i < managersList.length; i++) {
                html+="<tr>"
                html+="<td>"+managersList[i].id+"</td>"
                html+="<td>"+managersList[i].fullName+"</td>"
                html+="<td>"+"<button type='button' id='assign' class='btn btn-success' onclick='assignManagers("+managersList[i].id+")'>"+"Assign"+"</button>"+"</td>"
                html+="</tr>"
            }
        }
        managersList.innerHTML = html;
    }
    managerList.open("GET", "/manager/"+id, true);
    managerList.send();
}


function assignManagers(managerId) {
    let reqId = requestId.value;
    console.log(reqId,managerId)
    var assign = new XMLHttpRequest();
    assign.open('Post', '/manager/' + reqId + '/' + managerId, true)
    assign.setRequestHeader("Content-Type", "application/json");
    assign.onreadystatechange = () => {
        if (assign.status === 200 && assign.readyState === XMLHttpRequest.DONE) {
            loadManagers();
        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    assign.onerror = function () {
        console.log("Connection error");
    }
    assign.send();

}