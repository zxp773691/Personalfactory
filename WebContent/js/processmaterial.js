(function(){
	function frweight(){
		let mlweight=document.querySelector("#mlweight").value;
		let mlname=document.querySelector("#mlname").value;
		let xhr=new XMLHttpRequest();
		xhr.open("get","record?param=mlweightChange&mlname="+mlname,true);
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				let json=JSON.parse(xhr.responseText);
				let rate=json.material.rate;
				document.querySelector("#frweight").value=mlweight*rate;
			}
		}
		xhr.send(null);
	}
	window.addEventListener("load",()=>{
		document.querySelector("#mlweight").addEventListener("change",frweight,false);
},false);
})();
