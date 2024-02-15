pipeline {
    agent any

    tools {
        // Define the Node.js tool installation
        nodejs "NodeJS"
    }

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
                    dir('Frontend_React') {
                        // Install project dependencies
                        sh "node --version"
                        sh "npm --version"
                        sh 'npm install'

                        // Run React test cases
                        sh 'npm test'
                    }
                }
            }
        }
    }

    post {
        always {
            // Archive the HTML test report
            echo "artifacts"
            archiveArtifacts artifacts: 'reports/index.html' , followSymlinks: false
            archiveArtifacts artifacts: 'coverage/lcov-report/index.html'
        }

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
