<?php
   $outfile = tempnam(".", "cmd");
   $errfile = tempnam(".", "cmd");
   $payload = json_decode($_REQUEST['payload']);
   $descriptorspec = array(
       0 => array("pipe", "r"),
       1 => array("file", $outfile, "w"),
       2 => array("file", $errfile, "w")
   );
   $proc = proc_open('git pull', $descriptorspec, $pipes);
   //Open the file in append mode
   $my_file = 'logs.txt';
   $handle = fopen($my_file, 'a') or die('Cannot open file:  '.$my_file);
   if (!is_resource($proc)) 
   {
    $data = "Yup yahan to aya tha launda! Request happening";
    fwrite($handle, $data);
   }

   fclose($pipes[0]);    //Don't really want to give any input

   $exit = proc_close($proc);
   $stdout = file($outfile);
   $stderr = file($errfile);
   $data = $payload;
   fwrite($handle, $data);
   unlink($outfile);
   unlink($errfile);

?>