node {
	def mavenBuild = tool '3.5.2'
	def branchName = env.BRANCH_NAME

	stage ('Checkout SCM') {
		checkout scm
	}

	stage ('Build Webapp') {
//		sh "${mavenBuild}/bin/mvn clean"
		sh "${mavenBuild}/bin/mvn install"	
	}

	stage ('Build Docker Image') {
		sh "docker build --no-cache -t ${branchName} ."
	}

	stage ('Run Docker Container') {
		sh "docker run -d -p 8081:8080 ${branchName}"
	}

}
