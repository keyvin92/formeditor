pipeline {
    agent any
    tools {
        maven '3.5.2'
        jdk '1.8.0_181'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true -f ./formeditor/pom.xml install' 
            }
            
            
            
            
            }
    }
}
