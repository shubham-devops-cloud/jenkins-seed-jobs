String folderroot = "BUILD-JOBS"

folder(folderroot) {
    createMultibranchPipelineJob(folderroot, 'mysql', 'git@github.com:shubham-devops-cloud/mysql.git')
    createMultibranchPipelineJob(folderroot, 'elasticsearch', 'git@github.com:shubham-devops-cloud/elasticsearch.git')
    createMultibranchPipelineJob(folderroot, 'attendance', 'git@github.com:shubham-devops-cloud/attendance.git')
    createMultibranchPipelineJob(folderroot, 'employee', 'git@github.com:shubham-devops-cloud/employee.git')
    createMultibranchPipelineJob(folderroot, 'salary', 'git@github.com:shubham-devops-cloud/salary.git')
    createMultibranchPipelineJob(folderroot, 'frontend', 'git@github.com:shubham-devops-cloud/frontend.git')
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
