<?php
	$my_file = 'logs.txt';
				$handle = fopen($my_file, 'w') or die('Cannot open file:  '.$my_file);
				$data = "harami kutte file write to hona chahiye!";
				fwrite($handle, $data);
?>