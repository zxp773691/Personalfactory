(function (pageModule){

	function addstore(){
		let xhr=new XMLHttpRequest();
		xhr.open("get","voucher?param=addMenu&voucherno="+document.getElementById("voucherno").value,true);
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				let json=JSON.parse(xhr.responseText);
				if(json.i>0){
					document.getElementById("labeltip_voucherno").textContent = "凭证编号已存在";
				}else if(json.i==0){
				document.getElementById("labeltip_voucherno").textContent = "可以添加";
				}else if(json.i==-1){
					document.getElementById("labeltip_voucherno").textContent = "不能为空";
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
			document.querySelector("#voucher_show").hidden=true;
			document.querySelector("#voucher_add").hidden=false;
			document.querySelector("#table_row_id").hidden=false;
			document.querySelector("#labeltip_voucherno").hidden=true;
			document.querySelector("#btn_addAndUpdate").textContent="修改";
			let xhr=new XMLHttpRequest();
			xhr.open("get","voucher?param=query&id="+param,true);
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4&&xhr.status==200){
					let json=JSON.parse(xhr.responseText);
					document.querySelector("#id").value=json.voucher.id;
					document.querySelector("#voucherno").value=json.voucher.voucherno;
					if(json.voucher.type=="0"){
						document.querySelector("#type").value="购买原料";
					}else if(json.voucher.type=="1"){
						document.querySelector("#type").value="出售原料";
					}else if(json.voucher.type=="2"){
						document.querySelector("#type").value="出售面粉";
					}
					document.querySelector("#occurtime").value=json.voucher.occurtime;
					document.querySelector("#weight").value=json.voucher.weight;
					document.querySelector("#totalprice").value=json.voucher.totalprice;
					document.querySelector("#materialname").value=json.material.name;
					document.querySelector("#customername").value=json.customer.name;
					if(json.bususer==null){
						document.querySelector("#bususername").value="";
					}else{
					document.querySelector("#bususername").value=json.bususer.realname;
					}
					document.querySelector("#storeusername").value=json.storeuser.realname;
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
			window.location.href="voucher?param=doDelete&ids="+param;
		}
	}
	window.addEventListener("load",()=>{
		let page_url="voucher?param=init";
		pageModule.init(page_url);
		document.querySelector("#btn_addVoucher").addEventListener("click",()=>{
			document.querySelector("#btn_addAndUpdate").textContent="新增";
			document.querySelector("#voucher_show").hidden=true;
			document.querySelector("#voucher_add").hidden=false;
			document.querySelector("#table_row_id").hidden=true;
			document.querySelector("#labeltip_voucherno").hidden=false;
			document.querySelector("#voucherno").value="";
			document.querySelector("#type").value="";
			document.querySelector("#occurtime").value="";
			document.querySelector("#weight").value="";
			document.querySelector("#totalprice").value="";
			document.querySelector("#materialname").value="";
			document.querySelector("#customername").value="";
			document.querySelector("#bususername").value="";
			document.querySelector("#storeusername").value="";
		},false);
		document.querySelector("#btn_return").addEventListener("click",()=>{
			document.querySelector("#voucher_show").hidden=false;
			document.querySelector("#voucher_add").hidden=true;
		},false);
		document.querySelector("#btn_addAndUpdate").addEventListener("click",()=>{
			let operation=document.querySelector("#btn_addAndUpdate").textContent;
			let form=document.querySelector("#addAndUpdateForm");
			if(operation=="新增"){
				form.action="voucher?param=doAdd";
			}else{
				form.action="voucher?param=doUpdate";
			}
			form.submit();
		},false);
		document.querySelector("#btn_updateVoucher").addEventListener("click",updateStore,false);
		document.querySelector("#btn_deleteVoucher").addEventListener("click",deleteStore,false);

		document.querySelector("#voucherno").addEventListener("blur",addstore,false);
		document.querySelector("#all").addEventListener("change",selectAll,false);
	},false);
})(window.managerPageModule);
