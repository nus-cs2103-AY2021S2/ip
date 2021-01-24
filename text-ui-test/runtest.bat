@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java\fakebot\command\ -Xlint:none -d ..\bin ..\src\main\java\fakebot\command\*.java
javac  -cp ..\src\main\java\fakebot\task\ -Xlint:none -d ..\bin ..\src\main\java\fakebot\task\*.java
javac  -cp ..\src\main\java\fakebot -Xlint:none -d ..\bin ..\src\main\java\fakebot\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed comm
ands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin FakeBot < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
