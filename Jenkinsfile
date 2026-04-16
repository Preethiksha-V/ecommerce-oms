pipeline {
    agent any
    environment {
        // REPLACE with your actual Docker Hub ID
        DOCKER_HUB_USER = 'your-docker-id' 
    }
    stages {
        stage('Build Image') {
            steps {
                // Use 'bat' instead of 'sh' for Windows
                bat "docker build -t preethiksha/oms-app:latest ."
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', 
                                 passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                    // Windows requires double quotes for variable expansion in batch
                    bat "docker login -u preethiksha -p Namashiva@01"
                    bat "docker push preethiksha/oms-app:latest"
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
