pipeline {
    agent any
    tools {
        // Nome da instalação do Maven configurada no Jenkins
        maven 'Maven3' 
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'URL_DO_SEU_REPOSITORIO.git'
            }
        }
        stage('Build') {
            steps {
                // Compila o código sem rodar testes
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                // Executa testes unitários
                sh 'mvn test'
            }
            post {
                always {
                    // Publica resultados dos testes
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Implantando a aplicação...'
                // Exemplo: sh './deploy.sh'
            }
        }
    }
}
