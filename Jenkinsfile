CODE_CHANGES = getGitChanges()

pipeline {
    agent any
    stages {
        stage("build"){
            when{
                expression {
                    CODE_CHANGES == true
                }
            }
            steps{
                echo 'building the application..'
            }
        }
        stage("test"){
            steps{
                echo 'testing the application..'
            }
        }
        stage("deploy"){
            steps{
                echo 'deploying the application..'
            }
        }
    }
}