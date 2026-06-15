pipeline {
    agent any

    tools{
        maven "Maven 3.9.14"
        jdk "JDK"
    }
    environment{
        DOCKER_USERNAME = 'phaniyeshwanth123'
        DOCKER_NAME_LOCAL = 'restapi'
        DOCKER_REPO = 'phaniyeshwanth123/firstrepo'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }
    stages{
        stage('Build with Maven'){
            steps{
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Archive'){
            steps{
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
        stage('Build Docker Image in local'){
            steps{
                sh 'docker build -t $DOCKER_NAME_LOCAL:$IMAGE_TAG .'
            }
        }
        stage('Docker Login'){
            steps{
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: '$DOCKER_USERNAME',
                    passwordVariable: 'dckr_pat_2KpuSraOjbvL7t6JGn7Qo9Y16y0'
                )]){
                    sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                    
                }
            }
        }
        stage('Docker Tag'){
            steps{
                sh 'docker tag $DOCKER_NAME_LOCAL:$IMAGE_TAG $DOCKER_REPO:$IMAGE_TAG'
            }
        }
        stage('Docker Push'){
            steps{
                sh 'docker push $DOCKER_REPO:$IMAGE_TAG'
            }
        }
        stage('Restart Kubernetes deployment'){
            steps{
                bat """
                kubectl set image deployment/restapi-deployment wrapper=%DOCKER_REPO%:%IMAGE_TAG%
                """
            }
        }
    }
}