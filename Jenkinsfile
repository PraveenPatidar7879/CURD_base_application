pipeline {
    agent any
    tool (node.js "node")
    stages {
        stage('Checkout') {
            steps {
                // Checkout your code from version control system (e.g., Git)
                checkout scm
            }
        }

        stage('Install Dependencies and Run Tests') {
            steps {
                script {
                    // Navigate to the React_frontend directory
                    dir('Frontend_React') {
                        // Install project dependencies
                        sh 'npm install'

                        // Run React test cases
                        sh 'npm test'
                    }
                }
            }
        }
    }
}


