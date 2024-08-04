sourceFile = readFileFromWorkspace("_lib/templates/jobs/ToolingJobsTemplate.groovy")
jobs = new GroovyClassLoader(getClass().getClassLoader()).parseClass(sourceFile)

def folderroot = 'DEPLOY-JOBS'
//def folderName = folderroot + "/helm"

folder(folderroot){
    //displayName("HELM")
}


//Pipeline for MySql
jobs.createJobWithBranchExtension(pipelineJob(folderroot + "/deploy_mysql"), "es-charts-mysql", "main", fileName = "Jenkinsfile.deploy").parameters{
    choiceParam('REPONAME', ['es-charts-mysql'], 'Helm chart repo name')
    textParam{
        name('FEATUREIMAGE')
        defaultValue('public.ecr.aws/j9k0i2s2/dev-or-employee-system:mysql-main-0.0.1')
        description('Enter the image name which you want to deploy')
    }
    choiceParam('NAMESPACE', ['dev'], 'Kubernetes Namespace')
}


//Pipeline for Elasticsearch
jobs.createJobWithBranchExtension(pipelineJob(folderroot + "/deploy_elasticsearch"), "es-charts-elasticsearch", "main", fileName = "Jenkinsfile.deploy").parameters{
    choiceParam('REPONAME', ['es-charts-elasticsearch'], 'Helm chart repo name')
    textParam{
        name('FEATUREIMAGE')
        defaultValue('public.ecr.aws/j9k0i2s2/dev-or-employee-system:elasticsearch-main-0.0.1')
        description('Enter the image name which you want to deploy')
    }
    choiceParam('NAMESPACE', ['dev'], 'Kubernetes Namespace')
}