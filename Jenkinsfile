pipeline {
  agent any
  stages {
    stage("verify tooling") {
      steps {
        sh '''
          docker version
          docker info
          docker compose version 
          curl --version
          '''
      }
    }
    stage('Start container') {
      steps {
        sh 'docker compose up -d --no-color --wait'
        sh 'docker compose ps'
      }
    }
    stage('Wait for container') {
      steps {
        sh 'sleep 30'
      }
    }
    stage('Run tests against the container') {
      steps {
        script {
          def containerIds = sh(returnStdout: true, script: 'docker compose ps -q').trim().split('\n')
          def desiredContainerId = containerIds[0] 
          sh "docker exec '${desiredContainerId}' curl http://localhost:9090"
        }
      }
    }
  }
  
}
