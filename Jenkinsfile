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
    stage('Run tests against the container') {
      steps {
        script {
          def containerId = sh(returnStdout: true, script: 'docker compose ps -q').trim()
          def command = sh "docker exec ${containerId} curl http://localhost:9090"
          sh "echo '${containerId}-teste'"
        }
      }
    }
  }
  
}
