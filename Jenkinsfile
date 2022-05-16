pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install -Dlicense.skip=true -DskipTests'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn verify'
      }
    }


    stage('SonarQube Analysis') {
      when {
        not {
          anyOf {
            changeRequest()
          }
        }
      }
       steps {
         withSonarQubeEnv('SonarQube') {
           sh 'mvn sonar:sonar -Dsonar.branch.name=' + env.BRANCH_NAME
        }
      }
    }

    stage("Quality Gate") {
      when {
        not {
          anyOf {
            branch 'main'
            branch 'development'
            changeRequest()
          }
        }
      }
      steps {
        waitForQualityGate abortPipeline: true
      }
    }
  }

   tools {
      maven 'Maven 3.6.3'
    }
}
