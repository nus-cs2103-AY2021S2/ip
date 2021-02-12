#!/usr/bin/env bash

## NOTE THIS FILE MUST BE RUN IN THE text-ui-test DIRECTORY
## THE FILE PATHS ARE RELATIVE TO THE DIRECTORY FROM WHICH
## IT IS RUN, AND NOT WHERE THE FILE IS LOCATED

# create bin directory if it doesn't exist
if [ ! -d "../bin/dbot" ]
then
    mkdir ../bin/dbot
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../../out/production/CS2103 -Xlint:none -d ../bin/dbot ../src/main/java/dbot/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin/dbot DBot < input.txt > ACTUAL.TXT

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
