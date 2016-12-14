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
			alert("请选择要查看的凭证！");
		}else if(count>1){
			alert("一次只能查看一张凭证");
		}else if(count==1){
			document.querySelector("#voucher_show").hidden=true;
			document.querySelector("#voucher_add").hidden=false;
			document.querySelector("#table_row_id").hidden=false;
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
					document.querySelector("#bususername").value=json.bususer.realname;
					if(json.storeuser==null){
						document.querySelector("#storeusername").value="";
					}else{
					document.querySelector("#storeusername").value=json.storeuser.realname;
					}
				}
			}
			xhr.send(null);
		}
		
	}
	function deleteStore(){
		let checks=document.getElementsByName("id_check");
		let param="";
		let count=0;
		let userid=0;
		for(let i=0,len=checks.length;i<len;i++){
			if(checks[i].checked==true){
				param+=checks[i].id;
				count++;
			}
		}
		if(count==0){
			alert("请选择要确认的凭证！");
		}else if(count>1){
			alert("一次只能确认一张凭证");
		}else if(count==1){
			userid=document.querySelector("#userid").value;
			window.location.href="voucher?param=updatestoreuser&id="+param+"&userid="+userid;
			
		}
	}
	window.addEventListener("load",()=>{
		let page_url="voucher?param=makesure";
		pageModule.init(page_url);
		document.querySelector("#btn_return").addEventListener("click",()=>{
			document.querySelector("#voucher_show").hidden=false;
			document.querySelector("#voucher_add").hidden=true;
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
		document.querySelector("#btn_lookVoucher").addEventListener("click",updateStore,false);
		document.querySelector("#btn_makesureVoucher").addEventListener("click",deleteStore,false);
		document.querySelector("#all").addEventListener("change",selectAll,false);
	},false);
})(window.managerPageModule);
