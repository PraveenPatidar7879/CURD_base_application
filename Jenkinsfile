pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout your code from the version control system (e.g., Git)
                checkout scm
            }
        }

        stage('Install Dependencies and Run Tests') {
            steps {
                script {
                    // Navigate to the React_frontend directory
                    dir('springboot-backend') {

                        // Run Backend_server
                        sh 'mvn test'
                    }
                }
            }
        }
    }

    post {

        success {
            // Additional actions for a successful build
            echo 'Build and test execution successful!'
        }
        failure {
            // Additional actions for a failed build
            echo 'Build or test execution failed!'
        }
    }
}
