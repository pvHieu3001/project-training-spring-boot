mvn clean install -DskipTests && docker build -t smartosc . && docker run -p8004:8004 smartosc
