<?php
	$username=$_GET['username'];
	
	$con=mysql_connect("localhost","root","root");
	if($con){
		$n=0;
		//echo 'connect succes';
		mysql_select_db("user",$con);
		$result= mysql_query("select *from user_mes where username='$username'");
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
						"username"=>$re['username'],
						"company"=>$re['company'],
						"product_num"=>$re['product_num'],
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