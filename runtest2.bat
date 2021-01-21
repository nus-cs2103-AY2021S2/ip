javac -d bin src\main\java\*.java

if exist text-ui-test\ACTUAL.txt del text-ui-test\ACTUAL.txt

java -classpath bin Duke < text-ui-test\input.txt > text-ui-test\ACTUAL.txt

FC text-ui-test\ACTUAL.TXT text-ui-test\EXPECTED.TXT
