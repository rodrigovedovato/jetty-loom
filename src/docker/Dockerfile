FROM ubuntu:latest

WORKDIR /opt/letty/

RUN apt update -y && apt install -y wget && \
    wget -O "loom-jdk.tar.gz" "https://download.java.net/java/early_access/loom/7/openjdk-17-loom+7-342_linux-x64_bin.tar.gz" && \
    tar -xzvf "loom-jdk.tar.gz"

ENV PATH="/opt/letty/jdk-16/bin:${PATH}"

COPY letty-all.jar letty.jar

ENTRYPOINT [ "java", "-jar", "letty.jar" ]
