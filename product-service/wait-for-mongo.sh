#!/bin/sh
# wait-for-mongo.sh

# Default values, can be overridden by environment variables if needed
MONGO_HOST="${SPRING_DATA_MONGODB_HOST:-mongodb}"
MONGO_PORT="${SPRING_DATA_MONGODB_PORT:-27017}"
APP_JAR="app.jar"

echo "Waiting for MongoDB at ${MONGO_HOST}:${MONGO_PORT}..."

# Loop until nc can successfully connect to MongoDB host and port
# -z: Zero-I/O mode (scanning)
# -w<timeout>: Timeout for connection attempts (e.g., 1 second)
while ! nc -z -w1 "${MONGO_HOST}" "${MONGO_PORT}"; do
  echo "MongoDB is unavailable - sleeping"
  sleep 1
done

echo "MongoDB is up - executing command"
# Execute the original command (start the Spring Boot application)
exec java -jar "${APP_JAR}"
