sourceFile = readFileFromWorkspace("_lib/templates/jobs/ToolingJobsTemplate.groovy")
jobs = new GroovyClassLoader(getClass().getClassLoader()).parseClass(sourceFile)

def folderroot = 'DEPLOY-JOBS'
def folderName = folderroot + "/helm"

folder(folderName){
    displayName("HELM")
}

jobs.createJobWithBranchExtension(pipelineJob(folderName + "/deploy_salary"), "es-charts-salary", "main", fileName = "Jenkinsfile.deploy").parameters{
    choiceParam('REPONAME', ['es-charts-salary'], 'Helm chart repo name')
    textParam{
        name('FEATUREIMAGE')
        defaultValue("")
        description('Enter the image name which you want to deploy')
    }
    choiceParam('NAMESPACE', ['dev'], 'Kubernetes Namespace')
}