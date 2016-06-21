<?php 
 
 //Getting the requested id
 $id = $_GET['id'];
 
 //Importing database
 require_once('dbCon.php');
 
 //Creating sql query with where clause to get an specific employee
 $sql = "SELECT * FROM anggota WHERE id_anggota=$id";
 
 //getting result 
 $r = mysqli_query($con,$sql);
 
 //pushing result to an array 
 $result = array();
 $row = mysqli_fetch_array($r);
 array_push($result,array(
 "id"=>$row['id_anggota'],
 "nama"=>$row['nama_anggota'],
 "posisi"=>$row['posisi'],
 "penyakit"=>$row['penyakit_yang_diderita'],
 "tinggi"=>$row['tinggi_badan']
 ));
 
 //displaying in json format 
 echo json_encode(array('result'=>$result));
 
 mysqli_close($con);