<?php
	$conn = MySQLi_connect("mysql5045.site4now.net", "a764dd_tuongdb", "DuyTuong@68", "db_a764dd_tuongdb");
    
    $id = $_POST['id'];
	$tieude = $_POST['tieude'];
    $nd = trim($_POST['noidung']);
$cl = $_POST['color'];
    

    if($id == "" || $nd =="" || $tieude=="" || $cl  == "")
    {
        echo "ERROR07";
    }else
    {    $sql = "UPDATE note SET TieuDe = '$tieude', NoiDung = '$nd', Nhan = '$cl' WHERE note.ID = '$id' ";
            $query = mysqli_query($conn, $sql);
            if($query)
            {
                echo "true";
            }else{
                echo "ERROR8";
            }		
        
    }
		
?>