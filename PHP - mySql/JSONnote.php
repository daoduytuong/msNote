<?php
$conn = MySQLi_connect("mysql5045.site4now.net", "a764dd_tuongdb", "DuyTuong@68", "db_a764dd_tuongdb");
$username = $_POST['username'];
mysqli_query($conn, "SET NAMES 'utf8'");
$query = mysqli_query($conn, "SELECT * FROM NOTE where username = '$username' order by ID DESC ");
$mang = array();

while($row = mysqli_fetch_array($query))
{
    $ID = $row['ID'];
    $username = $row['username'];
    $TieuDe = $row['TieuDe'];
    $NoiDung = $row['NoiDung'];
    $Nhan = $row['Nhan'];
    array_push($mang, new Note($ID, $username, $TieuDe, $NoiDung, $Nhan));

}
echo json_encode($mang);
class Note{
    var $id;
    var $username;
    var $TieuDe;
    var $NoiDung;
    var $Nhan;
    function Note($i, $u, $t, $n, $nh)
    {
        $this->id = $i;
        $this->username = $u;
        $this->TieuDe = $t;
        $this->NoiDung = $n;
        $this->Nhan = $nh;
    }
}

?>
