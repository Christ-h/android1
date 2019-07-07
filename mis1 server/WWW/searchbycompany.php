<?php
	$company=$_GET['company'];
	
	$con=mysql_connect("localhost","root","root");
	if($con){
		$n=0;
		//echo 'connect succes';
		mysql_select_db("user",$con);
		mysql_query("set character set 'utf8'");//读库
        mysql_query("set names 'utf8'");//写库
		$result= mysql_query("select *from user_mes where company='$company'");
		$num = mysql_num_rows($result);
		//echo $num;
		if($num>0){
			
			while($re = mysql_fetch_array($result)){
				//echo $re['id'].'<br>';
				//echo $re['username'].'<br>';
				//echo $re['company'].'<br>';
				//echo $re['product_num'].'<br>';
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
		}else{
			echo 'connect fail';
	}
?>