@ECHO OFF

REM create bin directory if it doesn't exist
if not exist \Users\Jeremias\Documents\Github\ip\bin mkdir \Users\Jeremias\Documents\Github\ip\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp \Users\Jeremias\Documents\GitHub\ip\src\main\java -Xlint:none -d \Users\Jeremias\Documents\Github\ip\bin \Users\Jeremias\Documents\GitHub\ip\src\main\java\duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath \Users\Jeremias\Documents\Github\ip\bin duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
