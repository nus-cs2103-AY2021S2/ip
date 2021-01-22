#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../../bin" ]
then
    mkdir ../../bin
fi

# delete output from previous run
if [ -e "./ACTUAL_OK_8.TXT" ]
then
    rm ACTUAL_OK_8.TXT
if [ -e "./ACTUAL_ERR_8.TXT" ]
then
    rm ACTUAL_ERR_8.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../../src/main/java -Xlint:none -d ../../bin ../../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input8.txt file and redirect the standard output to the ACTUAL_OK_8.TXT and error output to ACTUAL_ERR_8.TXT
java -classpath ../../bin SurrealChat < input8.txt > ACTUAL_OK_8.TXT 2>ACTUAL_ERR_8.TXT

# convert to UNIX format
cp EXPECTED_OK_8.TXT EXPECTED-UNIX_OK_8.TXT
dos2unix ACTUAL_OK_8.TXT EXPECTED-UNIX_OK_8.TXT
cp EXPECTED_ERR_2.TXT EXPECTED-UNIX_ERR_8.TXT
dos2unix ACTUAL_ERR_8.TXT EXPECTED-UNIX_ERR_8.TXT

# compare the output to the expected output
diff ACTUAL_OK_8.TXT EXPECTED-UNIX_OK_8.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
diff ACTUAL_ERR_8.TXT EXPECTED-UNIX_ERR_8.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi