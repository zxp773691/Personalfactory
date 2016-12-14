(function(){
	function price(){
		let name=document.querySelector("#name").value;
		let xhr=new XMLHttpRequest();
		xhr.open("get","material?param=queryByName&name="+name,true);
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				let json=JSON.parse(xhr.responseText);
				document.querySelector("#price").value=json.material.price;
			}
		}
		xhr.send(null);
	}
	window.addEventListener("load",()=>{
		document.querySelector("#name").addEventListener("change",price,false);
},false);
})();
