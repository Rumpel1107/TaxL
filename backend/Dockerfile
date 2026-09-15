# Build context is the repository root (see compose.yml), so the git history is readable here.
# It needs a full clone with its tags: a shallow clone or an exported tree has no version to read.
# Base images are pinned to their full version so a rebuild months later builds the same thing (D60).

# Stage 1 — the version is the git tag, or the commit hash while there is none (D63).
FROM alpine/git:2.49.1 AS version
COPY .git /repo/.git
WORKDIR /repo
RUN git describe --tags --always > /version

# Stage 2 — compile, run the tests, package. Nothing on the host needs Java or Maven (D61).
FROM maven:3.9.16-eclipse-temurin-21 AS build
WORKDIR /build
COPY backend/pom.xml .
RUN mvn -B -q dependency:go-offline
COPY backend/src src
COPY --from=version /version /version
RUN mvn -B -Drevision="$(cat /version)" package
# The test cannot see this chain (it sets its own version), so the packaged file is checked literally.
RUN grep -qx "taxl.version=$(cat /version)" target/classes/application.properties

# Stage 3 — only the runtime and the jar.
FROM eclipse-temurin:21.0.12_8-jre
RUN useradd --system --no-create-home taxl
WORKDIR /app
COPY --from=build /build/target/taxl-api.jar app.jar
USER taxl
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
