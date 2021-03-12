Installation guide
-----

There are three ways to run SimpleFileVirusScanner.  

1. Run with docker-compose:
  Just start the `docker-compose.yml` in this directory.  
  
2. Run as jar: 
  * You need to have java 11 installed
  * Run `./mvnw package` to get the compiled jar
  * Add a `application.properties` file to the same directory as the jar file and add configurations for your clamav
    * `clamav.host=localhost` & `clamav.port=3310`
    
3. Run as single docker container
  * Pull the docker image: `docker pull ngotzmann/simple-file-virus-scanner`
  * Run the docker container with env variables for your clamav instance
    * `docker run -p 8080:8080 -e CLAMAV_HOST="127.0.0.1" -e CLAMAV_PORT=3310 ngotzmann/simple-file-virus-scanner:latest`
