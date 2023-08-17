pipeline {
    agent any

    tools {

        maven "M3"
        jdk 'JDK19'
    }

    triggers {
        parameterizedCron('''
        0 21 * * 0-6 %SUITE=smokeTests.xml;BROWSER=Chrome;HEADLESS=true;
        30 21 * * 0-6 %SUITE=regressionTests.xml;BROWSER=Chrome;HEADLESS=false;
        ''')
    }

    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'feature5', name: 'BRANCH', type: 'PT_BRANCH'
        choice(name: 'SUITE', choices: ['suites/smokeTests.xml', 'suites/regressionTests.xml'], description: 'Choose suite to run')
        booleanParam (name: 'HEADLESS', defaultValue: false, description: 'Headless mode')
    }

    stages {
        stage('Run test') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/ChernikaVi/QA24_QaseProject_Zlobich_Kovnat'

                sh 'mvn -Dmaven.test.failure.ignore=true -Dsuite=${params.SUITE} -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS} clean test'
            }

            post {
                success {
                    junit '**/target/reports/TEST-*.xml'
                }
            }
        }

        stage('Generate Allure report') {
    steps {
         script {
            allure([
             includeProperties: false,
             jdk: '',
             properties: [],
             reportBuildPolicy: 'ALWAYS',
             results: [[path: 'target/allure-results']]
             ])
        }
     }
     }
     }
     }