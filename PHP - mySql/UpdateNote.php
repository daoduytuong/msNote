<?php
	$conn = MySQLi_connect("localhost", "root", "", "msNote");
    
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