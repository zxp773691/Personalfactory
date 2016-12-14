(function (pageModule){

	function addstore(){
		let xhr=new XMLHttpRequest();
		xhr.open("get","store?param=addMenu&storeName="+document.getElementById("storeName").value,true);
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				let json=JSON.parse(xhr.responseText);
				if(json.i>0){
					document.getElementById("labeltip_name").textContent = "货物已存在";
				}else if(json.i==0){
				document.getElementById("labeltip_name").textContent = "可以添加";
				}else if(json.i==-1){
					document.getElementById("labeltip_name").textContent = "不能为空";
				}
			}
		}
		xhr.send(null);
	}
	function selectAll(){
		let all=document.querySelector("#all");
		let checks=document.getElementsByName("id_check");
		for(let i=0,len=checks.length;i<len;i++){
			if(all.checked==true){
				checks[i].checked=true;
			}else{
				checks[i].checked=false;
			}
		}
	}
	function updateStore(){
		let checks=document.getElementsByName("id_check");
		let param="";
		let count=0;
		for(let i=0,len=checks.length;i<len;i++){
			if(checks[i].checked==true){
				param+=checks[i].id;
				count++;
			}
		}
		if(count==0){
			alert("请选择要修改的数据！");
		}else if(count>1){
			alert("一次只能修改一行数据");
		}else if(count==1){
			document.querySelector("#store_show").hidden=true;
			document.querySelector("#store_add").hidden=false;
			document.querySelector("#table_row_id").hidden=false;
			document.querySelector("#tr_type").hidden=false;
			document.querySelector("#tr_newweight").hidden=false;
			document.querySelector("#labeltip_name").hidden=true;
			document.querySelector("#storeName").readOnly="readonly";
			document.querySelector("#weight").readOnly="readonly";
			document.querySelector("#btn_addAndUpdate").textContent="修改";
			let xhr=new XMLHttpRequest();
			xhr.open("get","store?param=query&id="+param,true);
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4&&xhr.status==200){
					let json=JSON.parse(xhr.responseText);
					document.querySelector("#id").value=json.storeInfo.id;
					document.querySelector("#storeName").value=json.storeInfo.name;
					document.querySelector("#weight").value=json.storeInfo.weight;
					if(json.storeInfo.type=="0"){
						document.querySelector("#type").value="原料";
					}else{
						document.querySelector("#type").value="面粉";
					}
				}
			}
			xhr.send(null);
		}
		
	}
	function deleteStore(){
		let checks=document.getElementsByName("id_check");
		let param="";
		for(let i=0,len=checks.length;i<len;i++){
			if(checks[i].checked==true){
				if(param==""){
					param+=checks[i].id;
				}else{
					param+=",";
					param+=checks[i].id;
				}
			}
		}
		if(param==""){
			alert("请选择要删除的数据！");
		}else{
			window.location.href="store?param=doDelete&ids="+param;
		}
	}
	window.addEventListener("load",()=>{
		let page_url="store?param=init";
		pageModule.init(page_url);
		document.querySelector("#btn_addStore").addEventListener("click",()=>{
			document.querySelector("#btn_addAndUpdate").textContent="新增";
			document.querySelector("#store_show").hidden=true;
			document.querySelector("#store_add").hidden=false;
			document.querySelector("#table_row_id").hidden=true;
			document.querySelector("#tr_type").hidden=true;
			document.querySelector("#tr_newweight").hidden=true;
			document.querySelector("#storeName").readOnly="";
			document.querySelector("#weight").readOnly="";
			document.querySelector("#labeltip_name").hidden=false;
			document.querySelector("#storeName").value="";
			document.querySelector("#weight").value="";
		},false);
		document.querySelector("#btn_return").addEventListener("click",()=>{
			document.querySelector("#store_show").hidden=false;
			document.querySelector("#store_add").hidden=true;
		},false);
		document.querySelector("#btn_addAndUpdate").addEventListener("click",()=>{
			let operation=document.querySelector("#btn_addAndUpdate").textContent;
			let form=document.querySelector("#addAndUpdateForm");
			if(operation=="新增"){
				form.action="store?param=doAdd";
			}else{
				form.action="store?param=doUpdate";
			}
			form.submit();
		},false);
		document.querySelector("#btn_updateStore").addEventListener("click",updateStore,false);
		document.querySelector("#btn_deleteStore").addEventListener("click",deleteStore,false);

		document.querySelector("#storeName").addEventListener("blur",addstore,false);
		document.querySelector("#all").addEventListener("change",selectAll,false);
	},false);
})(window.managerPageModule);
