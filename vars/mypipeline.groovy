 def call(body) {

        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()

        node {
            try {
                stage ('Build') {
                    sh "echo 'building ${config.projectName} ...'"
                }
                stage ('Tests') {
                    sh "echo 'testing'"
                }
            } catch (err) {
                currentBuild.result = 'FAILED'
                throw err
            }
        }
    }
