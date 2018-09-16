node {
	def mavenBuild = tool '3.5.2'
	def branchName
	def webappPort
	def webappContext
	def databasePort

	stage ('Checkout SCM') {
		checkout scm
	}

	stage ('Build Webapp') {
		sh "${mavenBuild}/bin/mvn install"	
		webappContext = sh(returnStdout: true, script: "ls target/*.war | cut -d'/' -f2")
	}

	stage ('Configure Environment') {
		branchName = env.BRANCH_NAME
	}

	stage ('Stop Running Docker Container') {
		try {
			sh "docker stop ${branchName}"
			sh "docker rm ${branchName}"
		} catch (exc) {
			echo "Docker container ${branchName} was not stopped"
		}
	}

	stage ('Build Docker Image') {
		sh "docker build --no-cache -t ${branchName} ."
	}

	stage ('Run Database Docker Container') {
		try {
			sh "docker stop mssql${branchName}"
			sh "docker rm mssql${branchName}"
		} catch (exc) {
			echo "Docker container mssql${branchName} was not stopped"
		}
		sh "docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Test_2018' --name mssql${branchName} -d -p 0:1433 microsoft/mssql-server-linux:2017-latest"
		databasePort = sh(returnStdout: true, script: "((docker port mssql${branchName}) | cut -d':' -f2)")
		echo "Database is running on http://0.0.0.0:${databasePort}"
	}

	stage ('Run Tomcat Docker Container') {
		sh "docker run -d -p 0:8080 --name ${branchName} ${branchName}"
		webappPort = sh(returnStdout: true, script: "((docker port ${branchName}) | cut -d':' -f2)")
	}

	stage ('Run Tests') {
		echo "Instance running on http://0.0.0.0:${webappPort}/${webappContext}"
		echo 'TODO: add tests to run here'
	}

}
