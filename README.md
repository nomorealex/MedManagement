## MedManagement

docker run -v C:\path\to\postgresfarma.sql:/docker-entrypoint-initdb.d/init.sql -p 5432:5432 -e POSTGRES_PASSWORD=password postgres

docker run -v /path/to/postgresfarma.sql:/docker-entrypoint-initdb.d/init.sql -p 5432:5432 -e POSTGRES_PASSWORD=password postgres