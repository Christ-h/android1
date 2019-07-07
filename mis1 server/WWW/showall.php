<?php
	$con=mysql_connect("localhost","root","root");
	if($con){
		$n=0;
		//echo 'connect succes';
		mysql_select_db("user",$con);
		mysql_query("set character set 'utf8'");//读库
		mysql_query("set names 'utf8'");//写库
		$result= mysql_query("select *from user_mes");
		$num = mysql_num_rows($result);
		//echo $num;
		if($num>0){
			
			while($re = mysql_fetch_array($result)){
				$arr[$n++]=array(
						"id"=>$re['id'],
						"shape"=>$re['shape'],
						"company"=>$re['company'],
						"product_num"=>$re['product_num'],
						"username"=>$re['username'],
						"phone_num"=>$re['phone_num'],
						"userange"=>$re['userange'],
				);
						
			}
			echo json_encode($arr);
		}else{
			echo 'no data';
			
		}
		}
	}
?>