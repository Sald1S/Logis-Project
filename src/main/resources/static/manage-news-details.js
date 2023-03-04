loadOneNews()
function loadOneNews() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('newsId')
    var oneNews = new XMLHttpRequest();
    oneNews.open("GET", "/news/"+id, true);
    oneNews.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let oneNews = JSON.parse(this.responseText);
            var html = "";
            html+="<h4>"+oneNews.title+"</h4>"
            html+="<img src='/newsPhoto/"+oneNews.image+"' width='40%' alt='' class='img-fluid'>"
            html+="<p>"+oneNews.content+"</p>"
        }
        newsDetails.innerHTML = html;
    }

    oneNews.send();
}

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');


function uploadSingleFile(file) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('newsId')
    var formData = new FormData();
    formData.append("file", file);
    var xhr = new XMLHttpRequest();
    xhr.open("PUT", "/news/"+id, true);
    xhr.onload = function() {
        console.log(xhr.responseText);
        if(xhr.status == 200) {
            loadOneNews()
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }
    xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event){

    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);