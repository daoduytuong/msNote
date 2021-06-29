<?php
	$conn = MySQLi_connect("localhost", "root", "", "msNote");
    
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