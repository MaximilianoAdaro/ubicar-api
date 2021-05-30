# Run postgres using docker
docker run -e POSTGRES_PASSWORD=password -e POSTGRES_USER=root -e POSTGRES_DB=ubicar -p 5432:5432 postgres