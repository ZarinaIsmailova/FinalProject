function readMore(){
    let more = document.getElementById("more");
    let btn = document.getElementById("btn");

    if(more.style.display === "none"){
        more.style.display="inline";
        btn.innerHTML = "Скрыть"
    }else {
        more.style.display="none";
        btn.innerHTML = "Подробнее"

    }

    //КОД КОТОРЫЙ ДОЛЖЕН БЫТЬ В html
    //<button id="btn" onclick="readMore()">Подробнее</button>
    //<span id="more">внутри тескст который должен быть скрыт</span>
}