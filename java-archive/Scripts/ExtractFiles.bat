set RUN_DIR=C:\Users\Jenny\SkyDrive\DokumentAxel\Matdatabas\git
set NEW_DIR=set RUN_DIR=C:\Users\Jenny\SkyDrive\DokumentAxel\Matdatabas\git\Extracted_files

mkdir %NEW_DIR%

::Copy necessary files
xcopy /Y /S /EXCLUDE:Excluded_files.txt %RUN_DIR%\* %NEW_DIR%\*
