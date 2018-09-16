node {
	def mavenBuild = tool '3.5.2'

	stage ('Build Webapp') {
		sh "${mavenBuild}/bin/mvn install"	
	}

	stage ('Build Docker Image') {
		sh "docker build . -t vaadineditor"
	}

	stage ('Run Docker Container') {
		sh "docker run -d -p 8080:8081 vaadineditor"
	}

}
