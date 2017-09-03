<%@ page language="java" import="java.util.*,Usermanage.UserInfor,util.WechatUtil" contentType="text/html;" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<% 
//获取用户的OpenID
//调用接口获得用户的其它各项信息
//String code = request.getParameter("code");
//UserInfor user=WechatUtil.getUserInfoFromCode(code);
%>
<!doctype html>
<html lang="zh-cmn-Hans">
 <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>注册借阅伴侣</title>
    <link rel="stylesheet" href="css/weui.css"/>
	<script src="js/jquery-1.7.1.js"></script>

 </head>
 <body >
  <div class="page">
		
		<div style="background-color: #0088ff;width:100%;height:60px">		
				<span class="" style="font-family:宋体;font-size:150%;color:white;display:block;padding:10px;padding-left:15px">欢迎注册借阅伴侣微信平台</span>
			
		</div>
		
		<div style="width:100%;height:120px"> 
			<div style="width:110px;height:110px; margin:10px auto;">
				<img id="headimgurl" src="image/timg.jpg" width="100%"  border="0" alt="">
			</div>
		</div>
		

		<form method="post" action="">

			<div class="weui-cells weui-cells_form " >		

				<div class="weui-cell">
					<div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入您的真实姓名" id="username" />
					</div>
				</div>
				<div style="width:100%;height:3px;background-color: #fafafa"></div>

				<div class="weui-cell">
					<div class="weui-cell__hd">
					    <label class="weui-label">手机号</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="tel" placeholder="请输入手机号" id="phone" name="phone" />
					</div>
				 </div>


				<div style="width:100%;height:3px;background-color: #fafafa"></div>
				<div class="weui-cell weui-cell_select weui-cell_select-after">

					<div class="weui-cell__hd">
						<label for="" class="weui-label">性别</label>
					</div>
					<div class="weui-cell__bd">
						<select class="weui-select" name="select2" id="sex">
							<option value="0">男</option>
							<option value="1">女</option>
					 </select>
					</div>
				 </div>

				<div style="width:100%;height:3px;background-color: #fafafa"></div>
				<div class="weui-cell">
					<div class="weui-cell__hd"><label for="" class="weui-label">出生日期</label></div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="date" id="birthdate" value=""/>
					</div>
				</div>
	
				<div class="weui-btn-area" style="width:60%;margin:10px auto">
					<a href="javascript:;" id="submit" class="weui-btn weui-btn_plain-primary">确定</a> 
				</div>
			</div>
		</form>
        <div class="msg"></div>
</div>



<script type="text/javascript">

function isTelOrMobile(telephone){  
    var teleReg = /^((0\d{2,3})-)(\d{7,8})$/;  
    var mobileReg =/^1[358]\d{9}$/;   
    if (!teleReg.test(telephone) && !mobileReg.test(telephone)){  
        return false;  
    }else{  
        return true;  
    }  
}
function isName(name){ 
	 var reg = /^[\u4e00-\u9fa5]{2,4}$/i; 
	 if (!reg.test(name)) 
		return false;
	 else return true;
}

	$('#submit').click(function() {
		
        var username= $('#username').val();
        var sex=$('#sex :selected').val() ;
		var birthdate=$('#birthdate').val() ;
		var phone=$('#phone').val() ;

//		if(isName(username)==true)
//		{
//			if(birthdate!="")
//			{
//				if(isCardNo(idcard)==true)
//				{
//					if(isTelOrMobile(phone)==true)
//					{
//						if(cherifynum!="")
//						{	$('.msg').html('');

//								postname:username,
//								postsex:sex,
//								postbirthdate:birthdate,
//								postidcard:idcard,
//								postphone:phone,
//								postcherifynum:cherifynum

                                $.ajax({
                                        type:"POST", //请求方式
                                        url:"http://localhost:8080/wexin/PostUtils/Register", //请求路径
                                        cache: false,
                                        data:"name="+username,  //向后台传参
                                        dataType: 'json',   //返回值类型
                                        success:function(person){//person为后台返回的数据，json格式
//                                            var dataObj = person,
//                                                    con = "";
//                                            $.each(dataObj, function(index, item){
//                                                con += "<li>编号："+item.id+"</li>";
////                                                con += "<li>用户名："+item.name+"</li>";
////                                                con += "<li>密码："+item.pwd+"</li>";
//                                            });
                                           // $("#con").html(person); //把内容入到这个div中即完成
                                            alert(person);
                                        },
                                        error:function(){
                                            alert("error");
                                        }

                                    });



//						}
//						else
//							$('.msg').html('请填写验证码');
//					}
//					else
//						$('.msg').html('手机号错误');
//				}
//				else
//					$('.msg').html('身份证号错误');
//			}
//			else
//				$('.msg').html('出生日期错误');
//
//		}
//		else
//			$('.msg').html('姓名错了');

		

    });



</script>

 </body>
</html>
