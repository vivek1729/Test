<?php
try
        {
                // Decode the payload json string
                $payload = json_decode($_REQUEST['payload']);
        }
        catch(Exception $e)
        {
                exit(0);
        }
 
        // Pushed to master?
        if ($payload->ref === 'refs/heads/master')
        {
                // Log the payload object
                file_put_contents('logs.txt', print_r($payload, TRUE), FILE_APPEND);

                // Write to file
                $my_file = 'logs.txt';
				$handle = fopen($my_file, 'w') or die('Cannot open file:  '.$my_file);
				$data = "harami kutte file write to hona chahiye!";
				fwrite($handle, $data);
        }
				 

?>