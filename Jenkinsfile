pipeline {
    agent any

    tools {
        // Make sure these are configured in Jenkins (Manage Jenkins -> Global Tool Configuration)
        maven 'Maven2'        // Name of Maven installation in Jenkins
        jdk 'JDK17'           // Name of JDK installation in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull code from Git
                git branch: 'main', url: 'https://github.com/ashidham91/BankingApplication'
            }
        }

        stage('Build') {
            steps {
                // Compile the project
                sh 'mvn clean compile'
                // Windows: use bat instead of sh
                // bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                // Run unit tests
                sh 'mvn test'
                // Generate coverage report
                sh 'mvn jacoco:report'
            }
        }

        stage('Check Coverage') {
            steps {
                // Fail build if coverage < 80%
                sh 'mvn jacoco:check'
            }
        }

        stage('Package') {
            steps {
                // Package your Spring Boot jar
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploy step (Optional: Docker/AWS/etc.)'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'       // Test results
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