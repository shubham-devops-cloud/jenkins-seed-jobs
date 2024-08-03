package templates.jobs

class ToolingJobsTemplate{
    def static hostName = "git@github.com:shubham-devops-cloud"
    def static CREDENTIALS_ID = "GitHubSSH"  

    static def createJobWithBranchExtension(job, repoName, branchName = "main", fileName = "Jenkinsfile", jobRotation = 3){
        job.with{
            logRotator{
                numToKeep(jobRotation)
            }
            definition{
                cpsScm{
                    scm{
                        git{
                            remote{
                                url("${hostName}/${repoName}.git")
                                credentials(CREDENTIALS_ID)
                            }
                            branch("${branchName}")
                        }
                    }
                    scriptPath("${fileName}")
                }
            }
        }
        return job
    }
}