String folderroot = "BUILD-JOBS"

folder(folderroot) {
    createMultibranchPipelineJob(folderroot, 'mysql', 'git@github.com:shubham-devops-cloud/mysql.git')
    createMultibranchPipelineJob(folderroot, 'elasticsearch', 'git@github.com:shubham-devops-cloud/elasticsearch.git')
    createMultibranchPipelineJob(folderroot, 'attendance', 'git@github.com:shubham-devops-cloud/attendance.git')
}

// Function to create multibranch pipeline jobs
def createMultibranchPipelineJob(folderroot, repoName, repoUrl) {
    multibranchPipelineJob("${folderroot}/${repoName}") {
        branchSources {
            git {
                id('1') // IMPORTANT: use a constant and unique identifier
                remote(repoUrl)
                credentialsId('GitHubSSH')
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
}
