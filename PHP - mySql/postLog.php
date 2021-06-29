<?php
	$conn = MySQLi_connect("localhost", "root", "", "msNote");

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