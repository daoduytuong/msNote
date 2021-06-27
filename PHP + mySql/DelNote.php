<?php
	$conn = MySQLi_connect("mysql5045.site4now.net", "a764dd_tuongdb", "DuyTuong@68", "db_a764dd_tuongdb");
    
    $id = $_POST['id'];   

    if($id == "" )
    {
        echo "ERROR09";
    }else
    {    $sql = "DELETE from note WHERE note.ID = '$id' ";
            $query = mysqli_query($conn, $sql);
            if($query)
            {
                echo "true";
            }else{
                echo "ERROR10";
            }		
        
    }
		
?>