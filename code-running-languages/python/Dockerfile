FROM python:3.11-alpine

# Create non-root user (Alpine syntax)
RUN addgroup -S coderunner && \
    adduser -S coderunner -G coderunner && \
    mkdir -p /workspace && \
    chown -R coderunner:coderunner /workspace

# Install common libraries
RUN pip install --no-cache-dir numpy

WORKDIR /workspace
USER coderunner

CMD ["tail", "-f", "/dev/null"]