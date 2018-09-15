pipeline {
    agent any
    tools {
        maven '3.5.2'
        jdk '1.8.0_181'
	docker '18.06.1-ce'
    }
    stages {
        stage ('Initialize Build Tools') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build Web Application') {
            steps {
                sh 'mvn install' 
            }
        }

	stage ('Build Docker Container') {
		sh 'docker build - < Dockerfile'
	}
    }
}
