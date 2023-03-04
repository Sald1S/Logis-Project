addNewsSubmitButton.addEventListener('click',addNews);
const toastLiveExample = document.getElementById('liveToast')
const toastImage= document.getElementById('imageToast')
function addNews(){
    let news = {"title": title.value, "topic": topic.value, "content": content.value}
    let body = JSON.stringify(news);
    var addNews = new XMLHttpRequest();
    addNews.open('Post', '/news', true)
    addNews.setRequestHeader("Content-Type", "application/json");
    addNews.onreadystatechange = () => {
        title.value = ""
        topic.value = ""
        content.value = ""
    }
    addNews.send(body);
    const toast = new bootstrap.Toast(toastLiveExample)
    toast.show()
}

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');


function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("PUT", "/news");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
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
    const toast = new bootstrap.Toast(toastImage)
    toast.show()
}, true);