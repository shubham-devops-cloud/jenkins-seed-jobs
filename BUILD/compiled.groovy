// Multibranch Pipeline for Databases

String folderroot = "BUILD-JOBS"
folder(folderroot){

}

// For MySql
multibranchPipelineJob(folderroot + '/' + 'mysql'){
    branchSources{
        git{
            id('1') // IMPORTANT: use a constant and unique identifier
            remote('git@github.com:shubham-devops-cloud/mysql.git')
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

// For Elasticsearch
multibranchPipelineJob(folderroot + '/' + 'elasticsearch'){
    branchSources{
        git{
            id('1') // IMPORTANT: use a constant and unique identifier
            remote('git@github.com:shubham-devops-cloud/elasticsearch.git')
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
