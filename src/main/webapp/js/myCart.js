window.onload = function(){

	//是否选中
	function panduanSelect(){
		var items = $('.col2').siblings();
		var arr= [];
		console.log(typeof items);
		items.each(function(){
			var selectedItem = $(this).find('input[type = "checkBox"]');
			if(selectedItem.is(":checked")){
				arr.push(selectedItem);
			}
		});
		return arr;
	}
	//购物车清单
	axios({
		methods:'get',
		url:'http://rap2api.taobao.org/app/mock/12040/myCart'
	})
	.then(function(response){
		var goodsLists = response.data.cartlists;
		var tmp ='';
		Object.keys(goodsLists).forEach(function(index,val,array){
			var item = goodsLists[index];
			tmp += `
			<tr class="col2">
				<td class="selectbox">
					<input type="checkBox" name="checkBoxItem">
				</td>
				<td>
					<img src="${item.imgUrl}">
				</td>
				<td class="price">${item.price}￥</td>
				<td class="item-num">
					<input type="text" class="count" name="" value="${item.count}">
					<span class="num-change">
						<sup class="addnum"><i class="iconfont icon-up1"></i></sup>
						<sub class="cutnum"><i class="iconfont icon-down"></i></sub>
					</span>
				</td>
				<td class="truePrice">${item.truePrice}￥</td>
				<td>
					<p class="likeOrNot">加入收藏夹</p>
					<p>删除</p>
				</td>
			</tr>`;
		});
		$('tr.col1').after(tmp);
		//数据加载完成

		//如果 CheckBox已经选中，数量的增减引起结算价格变化
		function priceChange(){
			console.log($this);
		}
		//add数量
		$('.addnum').bind('click',function(){
			alert("增加数量");
			var $this = $(this);
			// console.log($(this).parent().prev());
			var input = $(this).parent().prev();
			var value = input.val();
			input.val(++value);
			// priceChange();
			var par = $(this).parents('.col2');
			// 检查是否选中，在进行操作
			var select = par.find('input[type = "checkBox"]');
			var selectItemPrice = par.nextAll().find('.truePrice');
			console.log(selectItemPrice);
			if(select.is(':checked')){
				var prevNum = parseInt($('.finalNum').html());
				$('.finalNum').html(++prevNum);
				var prevPrice = parseInt($('.finalPrice').html());
				var newPrice = parseInt(selectItemPrice.html())+prevPrice;
				console.log(newPrice);
				$('.finalPrice').html(newPrice);
			}
		});
		//cut数量
		$('.cutnum').bind('click',function(){
			alert("减少数量");
			var input = $(this).parent().prev();
			var value = input.val();
			input.val(--value);

			var par = $(this).parents('.col2');
			// 检查是否选中，在进行操作
			var select = par.find('input[type = "checkBox"]');
			var selectItemPrice = par.nextAll().find('.truePrice');
			console.log(selectItemPrice);
			if(select.is(':checked')){
				var prevNum = parseInt($('.finalNum').html());
				$('.finalNum').html(--prevNum);
				var prevPrice = parseInt($('.finalPrice').html());
				var newPrice = prevPrice-parseInt(selectItemPrice.html());
				console.log(newPrice);
				$('.finalPrice').html(newPrice);
			}
		});

		//复选框选中价格及数量计算
		$('.col2 .selectbox input').bind('click',function(){
			var checkedItem = $(this).parent().nextAll().find('.count');
			var checkedItemPrice = $(this).parent().nextAll().filter('.truePrice');
			var finalNum = parseInt($('.finalNum').html());
			var finalPrice = parseInt($('.finalPrice').html());
			var totalNum;
			var totalPrice;
			totalNum = finalNum!=0 ?finalNum: 0;
			totalPrice = finalPrice!=0 ?finalPrice :0;
			var selected = $(this).prop('checked');
			var itemCount = checkedItem.val();
			var itemPrice = checkedItemPrice.html();
			if(selected){
				//选中
				// cutAndadd('+');
				// var itemCount = checkedItem.val();
				// var itemPrice = checkedItemPrice.html();
				totalNum += Number(itemCount);
				totalPrice += parseInt(itemPrice)*parseInt(itemCount);
			}else{
				//取消选中
				// cutAndadd('-');
				// var itemCount = checkedItem.val();
				// var itemPrice = checkedItemPrice.html();
				totalNum -= Number(itemCount);
				totalPrice -= parseInt(itemPrice)*parseInt(itemCount);
			}
			$('.finalPrice').html(totalPrice);
			$('.finalNum').html(totalNum);
		});

		//全选
		$('.selectAll').bind('click',function(){
			if(this.checked){
				alert('checkedAll');
				$('input[name = "checkBoxItem"]').each(function(){
					$(this).prop('checked',true);
				});
			}else{
				$('input[name = "checkBoxItem"]').each(function(){
					$(this).removeAttr('checked',false);
				});
			}
			if($('.col2').first()){

			}
		});

		//加入收藏夹
		$('.addlike').bind('click',function(){
			var arr = panduanSelect();
			$(arr).each(function(){
				$(this).parents('.col2').find('.likeOrNot').html('已收藏');
				$(this).prop('checked',false);
			});
			$('.finalPrice').html(0);
			$('.finalNum').html(0);
		})

		//删除
		$('.delete').bind('click',function(){
			var arr = panduanSelect();
			$(arr).each(function(){
				$(this).parents('.col2').remove();
			});
			$('.finalPrice').html(0);
			$('.finalNum').html(0);
		})

	})

	//热销商品
	axios({
		methods:'get',
		url:"http://rap2api.taobao.org/app/mock/12040/popularLists"
	})
	.then(function(response){
		var popularLists = response.data.popularGoods;
		var tmp = '';
		Object.keys(popularLists).forEach(function(index,val,array){
			var item = popularLists[index];
			tmp += `
			<li class="goods-item">
				<div class="item-wrapper">
					<a href="item-link">
						<img src="${item.imgUrl}">
					</a>

					<div class="goods-title">${item.title}</div>

					<div class="goods-info">
						<span class="goods-price">${item.price} <sub>${item.truePrice}</sub></span>
						<span class="goods-sales">销量：${item.count}</span>
					</div>
				</div>
			</li>`;
		})

		$('ul.goods-lists').append(tmp);
	})

	//统计商品价格
	// $.each($('.col2').siblings(),function(i,val){
	// 	console.log(i+""+val);
	// });



}