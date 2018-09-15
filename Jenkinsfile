pipeline {
    agent any
    tools {
        maven '3.5.2'
        jdk '1.8.0_181'
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
	    steps {
		sh 'docker build - < Dockerfile'
	    }
	}
    }
}
