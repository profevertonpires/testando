pipeline {

    agent any

    tools {
        maven 'Maven3'
        jdk 'Java21'
    }

    environment {
        APP_NAME = "testando-1.0.jar"
    }

    stages {

        stage('Checkout') {

            steps {
                 git branch: "main",
                url: 'https://github.com/profevertonpires/testando.git'
            }

        }

        stage('Build') {

            steps {
                sh 'mvn clean compile'
            }

        }

        stage('Tests') {

            steps {
                sh 'mvn test'
            }

        }

        stage('Package') {

            steps {
                sh 'mvn clean package -DskipTests'
            }

        }
        
         stage('MatarServico') {

            steps {
                sh 'pkill -9 -f ${APP_NAME} || true' 
            }
        }
        
        stage('SubirServico') {

            steps {
                 sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /var/lib/jenkins/workspace/segunda_22horas/target/${APP_NAME}  > prod.log 2>&1 &'
            }
        }
    }

    post {

        success {
            echo 'Pipeline executada com sucesso!'
        }

        failure {

            echo 'Erro na pipeline!'

        }

    }

}
