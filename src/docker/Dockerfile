FROM ubuntu:latest

WORKDIR /opt/letty/

RUN apt update -y && apt install -y wget && \
    wget -O "loom-jdk.tar.gz" "https://download.java.net/java/early_access/loom/2/openjdk-18-loom+2-74_linux-x64_bin.tar.gz" && \
    tar -xzvf "loom-jdk.tar.gz"

ENV PATH="/opt/letty/jdk-18/bin:${PATH}"

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "letty-all.jar" ]
