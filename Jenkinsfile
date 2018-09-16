node {
	def mavenBuild = tool '3.5.2'

	stage ('Build Webapp') {
		sh "${mavenBuild}/bin/mvn clean"
		sh "${mavenBuild}/bin/mvn install"	
	}

	stage ('Build Docker Image') {
		sh "docker build . -- no-cache -t vaadineditor"
	}

	stage ('Run Docker Container') {
		sh "docker run -d -p 8081:8080 vaadineditor"
	}

}
