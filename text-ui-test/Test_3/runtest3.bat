@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\..\bin mkdir ..\..\bin

REM delete standard and error output from previous run
if exist ACTUAL_OK_3.TXT del ACTUAL_OK_3.TXT
if exist ACTUAL_ERR_3.TXT del ACTUAL_ERR_3.TXT

REM compile the code into the bin folder
javac  -cp ..\..\src\main\java -Xlint:none -d ..\..\bin ..\..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input3.txt file and redirect the standard output to the ACTUAL_OK_3.TXT and error output to ACTUAL_ERR_3.txt
java -classpath ..\..\bin SurrealChat < input3.txt > ACTUAL_OK_3.TXT 2> ACTUAL_ERR_3.TXT

REM compare the output to the expected output
FC ACTUAL_OK_3.TXT EXPECTED_OK_3.TXT
FC ACTUAL_ERR_3.TXT EXPECTED_ERR_3.TXT
