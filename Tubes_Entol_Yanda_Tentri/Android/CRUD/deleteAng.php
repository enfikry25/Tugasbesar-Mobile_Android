<?php 
 //Getting Id
 $id = $_GET['id'];
 
 //Importing database
 require_once('dbCon.php');
 
 //Creating sql query
 $sql = "DELETE FROM anggota WHERE id_anggota=$id;";
 
 //Deleting record in database 
 if(mysqli_query($con,$sql)){
 echo 'Anggota berhasil dihapus';
 }else{
 echo 'Anggota gagal ditambah';
 }
 
 //closing connection 
 mysqli_close($con);
