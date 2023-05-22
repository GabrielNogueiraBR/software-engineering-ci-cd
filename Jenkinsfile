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
    stage('Wait for container to be ready') {
      steps {
        script {
          def timeout = 300 // Tempo máximo de espera em segundos
          def startTime = System.currentTimeMillis()

          echo "Aguardando o contêiner ficar pronto..."

          // Loop até que o contêiner esteja pronto ou o tempo limite seja atingido
          while (true) {
            try {
              sh 'curl -s -o /dev/null http://localhost:9090'
              echo "O contêiner está pronto!"
              break
            } catch (Exception e) {
              def currentTime = System.currentTimeMillis()
              def elapsedTime = (currentTime - startTime) / 1000

              if (elapsedTime >= timeout) {
                error "O tempo limite foi atingido. O contêiner não está pronto."
              }

              echo "Aguardando o contêiner ficar pronto... (Tempo decorrido: ${elapsedTime} segundos)"
              sleep 10 // Aguarda 10 segundos antes de verificar novamente
            }
          }
        }
      }
    }
    stage('Run tests against the container') {
      steps {
        sh 'curl http://localhost:9090'
      }
    }
  }
  
}
