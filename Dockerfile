FROM tomcat:8-jre8
MAINTAINER 'keyvin92@googlemail.com'

ADD target/vaadineditor-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080

