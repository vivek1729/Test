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

   if (!is_resource($proc)) {file_put_contents('/var/chroot/home/content/97/11124597/html/projects/Test/logs.txt', $payload, FILE_APPEND);}

   fclose($pipes[0]);    //Don't really want to give any input

   $exit = proc_close($proc);
   $stdout = file($outfile);
   $stderr = file($errfile);
   file_put_contents('/var/chroot/home/content/97/11124597/html/projects/Test/logs.txt', $e . ' ' . $payload, FILE_APPEND);
   unlink($outfile);
   unlink($errfile);

?>