pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout your code from version control system (e.g., Git)
                checkout scm
            }
        }
         stage('Install Node.js') {
            steps {
                sh 'curl -o- https://nodejs.org/dist/v14.17.5/node-v14.17.5-linux-x64.tar.gz | tar -xz'
                sh 'mv node-v14.17.5-linux-x64 /usr/local/node'
                sh 'echo "export PATH=/usr/local/node/bin:$PATH" >> ~/.bashrc'
                sh 'source ~/.bashrc'
            }
         }
        stage('Install Dependencies and Run Tests') {
            steps {
                script {
                    // Navigate to the React_frontend directory
                    dir('Frontend_React') {
                        // Install project dependencie
                        sh "node --version"
                        sh "npm  --version"
                     //   sh 'npm install'

                        // Run React test cases
                      //  sh 'npm test'
                    }
                }
            }
        }
    }
}


