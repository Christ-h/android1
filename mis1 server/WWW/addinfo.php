<?php
	$shape=$_GET['shape'];
	$company=$_GET['company'];
	$product_num=$_GET['product_num'];
	$username=$_GET['username'];
	$phone_num=$_GET['phone_num'];	
	$userange=$_GET['userange'];
	
	$con=mysql_connect("localhost","root","root");
	if($con){
		$n=0;
		mysql_select_db("user",$con);
		mysql_query("set character set 'utf8'");//读库
        mysql_query("set names 'utf8'");//写库
		mysql_query("set shapes 'utf8'");
		$result= mysql_query("insert into user_mes(shape,company,product_num,username,phone_num,userange)
							values('$shape','$company','$product_num','$username','$phone_num','$userange')");

		if($result){
				echo 'add success';
		
		}else{
			echo 'add fail';
			
		}
		}else{
			echo 'connect fail';
	}
?>