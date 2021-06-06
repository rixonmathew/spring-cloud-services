def buildApp() {
    echo 'building the application...'
    sh 'mvn clean -U'
}

def testApp() {
    echo 'testing the application...'
    sh 'mvn compile test package'
}

def deployApp() {
    echo 'deplying the application...'
    echo "deploying version ${params.VERSION}"
}

return this
