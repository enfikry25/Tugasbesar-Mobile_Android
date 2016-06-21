<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting values
 $nama = $_POST['nama'];
 $username = $_POST['username'];
 $password = $_POST['password'];
 $posisi = $_POST['posisi'];
 $penyakit = $_POST['penyakit'];
 $tinggi = $_POST['tinggi'];
 
 //Creating an sql query
 $sql = "INSERT INTO anggota (nama_anggota,username,password,posisi,penyakit_yang_diderita,tinggi_badan) VALUES ('$nama','$username','$password','$posisi','$penyakit','$tinggi')";
 
 //Importing our db connection script
 require_once('dbCon.php');
 
 //Executing query to database
 if(mysqli_query($con,$sql)){
 echo 'Anggota Telah Berhasil ditambah';
 }else{
 echo 'Pegawai gagal ditambah';
 }
 
 //Closing the database 
 mysqli_close($con);
 }