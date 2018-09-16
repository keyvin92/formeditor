node {
	def mavenBuild = tool '3.5.2'

	stage ('Build Webapp') {
		sh "${mavenBuild}/bin/mvn install"	
	}

	stage ('Build Docker Image') {
		sh "docker build"
	}

}
