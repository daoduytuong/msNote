<?php
	$conn = MySQLi_connect("mysql5045.site4now.net", "a764dd_tuongdb", "DuyTuong@68", "db_a764dd_tuongdb");
    
    $username = $_POST['username'];
	$tieude = $_POST['tieude'];
       $noidung= trim($_POST['noidung']);
$color =  $_POST['color'];

    

    if($username == "" || $noidung=="" || $tieude=="" || $color  == "")
    {
        echo "ERROR05";
    }else
    {
        $sql = "Insert into note (username, TieuDe, NoiDung, Nhan) values ('$username', '$tieude', '$noidung','$color')";
            $query = mysqli_query($conn, $sql);
            if($query)
            {
                echo "true";
            }else{
                echo "ERROR6";
            }
    }

		
?>