node {
	def mavenBuild = tool '3.5.2'
	def branchName
	def webappPort

	stage ('Checkout SCM') {
		checkout scm
	}

	stage ('Build Webapp') {
		sh "${mavenBuild}/bin/mvn install"	
	}

	stage ('Configure Environment') {
		branchName = env.BRANCH_NAME
	}

	stage ('Stop Running Docker Container') {
		sh "docker stop ${branchName}"
		sh "docker rm ${branchName}"
	}

	stage ('Build Docker Image') {
		sh "docker build --no-cache -t ${branchName} ."
	}

	stage ('Run Docker Container') {
		sh "docker run -d -p 0:8080 --name ${branchName} ${branchName}"
		webappPort = sh "$((sudo docker port master) | cut -d':' -f2)"
	}

	stage ('Run Tests') {
		echo "Instance running on http://0.0.0.0:${webappPort}"
		echo 'TODO: add tests to run here'
	}

}
