document.addEventListener("DOMContentLoaded", function(){
	var totalprice= document.querySelector('.total')
	var mainel=document.querySelector('.pizza')
	var pizzapriceel=document.querySelector('.pizzaprice')
	var pizzaprice=parseFloat(pizzapriceel.innerText)
	if(mainel!=null){
		var sizearr=Array.from(document.querySelectorAll('.size'))
		var sizepricearr=Array.from(document.querySelectorAll('.size-price-item__value'))
		var optionarr=Array.from(document.querySelectorAll('.option'))
		var optionpricearr=Array.from(document.querySelectorAll('.option-price-item__value'))
		var sizeprice=parseFloat(sizepricearr[0].innerText)
		var optionprice= parseFloat(optionpricearr[0].innerText)
		totalprice.innerText=pizzaprice + sizeprice + optionprice+' VND'

	
		sizearr.forEach(function(item,index){
			item.addEventListener('change',function(){
				sizepricearr.forEach(function(priceitem,priceindex){
					if(index==priceindex){
						priceitem.style.display = "block";
						newsizepriceel= priceitem.innerText;
						totalprice.innerText= parseFloat(totalprice.innerText)-sizeprice+parseFloat(newsizepriceel)+"VND"
						sizeprice=parseFloat(newsizepriceel)
					}					
					else priceitem.style.display = "none";
				})
			})
		})
		optionarr.forEach(function(item,index){
			item.addEventListener('change',function(){
				optionpricearr.forEach(function(priceitem,priceindex){
					if(index==priceindex){
						priceitem.style.display = "block";
						newoptionpriceel= priceitem.innerText;
						totalprice.innerText= parseFloat(totalprice.innerText)-optionprice+parseFloat(newoptionpriceel)+"VND"
						optionprice=parseFloat(newoptionpriceel)
					}					
					else priceitem.style.display = "none";
				})
			})
		})
	}
	else{
		totalprice.innerText= parseFloat(pizzaprice)+ ' VND'
	}
	
});