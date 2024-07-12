String folderroot = "BUILD-JOBS"
folder(folderroot){

}

// Multibranch Pipeline for Databases
multibranchPipelineJob(folderroot + '/' + 'mysql'){
    branchSource{
        git{
            id('1') // IMPORTANT: use a constant and unique identifier
            remote('git@github.com:shubham-devops-cloud/mysql.git')
            credentials('GitHubSSH')
            includes('main')
            excludes('')
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(6)
        }
    }
}
