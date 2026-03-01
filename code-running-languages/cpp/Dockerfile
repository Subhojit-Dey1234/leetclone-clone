FROM gcc:13

# Create non-root user
RUN groupadd -r coderunner && \
    useradd -r -g coderunner -m coderunner && \
    mkdir -p /workspace && \
    chown -R coderunner:coderunner /workspace

USER coderunner

# Keep container running
CMD ["tail", "-f", "/dev/null"]