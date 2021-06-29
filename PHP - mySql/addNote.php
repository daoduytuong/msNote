<?php
	$conn = MySQLi_connect("localhost", "root", "", "msNote");
    
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