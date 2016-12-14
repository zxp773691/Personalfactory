(function (pageModule){
	function updateRole(){
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
			window.location.href="userRole?param=update&id="+param;
		}		
	}
	function addstore(){
		let xhr=new XMLHttpRequest();
		xhr.open("get","user?param=addMenu&userName="+document.getElementById("userName").value,true);
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				let json=JSON.parse(xhr.responseText);
				if(json.i>0){
					document.getElementById("labeltip_name").textContent = "用户名已存在";
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
			document.querySelector("#user_show").hidden=true;
			document.querySelector("#user_add").hidden=false;
			document.querySelector("#table_row_id").hidden=false;
			document.querySelector("#userName").readOnly="readonly";
			document.querySelector("#labeltip_name").hidden=true;
			document.querySelector("#btn_addAndUpdate").textContent="修改";
			let xhr=new XMLHttpRequest();
			xhr.open("get","user?param=query&id="+param,true);
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4&&xhr.status==200){
					let json=JSON.parse(xhr.responseText);
					document.querySelector("#id").value=json.user.id;
					document.querySelector("#userName").value=json.user.username;
					document.querySelector("#password").value=json.user.password;
					document.querySelector("#userNo").value=json.user.userno;
					document.querySelector("#realName").value=json.user.realname;
					document.querySelector("#telephone").value=json.user.telephone;
					document.querySelector("#idCard").value=json.user.idcard;
					document.querySelector("#registerTime").value=json.user.registertime;
					if(json.user.state=="0"){
						document.querySelector("#state").value="离职";
					}else if(json.user.state=="1"){
						document.querySelector("#state").value="正常";
					}else{
						document.querySelector("#state").value="休假";
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
			window.location.href="user?param=doDelete&ids="+param;
		}
	}
	window.addEventListener("load",()=>{
		let page_url="user?param=init";
		pageModule.init(page_url);
		document.querySelector("#btn_addUser").addEventListener("click",()=>{
			document.querySelector("#btn_addAndUpdate").textContent="新增";
			document.querySelector("#user_show").hidden=true;
			document.querySelector("#user_add").hidden=false;
			document.querySelector("#table_row_id").hidden=true;
			document.querySelector("#userName").readOnly="";
			document.querySelector("#labeltip_name").hidden=false;
			document.querySelector("#userName").value="";
			document.querySelector("#password").value="";
			document.querySelector("#userNo").value="";
			document.querySelector("#realName").value="";
			document.querySelector("#telephone").value="";
			document.querySelector("#idCard").value="";
			document.querySelector("#registerTime").value="";
			document.querySelector("#state").value="";
		},false);
		document.querySelector("#btn_return").addEventListener("click",()=>{
			document.querySelector("#user_show").hidden=false;
			document.querySelector("#user_add").hidden=true;
		},false);
		document.querySelector("#btn_addAndUpdate").addEventListener("click",()=>{
			let operation=document.querySelector("#btn_addAndUpdate").textContent;
			let form=document.querySelector("#addAndUpdateForm");
			if(operation=="新增"){
				form.action="user?param=doAdd";
			}else{
				form.action="user?param=doUpdate";
			}
			form.submit();
		},false);
		document.querySelector("#btn_updateUser").addEventListener("click",updateStore,false);
		document.querySelector("#btn_deleteUser").addEventListener("click",deleteStore,false);
		document.querySelector("#btn_updateRole").addEventListener("click",updateRole,false);
		document.querySelector("#userName").addEventListener("blur",addstore,false);
		document.querySelector("#all").addEventListener("change",selectAll,false);
	},false);
})(window.managerPageModule);
