<?php
	$conn = MySQLi_connect("mysql5045.site4now.net", "a764dd_tuongdb", "DuyTuong@68", "db_a764dd_tuongdb");

	$username = $_POST['username'];
	$password = $_POST['password'];
	if ($username == "" || $password == "") 
{
		echo "ERROR01";
   }else
   {

		$sql="select * from user where username='$username' limit 1 ";
		$rs=mysqli_query($conn, $sql);
		$count=mysqli_num_rows($rs);
		if($count>0)
        {
			echo "ERROR02";
		}else
        {
			$sql = "Insert into user (username, password) values ('$username', '$password')";
            $query = mysqli_query($conn, $sql);
            if($query)
            {
                echo "SUCCESS";
				
            }else{
                echo "ERROR03";
            }
			
		}
   }
?>