node {
	def mavenBuild = tool '3.5.2'

	stage ('build') {
		sh "${mavenBuild}/bin/mvn install"	
	}

}
