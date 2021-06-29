<?php
$conn = MySQLi_connect("localhost", "root", "", "msNote");
        $file_path = "image/";
        $file_path = $file_path.($_FILES['upIMG']['name']);

        if(move_uploaded_file($_FILES['upIMG']['name'], $file_path))
        {
            echo "true";
        }
        else
        {
            echo "false";
        }
?>