(function (pageModule){
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
			document.querySelector("#record_show").hidden=true;
			document.querySelector("#record_add").hidden=false;
			document.querySelector("#table_row_id").hidden=false;
			document.querySelector("#btn_addAndUpdate").textContent="修改";
			let xhr=new XMLHttpRequest();
			xhr.open("get","record?param=query&id="+param,true);
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4&&xhr.status==200){
					let json=JSON.parse(xhr.responseText);
					document.querySelector("#id").value=json.recordInfo.id;
					document.querySelector("#processtime").value=json.recordInfo.processtime;
					document.querySelector("#mlname").value=json.recordInfo.mlname;
					document.querySelector("#frname").value=json.recordInfo.frname;
					document.querySelector("#mlweight").value=json.recordInfo.mlweight;
					document.querySelector("#frweight").value=json.recordInfo.frweight;
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
			window.location.href="record?param=doDelete&ids="+param;
		}
	}
	window.addEventListener("load",()=>{
		let page_url="record?param=init";
		pageModule.init(page_url);
		document.querySelector("#btn_addRecord").addEventListener("click",()=>{
			document.querySelector("#btn_addAndUpdate").textContent="新增";
			document.querySelector("#record_show").hidden=true;
			document.querySelector("#record_add").hidden=false;
			document.querySelector("#table_row_id").hidden=true;
			document.querySelector("#processtime").value="";
			document.querySelector("#mlname").value="";
			document.querySelector("#frname").value="";
			document.querySelector("#mlweight").value="";
			document.querySelector("#frweight").value="";
		},false);
		document.querySelector("#btn_return").addEventListener("click",()=>{
			document.querySelector("#record_show").hidden=false;
			document.querySelector("#record_add").hidden=true;
		},false);
		document.querySelector("#btn_addAndUpdate").addEventListener("click",()=>{
			let operation=document.querySelector("#btn_addAndUpdate").textContent;
			let form=document.querySelector("#addAndUpdateForm");
			if(operation=="新增"){
				form.action="record?param=doAdd";
			}else{
				form.action="record?param=doUpdate";
			}
			form.submit();
		},false);
		document.querySelector("#btn_updateRecord").addEventListener("click",updateStore,false);
		document.querySelector("#btn_deleteRecord").addEventListener("click",deleteStore,false);
		document.querySelector("#all").addEventListener("change",selectAll,false);
		document.querySelector("#mlweight").addEventListener("change",frweight,false);
	},false);
})(window.managerPageModule);
