#!/usr/bin/env bash

if [ ! -d "../bin" ]
then
  mkdir ../bin
fi

if [ -e "./ACTUAL.TXT" ]
then
  rm ACTUAL.TXT
fi

if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
  echo "********** BUILD FAILURE ************"
  exit 1
fi

java -classpath ../bin Duke < input.txt > ACTUAL.txt

diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
  echo "Test result: PASSED"
  exit 0
else
  echo "Test result: FAILED"
  exit 1
fi