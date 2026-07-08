pipeline {
    agent any

    stages {

        stage('Build Eureka') {
            steps {
                dir('eureka') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build API Gateway') {
            steps {
                dir('API_Gateway') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Restaurant Service') {
            steps {
                dir('restaurentlisting') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Build UserInfo Service') {
            steps {
                dir('userinfo') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Food Catalogue Service') {
            steps {
                dir('foodCatalogue') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Order Service') {
            steps {
                dir('order') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Docker Build Restaurant') {
            steps {
                dir('restaurentlisting') {
                    bat 'docker build -t debianrajdeep/restaurantlisting-server:latest .'
                }
            }
        }

        stage('Docker Push Restaurant') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub',
                        usernameVariable: 'debianrajdeep',
                        passwordVariable: 'raJ@20001612'
                    )
                ]) {

                    bat 'docker login -u %debianrajdeep% -p %raJ@20001612%'
                    bat 'docker push debianrajdeep/restaurantlisting-server:latest'
                }
            }
        }
    }
}