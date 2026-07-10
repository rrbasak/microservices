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

        stage('Docker Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                }
            }
        }

        stage('Docker Build Eureka') {
            steps {
                dir('eureka') {
                    bat 'docker build -t debianrajdeep/eureka-server:latest .'
                }
            }
        }

        stage('Docker Push Eureka') {
            steps {
                bat 'docker push debianrajdeep/eureka-server:latest'
            }
        }

        stage('Docker Build API Gateway') {
            steps {
                dir('API_Gateway') {
                    bat 'docker build -t debianrajdeep/api-gateway:latest .'
                }
            }
        }

        stage('Docker Push API Gateway') {
            steps {
                bat 'docker push debianrajdeep/api-gateway:latest'
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
                bat 'docker push debianrajdeep/restaurantlisting-server:latest'
            }
        }

        stage('Docker Build UserInfo') {
            steps {
                dir('userinfo') {
                    bat 'docker build -t debianrajdeep/userinfo-server:latest .'
                }
            }
        }

        stage('Docker Push UserInfo') {
            steps {
                bat 'docker push debianrajdeep/userinfo-server:latest'
            }
        }

        stage('Docker Build Food Catalogue') {
            steps {
                dir('foodCatalogue') {
                    bat 'docker build -t debianrajdeep/foodcatalogue-server:latest .'
                }
            }
        }

        stage('Docker Push Food Catalogue') {
            steps {
                bat 'docker push debianrajdeep/foodcatalogue-server:latest'
            }
        }

        stage('Docker Build Order Service') {
            steps {
                dir('order') {
                    bat 'docker build -t debianrajdeep/order-server:atlas-v1 .'
                }
            }
        }

        stage('Docker Push Order Service') {
            steps {
                bat 'docker push debianrajdeep/order-server:atlas-v1'
            }
        }
        stage('Deploy') {
            steps {
                withCredentials([
                    string(credentialsId: 'mongo-uri', variable: 'MONGO_URI')
                ]) {
                    dir('Microservices-Deployment') {
                        bat 'docker compose pull'
                        bat 'docker compose down'
                        bat 'docker compose up -d'
                    }
                }
            }
        }
    }
}