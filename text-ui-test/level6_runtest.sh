if [ ! -d "../bin" ]
then
        mkdir ../bin
    fi

    # delete output from previous run
    if [ -e "level6_actual.txt" ]
    then
            rm level6_actual.txt
        fi

        # compile the code into the bin folder, terminates if error occurred
        if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
        then
                echo "********** BUILD FAILURE **********"
                    exit 1
                fi

                # run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
                java -classpath ../bin Duke < level6_input.txt > level6_actual.txt

                # compare the output to the expected output
                diff level6_actual.txt level6_expected.txt
                if [ $? -eq 0 ]
                then
                        echo "Test result: PASSED"
                            exit 0
                        else
                                echo "Test result: FAILED"
                                    exit 1
                                fi
