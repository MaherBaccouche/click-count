pipeline{
    agent {
        kubernetes {
          //cloud 'kubernetes'
          label "click-count-jenkins-slave-${UUID.randomUUID().toString()}"
          yaml """
            apiVersion: v1
            kind: Pod
            spec:
              containers:
              - name: maven
                image: maven:3.3.9-jdk-8-alpine
                command:
                - cat
                tty: true
              - name: docker
                image: docker
                command:
                - cat
                tty: true
                securityContext:
                  runAsUser: 0
                volumeMounts:
                  - mountPath: /var/run/docker.sock
                    name: host-volume
            
              volumes:
                - name: host-volume
                  hostPath:
                    path: /var/run/docker.sock
            """
        }
    }

    stages{

        stage('Build'){
            steps{
                container('maven') {
                    git url: 'https://github.com/hamdikh/click-count'
                    sh 'mvn package'
                } 
            }
        }
        
        stage('Docker'){
            steps{
                container('docker') {
                  withCredentials([[$class: 'UsernamePasswordMultiBinding',
                  credentialsId: 'dockerhub',
                  usernameVariable: 'DOCKER_HUB_USER',
                  passwordVariable: 'DOCKER_HUB_PASSWORD']]) {
                  sh """
                    mv ${WORKSPACE}/target/*.war ${WORKSPACE}/docker/
                    docker login -u ${DOCKER_HUB_USER} -p "${DOCKER_HUB_PASSWORD}"
                    docker build -t ${DOCKER_HUB_USER}/clickcount:latest ${WORKSPACE}/docker/
                    docker push ${DOCKER_HUB_USER}/clickcount:latest
                    """
                    }   
                } 
            }
        }
    }
}
