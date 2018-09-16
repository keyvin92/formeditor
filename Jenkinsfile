node {
	def buildJobName = ${env.NODE_NAME}
	def mavenBuild = Artifactory.newMavenBuild()
	def buildInfo
	
	stage 'Configure Build Tools' {
		mavenBuild.tool = '3.5.2'
	}

	stage 'Build Webapp' {
		buildInfo = mavenBuild.run pom: 'pom.xml'	
	}

}
