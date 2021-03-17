@ECHO OFF

REM create bin directory if it doesn't exist
if not exist Users\Michael\Downloads\bin mkdir Users\Michael\Downloads\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp Users\Michael\Downloads\cs2103\ip\src\main\java -Xlint:none -d Users\Michael\Downloads\bin Users\Michael\Downloads\cs2103\ip\main\java\duke.Duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath \Users\Michael\Desktop\cs2103\ip\bin duke.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

ip