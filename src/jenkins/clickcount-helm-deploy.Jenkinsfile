pipeline{
    agent {
        kubernetes {
          //cloud 'kubernetes'
          label "click-count-jenkins-slave-${UUID.randomUUID().toString()}"
          yaml """
            apiVersion: v1
            kind: Pod
            spec:
              serviceAccount: xebia-sa
              serviceAccountName: xebia-sa
              containers:
              
              - name: kubectl
                image: lachlanevenson/k8s-kubectl
                command:
                - cat
                tty: true
                
              - name: helm
                image: alpine/helm
                command:
                - cat
                tty: true
                env:
                - name: TILLER_NAMESPACE
                  value: "xebia-test"
            
              volumes:
                - name: host-volume
                  hostPath:
                    path: /var/run/docker.sock
            """
        }
    }

    stages{
        
        
        stage('Checkout scm'){
            steps{
                git url: 'https://github.com/hamdikh/click-count'
            } 
        }
        
        stage('Kubectl') {
            
            steps{
              container('kubectl') {
                sh "kubectl get pods"
              }
            }
        }
        
        stage('Helm'){
            steps{
                container('helm') {
                    sh 'helm install --name click-count-${ENVRIONMENT} ${WORKSPACE}/src/helm/click-count'
                } 
            }
        }
        
    }

}
