pipeline {
    agent any

    stages {

        stage('Build Restaurant Service') {
            steps {
                dir('restaurentlisting') {
                    bat 'mvn clean package'
                }
            }
        }

    }
}