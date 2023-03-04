loadNews()
function loadNews() {
    var news = new XMLHttpRequest();
    news.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let newsList = JSON.parse(this.responseText);
            var html = "";
            for (let i = 0; i < newsList.length; i++) {
                html+="<div class='col-lg-4 col-md-6' data-aos='fade-up' data-aos-delay='100'>"
                html+="<div class='card'>"
                html+="<div class='card-img'>"
                html+="<img src='/newsPhoto/"+newsList[i].image+"' width='100%' alt='' class='img-fluid'>"
                html+="</div>"
                html+="<h3>"
                html+="<a href='/manageNewsDetails?newsId="+newsList[i].id+"' class='stretched-link'>"+newsList[i].title+"</a>"
                html+="</h3>"
                html+="<p>"+newsList[i].topic+"</p>"
                html+="</div>"
                html+="</div>"
            }
        }
        newsContent.innerHTML = html;
    }
    news.open("GET", "/news", true);
    news.send();
}