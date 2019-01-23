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
        
        stage('Helm - Redis - Staging'){
            steps{
                container('helm') {
                    script {
                        try{
                          sh 'helm delete --purge redis'
                        }    
                        catch (err){
                           echo "deleting helm chart failed, chart does not exist"
                        }
                    }
                    sh 'helm install --name redis stable/redis --tiller-namespace xebia-test --namespace xebia-test --set usePassword=false'
                } 
            }
        }
        
        stage('Helm - Clickcount - Staging'){
            steps{
                container('helm') {
                    script {
                        try{
                          sh 'helm delete --purge clickcount-staging'
                        }    
                        catch (err){
                           echo "deleting helm chart failed, chart does not exist"
                        }
                    }
                    sh 'helm install --name clickcount-staging ${WORKSPACE}/helm/click-count --set ingress.enabled=false'
                } 
            }
        }
        
        stage('Helm - Redis - Production'){
            steps{
                container('helm') {
                    script {
                        try{
                          sh 'helm delete --purge redis --tiller-namespace xebia-prod'
                        }    
                        catch (err){
                           echo "deleting helm chart failed, chart does not exist"
                        }
                    }
                    sh 'helm install --name redis stable/redis --tiller-namespace xebia-prod --namespace xebia-prod --set usePassword=false'
                } 
            }
        }
        
        stage('Helm - Clickcount - Production'){
            steps{
                container('helm') {
                    script {
                        try{
                          sh 'helm delete --purge --tiller-namespace xebia-prod clickcount-production'
                        }    
                        catch (err){
                           echo "deleting helm chart failed, chart does not exist"
                        }
                    }
                    sh 'helm install --tiller-namespace xebia-prod --namespace xebia-prod --name clickcount-production ${WORKSPACE}/helm/click-count'
                } 
            }
        }
    }
}
