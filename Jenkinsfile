pipeline {
    agent any

    tools {
        maven 'Maven2'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ashidham91/BankingApplication'
            }
        }

        stage('Build') {
            steps {
                dir('BankingApplication') {
                    bat 'mvn clean compile'
                }
            }
        }

        stage('Test') {
            steps {
                dir('BankingApplication') {
                    bat 'mvn test'
                    bat 'mvn jacoco:report'
                }
            }
        }

        stage('Check Coverage') {
            steps {
                dir('BankingApplication') {
                    bat 'mvn jacoco:check'
                }
            }
        }

        stage('Package') {
            steps {
                dir('BankingApplication') {
                    bat 'mvn package'
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploy step (Optional)'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
        success {
            echo 'Build Successful!'
        }
        failure {
            echo 'Build Failed!'
        }
    }
}