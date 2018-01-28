#!/usr/bin/groovy
import main.groovy.uk.co.olimor.jenkins_poc_groovy.Job 

def call(body) {

        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()

        node {
            try {
                stage ('Build') {
new uk.co.olimor.jenkins_poc_groovy.Job().build()
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
