# Flink Helloworld

## Prerequisites

* Download the nc111nt_safe.zip from https://joncraton.org/blog/46/netcat-for-windows
* Extract the .zip file

## Development

Open the App class and adapt the following: 

* Use the ```StreamExecutionEnvironment```to create the streaming environment 
* Create a ```DataStream``` using the ```socketTextStream``` method offered from the previous generated environment


## Execute 
* Open the Windows command line and navigate into the directory of the extracted .zip file
* Execute the following command on the command line ```nc -l -p 9099````
* Start the flink application as normal Java Application 
* Enter words/sentences on the opened socket stream on the command line 
* The console should print out a word count of the entered words/sentences