FROM node:20-alpine

# Create non-root user (Alpine syntax)
RUN groupadd -r coderunner && \
    useradd -r -g coderunner -m coderunner && \
    mkdir -p /workspace && \
    chown -R coderunner:coderunner /workspace

USER coderunner

CMD ["tail", "-f", "/dev/null"]