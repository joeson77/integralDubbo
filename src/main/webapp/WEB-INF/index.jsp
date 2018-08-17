<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>阳光公益</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700' rel='stylesheet' type='text/css'>
<!--//fonts-->
</head>
<body> 
<!--header-->

	<!--banner-->
	<script src="js/responsiveslides.min.js"></script>
	<script>
    $(function () {
      $("#slider").responsiveSlides({
      	auto: true,
      	speed: 500,
        namespace: "callbacks",
        pager: true,
      });
    });
  </script>
	<div class="slider">
	    <div class="callbacks_container">
	      <ul class="rslides" id="slider">
	        <li>
	          <img src="images/banner.jpg" alt="">
	        </li>
	      </ul>
	  </div>
	  </div>
	  <!--header-->
		<div class="header">
			<div class="container">			
				<div class="logo">
					<a href="index.html"><img src="images/logo.png" alt=" " /></a>
				</div>
				<div class="top-nav">
					<span class="menu"> </span>
					<ul>
						<li class="active" ><a href="index.jsp" class="scroll">首页 </a></li>
						<li><a href="weDo.do" class="scroll">我们的行动  </a></li>
						<li><a href="404.html" class="scroll">关于我们</a></li>
						<li><a href="404.html" class="scroll">动态新闻</a></li>
						<li><a href="404.html" class="scroll">联系我们</a></li>
						<c:if test="${user != null}"><li><a style="cursor: default;" href="javascript:return false;" class="scroll">欢迎您:${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;积分:${user.userIntegral}</a></li></c:if>
						<c:if test="${user == null}"><li><a href="#" class="scroll">登录</a></li></c:if>
					</ul>
					<!--script-->
				<script>
					$("span.menu").click(function(){
						$(".top-nav ul").slideToggle(500, function(){
						});
					});
			</script>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	<!---->
	<!--content-->
	<div class="content">
		<div class="container">
			<div class="donate">
				<div class="donate-grid">
					<p>什么是公益？</p>
				</div>
					<div class="sit-in">
						<div class="col-md-4 ad-in">
							<h5>公益的起源</h5>
							<p>公益是公共利益事业的简称。这是为人民服务的一种通俗讲法。指有关社会公众的福祉和利益。。“公益”一词至迟在1887年已经在中国出现并被使用，且被写入清光绪三十四年（1908年）12月27日颁布的《城镇乡地方自治章程》中。
社会公益组织，一般是指那些非政府的、不把利润最大化当作首要目标，且以社会公益事业为主要追求目标的社会组织。
早先的公益组织主要从事人道主义救援和贫民救济活动，很多公益组织起源于慈善机构。</p>
						</div>
						<div class="col-md-4 ad-in">
							<h5>现有教育公益项目</h5>
							<p>美丽中国 (Teach For China) 成立于2008年，是北京立德未来助学公益基金会
下设的教育非营利项目。北京立德未来助学公益基金会是在北京市民政局下注册的非公募基金会，中国少年儿童基金会是美丽中国的长期公募合作伙伴。美丽中国是支教项目，但不只是支教项目：项目每年招募优秀青年人才，输送到我国教育资源匮乏地区从事两年一线教育教学工作。而这批优秀的青年力量，将在两年后乃至更长久的发展中，长期关注和致力于推动中国教育均衡化发展。通过独创性的教育综合改革示范区、美丽小学，云南大学-美丽中国农村教育研究中心输出美丽中国教学实践和经验，努力为中国农村教育的改革与发展探索出可复制的模式和标准，配合国家农村教育改革提供决策研究和智力支持。我们希望，最终实现“让所有中国孩子都能获得同等的优质教育”的愿景。 </p>
						</div>
						<div class="col-md-4 ad-in">
							<h5>宣传公益</h5>
							<p>《开包子铺的爸爸》一部由北京格林文化记意传媒公司施涛(石头）导演的公益感人微电影，本片献给广大社会学会感恩，帮助弱难体户，传递爱的正能量影片。
大量影视作品宣传公益，影响力大，起到了一定的宣传作用。
公益微电影的拍摄，值得推崇和宣扬！</p>
						</div>
						<div class="clearfix"> </div>
					</div>
					
				<a href="#" class="btn  btn-1c">捐赠</a>
				
			</div>	
		</div>
		<!---->
		<div class="number-at">
		<div class="in-number-in">
				<div class="in-number">
					<div class="wmuSlider example1">
					<div class="wmuSliderWrapper">
						<article style="position: absolute; width: 100%; opacity: 0;"> 
					  			<div class="welcome_box">
					  				<p>献出一份爱心,托起一份希望。</p>
					  			</div>
								<div class="clearfix"> </div>
						</article>
						<article style="position: absolute; width: 100%; opacity: 0;"> 
					  			<div class="welcome_box">
					  				<p>爱心是一滴水,奉献于人,才有价值。</p>
					  			</div>
								<div class="clearfix"> </div> 	
						</article>
						<article style="position: absolute; width: 100%; opacity: 0;"> 
				   				<div class="welcome_box">
					  				<p>让爱心与我们同在！</p>
					  			</div>
								<div class="clearfix"> </div>	   		 	
						</article>
			</div>
		</div>
		<!---->
		  <script src="js/jquery.wmuSlider.js"></script> 
			  <script>
       			$('.example1').wmuSlider({
					 pagination : false,
				});         
   		     </script> 	
			</div>
		</div>
		<div class="grid-men">
				<div class="men-grid">
					<span>05</span>
					<p>地震无情，白衣有爱</p>
					<a href="single.html"><img class="img-responsive" src="images/pi1.jpg" alt=" " /></a>
				</div>
				<div class="men-grid number-ape">
					<span>01</span>
					<p>天使之旅，九寨行动</p>
					<a href="single.html"><img class="img-responsive" src="images/pi2.jpg" alt=" " /></a>
				</div>
				<div class="men-grid ad-right">
					<span>18</span>
					<p>关注留守儿童</p>
					<a href="single.html"><img class="img-responsive" src="images/pi3.jpg" alt=" " /></a>
				</div>
				<div class="clearfix"> </div>
		</div>
			<div class="clearfix"> </div>
			</div>
			<!---->
			<div class="services-in">
				<div class="container">
					<h3>SERVICES</h3>
					<div class="service-grid">
						<div class="col-md-4 ser-in">
							<a href="single.html" class="b-link-stripe b-animate-go  thickbox"><div class="b-line b-line1"></div><div class="b-line b-line2"></div><div class="b-line b-line3"></div><div class="b-line b-line4"></div><div class="b-line b-line5"></div>
						     <img class="img-responsive" src="images/pic.jpg" style="top: 0px;" alt=" " /><div class="b-wrapper"><h2 class="b-animate b-from-left    b-delay03 "><img src="images/plus.png" alt="" style="top: 0px;"></h2>
						  </div></a>
							<h5>Duis autem vel eum</h5>
							<span>Claritas est etiam</span>
							<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam,.</p>														
							<a href="#" class="btn  btn-1c btn1 btn-1d">MORE INFO</a>						
						</div>
						<div class="col-md-4 ser-in">
						<a href="single.html" class="b-link-stripe b-animate-go  thickbox"><div class="b-line b-line1"></div><div class="b-line b-line2"></div><div class="b-line b-line3"></div><div class="b-line b-line4"></div><div class="b-line b-line5"></div>
						     <img class="img-responsive" src="images/pic1.jpg" style="top: 0px;" alt=" " /><div class="b-wrapper"><h2 class="b-animate b-from-left    b-delay03 "><img src="images/plus.png" alt="" style="top: 0px;"></h2>
						  </div></a>
							<h5>Duis autem vel eum</h5>
							<span>Claritas est etiam</span>
							<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam,.</p>
							<a href="#" class="btn  btn-1c btn1 btn-1d">MORE INFO</a>
						</div>
						<div class="col-md-4 ser-in">
							<a href="single.html" class="b-link-stripe b-animate-go  thickbox"><div class="b-line b-line1"></div><div class="b-line b-line2"></div><div class="b-line b-line3"></div><div class="b-line b-line4"></div><div class="b-line b-line5"></div>
						     <img class="img-responsive" src="images/pic2.jpg" style="top: 0px;" alt=" " /><div class="b-wrapper"><h2 class="b-animate b-from-left    b-delay03 "><img src="images/plus.png" alt="" style="top: 0px;"></h2>
						  </div></a>
							<h5>Duis autem vel eum</h5>
							<span>Claritas est etiam</span>
							<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam,.</p>
							<a href="#" class="btn  btn-1c btn1 btn-1d">MORE INFO</a>							
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
	</div>
	<!--footer-->
	<div class="footer">
		<div class="container">
			<span class="drop"> </span>
			<p>Clarity <span>38 mirus lorem 1230</span> Newyork, 2587-4577</p>
				<p class="footer-class">Design by <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
				<ul class="social-icons">
					<li><a href="#"><span> </span> </a></li>
					<li class="twitter"><a href="#"><span> </span></a></li>
					<li class="gmail"><a href="#"><span> </span> </a></li>
					<li class="print"><a href="#"><span> </span> </a></li>
				</ul>
		</div>	
		</div>
</body>
</html>