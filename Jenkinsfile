pipeline {
    agent {
        label 'worker'
    }
    environment {
        AWS_ACCOUNT_ID      =   "718079336318"
        AWS_DEFAULT_REGION  =   "eu-west-1"
        IMAGE_REPO_NAME     =   "auth"
        IMAGE_TAG           =   "latest"
        REPOSITORY_URI      =   "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
        DOCKER_PASSWD       =   credentials('docker-nexus-passwd')
    }
    tools{
        maven 'maven3'
    }
    stages{
        stage('Clean') {
            steps {
                deleteDir()
            }
        }
        stage('Cloning SCM') {
            steps {
                checkout scm
            }
        }
     

}

}
