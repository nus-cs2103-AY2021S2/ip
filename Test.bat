@ECHO OFF
ECHO Testing your lab...
java -jar checkstyle-8.2-all.jar -c cs2030_checks.xml *.java
@PAUSE