FROM gcc:13

# Create non-root user
RUN addgroup -g 1000 coderunner && \
    adduser -D -u 1000 -G coderunner coderunner

# Create workspace
RUN mkdir -p /workspace && chown coderunner:coderunner /workspace
WORKDIR /workspace

USER coderunner

# Keep container running
CMD ["tail", "-f", "/dev/null"]