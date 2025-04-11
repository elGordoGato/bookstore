# Build stage with Maven
FROM maven:3.9-eclipse-temurin-21-alpine as build
WORKDIR /workspace/app

# Copy only the POM first to leverage Docker cache
COPY pom.xml .

# Download dependencies (this step is cached as long as pom.xml doesn't change)
RUN mvn dependency:go-offline

# Copy the source code
COPY src src

# Build the application
RUN mvn install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.bookstore.BookstoreApplication"]