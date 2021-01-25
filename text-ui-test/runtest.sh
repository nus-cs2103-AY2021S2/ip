#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL" ]
then
    rm ACTUAL
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input file and redirect the output to the ACTUAL
java -classpath ../bin duke.Duke < input > ACTUAL

# convert to UNIX format
cp EXPECTED EXPECTED-UNIX
dos2unix ACTUAL EXPECTED-UNIX

# compare the output to the expected output
diff ACTUAL EXPECTED-UNIX
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi