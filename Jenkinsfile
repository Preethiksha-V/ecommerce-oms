pipeline {
    agent any
    environment {
        DOCKER_HUB_USER = 'preethiksha' 
    }
    stages {
        stage('Build Image') {
            steps {
                bat "docker build -t %DOCKER_HUB_USER%/oms-app:latest ."
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', 
                                 passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                    // This uses the credentials safely
                    bat "docker login -u %USER% -p %PASS%"
                    bat "docker push %DOCKER_HUB_USER%/oms-app:latest"
                }
            }
        }
        stage('Deploy to K8s') {
            steps {
                bat "kubectl apply -f k8s-deploy.yaml"
            }
        }
    }
}
