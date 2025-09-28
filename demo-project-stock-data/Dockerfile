FROM rappdw/docker-java-python
LABEL maintainer="itsupport@gmail.com"

ADD target/demo-project-stock-data-0.0.1-SNAPSHOT.jar demo-project-stock-data-0.0.1-SNAPSHOT.jar  
ADD target/ohlcv.py ohlcv.py
ADD target/symbol.py symbol.py
ADD target/requirements.txt requirements.txt

RUN pip3 install -r requirements.txt

ENTRYPOINT ["java","-jar","demo-project-stock-data-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080