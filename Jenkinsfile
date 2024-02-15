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
            archiveArtifacts 'Frontend_React/coverage/lcov-report/**/*.html'

            // Publish HTML test report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'Frontend_React/coverage/lcov-report',
                reportFiles: 'index.html',
                reportName: 'React Test Report'
            ])
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
