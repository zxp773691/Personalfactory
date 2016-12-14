(function (pageModule){

	function addstore(){
		let xhr=new XMLHttpRequest();
		xhr.open("get","customer?param=addMenu&name="+document.getElementById("name").value,true);
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				let json=JSON.parse(xhr.responseText);
				if(json.i>0){
					document.getElementById("labeltip_name").textContent = "客户姓名已存在";
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
			document.querySelector("#customer_show").hidden=true;
			document.querySelector("#customer_add").hidden=false;
			document.querySelector("#table_row_id").hidden=false;
			document.querySelector("#labeltip_name").hidden=true;
			document.querySelector("#btn_addAndUpdate").textContent="修改";
			let xhr=new XMLHttpRequest();
			xhr.open("get","customer?param=query&id="+param,true);
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4&&xhr.status==200){
					let json=JSON.parse(xhr.responseText);
					document.querySelector("#id").value=json.customer.id;
					document.querySelector("#customerno").value=json.customer.customerno;
					document.querySelector("#name").value=json.customer.name;
					document.querySelector("#address").value=json.customer.address;
					document.querySelector("#idcard").value=json.customer.idcard;
					document.querySelector("#registertime").value=json.customer.registertime;
					document.querySelector("#telephone").value=json.customer.telephone;
					document.querySelector("#remark").value=json.customer.remark;
					if("0"==json.customer.state){
						document.querySelector("#state").value="黑名单";
					}else{
						document.querySelector("#state").value="正常";
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
			window.location.href="customer?param=doDelete&ids="+param;
		}
	}
	window.addEventListener("load",()=>{
		let page_url="customer?param=init";
		pageModule.init(page_url);
		document.querySelector("#btn_addCustomer").addEventListener("click",()=>{
			document.querySelector("#btn_addAndUpdate").textContent="新增";
			document.querySelector("#customer_show").hidden=true;
			document.querySelector("#customer_add").hidden=false;
			document.querySelector("#table_row_id").hidden=true;
			document.querySelector("#labeltip_name").hidden=false;
			document.querySelector("#name").value="";
			document.querySelector("#telephone").value="";
			document.querySelector("#remark").value="";
			document.querySelector("#address").value="";
			document.querySelector("#idcard").value="";
			document.querySelector("#registertime").value="";
			document.querySelector("#customerno").value="";
		},false);
		document.querySelector("#btn_return").addEventListener("click",()=>{
			document.querySelector("#customer_show").hidden=false;
			document.querySelector("#customer_add").hidden=true;
		},false);
		document.querySelector("#btn_addAndUpdate").addEventListener("click",()=>{
			let operation=document.querySelector("#btn_addAndUpdate").textContent;
			let form=document.querySelector("#addAndUpdateForm");
			if(operation=="新增"){
				form.action="customer?param=doAdd";
			}else{
				form.action="customer?param=doUpdate";
			}
			form.submit();
		},false);
		document.querySelector("#btn_updateCustomer").addEventListener("click",updateStore,false);
		document.querySelector("#btn_deleteCustomer").addEventListener("click",deleteStore,false);

		document.querySelector("#name").addEventListener("blur",addstore,false);
		document.querySelector("#all").addEventListener("change",selectAll,false);
	},false);
})(window.managerPageModule);
