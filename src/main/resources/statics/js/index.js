// NAVBAR SCRIPTS

window.onscroll = function() {
  scrollFunction()
};


window.onload = function() {
  navOnLoad();
};

var rgb = 'rgb(' + 30 + ',' + 63 + ',' + 97 + ',';
var navbar = document.getElementById("header");

// rende transparente la barra una volta entrato nel centro
function scrollFunction() {

  if (document.documentElement.scrollTop > 100) {
    navbar.style.backgroundColor = rgb + 1 + ')';
  } else {
    navbar.style.backgroundColor = rgb + 0 + ')';
  }
};

// se la pagina si avvia in testa, rende la pagina transparente
function navOnLoad() {

  if (document.documentElement.scrollTop < 100) {
    navbar.style.backgroundColor = rgb + 0 + ')';
  }
};

// BACKGROUND IMAGE SCRIPT

// automatic way to change background img on index.HTML
//every 5 o 10 seconds

var imgList = ["../statics/images/background/back0.jpg",
  "../statics/images/background/back1.jpg",
  "../statics/images/background/back2.jpg",
  "../statics/images/background/back3.jpg",
  "../statics/images/background/back4.jpg"
];

var currentImage;
var index = 1;
var element = document.getElementById('banner')


function changeImage() {
  if (index == 5) {
    index = 0;
  }

  currentImage = imgList[index];

  element.style.backgroundImage = "url(" + currentImage + ")";
  index++;

};

setInterval("changeImage()", 5000);


// ESTEREGG

function playAudio() {
  var myAudio = document.getElementById('audio');
  if (!myAudio.paused) {
    myAudio.pause();
  } else {
    myAudio.play();
  }
}

//get requests

$('#goToCatalog').on('click', function() {
  window.location.href = "/catalog?search=all";
});

$(document).on('click', '.item', function() {
  var title = $(this).find('p').text()
  window.location.href = "/movie?title=" + title
});