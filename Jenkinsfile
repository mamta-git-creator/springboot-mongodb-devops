pipeline {
  agent any
  environment { IMAGE = "YOUR_DOCKERHUB_USERNAME/devops-demo" }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Test') { steps { sh 'mvn -B test' } }
    stage('Build Image') { steps { sh 'docker build -t $IMAGE:$BUILD_NUMBER .' } }
    stage('Push Image') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
          sh 'echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin'
          sh 'docker push $IMAGE:$BUILD_NUMBER'
        }
      }
    }
    stage('Deploy') {
      steps {
        sh 'docker compose pull || true'
        sh 'docker compose up -d --build'
      }
    }
  }
  post { always { sh 'docker image prune -f || true' } }
}
