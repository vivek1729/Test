<?php
   $outfile = tempnam(".", "cmd");
   $errfile = tempnam(".", "cmd");
   $descriptorspec = array(
       0 => array("pipe", "r"),
       1 => array("file", $outfile, "w"),
       2 => array("file", $errfile, "w")
   );
   $proc = proc_open('git pull', $descriptorspec, $pipes);

   if (!is_resource($proc)) return 255;

   fclose($pipes[0]);    //Don't really want to give any input

   $exit = proc_close($proc);
   $stdout = file($outfile);
   $stderr = file($errfile);

   unlink($outfile);
   unlink($errfile);

?>