	function appendDiv(event){
	/*when an item is clicked, update the original select box,
	and the selected item:*/
	var y, i, k, s, h, sl, yl;
	selectOptions = event.target.parentNode.parentNode.parentNode
			.getElementsByTagName("select")[0];
	selectOptionsLength = selectOptions.length;
	h = event.target.parentNode.previousSibling;//<div class="select-selected">Brunei</div>
	h.setAttribute('value',event.path[0].attributes[0].value);// adding code and value value="33" code="673"
	h.setAttribute('code',event.path[0].attributes[1].value);//<div class="select-selected" value="33" code="673">Brunei</div>
	for (i = 0; i < selectOptionsLength; i++) {
		if (selectOptions.options[i].innerHTML == event.target.innerHTML) {
			selectOptions.selectedIndex = i;
			h.innerHTML = event.target.innerHTML;
			y = event.target.parentNode
					.getElementsByClassName("same-as-selected");
			yl = y.length;
			for (k = 0; k < yl; k++) {
				y[k].removeAttribute("class");
			}
			event.target.setAttribute("class",
					"same-as-selected");
			break;
		}
	}
	//h.click();
}

function getDropDown(classname,classnameInsideDivId,selectedDivId, onClickLocationChange){
	$("#"+classnameInsideDivId).remove()
	var x, i, j, l, ll, selElmnt, selectedDiv, optionsdiv, c;
	x = document.getElementsByClassName(classname);
	l = x.length;
	for (i = 0; i < l; i++) {
		selElmnt = $("."+classname).find("select")[0];
		//console.log("x["+i+"].getElementsByTagName(select)["+i+"] =",selElmnt)//all select options
		ll = selElmnt.length;//247
		/*for each element, create a new DIV that will act as the selected item:*/
		mainDiv = document.createElement("DIV");
		mainDiv.setAttribute("id", classnameInsideDivId);
		
		selectedDiv = document.createElement("DIV");
		selectedDiv.setAttribute("class", "select-selected");
		selectedDiv.setAttribute("id", selectedDivId);
		selectedDiv.setAttribute("onclick", "selectedDiv(event)");
		selectedDiv.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
		
		/*for each element, create a new DIV that will contain the option list:*/
		optionsdiv = document.createElement("DIV");
		optionsdiv.setAttribute("class", "select-items select-hide");
		
		//console.log(optionsdiv)
		for (j = 1; j < ll; j++) {
			/*for each option in the original select element,
			create a new DIV that will act as an option item:*/
			var value = selElmnt.options[j].getAttribute('value')//adding value to inner div
			var code =  selElmnt.options[j].getAttribute('code')//adding code to inner div
			var text = selElmnt.options[j].innerHTML;//adding text
			c = document.createElement("DIV"); 
			//c = '<div value="'+value+'" code="'+code+'" onclick="getStateList('+value+')">'+text+'</div>';
			c.innerHTML = text;
			c.setAttribute('value',value);
			c.setAttribute('code',code);
			c.setAttribute('onclick',onClickLocationChange+'('+value+')');//adding code to inner div
			optionsdiv.appendChild(c);
			//console.log(optionsdiv)
		}
		mainDiv.append(selectedDiv)
		mainDiv.append(optionsdiv);
		$("."+classname).append(mainDiv);
	}
} 
function selectedDiv(e){
	/*when the select box is clicked, close any other select boxes,
	and open/close the current select box:*/
	e.stopPropagation();
	closeAllSelect(e.target);
	e.target.nextSibling.classList.toggle("select-hide");
	e.target.classList.toggle("select-arrow-active");
}
function closeAllSelect(elmnt) {
		/*a function that will close all select boxes in the document,
		except the current select box:*/
		var x, y, i, xl, yl, arrNo = [];
		x = document.getElementsByClassName("select-items");
		y = document.getElementsByClassName("select-selected");
		xl = x.length;
		yl = y.length;
		for (i = 0; i < yl; i++) {
			if (elmnt == y[i]) {
				arrNo.push(i)
			} else {
				y[i].classList.remove("select-arrow-active");
			}
		}
		for (i = 0; i < xl; i++) {
			if (arrNo.indexOf(i)) {
				x[i].classList.add("select-hide");
			}
		}
	}
	/*if the user clicks anywhere outside the select box,
	 then close all select boxes:*/
	document.addEventListener("click", closeAllSelect);