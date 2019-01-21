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

              - name: helm
                image: alpine/helm
                command:
                - cat
                tty: true
                env:
                - name: TILLER_NAMESPACE
                  value: "xebia-test"
            
            """
        }
    }

    stages{
        
        stage('Checkout scm'){
            steps{
                git url: 'https://github.com/hamdikh/click-count'
            } 
        }
        
        stage('Helm - Init'){
            steps{
                container('helm') {
                    sh 'helm init -c --service-account xebia-sa'
                } 
            }
        }
        
        stage('Helm - Staging'){
            steps{
                container('helm') {
                    sh """
                    helm delete --purge clickcount-staging
                    helm install --name clickcount-staging ${WORKSPACE}/src/helm/click-count
                    """
                } 
            }
        }
        
        stage('Helm - PROD'){
            steps{
                container('helm') {
                    sh """
                    helm delete --purge --tiller-namespace xebia-prod clickcount-production
                    helm install --tiller-namespace xebia-prod --name clickcount-production ${WORKSPACE}/src/helm/click-count
                    """
                } 
            }
        }
    }

}
