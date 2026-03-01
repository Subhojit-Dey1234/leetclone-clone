FROM eclipse-temurin:17-jre

# Create non-root system user safely
RUN groupadd -r coderunner && \
    useradd -r -g coderunner -m coderunner && \
    mkdir -p /workspace && \
    chown -R coderunner:coderunner /workspace

WORKDIR /workspace
USER coderunner

CMD ["tail", "-f", "/dev/null"]