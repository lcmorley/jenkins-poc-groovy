import uk.co.olimor.jenkins_poc_groovy.Job;

 def call(body) {

        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()

        node {
            try {
                stage ('Build') {
                    new Job().build();
                    sh "echo 'building ${config.projectName} ...'"
                }
                stage ('Tests') {
                   new Job().checkout();
                }
            } catch (err) {
                currentBuild.result = 'FAILED'
                throw err
            }
        }
    }