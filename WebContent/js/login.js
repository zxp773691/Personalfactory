(function login(){
	function init(){
		document.querySelector("#btn_login").addEventListener("click",function(){
			document.querySelector("#message").textContent="";
		},false);
	}
	window.addEventListener("load",init,false);
})();