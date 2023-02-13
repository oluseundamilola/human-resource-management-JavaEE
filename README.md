# Build
mvn clean package && docker build -t com.hr/java-ee8-hr .

# RUN

docker rm -f java-ee8-hr || true && docker run -d -p 8080:8080 -p 4848:4848 --name java-ee8-hr com.hr/java-ee8-hr 