// function loadNews() {
//     var news = new XMLHttpRequest();
//     news.onreadystatechange = function () {
//         if (this.readyState == 4 && this.status == 200) {
//             let newsList = JSON.parse(this.responseText);
//             var html = "";
//             for (let i = 0; i < newsList.length; i++) {
//                 html+="<div class='col-lg-4 col-md-6' data-aos='fade-up' data-aos-delay='100'>"
//                 html+="<div class='card'>"
//                 html+="<div class='card-img'>"
//                 html+="<img src='/profile/"+newsList[i].image+"' width='100%' alt='' class='img-fluid'>"
//                 html+="</div>"
//                 html+="<h3>"
//                 html+="<a href='/news-details?newsId="+newsList[i].id+"' class='stretched-link'>"+newsList[i].title+"</a>"
//                 html+="</h3>"
//                 html+="<p>"+newsList[i].topic+"</p>"
//                 html+="</div>"
//                 html+="</div>"
//             }
//         }
//         newsContent.innerHTML = html;
//     }
//     news.open("GET", "/news", true);
//     news.send();
// }


const list_element = document.getElementById('list');
const pagination_element = document.getElementById('pagination');

let current_page = 1;
let rows = 6;

function DisplayList(wrapper, rows_per_page, page) {
    wrapper.innerHTML = "";
    page--;
    loadNews()

    function loadNews() {
        var news = new XMLHttpRequest();
        news.open("GET", "/news", true);
        news.send();
        news.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let newsList = JSON.parse(this.responseText);
                let start = rows_per_page * page;
                let end = start + rows_per_page;
                let paginatedItems = newsList.slice(start, end);
                let html = ""
                for (let i = 0; i < paginatedItems.length; i++) {
                    html += "<div class='col-lg-4 col-md-6' data-aos='fade-up' data-aos-delay='100'>"
                    html += "<div class='card'>"
                    html += "<div class='card-img'>"
                    html += "<img src='/newsPhoto/" + paginatedItems[i].image + "' width='100%' alt='' class='img-fluid'>"
                    html += "</div>"
                    html += "<h3>"
                    html += "<a href='/news-details?newsId=" + paginatedItems[i].id + "' class='stretched-link'>" + paginatedItems[i].title + "</a>"
                    html += "</h3>"
                    html += "<p>" + paginatedItems[i].topic + "</p>"
                    html += "</div>"
                    html += "</div>"

                    wrapper.innerHTML = html;
                }
            }
        }
    }
}

function SetupPagination(wrapper, rows_per_page) {
    wrapper.innerHTML = "";
    var news = new XMLHttpRequest();
    news.open("GET", "/news", true);
    news.send();
    let newsList = ""
    news.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            newsList = JSON.parse(this.responseText);
            let page_count = Math.ceil(newsList.length / rows_per_page);
            for (let i = 1; i < page_count + 1; i++) {
                let btn = PaginationButton(i, newsList);
                wrapper.appendChild(btn);
            }
        }
    }
}


function PaginationButton(page) {
    var news = new XMLHttpRequest();
    news.open("GET", "/news", true);
    news.send();
    let newsList = ""
    news.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            newsList = JSON.parse(this.responseText);
        }
    }
    let button = document.createElement('button');
    button.innerText = page;

    if (current_page == page) button.classList.add('active');

    button.addEventListener('click', function () {
        current_page = page;
        DisplayList(list_element, rows, current_page);

        let current_btn = document.querySelector('.pagenumbers button.active');
        current_btn.classList.remove('active');

        button.classList.add('active');
    });

    return button;
}


DisplayList(list_element, rows, current_page);
SetupPagination(pagination_element, rows);

