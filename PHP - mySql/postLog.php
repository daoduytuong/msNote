<?php
	$conn = MySQLi_connect("mysql5045.site4now.net", "a764dd_tuongdb", "DuyTuong@68", "db_a764dd_tuongdb");

	$username = $_POST['username'];
	$password = $_POST['password'];
	$sql="select * from user where username='$username' and password='$password' limit 1 ";
		$row=mysqli_query($conn, $sql);
		$count=mysqli_num_rows($row);
		if($count>0){
			echo "true";
		}else{			
			echo "false";
		}
?>