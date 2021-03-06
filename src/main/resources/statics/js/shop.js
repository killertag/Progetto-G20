var page = 1
var element
var container
var timer = 1

//aggiunta dei listener agli elementi cliccabili
window.onload = function() {
  startPage()
  $('.buttons button[name="back"]').css("opacity", "0.5").css("cursor", "no-drop");
}

$('#total .sconto button').on('click', function() {
  discoFunc()
});
$('#total .sconto input').on('keypress', function(e) {
  if (e.which == 13) {
    discoFunc();
  }
});

$('#total .card button').on('click', function() {
  buyFunc()
});

//post request al click del pulsante "APPLY"
function discoFunc() {
  $('.loader').css("visibility", "visible")
  var ajax = $.ajax({
    type: "POST",
    url: "/shopCart",
    data: {
      action: "discount",
      code: $('#total .sconto input').val(),
      price: $('#total .card #totalPrice').text().split(" ")[1]
    },
    success: function(response) {
      $('.loader').css("visibility", "hidden")

      //nel caso non funziona ritorno 0 dal server
      if (response === "not") {
        $('#total #buyMess').text("Please enter a valid code")
      } else if (response === "already") {
        $('#total #buyMess').text("Code already used")
      } else {
        $('#total #buyMess').text("Code successfully applied")
        //cambio il prezzo visualizzato
        $('#total .card #totalPrice').text("€ " + response)
      }
    }
  })
}

//post request al click del pulsante "BUY"
function buyFunc() {
  var data = $('.deadline input').val().split("-")
  $('.loader').css("visibility", "visible")
  var ajax = $.ajax({
    type: "POST",
    url: "/shopCart",
    data: {
      action: "buy",
      codeCard: $('.cardNumber input').val(),
      date: data[1] + "/" + data[0],
      cvv: $('.cvv input').val(),
      email: $('.insertEmail input').val()
    },
    success: function(response) {
      $('.loader').css("visibility", "hidden")
      if (response === "true") {
        $('.ticketConfirm').css("visibility", "visible")
      }
      if (response === "false") {
        $('#total #buyMess').text("Insert data not correct. Verify please.")
      } else {
        $('#total #buyMess').text(response)
      }
    }
  })
}

//gestione delle 4 pagine indicizzate nella barra superiore (sono 4)
//gestione delle aniamzioni dei bottoni
function setPageValue() {
  element = "li[value='" + page + "']"
}

function setContainer() {
  container = '#' + $(element).attr('text')
}

function startPage() {
  setPageValue()
  setContainer()
  $(element).addClass('active')
  $(container).addClass('active')
}

$('.buttons button[name="back"]').on('click', function() {
  $('.buttons button[name="continue"]').css("opacity", "1").css("cursor", "pointer");
  if (page == 2) {
    $(this).css("opacity", "0.5").css("cursor", "no-drop");
  }

  if (page == 1) {
    return
  }

  $(element).removeClass('active')
  $(container).removeClass('active')
  page -= 1
  setPageValue()
  setContainer()
  $(element).addClass('active')
  $(container).addClass('active')

})

$('.buttons button[name="continue"]').on('click', function() {
  $('.buttons button[name="back"]').css("opacity", "1").css("cursor", "pointer");

  if (page == 3) {
    $(this).css("opacity", "0.5").css("cursor", "no-drop");
  }

  if (page == 4) {
    return;
  }

  $(element).removeClass('active')
  $(container).removeClass('active')
  page += 1
  setPageValue()
  setContainer()
  $(element).addClass('active')
  $(container).addClass('active')
})

//ricarica la pagina al back del browser
window.addEventListener("pageshow", function(event) {
  var historyTraversal = event.persisted ||
    (typeof window.performance != "undefined" &&
      window.performance.navigation.type === 2);
  if (historyTraversal) {
    // Handle page restore.
    window.location.reload();
  }
});


//NON DISPONIBILE, MA IN FUTURO FORSE SI
function startTimer() {
  // Set the date we're counting down to
  var timerDate = new Date()
  timerDate.setMinutes(timerDate.getMinutes() + timer)
  timerDate = timerDate.getTime()

  // Update the count down every 1 second
  var x = setInterval(function() {

    // Get today's date and time
    var now = new Date().getTime();

    // Find the distance between now and the count down date
    var distance = timerDate - now;

    // Time calculations for days, hours, minutes and seconds
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

    // Display the result in the element with id="demo"
    document.getElementById("demo").innerHTML =
      minutes + "m " + seconds + "s ";

    // If the count down is finished, write some text
    if (distance < 0) {
      clearInterval(x);
      document.getElementById("demo").innerHTML = "EXPIRED";
      window.location.href = "/home"
    }
  }, 1000);

}