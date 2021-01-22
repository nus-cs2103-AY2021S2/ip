@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\..\bin mkdir ..\..\bin

REM delete standard output from previous run
if exist ACTUAL_OK_1.TXT del ACTUAL_OK_1.TXT

REM compile the code into the bin folder
javac  -cp ..\..\src\main\java -Xlint:none -d ..\..\bin ..\..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input1.txt file and redirect the standard output to the ACTUAL_OK_1.TXT
java -classpath ..\..\bin SurrealChat < input1.txt > ACTUAL_OK_1.TXT

REM compare the output to the expected output
FC ACTUAL_OK_1.TXT EXPECTED_OK_1.TXT
