pipeline {

    agent any

    tools {
        maven 'Maven3'
        jdk 'Java21'
    }

    environment {
        APP_NAME = "testando"
    }

    stages {

        stage('Checkout') {

            steps {
                git branch: "${env.BRANCH_NAME}",
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

        stage('Deploy DEV') {

            when {
                branch 'develop'
            }

            steps {

                echo "Deploy DEV"

                sh '''
                pkill -f spring-app || true

                nohup java \
                -jar target/*.jar \
                --spring.profiles.active=dev \
                > dev.log 2>&1 &
                '''
            }

        }

        stage('Deploy HOMOLOG') {

            when {
                branch 'homolog'
            }

            steps {

                echo "Deploy HOMOLOG"

                sh '''
                pkill -f spring-app || true

                nohup java \
                -jar target/*.jar \
                --spring.profiles.active=homolog \
                > homolog.log 2>&1 &
                '''
            }

        }

        stage('Deploy PROD') {

            when {
                branch 'main'
            }

            steps {

                echo "Deploy PROD"

                sh '''
                pkill -f spring-app || true

                nohup java \
                -jar target/*.jar \
                --spring.profiles.active=prod \
                > prod.log 2>&1 &
                '''
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
