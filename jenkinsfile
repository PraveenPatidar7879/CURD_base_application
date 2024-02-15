pipeline {
    agent any

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
                        // Install Node.js and npm
                        def nodejsHome = tool 'NodeJS'
                        env.PATH = "${nodejsHome}/bin:${env.PATH}"

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


