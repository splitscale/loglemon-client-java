
pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('initialize') {
          steps {
            sh 'mvn -v'
            sh 'java -version'
            sh 'git --version'
            sh 'docker -v'
          }
        }

        stage('pull') {
          steps {
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/splitscale/loglemon-client-java.git']]])
          }
        }

        stage('test') {
          steps {
            script {
              sh 'mvn clean test'
            }
          }
        }

        stage('install') {
          steps {
            script {
              sh 'mvn clean install'
            }
          }
        }
    }
}

