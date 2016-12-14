(function(){
	function init(){
		document.querySelector("#return_main").addEventListener("click",()=>{
			window.location.href="lar?param=login&userName="+document.getElementById("username").value+"&password="+document.getElementById("password").value;
		},false);
		document.querySelector("#return_login").addEventListener("click",()=>{
			window.location.href="init";
		},false);
	}
	$(function(){
		  $(".leftnav h6").click(function(){
			  $(this).next().slideToggle(200);	
			  $(this).toggleClass("on"); 
		  })
		  $(".leftnav ul li a").click(function(){
//			    $("#a_leader_txt").text($(this).text());
		  		$(".leftnav ul li a").removeClass("on");
				$(this).addClass("on");
		  })
		});
	window.addEventListener("load",init,false);
})();

