
var startInterval;
var milisec;

function start() {
	setSpeed();
	document.getElementById("mainForm:finished").value='false';
	startInterval=setInterval('loadXMLDoc();', milisec);
}
      
function setSpeed() {
	var speed=document.getElementById("mainForm:simulationSpeed").value;
	milisec=110-speed;
	milisec=milisec*2;
	
	if (speed == 0) {
		milisec=500;
	}
	
	
//	console.log(milisec);
	
	//milisec=2000;
	
}


function finish() {	
	clearInterval(startInterval);
}

function loadXMLDoc() {
	
	var finished=document.getElementById("mainForm:finished").value;
	
	setSpeed();
	clearInterval(startInterval);
	startInterval=setInterval('loadXMLDoc();', milisec);


	if (finished == 'true') {
		//alert ('finished: ' + finished);
		finish();
	}	
	
	
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  
  
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
	  
	  
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    
    //ako ce biti problema sa INNER HTML na internet exploreru
    /*
    var newdiv = document.createElement("div");
    newdiv.innerHTML = xmlhttp.responseText;
    var container = document.getElementById("myDiv");
    container.appendChild(newdiv);
    */
    
    
    }
  }

var trenutnaSimulacija=document.getElementById("mainForm:trenutnaSimulacija").value;

//random: IE cashira ajax request pa mu svaki puta treba promijeniti url, varijabla nema utjecaja na updatera
xmlhttp.open("GET","Updater?trenutnaSimulacija="+trenutnaSimulacija+ "&random=" + Math.random(),true);
//xmlhttp.open("GET","simulacija.jsp?trenutnaSimulacija="+trenutnaSimulacija,true);
xmlhttp.send();


}



function loadXMLDocPojedinacno() {
	
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    }
  }

var trenutnaSimulacija=document.getElementById("mainForm:trenutnaSimulacija").value;

//random: IE cashira ajax request pa mu svaki puta treba promijeniti url, varijabla nema utjecaja na updatera
xmlhttp.open("GET","Updater?trenutnaSimulacija="+trenutnaSimulacija+ "&random=" + Math.random(),true);
//xmlhttp.open("GET","simulacija.jsp?trenutnaSimulacija="+trenutnaSimulacija,true);
xmlhttp.send();



}

function sleep(milliseconds) {
	  var start = new Date().getTime();
	  for (var i = 0; i < 1e7; i++) {
	    if ((new Date().getTime() - start) > milliseconds){
	      break;
	    }
	  }
	}


function pauseSimulation() {
	clearInterval(startInterval);
}

function resumeSimulation() {
	start();
}

function nextDraw() {
	loadXMLDocPojedinacno();
}


function showDialog() {
    PF('statusDialog').show();
}
 
function hideDialog() {
    PF('statusDialog').hide();
}
