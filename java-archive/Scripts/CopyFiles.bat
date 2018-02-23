set RUN_DIR=C:\Users\Jenny\SkyDrive\DokumentAxel\Matdatabas\git\java-archive
set FOOD_DIR=C:\Users\Jenny\SkyDrive\DokumentAxel\Matdatabas\git\foodmanager
set installDir="C:\FoodDatabase\dev"

::Copy necessary files
xcopy /Y /S %RUN_DIR%\WebContent\* %installDir%\webapps\ROOT\*