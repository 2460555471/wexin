<%@ page language="java" import="java.util.*,Usermanage.UserInfor,util.WechatUtil" contentType="text/html;" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="zh-cmn-Hans">
 <head>
 	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>我的微信图书馆</title>
    <link rel="stylesheet" href="css/weui.css"/>
	<script src="js/jquery-1.7.1.js"></script>
	<style type="text/css">
	 html,body,ul,li {
	margin:0;
	padding:0;
	border:0;
	vertical-align:baseline;
}
	.tad11 {
     float:left;
     width:50%;
     height:60px;
     background:#0088ff;
     color:#fff;
     text-align:center;
     line-height:30px;
     cursor:pointer;
     }
		.tad11on{
	 background:blue;
	 }
		
		
	.tad21 {
     float:left;
     width:33.2%;
     height:60px;
     background:#0088ff;
     padding-left:0.1px;
     color:#fff;
     text-align:center;
     line-height:30px;
     cursor:pointer;
     }
		.tad21on{
	 background:blue;
	 }
	 
	 .tad31 {
     float:left;
     width:33.2%;
     height:60px;
     background:#0088ff;
     padding-left:0.1px;
     color:#fff;
     text-align:center;
     line-height:30px;
     cursor:pointer;
     }
		.tad31on{
	 background:blue;
	 }
	 
	 .tad41 {
     float:left;
     width:33.2%;
     height:60px;
     background:#0088ff;
     padding-left:0.1px;
     color:#fff;
     text-align:center;
     line-height:30px;
     cursor:pointer;
     }	
		.tad41on{
	 background:blue;
	 }
	 .hotbooklist
	 {
	 	width:100%;
	 	height:100px;
	 	padding-left:10px;
	 	text-decoration: none;
	 	border:1px solid red;
	 	list-style:none;
	 }
	 .hotbookdiv
	 {
		color:rgb(51, 51, 51);
        display:block
        font-family:宋体;
        font-size:12px;
        font-weight:normal;
        width:100%;
        height:100%;
	 }
	 .hotbookname
	 {
	 	font-family:宋体;
		font-size:18px;
		font-weight:normal;
	 }
	 .hotpicurl
	 {
	 	width:25%;
	 	height:100%;
	 	float:left;
	 	border:1px red solid;
	 }
	</style>
 </head>
 <body>
 
