node {
	def mavenBuild = tool '3.5.2'
	def branchName

	stage ('Checkout SCM') {
		checkout scm
	}

	stage ('Build Webapp') {
//		sh "${mavenBuild}/bin/mvn clean"
		sh "${mavenBuild}/bin/mvn install"	
	}

	stage ('Configure Environment') {
		branchName = env.BRANCH_NAME
	}

	stage ('Build Docker Image') {
		sh "docker build --no-cache -t ${branchName} ."
	}

	stage ('Run Docker Container') {
		sh "docker stop ${branchName}"
		sh "docker run -d -p 0:8080 --name ${branchName} ${branchName}"
	}

	stage ('Run Tests') {
		echo 'Todo add tests to run here.'
	}

}
