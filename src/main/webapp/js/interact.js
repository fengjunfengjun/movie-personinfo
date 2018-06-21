window.onload= function(){

	//默认状态页面信息
	(function firstload(){
		axios({
			method:'post',
			url:'http://rap2api.taobao.org/app/mock/12040/correctmsg',
			data:{
				"name":"zqian",
				"age":14
			},
			headers:{'Content-Type':'application/x-www-form-urlencoded'}
		}).then(function(response){
			// var listmsg = response.data.data;
			// var vipType = listmsg.vipType;
			// var personalLogo = listmsg.pensonalLogo;
			// var username = listmsg.username;
			// var personal_portrait = document.getElementsByClassName('personal_portrait');
			// var viplogo = document.getElementsByClassName('viplogo');
			// var userName = document.getElementsByClassName("userName");
			// Object.keys(personal_portrait).forEach(function(index,value,array){
			// 	personal_portrait[index].setAttribute('src',personalLogo);
			// });
			// Object.keys(viplogo).forEach(function(index,value,array){
			// 	viplogo[index].style.color = "#d0a169";
			// });
			// Object.keys(userName).forEach(function(index,value,array){
			// 	userName[index].innerHTML = username;
			// });
			console.log(response);
		});
	})();

	// //登录注册
	// var login = document.getElementById('login');
	// var register = document.getElementById('register');
	// login.addEventListener('click',loginDialog,false);
	// register.addEventListener('click',registerDialog,false);

	// //登录框
	// function loginDialog(){

	// }
	//开通会员元素
	var buyvip = document.getElementById('buyvip');
	var buytennisvip  =document.getElementById("buytennisvip");
	var viplogo = document.getElementsByClassName('viplogo');
	var viptennislogo = document.getElementsByClassName('viptennislogo');

	buyvip.addEventListener('click',buyviprap,false);
	buytennisvip.addEventListener('click',buytennisviprap,false);


	var temp = '';

	//功能区点击跳转元素
	var playrecords = document.getElementsByClassName('playrecords');
	var subscription = document.getElementsByClassName('subscription');
	var binge_watch = document.getElementsByClassName('binge_watch');
	var my_message = document.getElementsByClassName('my_message');
	var space_room = document.getElementsByClassName('space_room');
	var personal_setting = document.getElementsByClassName('personal_setting');
	var personalCart = document.getElementById('personalCart');
	var listftnbtns = document.getElementsByClassName('listbtn');


	playrecords[0].addEventListener('click',playrecordsrap,false);
	subscription[0].addEventListener('click',subscriptionrap,false);
	binge_watch[0].addEventListener('click',binge_watchrap,false);
	// my_message[0].addEventListener('click',my_message,false);
	space_room[0].addEventListener('click',space_roomrap,false);
	personal_setting[0].addEventListener('click',getpersonalmsgrap,false);


	//选中状态控制
	// function setactive(){
	// 	Object.keys(listftnbtns).forEach(function(item,index,array){
	// 		listftnbtns[index].classList.remove("active");
	// 	})
	// 	alert(this);
	// 	this.classList.add('active');
	// }

	//普通会员开通接口
	function buyviprap(){
		axios({
			method:'get',
			url:'buyVip.do',
			// data:{
			// 	username:'',
			// 	code:1
			// }
		})
		.then(function(response){
			var data = response.data;
			console.log(response);
			if(data.code == 1){
				Object.keys(viplogo).forEach(function(item,index,array){
					viplogo[index].style.color = "#d0a169";
				})
				alert(data.msg);
			}else if(data.code == 0){
				alert(data.msg);
			}
		})
	}
	//网球会员开通接口
	function buytennisviprap(){
		axios({
			method:'get',
			url:'http://rap2api.taobao.org/app/mock/12040/buyvip2',
			// data:{
			// 	username:'',
			// 	code:1
			// }
		})
		.then(function(response){
			var data = response.data;
			if(data.code == 1){
				Object.keys(viptennislogo).forEach(function(item,index,array){
					viptennislogo[index].style.color = "#d0a169";
				})
				alert(data.msg);
			}
		})
	}
	//播放记录回调函数
	function playrecordsrap(){
		Object.keys(listftnbtns).forEach(function(item,index,array){
			listftnbtns[index].classList.remove("active");
		})
		this.classList.add('active');

		while(main_content.hasChildNodes()){
			main_content.removeChild(main_content.firstChild);
		}
		temp = `<div class="title">
					<span>播放记录</span>
					<input type="radio" name="filtertele"><label>过滤短视频</label>
					<input type="radio" name="filtertele"><label>过滤已看完</label>
				</div>`;
		axios({
				method:'get',
				url:'http://rap2api.taobao.org/app/mock/12040/records'
			})
			.then(function(response){
				var data = response.data.data;
				var records_lists = data.movies;
				records_lists.forEach(function(item,index,array){
					console.log(records_lists);
					temp += `<span class="playtime">今天</span>`;
					temp += `
						<div class="teleplay clearflow">
							<img class="telephoto" src="${data.img}">

							<p class="tele_title">${item.movieName}</p>
						
							<p class="playcount">
								<i class="iconfont icon-bofang"></i>			
								<span>播放次数 ${item.playCount} 万次</span>
							</p>
						</div>
					`;
				});
				main_content.innerHTML = temp;
				// alert('hello');
				temp = '';
			})
	}

	//订阅回调函数
	function subscriptionrap(){
		Object.keys(listftnbtns).forEach(function(item,index,array){
			listftnbtns[index].classList.remove("active");
		})
		this.classList.add('active');

		main_content.innerHTML = "";
		axios({
				method:'get',
				url:'http://rap2api.taobao.org/app/mock/12040/subcription'
			})
			.then(function(response){
				var data = response.data;
				var titlecontent = '';
				var pingdaodata = '';
				var subscription_lists = data.subscription_lists;
				var wrapper = document.createElement('div');
				var updatedatas = document.createElement('div');
				var updatepingdao = document.createElement('div');
				var ul = document.createElement('ul');
				wrapper.className = 'content_details';
				ul.className = 'piclist';
				updatedatas.className = 'updatedatas';
				updatepingdao.className = 'pindao_details';
				

				console.log(main_content);
				
				titlecontent +=`
						<div class="title">
							<li class="selected">更新</li>
							<li>频道</li>
						</div>`;
				pingdaodata = `<div class="nodata pindao">
									<i class="iconfont icon-icon-test"></i>
									<em>你没有关注频道</em>
								</div>`;
				console.log(data);
				if(data){
					subscription_lists.forEach(function(item,index,array){
						temp += `
							<li>
		                        <div class="piclist_pic">

		                             <a href="javascript:;" title="微表情揭林更新王丽坤肢体同步—猛回头">


		                             	<img src="${item.img}">

			                            <div class="wrapper_listTitle">
			                                <div class="listTitle">
			                                    <span class="listTitle_left">昨日</span>
			                                    <span class="listTitle_right">${item.tele_long}</span>
				                            </div>
			                        	</div>
		                            </a>
		                        </div>
			                    <div class="piclist_info">
			                        <p class="piclist_title">
			                            <a href="javascript:;" title="微表情揭林更新王丽坤肢体同步—猛回头">微表情揭林更新王丽坤肢体同步—猛回头</a>
			                        </p>
			                        <div class="play_status">
			                        	<a  href="" target="_blank">${item.subtitle}</a>
			                        	<span class="fr">
			                        	 	<i class="iconfont icon-bofang"></i>${item.playCount}次
			                        	</span>
			                        </div>
			                    </div>
		                    </li>
						`;
					})
				}else{
					temp +=`<div class="nodata update">
								<i class="iconfont icon-icon-test"></i>
								<em>您没有订阅内容</em>
							</div>
					`;
				}
				// wrapper.innerHTML = titlecontent;
				ul.innerHTML = temp;
				updatedatas.append(ul);

				wrapper.append(updatedatas);
				updatepingdao.innerHTML = pingdaodata;
				wrapper.append(updatepingdao);

				main_content.innerHTML = titlecontent;
				main_content.append(wrapper);
				console.log(main_content);
				tabChange();
				temp = '';
			})
		// tabChange();
	}

	//追剧回调函数
	function binge_watchrap(){
		Object.keys(listftnbtns).forEach(function(item,index,array){
			listftnbtns[index].classList.remove("active");
		})
		this.classList.add('active');
		main_content.innerHTML = '';
		temp = `
			<div id ="title" class="title">
				<li class="selected">近期追剧</li>
				<li>视频收藏</li>
			</div>

			<div class="content_details">


				<div class="zhuiju_details">
					<div class="nodata zhuiju">
						<i class="iconfont icon-icon-test"></i>
						<em>您没有正在追的剧</em>
					</div>
				</div>


				<div class="shoucang_details">

					<div class="nodata shoucang">
						<i class="iconfont icon-icon-test"></i>
						<em>您没有收藏过视频</em>
					</div>
				</div>
			</div>
		`;
		main_content.innerHTML = temp;
		temp = '';
		tabChange();
	}

	//我的消息回调函数

	//我的空间动态
	function space_roomrap(){
		Object.keys(listftnbtns).forEach(function(item,index,array){
			listftnbtns[index].classList.remove("active");
		})
		this.classList.add('active');
		main_content.innerHTML = '';

		var space_room_wrapper = document.createElement('div');
		var my_space_tab = document.createElement('div');
		var content_details = document.createElement('div');

		space_room_wrapper.className = 'my_space_wrap';
		my_space_tab.className = 'my_space_tab';
		content_details.className = 'content_details';

		var temp = `<div class="my_space_info clearflow">

						<img src="img/portrait.png" class="img-circle personal_portrait">

						<div class="personal_details">
							<p>
								<a href="javascript:;" class="userName">仰望pp901</a>
								<a href="" class="sub_title">
									<i class="iconfont icon-group"></i>
								</a>
							</p>

							<div class="person_intro">
								<span class="a_word">一句话签名</span>
								<a href=""><i class="iconfont icon-edit" class="edit_word"></i></a>

							</div>

							<!-- 个人签名编辑框 -->
							<div class="eidt_details">
								<input type="text" name="person_intro_word" value="一句话介绍下自己吧">
								<input type="button" name="save" class="save" value="保存">
								<input type="button" name="cancel" class="cancel" value="取消">
							</div>
						</div>
					</div>`;
		var listbtns = `<div class="title">
							<li class="selected">动态</li>
							<li>圈子</li>
							<li>视频</li>
							<li>播单</li>
						</div>`;
		var nodata_details = ` <div class="state_details">
									<div class="nodata state">
										<i class="iconfont icon-icon-test"></i>
										<em>Ta还未发过任何动态</em>
									</div>
								</div>
								<div class="circle_details">

									<div class="nodata circle">
										<i class="iconfont icon-icon-test"></i>
										<em>Ta还未加入任何圈子～</em>
									</div>
								</div>

								<div class="video_details">
									
									<div class="nodata video">
										<i class="iconfont icon-icon-test"></i>
										<em>还未上传任何视频哦，快去上传新视频吧~</em>
									</div>
								</div>

								<div class="listsnote_details">
									
									<div class="nodata listsnote">
										<i class="iconfont icon-icon-test"></i>
										<em>快去创建播单收纳喜欢的视频吧~</em>
									</div>
								</div>`;
		my_space_tab.innerHTML = listbtns;
		space_room_wrapper.innerHTML = temp;
		content_details.innerHTML = nodata_details;

		my_space_tab.append(content_details);
		space_room_wrapper.append(my_space_tab);

		main_content.append(space_room_wrapper);

		tabChange();
	}

	//个人信息获取回调函数
	function getpersonalmsgrap(){
		Object.keys(listftnbtns).forEach(function(item,index,array){
			listftnbtns[index].classList.remove("active");
		})
		this.classList.add('active');
		main_content.innerHTML = '';
		temp = '';

		(function change(){
		    $.ajax({  
			    type: "get",  
			    url: 'personalmsg.html',  
			    success: function(html) {  
			        main_content.innerHTML = html;  
			        tabChange();
			    }  
		    }); 
		})();

		axios({
				method:'get',
				// url:'findUser.do'
				url:'http://rap2api.taobao.org/app/mock/data/70829'
			})
		.then(function(response){
			//个人信息显示接口
			var changehead = document.getElementById('changehead');
			var personal_portrait = document.getElementsByClassName('personal_portrait');
			var userNames = document.getElementsByClassName('userName');
			var userId = document.getElementsByClassName('userId');
			var sex = document.getElementsByClassName('sexmsg');
			var living = document.getElementsByClassName('livingmsg');
			var qq = document.getElementsByClassName('qqmsg');
			var email = document.getElementsByClassName('email');
			var tel = document.getElementsByClassName('tel');	
			var vipType = document.getElementsByClassName('vipType');
			var qqinput = document.getElementsByName('qqnum');	
			var sexinput = document.getElementsByName('sex');	
			var livinginput = document.getElementsByName('living');		
		
			var usersmsg = response.data.data;
			console.log(response.data);

				Object.keys(userNames).forEach(function(item,index,array){
					userNames[index].innerHTML = usersmsg.userName;
				});
				Object.keys(userId).forEach(function(item,index,array){
					userId[index].innerHTML = usersmsg.useId;
				});
				Object.keys(sex).forEach(function(item,index,array){
					if(usersmsg.sex == 1){
						sex[index].innerHTML = '男';
						sexinput.value = '男';
					}else{
						sex[index].innerHTML = '女';
						sexinput.value = '女';
					}
				});
				Object.keys(living).forEach(function(item,index,array){
					living[index].innerHTML = usersmsg.living;
					livinginput.value = usersmsg.living;
				});
				Object.keys(qq).forEach(function(item,index,array){
					qq[index].innerHTML = usersmsg.qq;
					qqinput.value = usersmsg.qq;
				});
				Object.keys(personal_portrait).forEach(function(item,index,array){
					personal_portrait[index].src = usersmsg.portrait;console.log(usersmsg.portrait);
				});
				Object.keys(tel).forEach(function(item,index,array){
					tel[index].innerHTML = usersmsg.tel;
				});
				Object.keys(email).forEach(function(item,index,array){
					email[index].innerHTML = usersmsg.email;
				});
				Object.keys(vipType).forEach(function(item,index,array){
					if(usersmsg.vipType == 0){
					vipType[index].innerHTML = "尚未开通任何会员";
					}else if(usersmsg.vipType == 1){
					vipType[index].innerHTML = `<i class="iconfont icon-iconfonthuiyuan">会员</i>`;
					}else if(usersmsg.vipType == 2){
					vipType[index].innerHTML = `<i class="iconfont icon-iostennisballoutline">网球会员</i>`;
					}
				});


			//头像更改
			if(changehead){

				changehead.addEventListener('change',changeheadrap,false);
			}else{

				alert('no changehead');
			}

		//调用信息修改函数
		personal_correct_msg();

		//修改邮箱
		fixemail();

		//修改手机号
		fixphone();
		})
	}


	//个人信息修改回调函数
	function personal_correct_msg(){
		var newuserName = '';
		var newuserqq = '';
		var newsex = '';
		var newemail = '';
		var newtel = '';
		var qqinput = document.getElementById('qqnum');
		var sexinput = document.getElementById('sex');
		var livinginput = document.getElementById('living');

		var qq = document.getElementsByClassName('qqmsg');
		var sex = document.getElementsByClassName('sexmsg');
		var living = document.getElementsByClassName('livingmsg');

		newuserqq = qqinput.value = qq[0].innerHTML;
		newsex = sexinput.value = sex[0].innerHTML;
		newliving = livinginput.value = living[0].innerHTML;

		console.log(qqinput.value);
		console.log(sexinput.value);
		Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
			if (index > -1) {
			this.splice(index, 1);
			}
		};
		var msgdetails = document.getElementsByClassName('msgdetails');
		var save = document.getElementsByClassName('save');
		var cancle = document.getElementsByClassName("cancle");

		// var cancle = document.getElementsByClassName('cancle');
		var msgshezhi = document.getElementsByClassName('msgshezhi');
		var firstshow = [];
		var correctshow = [];

		Object.keys(msgshezhi).forEach(function(index){
			msgshezhi[index].addEventListener('click',correctmsg,false);
		})

		Object.keys(save).forEach(function(index){
			save[index].addEventListener('click',savemsg,false);
		})
		Object.keys(cancle).forEach(function(index){
			cancle[index].addEventListener('click',savemsg,false);
		})
		function correctmsg(){
			
			var super_par = this.parentNode;
			firstshow.push(super_par.children[1]);
			correctshow.push(super_par.children[2]);
			correctshow.push(super_par.children[3]);
			fixpersonalmsg();		
			
		}
		function savemsg(){
			
			var super_par0 = this.parentNode;
			var super_par = super_par0.parentNode;
			
			newuserqq = qqinput.value;
			newsex = sexinput.value;
			newliving = livinginput.value;

			if(newsex == '女'){
				newsexcode = 1;
			}else if(newsex =='男'){
				newsexcode = 0;
			}

			Object.keys(sex).forEach(function(item,index,array){
				sex[index].innerHTML = newsex;
			});
			
			Object.keys(qq).forEach(function(item,index,array){
				qq[index].innerHTML = newuserqq;
			});
			Object.keys(living).forEach(function(item,index,array){
				living[index].innerHTML = newliving;
			});
			$.ajax({
				method:'post',
				url:'updateUserInfo.do',
			    data:{
					userName:newuserName,
					sex:newsexcode,
					qq:newuserqq,
					living:newliving,
					tel:'',
					email:''
				},					
			})

			savepersonalmsg();

		}

		function fixpersonalmsg(){

			firstshow.forEach(function(item,index,array){
			item.style.display = 'inline-block';
			})

			correctshow.forEach(function(item,index,array){
				item.style.display = 'none';
			})
		}
		function savepersonalmsg(){

			firstshow.forEach(function(item,index,array){
				item.style.display = 'none';
			})
			
			correctshow.forEach(function(item,index,array){
				item.style.display = 'inline-block';
			})

			 correctshow = [];
			 firstshow = [];
		}
	}

	//修改手机号
	function fixphone(){
		var fixphone = document.getElementById('fixphone');
		var saveTel = document.getElementById('saveTel')
		var tel = document.getElementsByClassName('tel');

		fixphone.addEventListener('click',function(){
			var normalvalue = tel[0].innerHTML;
			var telInput = `
			<input type="text" name="" value="${normalvalue}" id="telInput">
		`;
			tel[0].innerHTML = telInput;
			fixphone.style.display = 'none';
			saveTel.style.display = 'inline-block';	
		},false);

		saveTel.addEventListener('click',function(){
			var newTelValue = document.getElementById('telInput').value;
			tel[0].innerHTML = newTelValue;
			fixphone.style.display = 'inline-block';
			saveTel.style.display = 'none';
			$.ajax({
				method:'post',
				url:'updateUserInfo.do',
				data:{
					userName:'',
					sex:'',
					qq:'',
					living:'',
					tel:newTelValue,
					email:''
				}
			})
		},false)
	}
	//修改邮箱
	function fixemail(){
		var fixemail = document.getElementById('fixemail');
		var saveEmail = document.getElementById('saveEmail');
		var email = document.getElementsByClassName('email');

		fixemail.addEventListener('click',function(){
			var normalvalue = email[0].innerHTML;
			var emailinput = `
			<input type="text" name="" value="${normalvalue}" id="emailinput">
		`;
			email[0].innerHTML = emailinput;
			fixemail.style.display = 'none';
			saveEmail.style.display = 'inline-block';	
		},false);
		saveEmail.addEventListener('click',function(){
			var newEmailValue = document.getElementById('emailinput').value;
			email[0].innerHTML = newEmailValue;
			fixemail.style.display = 'inline-block';
			saveEmail.style.display = 'none';
			$.ajax({
				method:'post',
				url:'updateUserInfo.do',
				data:{
					userName:'',
					sex:'',
					qq:'',
					living:'',
					tel:'',
					email:newEmailValue
				}
			})
		},false)
	}
	//选项卡切换
	function tabChange(){
		var title_tabs = document.getElementsByClassName('title');
		var content_details = document.getElementsByClassName('content_details');
		var tabBtns = title_tabs[0].children;
		var tabContent = content_details[0].children;

		for(var i = 0; i <tabBtns.length;i++){
			// console.log(tabBtns[i].nextSbiling);
			// tabBtns[i].classList.remove('selected');
			tabBtns[i].index = i;
			tabBtns[i].onclick =function(){
				// alert('click');
				// console.log(typeof tabContent);
				for(var j = 0;j<tabBtns.length;j++){
					// console.log(i + "" +tabBtns[i].classList);
					tabBtns[j].classList.remove('selected');
					tabContent.item(j).style.display = 'none';
				}

				this.className = 'selected';
				tabContent.item(this.index).style.display = 'block';
				console.log( i+""+tabContent.item(i));
			}
		}
	}


	//头像设置回调函数
	function changeheadrap(e){
		var fileinput = document.getElementById('changehead');
		var fileshow = document.getElementsByClassName('personal_portrait');

		var file = fileinput.files[0];
		var imageType = /image.*/;
		var imgUrl;
		if (file.type.match(imageType)) {
          var reader = new FileReader();

          reader.onload = function(e) {
            var img = new Image();
            imgUrl = reader.result;
          	Object.keys(fileshow).forEach(function(index,value,array){
          		fileshow[index].innerHTML = '图片修改';
          		console.log(reader.result);
          		console.log(fileshow[index]);
          		console.log(img);
	            fileshow[index].src = imgUrl;
          	});
        }
          reader.readAsDataURL(file); 
          // 在Data URL协议中，图片被转换成base64编码的字符串形式，并存储在URL中
        } else {
            alert("File not supported!");
        }
		axios({
			method:'post',
			url:'',
			data:{
				portraits:imgUrl
			},
			headers:{'Content-Type':'application/x-www-form-urlencoded'}
		}).then(function(response){
			console.log(response);
		})

	}

	//登录注册
	$('#login').bind('click',function(){
		$("#loginBox").show();
		$('#wrapper').css('backgroundColor','#fcfcfc');
	})
	 //账号验证
	 function zhanghao_yz() {
	     var reg = /^[A-Za-z]\w+$/; //正则表达式 必须以字母开头的账号
	     if ($("#account").val().search(reg) == -1) {
	         $(".infoAccount").html("账号字母开头的 可以包含数字字母下划线");
	         /*alert("密码只能是6-9位数字");*/
	         return false;
	     } else {
	         $(".infoAccount").html("账号验证成功");
	         /*  alert("验证成功");*/
	         return true;
	     }
	     return true;
	 }
	 $('#account').blur(zhanghao_yz);
	 function password_check() {
	     var reg = /^\d{6,9}$/; //正则表达式 必须以数字开头和结尾  6-9位
	     if ($("#psd").val().search(reg) == -1) {
	         $(".infoPsd").html("密码只能是6-9位数字");
	         /*alert("密码只能是6-9位数字");*/
	         return false;
	     } else {
	         $(".infoPsd").html("密码验证成功");
	         /*  alert("验证成功");*/
	         return true;
	     }
	     return true;
	 }
 	 $('#psd').blur(password_check);

 	 //关闭
 	 $('#close').bind('click',function(){
 	 	$("#loginBox").hide();
 	 })

 	 //登录验证,用户登录接口
 	 $('#loginBtn').bind('click',function(){
 	 	var userName = $('#account').val();
 	 	var psd = $('#psd').val();
 	 	
 	 	$.ajax({
 	 		method:'post',
 	 		url:'',
 	 		data:{
 	 			userName:userName,
 	 			psd:psd,
 	 		}
 	 		success:function(){
 	 			
 	 		}
 	 	})

 	 })
};