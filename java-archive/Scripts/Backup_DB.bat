set path=%PATH%;C:\Program Files\MySQL\MySQL Server 5.7\bin
mysqldump --compact -uroot -proot fooddatabase_release > C:\Users\Jenny\SkyDrive\DokumentDokumentAxel\Matdatabas\DB-backup\db_backup_%date%.sql

::mysql.exe -uroot -proot -s -N -e "SHOW DATABASES" |
  ::for /F "usebackq" %%D in (`findstr /V "information_schema performance_schema"`)
    ::do mysqldump %%D -uroot -p1234 > S:\Backup\MySQL\%%D.sql