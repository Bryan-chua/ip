#!/usr/bin/env bash

cd "$(dirname "$0")/.." || exit 1
mkdir -p bin
had_data_file=false
if [ -f data/bob.txt ]; then
    had_data_file=true
    cp data/bob.txt _temp/bob-test-backup.txt
fi
if [ -d data/bob.txt ]; then
    rmdir data/bob.txt
fi
rm -f text-ui-test/ACTUAL.TXT data/bob.txt

if ! javac -cp src/main/java -Xlint:none -d bin src/main/java/bob/*.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

java -classpath bin bob.Bob < text-ui-test/input.txt > text-ui-test/ACTUAL.TXT
if ! diff --strip-trailing-cr text-ui-test/ACTUAL.TXT text-ui-test/EXPECTED.TXT; then
    echo "********** UI TEST FAILURE **********"
    exit 1
fi

if ! diff --strip-trailing-cr data/bob.txt text-ui-test/EXPECTED-DATA.TXT; then
    echo "********** STORAGE TEST FAILURE **********"
    exit 1
fi

java -classpath bin bob.Bob < text-ui-test/input-load.txt > text-ui-test/ACTUAL-LOAD.TXT
if ! diff --strip-trailing-cr text-ui-test/ACTUAL-LOAD.TXT text-ui-test/EXPECTED-LOAD.TXT; then
    echo "********** STORAGE LOAD TEST FAILURE **********"
    exit 1
fi

cp text-ui-test/CORRUPTED-DATA.TXT data/bob.txt
java -classpath bin bob.Bob < text-ui-test/input-list.txt > text-ui-test/ACTUAL-CORRUPT.TXT
if ! diff --strip-trailing-cr text-ui-test/ACTUAL-CORRUPT.TXT text-ui-test/EXPECTED-CORRUPT.TXT; then
    echo "********** CORRUPTED DATA TEST FAILURE **********"
    exit 1
fi

rm data/bob.txt
mkdir data/bob.txt
java -classpath bin bob.Bob < text-ui-test/input-error.txt > text-ui-test/ACTUAL-IO-ERROR.TXT
if ! diff --strip-trailing-cr text-ui-test/ACTUAL-IO-ERROR.TXT text-ui-test/EXPECTED-IO-ERROR.TXT; then
    rmdir data/bob.txt
    echo "********** DATA FILE ERROR TEST FAILURE **********"
    exit 1
fi
rmdir data/bob.txt

java -classpath bin bob.Bob < /dev/null > text-ui-test/ACTUAL-EMPTY-INPUT.TXT
if ! diff --strip-trailing-cr text-ui-test/ACTUAL-EMPTY-INPUT.TXT text-ui-test/EXPECTED-EMPTY-INPUT.TXT; then
    echo "********** EMPTY INPUT TEST FAILURE **********"
    exit 1
fi

if [ "$had_data_file" = true ]; then
    cp _temp/bob-test-backup.txt data/bob.txt
    rm _temp/bob-test-backup.txt
else
    cp text-ui-test/EXPECTED-DATA.TXT data/bob.txt
fi

echo "Test result: PASSED"
