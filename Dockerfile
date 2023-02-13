FROM airhacks/glassfish
COPY ./target/java-ee8-hr.war ${DEPLOYMENT_DIR}
