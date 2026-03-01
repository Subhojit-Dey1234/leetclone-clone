FROM python:3.11-alpine

# Create non-root user for security
RUN addgroup -g 1000 coderunner && \
    adduser -D -u 1000 -G coderunner coderunner

# Create workspace directory
RUN mkdir -p /workspace && chown coderunner:coderunner /workspace
WORKDIR /workspace

# Install any common libraries you might need
RUN pip install --no-cache-dir numpy

# Switch to non-root user
USER coderunner

# Keep container running
CMD ["tail", "-f", "/dev/null"]