@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
REM java -classpath ..\bin Duke < input.txt > ACTUAL.TXT
REM
REM REM compare the output to the expected output
REM FC ACTUAL.TXT EXPECTED.TXT
REM
REM java -classpath ..\bin Duke < scratch.txt

REM test files for current usage
REM includes saving bugs
java -classpath ..\bin Duke < inputL8.txt > outputL8.txt
FC outputL8.txt expectedL8.txt