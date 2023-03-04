loadOneNews();

function loadOneNews() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('newsId')
    var oneNews = new XMLHttpRequest();
    oneNews.open("GET", "/news/"+id, true);
    oneNews.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            loadComments()
            let oneNews = JSON.parse(this.responseText);
            var html = "";
            html+="<h4>"+oneNews.title+"</h4>"
            html+="<img src='/newsPhoto/" + oneNews.image + "' width='850px' alt='' class='img-fluid'>"
            html+="<p>"+oneNews.content+"</p>"
        }
        newsDetails.innerHTML = html;
    }

    oneNews.send();
}


function loadComments() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('newsId')

    var com = new XMLHttpRequest();
    com.open("GET", "/comment/"+id, true);
    com.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let listComment = JSON.parse(this.responseText);
            var html = "";
            for (let i = 0; i < listComment.length; i++) {
                let news = listComment[i].time;
                let postDate = new Date(news).toLocaleDateString('en-us', {
                    weekday: "long",
                    year: "numeric",
                    month: "short",
                    day: "numeric",
                    hour: "numeric",
                    minute: "numeric"
                }
                )
                html+="<div class=\"card p-3 mt-2\">"
                html+="<div class=\"d-flex justify-content-between align-items-center\">"
                html+="<div class=\"user d-flex flex-row align-items-center\">"
                html+="<span>"
                html+="<small class=\"font-weight-bold text-primary mt-3\">"+listComment[i].user.full_name+"</small>"
                html+="<small class=\"font-weight-bold\">"+"  "+listComment[i].comment+"</small>"
                html+="</span>"
                html+="</div>"
                html+="<small>"+postDate+"</small>"
                html+="</div>"
                html+="</div>"
            }
        }
        commentsOfNews.innerHTML = html;
    }

    com.send();
}
addCommentButton.addEventListener('click', addComment)
function addComment() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('newsId')
    let comment = {"comment": commentInput.value}
    var body = JSON.stringify(comment);
    console.log(body)
    var addComment = new XMLHttpRequest();
    addComment.open('POST', '/comment/'+id, true)
    addComment.setRequestHeader("Content-Type", "application/json");
    addComment.onreadystatechange = () => {
        if (addComment.status === 200 && addComment.readyState === XMLHttpRequest.DONE) {
            //calculatedPrice.innerHTML = data;
            loadComments()
            commentInput.value = ""
        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    addComment.onerror = function () {
        console.log("Connection error");
    }
    addComment.send(body);
}