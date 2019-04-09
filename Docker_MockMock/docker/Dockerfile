FROM java:8
MAINTAINER Isaia Gerardi <isaia.spinelli@heig-vd.ch>

COPY MockMock-1.4.0.one-jar.jar /opt/app/serverMockMock.jar
COPY NomProjet-1.0-SNAPSHOT.jar /opt/app/prankSMTP.jar

CMD ["java", "-jar", "/opt/app/serverMockMock.jar"]