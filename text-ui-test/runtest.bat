@ECHO OFF
SETLOCAL

PUSHD %~dp0\..

IF NOT EXIST bin MKDIR bin
SET HAD_DATA_FILE=0
IF EXIST data\bob.txt (
    IF NOT EXIST data\bob.txt\NUL (
        SET HAD_DATA_FILE=1
        COPY /Y data\bob.txt _temp\bob-test-backup.txt > NUL
    )
)
IF EXIST text-ui-test\ACTUAL.TXT DEL text-ui-test\ACTUAL.TXT
IF EXIST data\bob.txt\NUL RMDIR data\bob.txt
IF EXIST data\bob.txt DEL data\bob.txt

javac -cp src\main\java -Xlint:none -d bin src\main\java\bob\*.java
IF ERRORLEVEL 1 (
    ECHO ********** BUILD FAILURE **********
    POPD
    EXIT /B 1
)

java -classpath bin bob.Bob < text-ui-test\input.txt > text-ui-test\ACTUAL.TXT
FC text-ui-test\ACTUAL.TXT text-ui-test\EXPECTED.TXT
IF ERRORLEVEL 1 (
    ECHO ********** UI TEST FAILURE **********
    POPD
    EXIT /B 1
)

FC data\bob.txt text-ui-test\EXPECTED-DATA.TXT
IF ERRORLEVEL 1 (
    ECHO ********** STORAGE TEST FAILURE **********
    POPD
    EXIT /B 1
)

java -classpath bin bob.Bob < text-ui-test\input-load.txt > text-ui-test\ACTUAL-LOAD.TXT
FC text-ui-test\ACTUAL-LOAD.TXT text-ui-test\EXPECTED-LOAD.TXT
IF ERRORLEVEL 1 (
    ECHO ********** STORAGE LOAD TEST FAILURE **********
    POPD
    EXIT /B 1
)

COPY /Y text-ui-test\CORRUPTED-DATA.TXT data\bob.txt > NUL
java -classpath bin bob.Bob < text-ui-test\input-list.txt > text-ui-test\ACTUAL-CORRUPT.TXT
FC text-ui-test\ACTUAL-CORRUPT.TXT text-ui-test\EXPECTED-CORRUPT.TXT
IF ERRORLEVEL 1 (
    ECHO ********** CORRUPTED DATA TEST FAILURE **********
    POPD
    EXIT /B 1
)

DEL data\bob.txt
MKDIR data\bob.txt
java -classpath bin bob.Bob < text-ui-test\input-error.txt > text-ui-test\ACTUAL-IO-ERROR.TXT
FC text-ui-test\ACTUAL-IO-ERROR.TXT text-ui-test\EXPECTED-IO-ERROR.TXT
IF ERRORLEVEL 1 (
    RMDIR data\bob.txt
    ECHO ********** DATA FILE ERROR TEST FAILURE **********
    POPD
    EXIT /B 1
)
RMDIR data\bob.txt

java -classpath bin bob.Bob < NUL > text-ui-test\ACTUAL-EMPTY-INPUT.TXT
FC text-ui-test\ACTUAL-EMPTY-INPUT.TXT text-ui-test\EXPECTED-EMPTY-INPUT.TXT
IF ERRORLEVEL 1 (
    ECHO ********** EMPTY INPUT TEST FAILURE **********
    POPD
    EXIT /B 1
)

IF "%HAD_DATA_FILE%"=="1" (
    COPY /Y _temp\bob-test-backup.txt data\bob.txt > NUL
    DEL _temp\bob-test-backup.txt
) ELSE (
    COPY /Y text-ui-test\EXPECTED-DATA.TXT data\bob.txt > NUL
)

ECHO Test result: PASSED
POPD
EXIT /B 0
