<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
 $id = $_POST['id'];
 $nama = $_POST['nama'];
 $posisi = $_POST['posisi'];
 $penyakit = $_POST['penyakit'];
 $tinggi = $_POST['tinggi'];
 //importing database connection script 
 require_once('dbCon.php');
 
 //Creating sql query 
 $sql = "UPDATE anggota SET nama_anggota = '$nama', posisi = '$posisi', penyakit_yang_diderita = '$penyakit',tinggi_badan='$tinggi' WHERE id_anggota = $id;";
 
 //Updating database table 
 if(mysqli_query($con,$sql)){
 echo 'Anggota telah berhasil diupdate';
 }else{
 echo 'Anggota gagal ditambah';
 }
 
 //closing connection 
 mysqli_close($con);
 }
