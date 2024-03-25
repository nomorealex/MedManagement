## MedManagement

### Running database (Docker)
--------
CMD
set PASS=password123!

docker run -v C:\path\to\postgresfarma.sql:/docker-entrypoint-initdb.d/init.sql -p 5432:5432 -e POSTGRES_PASSWORD=%PASS% postgres

--------
Powershell
$Env:PASS = 'password123!'

docker run -v C:\path\to\postgresfarma.sql:/docker-entrypoint-initdb.d/init.sql -p 5432:5432 -e POSTGRES_PASSWORD=$Env:pass postgres

--------
Linux
export PASS=password123!

docker run -v /path/to/postgresfarma.sql:/docker-entrypoint-initdb.d/init.sql -p 5432:5432 -e POSTGRES_PASSWORD=${PASS} postgres


### Compile and Run
javac -d ".\out" --module-path /path/javafx.../lib --add-modules javafx.controls,javafx.media,javafx.graphics ./pt/isec/pa/tinypac/Main.java

java --classpath /out --module-path /path/javafx.../lib --add-modules javafx.controls,javafx.media,javafx.graphics ./pt/isec/pa/tinypac/Main.java