<div class="container js_container"></div>
<div class="page">
    <div class="page__bd" style="height: 100%;">
        <div class="weui-tab">
            <div class="weui-tab__panel">
            	<!-- tab1 -->
				<div id="tab1">
                     <div>
                     	<div id="tad11" class="tad11 tad11on">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">书名查找</span>
                     	</div>
                     	<div id="tad12"class="tad11">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">作者查找</span>
                     	</div>
                     	<div style="clear:both"></div>
                     </div>
					 <script>
                        $(document).ready(function() {
                            $("#tad11").click(function() {
                                $("#tad11").addClass("tad11on");
                                $("#tad12").removeClass("tad11on");
                            });
                             $("#tad12").click(function() {
                                $("#tad12").addClass("tad11on");
                                $("#tad11").removeClass("tad11on");
                            });                          
                        });
                     </script>
                        
					 <div style="width:100%;height:5px;background-color: #fafafa"></div>
					 
					 
					 <div class="weui-search-bar" id="searchBar">
                        <form class="weui-search-bar__form">
                            <div class="weui-search-bar__box">
                                <i class="weui-icon-search"></i>
                                <input type="search" style="height:28px;" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required/>
                                <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
                            </div>
                            <label class="weui-search-bar__label" id="searchText">
                                <i class="weui-icon-search"></i>
                                <span>搜索</span>
                            </label>
                        </form>
                        <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
                    </div>
                    <div class="weui-cells searchbar-result" id="searchResult" style="display:none;">
                        <div class="weui-cell weui-cell_access">
                            <div class="weui-cell__bd weui-cell_primary">
                                <p>实时搜索文本</p>
                            </div>
                        </div>
                        <div class="weui-cell weui-cell_access">
                            <div class="weui-cell__bd weui-cell_primary">
                                <p>实时搜索文本</p>
                            </div>
                        </div>
                        <div class="weui-cell weui-cell_access">
                            <div class="weui-cell__bd weui-cell_primary">
                                <p>实时搜索文本</p>
                            </div>
                        </div>
                        <div class="weui-cell weui-cell_access">
                            <div class="weui-cell__bd weui-cell_primary">
                                <p>实时搜索文本</p>
                            </div>
                        </div>
                    </div>
									 
                </div>
                 
                 
                 <!-- tab2 -->
                 <div id="tab2" style="display:none;">
                     <div>
                     	<div id="tad21" class="tad21 tad21on">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">热门标签</span>
                     	</div>
                     	<div id="tad22"class="tad21">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">标签查找</span>
                     	</div>
                     	<div id="tad23"class="tad21">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">中图分类</span>
                     	</div>
                     	<div style="clear:both"></div>
                     </div>
					 <script>
                        $(document).ready(function() {
                            $("#tad21").click(function() {
                                $("#tad21").addClass("tad21on");
                                $("#tad22").removeClass("tad21on");
                                $("#tad23").removeClass("tad21on");
                            });
                             $("#tad22").click(function() {
                                $("#tad22").addClass("tad21on");
                                $("#tad21").removeClass("tad21on");
                                 $("#tad23").removeClass("tad21on");
                            });    
                            $("#tad23").click(function() {
                                $("#tad23").addClass("tad21on");
                                $("#tad21").removeClass("tad21on");
                                $("#tad22").removeClass("tad21on");
                            });                        
                        });
                     </script>                       

				</div>
				 <!-- tab3 -->
                 <div id="tab3" style="display:none;">
                 	<div>
                     	<div id="tad31" class="tad31 tad31on">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">热搜排行</span>
                     	</div>
                     	<div id="tad32"class="tad31">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">新书上架</span>
                     	</div>
                     	<div id="tad33"class="tad31">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">推荐阅读</span>
                     	</div>
                     	<div style="clear:both"></div>
                     </div>
                                   
                     
                     <!-- 内容 -->
                     <div style="width:100%;height:3px;background-color: #fafafa"></div>
 					 <div>
 					 	
 					 	<ul >
                        	<li class="hotbooklist">
                        		<a href="http://www.baidu.com">
                        			<div class="hotpicurl">
                        				
                        			</div>
                        		</a>	
                        			
                        		<div class="hotbookdiv">
                        				<span class="hotbookname"><span class="">1 </span>C语言程序设计 TP2312</scan>
                            			
                            			
                            			
                            			<span class="">&nbsp;&nbsp;&nbsp;&nbsp;<span class="">侯沂等      编著</span></span>

                            			<span style="float:right">馆藏<span class="">5</span>本</span><br>

                            			<span class="">&nbsp;&nbsp;&nbsp;&nbsp;<span class="">机械工业出版社</span></span>
                            			<span class="">&nbsp;&nbsp;&nbsp;&nbsp;<span class="">2016</span></span>
                           				<span style="float:right">可借<span class="">5</span>本</span>
                        		</div>
                        		
                        	</li>
                    	</ul>
                    </div>
 					 
 				</div>
					 <script>
                        $(document).ready(function() {
                            $("#tad31").click(function() {
                                $("#tad31").addClass("tad31on");
                                $("#tad32").removeClass("tad31on");
                                $("#tad33").removeClass("tad31on");
                            });
                             $("#tad32").click(function() {
                                $("#tad32").addClass("tad31on");
                                $("#tad31").removeClass("tad31on");
                                 $("#tad33").removeClass("tad31on");
                            });    
                            $("#tad33").click(function() {
                                $("#tad33").addClass("tad31on");
                                $("#tad31").removeClass("tad31on");
                                $("#tad32").removeClass("tad31on");
                            });                        
                        });
                     </script>  
                    
				 <!-- tab4 -->
				 <div id="tab4" style="display:none;">
                    <div>
                     	<div id="tad41" class="tad41 tad41on">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">借订记录</span>
                     	</div>
                     	<div id="tad42"class="tad41">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">我的收藏</span>
                     	</div>
                     	<div id="tad43"class="tad41">
                     		<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">我的信息</span>
                     	</div>
                     	<div style="clear:both"></div>
                     </div>
					 <script>
                        $(document).ready(function() {
                            $("#tad41").click(function() {
                                $("#tad41").addClass("tad41on");
                                $("#tad42").removeClass("tad41on");
                                $("#tad43").removeClass("tad41on");
                            });
                             $("#tad42").click(function() {
                                $("#tad42").addClass("tad41on");
                                $("#tad41").removeClass("tad41on");
                                 $("#tad43").removeClass("tad41on");
                            });    
                            $("#tad43").click(function() {
                                $("#tad43").addClass("tad41on");
                                $("#tad41").removeClass("tad41on");
                                $("#tad42").removeClass("tad41on");
                            });                          
                        });
                     </script>  
                 </div>
            </div>

            <div class="weui-tabbar" style="height: 65px;position: fixed;">
                <a href="javascript:;" id="tabbar1" class="weui-tabbar__item weui-bar__item_on">
                    <span style="display: inline-block;position: relative;">
                        <img src="./images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                    </span>
                    <p class="weui-tabbar__label">书目检索</p>
                </a>
                <a href="javascript:;" id="tabbar2" class="weui-tabbar__item ">
                    <img src="./images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                    <p class="weui-tabbar__label">分类浏览</p>
                </a>
                <a href="javascript:;" id="tabbar3" class="weui-tabbar__item">
                    <span style="display: inline-block;position: relative;">
                        <img src="./images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                    </span>
                    <p class="weui-tabbar__label">热门推荐</p>
                </a>
                <a href="javascript:;" id="tabbar4" class="weui-tabbar__item">
                    <img src="./images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                    <p class="weui-tabbar__label">我的图书馆</p>
                </a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
		$("#tabbar1").on('click', function () {
			$(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');  
			$("#tab1").show();
			$("#tab2").hide();
			$("#tab3").hide();
			$("#tab4").hide();
        });
		$("#tabbar2").on('click', function () {
			$(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');  
			$("#tab1").hide();
			$("#tab2").show();
			$("#tab3").hide();
			$("#tab4").hide();
        });
		$("#tabbar3").on('click', function () {
			$(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on'); 
			$("#tab1").hide();
			$("#tab2").hide();
			$("#tab3").show();
			$("#tab4").hide();
        });
		$("#tabbar4").on('click', function () {
			$(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on'); 
			$("#tab1").hide();
			$("#tab2").hide();
			$("#tab3").hide();
			$("#tab4").show();
        });
    });
</script>


<script type="text/javascript">
    $(function(){
        var $searchBar = $('#searchBar'),
                $searchResult = $('#searchResult'),
                $searchText = $('#searchText'),
                $searchInput = $('#searchInput'),
                $searchClear = $('#searchClear'),
                $searchCancel = $('#searchCancel');

        function hideSearchResult(){
            $searchResult.hide();
            $searchInput.val('');
        }
        function cancelSearch(){
            hideSearchResult();
            $searchBar.removeClass('weui-search-bar_focusing');
            $searchText.show();
        }

        $searchText.on('click', function(){
            $searchBar.addClass('weui-search-bar_focusing');
            $searchInput.focus();
        });
        $searchInput
                .on('blur', function () {
                    if(!this.value.length) cancelSearch();
                })
                .on('input', function(){
                    if(this.value.length) {
                        $searchResult.show();
                    } else {
                        $searchResult.hide();
                    }
                })
        ;
        $searchClear.on('click', function(){
            hideSearchResult();
            $searchInput.focus();
        });
        $searchCancel.on('click', function(){
            cancelSearch();
            $searchInput.blur();
        });
    });
</script>

</body>
</html>