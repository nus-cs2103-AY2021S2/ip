#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# create data directory and tasks.json if it doesn't exist (for testing consistency)
if [ ! -d "./data" ]
then
    mkdir ./data
    echo "[]" > ./data/tasks.json
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# modified to compile with json-simple
# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ~/.gradle/caches/modules-2/files-2.1/com.googlecode.json-simple/json-simple/1.1.1/c9ad4a0850ab676c5c64461a05ca524cdfff59f1/json-simple-1.1.1.jar:./ ../src/main/java/com/tjtanjin/steve/*.java ../src/main/java/com/tjtanjin/steve/*/*.java -Xlint:none -d ../bin ../src/main/java/com/tjtanjin/steve/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# modified to run with json-simple
# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -cp ~/.gradle/caches/modules-2/files-2.1/com.googlecode.json-simple/json-simple/1.1.1/c9ad4a0850ab676c5c64461a05ca524cdfff59f1/json-simple-1.1.1.jar:./:../bin com.tjtanjin.steve.Steve < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
