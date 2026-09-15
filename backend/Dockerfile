# Build context is the repository root (see compose.yml), so the git history is readable here.

# Stage 1 — the version is the git tag, or the commit hash while there is none (slice zero, B).
FROM alpine/git:2.49.1 AS version
COPY .git /repo/.git
WORKDIR /repo
RUN git describe --tags --always > /version

# Stage 2 — compile, run the tests, package. Nothing on the host needs Java or Maven (D61).
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build
COPY backend/pom.xml .
RUN mvn -B -q dependency:go-offline
COPY backend/src src
COPY --from=version /version /version
RUN mvn -B -Drevision="$(cat /version)" package

# Stage 3 — only the runtime and the jar.
FROM eclipse-temurin:21-jre
RUN useradd --system --no-create-home taxl
WORKDIR /app
COPY --from=build /build/target/taxl-api.jar app.jar
USER taxl
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
