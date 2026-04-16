pipeline {
    agent any
    environment {
        DOCKER_HUB_USER = 'your-docker-id' // REPLACE THIS
    }
    stages {
        stage('Build Image') {
            steps {
                // This builds using your Dockerfile
                sh "docker build -t ${DOCKER_HUB_USER}/oms-app:latest ."
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', 
                                 passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                    sh "docker login -u ${USER} -p ${PASS}"
                    sh "docker push ${DOCKER_HUB_USER}/oms-app:latest"
                }
            }
        }
        stage('Deploy to K8s') {
            steps {
                sh "kubectl apply -f k8s-deploy.yaml"
            }
        }
    }
}
