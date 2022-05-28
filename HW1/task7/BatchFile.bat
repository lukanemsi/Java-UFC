
set path="C:\Program Files\Java\jdk-11.0.15\bin"; 
javac TestBatch.java -source 11 -target 11
java TestBatch

pause
set path="C:\Program Files\Java\jdk1.7.0_80\bin";   
javac TestBatch.java -source 1.7 -target 1.7
java TestBatch

pause

set path="C:\Program Files\Java\jdk1.8.0_202\bin"; 
javac TestBatch.java -source 1.8 -target 1.8
java TestBatch

pause